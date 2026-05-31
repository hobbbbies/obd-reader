package com.example.obdreader.ui

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresPermission
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.obdreader.R

class DeviceListAdapter(
    private val onClick: (BluetoothDevice) -> Unit
) : ListAdapter<BluetoothDevice, DeviceListAdapter.ViewHolder>(DeviceDiffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = getItem(position)
        holder.tvName.text = device.name ?: "Unknown Device"
        holder.itemView.setOnClickListener { onClick(device) }
    }

    companion object DeviceDiffCallback : DiffUtil.ItemCallback<BluetoothDevice>() {
        override fun areItemsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
            return oldItem.address == newItem.address
        }

        override fun areContentsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
            return oldItem.address == newItem.address
        }
    }
}
