package com.example.obdreader.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.obdreader.bluetooth.BluetoothScanner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ObdViewModel(application: Application) : AndroidViewModel(application) {

    enum class Screen { MAIN, CONNECT }

    private val _screen = MutableStateFlow(Screen.MAIN)
    val screen: StateFlow<Screen> = _screen

    val scanner = BluetoothScanner(application, viewModelScope)

    val isBluetoothSupported: Boolean
        get() = scanner.isBluetoothSupported

    val isBluetoothEnabled: Boolean
        get() = scanner.isBluetoothEnabled
}
