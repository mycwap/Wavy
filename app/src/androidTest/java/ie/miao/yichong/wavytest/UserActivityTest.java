package ie.miao.yichong.wavytest;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ie.miao.yichong.wavytest.models.User;
import ie.miao.yichong.wavytest.views.UserActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class UserActivityTest {

    User sampleUser;

    @Rule
    public ActivityTestRule<UserActivity> activityRule = new ActivityTestRule<>(
            UserActivity.class);


    @Before
    public void setUp() throws Exception {
        sampleUser = new User("9bfb7d75-4f0e-4b09-aba7-1ce8a93d41da",
                "Wa", "vy", "wavy@wavy.com"
                , "https://cdn-images-1.medium.com/max/1200/1*SXwAJmh1cMSi1vApbqCelQ@2x.png", "+4900000101010");

    }



    @Test
    public void checkUserInformation() throws Throwable {

        activityRule.runOnUiThread(new Runnable() {

            @Override
            public void run() {


                UserActivity mActivity = activityRule.getActivity();


                mActivity.displayUser(sampleUser);




            }

        });

        onView(withId(R.id.nameTextView)).check(matches((withText(sampleUser.getFirstName() + " " + sampleUser.getLastName()))));
        onView(withId(R.id.phoneTextView)).check(matches((withText(sampleUser.getPhoneNumber()))));
        onView(withId(R.id.emailTextView)).check(matches((withText(sampleUser.getEmail()))));
    }


    @Test
    public void checkDeleteSuccess() throws Throwable {
        final UserActivity mActivity = activityRule.getActivity();
        activityRule.runOnUiThread(new Runnable() {

            @Override
            public void run() {





                mActivity.displayUser(sampleUser);
                mActivity.deletePhoneNumber(true);




            }

        });
        onView(withText(R.string.delete_success)).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withId(R.id.phoneTextView)).check(matches((withText(""))));
        onView(withId(R.id.deleteButton)).check(matches(not(isDisplayed())));
    }



    @Test
    public void checkDeleteFailed() throws Throwable {
        final UserActivity mActivity = activityRule.getActivity();
        activityRule.runOnUiThread(new Runnable() {

            @Override
            public void run() {





                mActivity.displayUser(sampleUser);
                mActivity.deletePhoneNumber(false);




            }

        });
        onView(withText(R.string.delete_failed)).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(withId(R.id.phoneTextView)).check(matches((withText(sampleUser.getPhoneNumber()))));
        onView(withId(R.id.deleteButton)).check(matches(isDisplayed()));
    }
}
