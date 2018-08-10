package org.systers.mentorship.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView

const val NON_VALID_VALUE_REPLACEMENT = "----"

/**
 * Sets the text from a textView with the following key-value pair format, such as [spanText]: [text]
 * [spanText] uses bold style
 * @param textView will have the key-value text pair
 * @param spanText contains the key that should be in bold style
 * @param text contains the value which has non bold style
 */
fun setTextViewStartingWithBoldSpan(textView: TextView, spanText: String, text: String?) {
    val validText = if (text.isNullOrBlank()) NON_VALID_VALUE_REPLACEMENT else text
    val keyValueText = "$spanText: $validText"
    val sb = SpannableStringBuilder(keyValueText)
    val start = 0
    val end = spanText.length + 1 // +1 for the ":" character
    val style = StyleSpan(Typeface.BOLD)
    sb.setSpan(style, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    textView.text = sb
}
