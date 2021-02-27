package com.alvayonara.kade_submission_alvayonara.testing

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val resource = "GLOBAL"
    private val countingIdlingResource = CountingIdlingResource(resource)

    val idlingResource: IdlingResource
        get() = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }
}