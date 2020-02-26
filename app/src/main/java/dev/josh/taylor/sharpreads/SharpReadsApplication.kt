package dev.josh.taylor.sharpreads

import android.app.Application
import dev.josh.taylor.sharpreads.di.component.AppComponent
import dev.josh.taylor.sharpreads.di.component.DaggerAppComponent

class SharpReadsApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()
}