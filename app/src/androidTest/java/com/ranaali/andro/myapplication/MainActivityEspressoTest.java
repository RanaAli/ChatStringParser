package com.ranaali.andro.myapplication;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ranaali.andro.ChatStringParser.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * This test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    private static final String INPUT_TEXT = "good morning! this is @ali. " +
            "good morning! this is (happy). Olympics are starting soon; \" +\n" +
            "            \"http://www.nbcolympics.com";
    private static final String EXPECTED_TEXT = "good morning! this is @ali. good morning!" +
            " this is (happy). Olympics are starting soon; \" +\n" +
            "            \"http://www.nbcolympics.com\n" +
            "\n" +
            "{\"mentions\":[\"ali\"],\"emoticons\":[\"happy\"],\"links\":" +
            "[{\"url\":\"http:\\/\\/www.nbcolympics.com\",\"title\":\"2016 Rio Olympic " +
            "Games | NBC Olympics\"}]}";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkTheViewIsPopulating() {
        Espresso.onView(withId(R.id.mainScreenInputEditText))
                .perform(typeText(INPUT_TEXT), closeSoftKeyboard());
        onView(withId(R.id.mainScreenInputButton)).perform(click());

        onView(withId(R.id.mainScreenOutputTextView)).check(matches(withText(EXPECTED_TEXT)));
    }


}
