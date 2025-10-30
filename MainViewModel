package com.example.post4.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.post4.data.AppDatabase
import com.example.post4.data.Warga
import com.example.post4.data.WargaRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: WargaRepository

    val allWarga: LiveData<List<Warga>>

    init {
        val dao = AppDatabase.Companion.getDatabase(application).wargaDao()
        repo = WargaRepository(dao)
        allWarga = repo.allWarga
    }

    fun insert(warga: Warga) = viewModelScope.launch {
        repo.insert(warga)
    }

    fun deleteAll() = viewModelScope.launch {
        repo.deleteAll()
    }
}
