package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void emptyNameAgeTest() {
        onView(withId(R.id.editTExtName)).check(matches(withText("")));
        onView(withId(R.id.editTextAge)).check(matches(withText("")));
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void emptyNameTest() {
        onView(withId(R.id.editTExtName)).check(matches(withText("")));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void emptyListTest() {
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }

    @Test
    public void emptyAgeTest() {
        onView(withId(R.id.editTExtName)).perform(replaceText(("Ying")));
        onView(withId(R.id.editTextAge)).check(matches(withText("")));
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void addNameAgeFirstTest() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list, 0)).check(matches(hasDescendant(withText("Ying"))));
        onView(withRecyclerView(R.id.list, 0)).check(matches(hasDescendant(withText("20"))));
        onView(withId(R.id.buttonDelete)).perform(click());
    }

    @Test
    public void addNameAgeSecondTest() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list, 1)).check(matches(hasDescendant(withText("Ladarat"))));
        onView(withRecyclerView(R.id.list, 1)).check(matches(hasDescendant(withText("20"))));
        onView(withId(R.id.buttonDelete)).perform(click());
    }

    @Test
    public void addNameAgeThirdTest() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"));
        onView(withId(R.id.editTextAge)).perform(replaceText("80"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list, 2)).check(matches(hasDescendant(withText("Somkait"))));
        onView(withRecyclerView(R.id.list, 2)).check(matches(hasDescendant(withText("80"))));
        onView(withId(R.id.buttonDelete)).perform(click());
    }

    @Test
    public void addNameAgeFourthTest() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"));
        onView(withId(R.id.editTextAge)).perform(replaceText("80"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"));
        onView(withId(R.id.editTextAge)).perform(replaceText("60"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list, 3)).check(matches(hasDescendant(withText("Prayoch"))));
        onView(withRecyclerView(R.id.list, 3)).check(matches(hasDescendant(withText("60"))));
        onView(withId(R.id.buttonDelete)).perform(click());
    }

    @Test
    public void addNameAgeFifthTest() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"));
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"));
        onView(withId(R.id.editTextAge)).perform(replaceText("80"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"));
        onView(withId(R.id.editTextAge)).perform(replaceText("60"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"));
        onView(withId(R.id.editTextAge)).perform(replaceText("50"));
        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withRecyclerView(R.id.list, 4)).check(matches(hasDescendant(withText("Prayoch"))));
        onView(withRecyclerView(R.id.list, 4)).check(matches(hasDescendant(withText("50"))));
        onView(withId(R.id.buttonDelete)).perform(click());
    }

    private Matcher<View> withRecyclerView(int list, int i) {
        return childAtPosition(childAtPosition(withId(list), i), 0);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
