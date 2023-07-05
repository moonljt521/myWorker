package com.moon.worker.utils

import android.widget.Toast
import com.moon.worker.LinqApplication

/**
 * @Des：
 * @author: moon
 * @date: 6/28/23
 */
fun showToast(msg : String) {
    Toast.makeText(LinqApplication.application , msg ,Toast.LENGTH_SHORT).show()
}