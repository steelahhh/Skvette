package dev.steelahhh.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

/**
 * author: terrakok
 */
@Suppress("ClassName", "PrivatePropertyName")
class Paginator<T>(
    private val scope: CoroutineScope,
    private val requestFactory: suspend ((Int) -> List<T>),
    private val viewController: ViewController<T>
) {

    interface ViewController<T> {
        fun showEmptyProgress() = Unit
        fun hideEmptyProgress() = Unit
        fun showEmptyView() = Unit
        fun hideEmptyView() = Unit
        fun showEmptyError(error: Throwable) = Unit
        fun hideEmptyError() = Unit
        fun showRefreshProgress() = Unit
        fun hideRefreshProgress() = Unit
        fun showPageLoadingProgress() = Unit
        fun hidePageLoadingProgress() = Unit
        fun hideData() = Unit
        fun showData(data: List<T>) = Unit
        fun showRefreshError(error: Throwable) = Unit
        fun showPageLoadingError(error: Throwable) = Unit
        fun hidePageLoadingError() = Unit
    }

    open class EmptyViewController<T> : ViewController<T> {
        override fun showEmptyProgress() = Unit
        override fun hideEmptyProgress() = Unit
        override fun showEmptyView() = Unit
        override fun hideEmptyView() = Unit
        override fun showEmptyError(error: Throwable) = Unit
        override fun hideEmptyError() = Unit
        override fun showRefreshProgress() = Unit
        override fun hideRefreshProgress() = Unit
        override fun showPageLoadingProgress() = Unit
        override fun hidePageLoadingProgress() = Unit
        override fun hideData() = Unit
        override fun showData(data: List<T>) = Unit
        override fun showRefreshError(error: Throwable) = Unit
        override fun showPageLoadingError(error: Throwable) = Unit
        override fun hidePageLoadingError() = Unit
    }

    private val FIRST_PAGE = 1

    private var currentState: State<T> = EMPTY()
    private var currentData = emptyList<T>()
    private var currentPage = 1
    private var job: Job? = null

    fun restart() {
        currentState.restart()
    }

    fun refresh() {
        currentState.refresh()
    }

    fun loadNewPage() {
        currentState.loadNewPage()
    }

    fun release() {
        currentState.release()
    }

    private fun loadPage(page: Int) {
        job?.cancel()
        job = scope.launch {
            try {
                val data = requestFactory.invoke(page)
                currentState.newData(data)
            } catch (e: Exception) {
                currentState.fail(e)
            }
        }
    }

    private interface State<T> {
        fun restart() {}
        fun refresh() {}
        fun loadNewPage() {}
        fun release() {}
        fun newData(data: List<T>) {}
        fun fail(error: Throwable) {}
    }

    private inner class EMPTY : State<T> {

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class EMPTY_PROGRESS : State<T> {

        override fun restart() {
            loadPage(FIRST_PAGE)
        }

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentPage = FIRST_PAGE
                currentData = data
                viewController.hideEmptyProgress()
                viewController.showData(currentData)
            } else {
                currentState = EMPTY_DATA()
                viewController.hideEmptyProgress()
                viewController.showEmptyView()
            }
        }

        override fun fail(error: Throwable) {
            currentState = EMPTY_ERROR()
            viewController.hideEmptyProgress()
            viewController.showEmptyError(error)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class EMPTY_ERROR : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.hideEmptyError()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            viewController.hideEmptyError()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class EMPTY_DATA : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.hideEmptyView()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            viewController.hideEmptyView()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class DATA : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.hideData()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun refresh() {
            currentState = REFRESH()
            viewController.showRefreshProgress()
            loadPage(FIRST_PAGE)
        }

        override fun loadNewPage() {
            currentState = PAGE_PROGRESS()
            viewController.showPageLoadingProgress()
            loadPage(currentPage + 1)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class REFRESH : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.hideData()
            viewController.hideRefreshProgress()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentPage = FIRST_PAGE
                currentData = data
                viewController.hideRefreshProgress()
                viewController.showData(currentData)
            } else {
                currentState = EMPTY_DATA()
                viewController.hideData()
                viewController.hideRefreshProgress()
                viewController.showEmptyView()
            }
        }

        override fun fail(error: Throwable) {
            currentState = DATA()
            viewController.hideRefreshProgress()
            viewController.showRefreshError(error)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class PAGE_PROGRESS : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.hideData()
            viewController.hidePageLoadingProgress()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentPage++
                currentData = currentData.plus(data)
                viewController.showData(currentData)
                viewController.hidePageLoadingProgress()
            } else {
                currentState = ALL_DATA()
                viewController.hidePageLoadingProgress()
            }
        }

        override fun refresh() {
            currentState = REFRESH()
            viewController.hidePageLoadingProgress()
            viewController.showRefreshProgress()
            loadPage(FIRST_PAGE)
        }

        override fun fail(error: Throwable) {
            currentState = PAGE_ERROR()
            viewController.hidePageLoadingProgress()
            viewController.showPageLoadingError(error)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class PAGE_ERROR : State<T> {
        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.hideData()
            viewController.hidePageLoadingError()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun refresh() {
            currentState = REFRESH()
            viewController.hidePageLoadingError()
            viewController.showRefreshProgress()
            loadPage(FIRST_PAGE)
        }

        override fun loadNewPage() {
            currentState = PAGE_PROGRESS()
            viewController.hidePageLoadingError()
            viewController.showPageLoadingProgress()
            loadPage(currentPage + 1)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class ALL_DATA : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.hideData()
            viewController.showEmptyProgress()
            loadPage(FIRST_PAGE)
        }

        override fun refresh() {
            currentState = REFRESH()
            viewController.showRefreshProgress()
            loadPage(FIRST_PAGE)
        }

        override fun release() {
            currentState = RELEASED()
            job?.cancel()
        }
    }

    private inner class RELEASED : State<T>
}
