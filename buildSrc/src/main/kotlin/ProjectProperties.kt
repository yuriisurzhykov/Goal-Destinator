import org.gradle.api.JavaVersion

object ProjectProperties {
    const val minSdk = 26
    const val compileSdk = 34
    const val targetSdk = 34
    const val kotlinJvmTarget = "11"
    val javaSourceCompatibility = JavaVersion.VERSION_11
    val javaTargetCompatibility = JavaVersion.VERSION_11
}