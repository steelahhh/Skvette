package dev.steelahhh.core

import com.github.michaelbull.result.Result
import dev.steelahhh.core.InvokeStatus.Error
import dev.steelahhh.core.InvokeStatus.Started
import dev.steelahhh.core.InvokeStatus.Success
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

sealed class InvokeStatus {
  object Idle : InvokeStatus()
  object Started : InvokeStatus()
  object Success : InvokeStatus()
  data class Error(val throwable: Throwable) : InvokeStatus()
}

abstract class Interactor<in P> {
  protected abstract val scope: CoroutineScope

  operator fun invoke(params: P, timeoutMs: Long = defaultTimeoutMs): Flow<InvokeStatus> {
    val channel = ConflatedBroadcastChannel<InvokeStatus>(InvokeStatus.Idle)
    scope.launch {
      try {
        withTimeout(timeoutMs) {
          channel.send(Started)
          try {
            doWork(params)
            channel.send(Success)
          } catch (t: Throwable) {
            channel.send(Error(t))
          }
        }
      } catch (t: TimeoutCancellationException) {
        channel.send(Error(t))
      }
    }
    return channel.asFlow()
  }

  suspend fun executeSync(params: P) = withContext(scope.coroutineContext) { doWork(params) }

  protected abstract suspend fun doWork(params: P)

  companion object {
    private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
  }
}

abstract class ResultInteractor<in P, R> {
  abstract val dispatcher: CoroutineDispatcher

  suspend operator fun invoke(params: P): Result<R, Throwable> {
    return withContext(dispatcher) { doWork(params) }
  }

  protected abstract suspend fun doWork(params: P): Result<R, Throwable>
}

interface ObservableInteractor<T> {
  val dispatcher: CoroutineDispatcher
  fun observe(): Flow<T>
}

abstract class SuspendingWorkInteractor<P : Any, T : Any> : ObservableInteractor<T> {
  private val channel = ConflatedBroadcastChannel<T>()

  suspend operator fun invoke(params: P) = channel.send(doWork(params))

  abstract suspend fun doWork(params: P): T

  override fun observe(): Flow<T> = channel.asFlow()
}

abstract class SubjectInteractor<P : Any, T> : ObservableInteractor<T> {
  private val channel = ConflatedBroadcastChannel<P>()

  operator fun invoke(params: P) = channel.sendBlocking(params)

  protected abstract fun createObservable(params: P): Flow<T>

  override fun observe(): Flow<T> = channel.asFlow()
    .distinctUntilChanged()
    .flatMapLatest { createObservable(it) }
}

operator fun Interactor<Unit>.invoke() = invoke(Unit)
operator fun <T> SubjectInteractor<Unit, T>.invoke() = invoke(Unit)

fun <I : ObservableInteractor<T>, T> CoroutineScope.launchObserve(
  interactor: I,
  f: suspend (Flow<T>) -> Unit
) = launch(interactor.dispatcher) { f(interactor.observe()) }
