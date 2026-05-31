package com.example.obdreader.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.obdreader.databinding.ActivityMainBinding
import com.example.obdreader.viewmodel.ObdViewModel
import com.example.obdreader.ui.MainFragment
import com.example.obdreader.ui.ConnectFragment

private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ObdViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "onCreate: Created MainActivity")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigate(ObdViewModel.Screen.CONNECT)
//        checkBluetoothStatus()
    }

    private fun checkBluetoothStatus() {
        Log.i(TAG, "checkBluetoothStatus: Supported=${viewModel.isBluetoothSupported}, Enabled=${viewModel.isBluetoothEnabled}")
        if (!viewModel.isBluetoothSupported) {
            Toast.makeText(this, "Device does not support Bluetooth", Toast.LENGTH_LONG).show()
            return
        }

        if (!viewModel.isBluetoothEnabled) {
            Log.i(TAG, "checkBluetoothStatus: Navigating to ConnectFragment")
            navigate(ObdViewModel.Screen.CONNECT)
        }
    }

    private fun navigate(screen: ObdViewModel.Screen) {
        val target = screen.name
        val current = supportFragmentManager.findFragmentById(com.example.obdreader.R.id.host)
        if (current?.tag == target) return

        val fragment: Fragment = when (screen) {
            ObdViewModel.Screen.MAIN -> MainFragment()
            ObdViewModel.Screen.CONNECT -> ConnectFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(com.example.obdreader.R.id.host, fragment, target)
            .setReorderingAllowed(true)
            .commit()
    }
}
