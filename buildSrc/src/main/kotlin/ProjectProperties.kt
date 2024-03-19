import org.gradle.api.JavaVersion

object ProjectProperties {
    const val minSdk = 26
    const val compileSdk = 34
    const val targetSdk = 34
    const val kotlinJvmTarget = "17"
    val javaSourceCompatibility = JavaVersion.VERSION_17
    val javaTargetCompatibility = JavaVersion.VERSION_17
}