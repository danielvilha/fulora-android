package com.fulora.app.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fulora.app.database.FuloraDatabaseDao

/**
 * Created by danielvilha on 17/04/21
 * https://github.com/danielvilha
 */
class HomeViewModel(
    val database: FuloraDatabaseDao,
    application: Application
): AndroidViewModel(application) {
}