package com.example.common

import android.content.Context

sealed class UIText {
    data class DynamicString(val input: String) : UIText()
    data class StringResource(val id: Int) : UIText()
    companion object {
        fun getText(uiText: UIText, context: Context): String {
            return when (uiText) {
                is StringResource ->
                    context.getString(uiText.id)
                is DynamicString ->
                    uiText.input
            }
        }
    }
}
