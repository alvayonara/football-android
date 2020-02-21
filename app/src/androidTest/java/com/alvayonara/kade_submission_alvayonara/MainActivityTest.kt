package com.alvayonara.kade_submission_alvayonara

import android.view.KeyEvent
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.alvayonara.kade_submission_alvayonara.ui.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearchEventBehaviour() {
        // Memastikan BottomNavigationView telah ditampilkan.
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        Thread.sleep(1000)

        // Memberikan tindakan klik pada menu Match di BottomNavigationView.
        onView(withId(R.id.navigation_match)).perform(click())
        Thread.sleep(1000)

        // Memastikan tombol search telah ditampilkan.
        onView(withId(R.id.img_search)).check(matches(isDisplayed()))
        Thread.sleep(1000)

        // Memberikan tindakan klik pada tombol search.
        onView(withId(R.id.img_search)).perform(click())
        Thread.sleep(1000)

        // Memastikan tombol menu Search View telah ditampilkan.
        onView(allOf(withId(R.id.search_match), isDisplayed())).check(matches(isDisplayed()))
        Thread.sleep(1000)

        // Memberikan tindakan klik pada tombol menu Search View.
        onView(allOf(withId(R.id.search_match), isDisplayed())).perform(click())
        Thread.sleep(1000)

        // Memasukkan teks "real madrid" pada Search View.
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("real madrid"))
        Thread.sleep(1000)

        // Menekan enter untuk memulai proses pencarian.
        onView(
            allOf(
                withId(R.id.search_match),
                isDisplayed()
            )
        ).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(4000)

        // Memastikan Recycler View telah ditampilkan.
        onView(withId(R.id.event_list)).check(matches(isDisplayed()))
        Thread.sleep(1000)

        // Menghapus teks pada Search View.
        onView(isAssignableFrom(EditText::class.java)).perform(clearText())
        Thread.sleep(1000)

        // Memasukkan teks "barca" pada Search View.
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("barca"))
        Thread.sleep(1000)

        // Menekan enter untuk memulai proses pencarian.
        onView(
            allOf(
                withId(R.id.search_match),
                isDisplayed()
            )
        ).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(4000)

        // Memastikan Recycler View tidak ditampilkan.
        onView(withId(R.id.event_list)).check(matches(not(isDisplayed())))
        Thread.sleep(1000)

        // Memastikan halaman "Search Not Found" telah ditampilkan.
        onView(withId(R.id.layout_search_not_found)).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }
}