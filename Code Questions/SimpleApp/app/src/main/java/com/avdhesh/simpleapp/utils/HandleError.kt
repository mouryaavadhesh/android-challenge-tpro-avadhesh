package com.avdhesh.simpleapp.utils

import android.content.Context
import android.widget.Toast


internal object HandleError {

    fun showErrorMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}