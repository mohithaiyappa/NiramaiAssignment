package com.example.niramaiassignment.utils

import android.graphics.Rect
import android.graphics.drawable.InsetDrawable
import android.widget.EditText

//EditText
fun EditText.removeHorizontalInsets(): EditText {
    if(background is InsetDrawable){
        val insetDrawable = background as InsetDrawable
        val originalDrawable = insetDrawable.drawable!!
        val insetDrawablePadding = Rect()
        insetDrawable.getPadding(insetDrawablePadding)
        val originalDrawablePadding = Rect()
        originalDrawable.getPadding(originalDrawablePadding)

        // We subtract original padding dimensions from inset drawable padding dimensions.
        // We assume that padding is calculated by summing original drawable padding
        // and inset drawable insets. So to retrieve only insets we have to perform subtraction
        val insetDrawableInsets = Rect(
            insetDrawablePadding.left - originalDrawablePadding.left,
            insetDrawablePadding.top - originalDrawablePadding.top,
            insetDrawablePadding.right - originalDrawablePadding.right,
            insetDrawablePadding.bottom - originalDrawablePadding.bottom)
        // Remove side spacing from editText background to make it fit fully into layout width
        background = InsetDrawable(
            originalDrawable,
            0, insetDrawablePadding.top, 0, insetDrawableInsets.bottom)
    }
    return this
}