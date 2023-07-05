package com.moon.worker.ui.site.list

import androidx.lifecycle.ViewModel

/**
 * @Des：
 * @author: moon
 * @date: 6/28/23
 */
class SiteListViewModel : ViewModel()  {

    val testList = listOf<SiteListItem>(
        SiteListItem("1", "11"),
        SiteListItem("1", "11"),
        SiteListItem("1", "22"),
        SiteListItem("1", "11"),
        SiteListItem("1", "33"),
        SiteListItem("1", "11"),
        SiteListItem("1", "444"),
    )

}


data class SiteListItem(
    val id : String,
    val title: String
)