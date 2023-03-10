package com.moon.worker.ui.guide

import androidx.lifecycle.ViewModel
import com.moon.worker.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @Des：
 * @author: moon
 * @date: 3/10/23
 */
class GuideViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GuideUiState())

    val uiState : StateFlow<GuideUiState> = _uiState

    val guideItems = listOf<GuideUiState>(
        GuideUiState(icon = R.drawable.guide_1 , title = "Stay connected", description = "Stay connected','send messages,photos,job assignments and more,plus improve communication with the translation tool"),
        GuideUiState(icon = R.drawable.guide_2 , title = "Work smarter", description = "Work smarter','receive progress photos from your team and keep all work photos organized and in our cloud storage"),
        GuideUiState(icon = R.drawable.guide_3 , title = "Grow faster", description = "Grow faster','communicate about new or orgoing jobs with anyone,anywhere you want."),
    )

}

