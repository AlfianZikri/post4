package com.example.post4.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.post4.data.Warga

class WargaAdapter : RecyclerView.Adapter<WargaAdapter.VH>() {

    private var list = listOf<Warga>()

    fun submitList(newList: List<Warga>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemWargaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    inner class VH(private val b: ItemWargaBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(w: Warga, pos: Int) {
            val nomor = "${pos + 1}. ${w.nama} (${w.jenisKelamin}) - ${w.statusPernikahan}"
            b.tvTitle.text = nomor
            b.tvNik.text = "NIK: ${w.nik}"
            b.tvAlamat.text = "Alamat: RT ${w.rt}/RW ${w.rw}, ${w.desa}, ${w.kecamatan}, ${w.kabupaten}"
        }
    }
}
