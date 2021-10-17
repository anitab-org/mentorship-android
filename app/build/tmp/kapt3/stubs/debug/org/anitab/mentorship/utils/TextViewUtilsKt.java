package org.anitab.mentorship.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001\u001a \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"NON_VALID_VALUE_REPLACEMENT", "", "getTextWithBoldWord", "Landroid/text/SpannableStringBuilder;", "mainText", "boldWord", "setTextViewStartingWithBoldSpan", "", "textView", "Landroid/widget/TextView;", "spanText", "text", "app_debug"})
public final class TextViewUtilsKt {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NON_VALID_VALUE_REPLACEMENT = "----";
    
    /**
     * Sets the text from a textView with the following key-value pair format, such as [spanText]: [text]
     * [spanText] uses bold style
     * @param textView will have the key-value text pair
     * @param spanText contains the key that should be in bold style
     * @param text contains the value which has non bold style
     */
    public static final void setTextViewStartingWithBoldSpan(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, @org.jetbrains.annotations.NotNull()
    java.lang.String spanText, @org.jetbrains.annotations.Nullable()
    java.lang.String text) {
    }
    
    /**
     * @param mainText string containing [boldWord]
     * @param boldWord string that should have bold text style
     * @return [SpannableStringBuilder] with [boldWord] within [mainText] with Bold text style
     */
    @org.jetbrains.annotations.NotNull()
    public static final android.text.SpannableStringBuilder getTextWithBoldWord(@org.jetbrains.annotations.NotNull()
    java.lang.String mainText, @org.jetbrains.annotations.NotNull()
    java.lang.String boldWord) {
        return null;
    }
}