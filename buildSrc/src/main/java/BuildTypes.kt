/**
 * Created By Khizzar
 * Created On 11/10/2023
 **/

interface BuildTypes {
    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }
}

object BuildTypeDebug {
    const val isMinifyEnabled = false
    const val isShrinkResourceEnabled = false
    const val isDebuggableEnabled = true
    const val getBaseUrl = BuildConfigFields.BASE_URL
}

object BuildTypeRelease {
    const val isMinifyEnabled = true
    const val isShrinkResourceEnabled = false
    const val isDebuggableEnabled = false
    const val getBaseUrl = BuildConfigFields.BASE_URL
}