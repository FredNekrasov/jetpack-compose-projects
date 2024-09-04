package com.fredprojects.features.pws.presentation.vm

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.*
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import com.fredprojects.features.pws.presentation.mappers.*
import com.fredprojects.features.pws.presentation.models.PWPModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID
import javax.inject.Inject

/**
 * UpsertPWVM is used to manage the state of the upsert view
 * @param savedStateHandle is used to get the id of the practical work
 */
@HiltViewModel
class UpsertPWVM @Inject constructor(
    private val useCases: PWUseCases,
    private val appContext: Application,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    /**
     * @see upsertPWStateMSF is used to emit data to the state flow
     * @see upsertPWState is used to display data in the view
     */
    private val upsertPWStateMSF = MutableStateFlow(UpsertPWState())
    val upsertPWState = upsertPWStateMSF.asStateFlow()
    /**
     * The upsert is used to upsert a practical work
     * @param pw is the practical work to be inserted or updated
     */
    fun upsert(pw: PWPModel) {
        viewModelScope.launch {
            upsertPWStateMSF.emit(upsertPWState.value.copy(status = useCases.upsert(pw.toDomain())))
        }
    }

    /**
     * The takePicture is used to take a picture of the practical work
     * @param launch is the function to launch the camera
     */
    fun takePicture(launch: (Uri) -> Unit) {
        viewModelScope.launch {
            val file = File(appContext.filesDir, "fred${UUID.randomUUID()}.jpg")
            val uri = FileProvider.getUriForFile(appContext, "fredProjectsHW.provider", file)
            if(appContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) launch(uri)
            upsertPWStateMSF.emit(upsertPWState.value.copy(pw = upsertPWState.value.pw?.copy(image = uri.toString())))
        }
    }
    /**
     * The init is used to get the practical work data
     * The id is the id of the practical work
     */
    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            if (id != -1) {
                viewModelScope.launch {
                    useCases.getPWs.getById(id)?.let {
                        upsertPWStateMSF.emit(upsertPWState.value.copy(pw = it.toPresentation()))
                    }
                }
            }
        }
    }
}