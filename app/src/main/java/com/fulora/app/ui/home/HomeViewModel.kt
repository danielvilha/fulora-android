package com.fulora.app.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fulora.app.source.local.dao.PlantDao

/**
 * Created by danielvilha on 17/04/21
 * https://github.com/danielvilha
 */
class HomeViewModel(
    val database: PlantDao,
    application: Application
): AndroidViewModel(application) {
}