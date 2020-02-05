package com.alvayonara.kade_submission_alvayonara

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(val name: String?, val description: String?, val image: Int) : Parcelable