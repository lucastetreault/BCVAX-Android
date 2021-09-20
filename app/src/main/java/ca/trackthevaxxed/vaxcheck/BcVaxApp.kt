package ca.trackthevaxxed.vaxcheck

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * [BcVaxApp]
 *
 * @author Pinakin Kansara
 */
@HiltAndroidApp
class BcVaxApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: BcVaxApp? = null

        fun getContext(): Context? {
            return instance
        }
    }
}
