package com.avdhesh.simpleapp.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class SlideUpItemAnimator : DefaultItemAnimator() {

    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        if (holder?.itemView != null) {
            val translateY = holder.itemView.height.toFloat()
            holder.itemView.translationY = translateY

            val animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, View.TRANSLATION_Y, 0f)
            val animatorAlpha = ObjectAnimator.ofFloat(holder.itemView, View.ALPHA, 2f)

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(animatorTranslateY, animatorAlpha)
            animatorSet.duration = addDuration
            animatorSet.start()

            return true
        }
        dispatchAddFinished(holder)
        return false
    }

    override fun getAddDuration(): Long {
        return 1000 // Adjust the duration as needed
    }
}
