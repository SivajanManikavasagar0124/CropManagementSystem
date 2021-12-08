package espressoTest;


import android.app.Activity;
import android.view.Gravity;


import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import ca.sunshineboys.it.cropmanagementsystem.R;
import ca.sunshineboys.it.cropmanagementsystem.SunshineMain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;




public class navDrawerTest {

    @Rule
    public ActivityScenarioRule<SunshineMain> activityScenarioRule = new ActivityScenarioRule<>(SunshineMain.class);



    @Test
    public void firstTest(){
        onView(withId(R.id.waternowButton))
                .perform(click())
                .check(matches(isDisplayed()));


    }

    @Test
    public void secondTest(){
        onView(withId(R.id.bugreport_settings))
                .perform(click());


    }
    }

