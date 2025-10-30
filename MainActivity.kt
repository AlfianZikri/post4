package com.example.post4.ui

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.post4.ui.MainViewModel
import com.example.post4.ui.WargaAdapter
import com.example.post4.data.Warga
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()
    private val adapter = WargaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupRecycler()
        observeData()
        setupButtons()
    }

    private fun setupSpinner() {
        val items = listOf("Belum Menikah", "Menikah", "Cerai")
        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, items)
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerStatus.adapter = spinnerAdapter
    }

    private fun setupRecycler() {
        binding.rvWarga.adapter = adapter
    }

    private fun observeData() {
        vm.allWarga.observe(this) { list ->
            adapter.submitList(list)
        }
    }

    private fun setupButtons() {
        binding.btnSimpan.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val nik = binding.etNik.text.toString().trim()
            if (nama.isEmpty() || nik.isEmpty()) {
                Snackbar.make(binding.root, "Nama dan NIK wajib diisi", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val jenisKelamin = when (binding.rgGender.checkedRadioButtonId) {
                binding.rbL.id -> "Laki-Laki"
                binding.rbP.id -> "Perempuan"
                else -> "Laki-Laki"
            }

            val warga = Warga(
                nama = nama,
                nik = nik,
                kabupaten = binding.etKabupaten.text.toString().trim(),
                kecamatan = binding.etKecamatan.text.toString().trim(),
                desa = binding.etDesa.text.toString().trim(),
                rt = binding.etRT.text.toString().trim(),
                rw = binding.etRW.text.toString().trim(),
                jenisKelamin = jenisKelamin,
                statusPernikahan = binding.spinnerStatus.selectedItem.toString()
            )

            vm.insert(warga)
            Snackbar.make(binding.root, "Data berhasil disimpan", Snackbar.LENGTH_SHORT).show()
            clearForm()
        }

        binding.btnReset.setOnClickListener {
            clearForm()
            vm.deleteAll()
            Snackbar.make(binding.root, "Data berhasil dihapus", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun clearForm() {
        binding.etNama.setText("")
        binding.etNik.setText("")
        binding.etKabupaten.setText("")
        binding.etKecamatan.setText("")
        binding.etDesa.setText("")
        binding.etRT.setText("")
        binding.etRW.setText("")
        binding.rgGender.check(binding.rbL.id)
        binding.spinnerStatus.setSelection(0)
    }
}
