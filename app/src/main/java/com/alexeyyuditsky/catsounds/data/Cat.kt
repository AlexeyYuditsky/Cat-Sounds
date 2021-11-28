package com.alexeyyuditsky.catsounds.data

import android.os.Parcelable
import com.alexeyyuditsky.catsounds.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(val image: Int, val title: Int, val sound: Int) : Parcelable {
    companion object {
        fun listCat(): List<List<Cat>> {
            return listOf(
                listOf(
                    Cat(R.drawable.meow1, R.string.meow1, R.raw.meow1),
                    Cat(R.drawable.meow2, R.string.meow2, R.raw.meow2),
                    Cat(R.drawable.meow3, R.string.meow3, R.raw.meow3),
                    Cat(R.drawable.meow4, R.string.meow4, R.raw.meow4),
                    Cat(R.drawable.meow5, R.string.meow5, R.raw.meow5),
                    Cat(R.drawable.meow6, R.string.meow6, R.raw.meow6),
                    Cat(R.drawable.meow7, R.string.meow7, R.raw.meow7),
                    Cat(R.drawable.meow8, R.string.meow8, R.raw.meow8),
                    Cat(R.drawable.meow9, R.string.meow9, R.raw.meow9),
                    Cat(R.drawable.meow10, R.string.meow10, R.raw.meow10),
                    Cat(R.drawable.meow11, R.string.meow11, R.raw.meow11),
                    Cat(R.drawable.meow12, R.string.meow12, R.raw.meow12),
                ), listOf(
                    Cat(R.drawable.angry1, R.string.angry1, R.raw.angry1),
                    Cat(R.drawable.angry2, R.string.angry2, R.raw.angry2),
                    Cat(R.drawable.angry3, R.string.angry3, R.raw.angry3),
                    Cat(R.drawable.angry4, R.string.angry4, R.raw.angry4),
                    Cat(R.drawable.angry5, R.string.angry5, R.raw.angry5),
                    Cat(R.drawable.angry6, R.string.angry6, R.raw.angry6),
                    Cat(R.drawable.angry7, R.string.angry7, R.raw.angry7),
                    Cat(R.drawable.angry8, R.string.angry8, R.raw.angry8),
                    Cat(R.drawable.angry9, R.string.angry9, R.raw.angry9),
                    Cat(R.drawable.angry10, R.string.angry10, R.raw.angry10),
                    Cat(R.drawable.angry11, R.string.angry11, R.raw.angry11),
                    Cat(R.drawable.angry12, R.string.angry12, R.raw.angry12),
                ), listOf(
                    Cat(R.drawable.kitten1, R.string.kitten1, R.raw.kitten1),
                    Cat(R.drawable.kitten2, R.string.kitten2, R.raw.kitten2),
                    Cat(R.drawable.kitten3, R.string.kitten3, R.raw.kitten3),
                    Cat(R.drawable.kitten4, R.string.kitten4, R.raw.kitten4),
                    Cat(R.drawable.kitten5, R.string.kitten5, R.raw.kitten5),
                    Cat(R.drawable.kitten6, R.string.kitten6, R.raw.kitten6),
                    Cat(R.drawable.kitten7, R.string.kitten7, R.raw.kitten7),
                    Cat(R.drawable.kitten8, R.string.kitten8, R.raw.kitten8),
                    Cat(R.drawable.kitten9, R.string.kitten9, R.raw.kitten9),
                    Cat(R.drawable.kitten10, R.string.kitten10, R.raw.kitten10),
                    Cat(R.drawable.kitten11, R.string.kitten11, R.raw.kitten11),
                    Cat(R.drawable.kitten12, R.string.kitten12, R.raw.kitten12),
                )
            )
        }
    }
}