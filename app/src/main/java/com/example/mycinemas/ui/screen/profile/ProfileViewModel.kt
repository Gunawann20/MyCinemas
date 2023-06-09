package com.example.mycinemas.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.example.mycinemas.R
import com.example.mycinemas.model.Biodata
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel: ViewModel() {

    private val _biodata: MutableStateFlow<Biodata> = MutableStateFlow(Biodata(R.drawable.profile, "Gunawan", "gunawanatmoko75@students.unnes.ac.id"))
    val biodata:StateFlow<Biodata> = _biodata


}