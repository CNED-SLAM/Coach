package com.example.coach.view;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.coach.R;

@RunWith(AndroidJUnit4.class)
public class CalculActivityTest {

    @Rule
    public ActivityScenarioRule<CalculActivity> activityRule =
            new ActivityScenarioRule<>(CalculActivity.class);

    @Test
    public void testCalculIMG() {
        // Saisie du poids
        onView(withId(R.id.txtPoids))
                .perform(typeText("70"), closeSoftKeyboard());

        // Saisie de la taille
        onView(withId(R.id.txtTaille))
                .perform(typeText("180"), closeSoftKeyboard());

        // Saisie de l'âge
        onView(withId(R.id.txtAge))
                .perform(typeText("40"), closeSoftKeyboard());

        // Sélection du sexe Homme
        onView(withId(R.id.rdHomme))
                .perform(click());

        // Clic sur Calculer
        onView(withId(R.id.btnCalc))
                .perform(click());

        // Vérification du résultat attendu
        onView(withId(R.id.lblIMG))
                .check(matches(withText("18.9 : IMG normal")));

        // Pause pour voir le résultat
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
