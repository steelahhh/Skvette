import de.fayard.refreshVersions.bootstrapRefreshVersions

include(":app")
include(":core")
include(":coreui")
include(":data")
include(":features:photos")


buildscript {
  repositories { gradlePluginPortal() }
  dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
}

bootstrapRefreshVersions()
