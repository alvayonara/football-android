package com.alvayonara.kade_submission_alvayonara

import android.view.KeyEvent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.alvayonara.kade_submission_alvayonara.testing.EspressoIdlingResource
import com.alvayonara.kade_submission_alvayonara.ui.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testSearchEventBehaviour() {
        // Memastikan BottomNavigationView telah ditampilkan.
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))

        // Memberikan tindakan klik pada menu Home di BottomNavigationView.
        onView(withId(R.id.navigation_home)).perform(click())

        // Memastikan RecyclerView League telah ditampilkan.
        onView(withId(R.id.league_list)).check(matches(isDisplayed()))

        // Melakukan scrolling recyclerview.
        onView(withId(R.id.league_list)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                2
            )
        )

        // Memberikan tindakan klik pada salah satu item recyclerview.
        onView(withId(R.id.league_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

        onView(withText(R.string.search_match)).perform(click())

        // Memastikan tombol menu Search View telah ditampilkan.
        onView(allOf(withId(R.id.search_match), isDisplayed())).check(matches(isDisplayed()))

        // Memberikan tindakan klik pada tombol menu Search View.
        onView(allOf(withId(R.id.search_match), isDisplayed())).perform(click())

        // Memasukkan teks "real madrid" pada Search View.
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("real madrid"))

        // Menekan enter untuk memulai proses pencarian.
        onView(
            allOf(
                withId(R.id.search_match),
                isDisplayed()
            )
        ).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        // Memastikan Recycler View telah ditampilkan.
        onView(withId(R.id.event_list)).check(matches(isDisplayed()))

        // Menghapus teks pada Search View.
        onView(isAssignableFrom(EditText::class.java)).perform(clearText())

        // Memasukkan teks "barca" pada Search View.
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("barca"))

        // Menekan enter untuk memulai proses pencarian.
        onView(
            allOf(
                withId(R.id.search_match),
                isDisplayed()
            )
        ).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        // Memastikan Recycler View tidak ditampilkan.
        onView(withId(R.id.event_list)).check(matches(not(isDisplayed())))

        // Memastikan halaman "Search Not Found" telah ditampilkan.
        onView(withId(R.id.layout_search_not_found)).check(matches(isDisplayed()))
    }
}