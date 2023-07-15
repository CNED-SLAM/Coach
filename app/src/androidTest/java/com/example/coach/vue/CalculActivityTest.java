package com.example.coach.vue;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;

import com.example.coach.R;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculActivityTest {

    @Rule
    public ActivityScenarioRule<CalculActivity> rule = new ActivityScenarioRule<CalculActivity>(CalculActivity.class);

    @Test
    public void scenario(){
        onView(withId(R.id.txtPoids)).perform(typeText("55"), closeSoftKeyboard());
        onView(withId(R.id.txtTaille)).perform(typeText("160"), closeSoftKeyboard());
        onView(withId(R.id.txtAge)).perform(typeText("30"), closeSoftKeyboard());
        onView(withId(R.id.rdFemme)).perform(click());
        onView(withId(R.id.btnCalc)).perform(click());
        SystemClock.sleep(5000);
    }

}