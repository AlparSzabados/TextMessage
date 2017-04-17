package textmessage.szabados.alpar.textmessage;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class TextMessageSenderInstrumentedTest {
    private static final String INPUT = "Testing";

    @Rule
    public ActivityTestRule<TextMessageSender> tMSTestRule = new ActivityTestRule<>(TextMessageSender.class, true, true);

    @Test
    public void testTextViewDisplayed() {
        ViewInteraction actual = onView(withId(R.id.customText)).perform(typeText(INPUT));
        actual.check(matches(isDisplayed()));
    }

    @Test
    public void testFabButtonDisplayed() {
        ViewInteraction actual = onView(withId(R.id.fab));
        actual.check(matches(isDisplayed()));
    }

    @Test
    public void testFabButtonOnClickClearsText() {
        ViewInteraction inputText = onView(withId(R.id.customText)).perform(typeText(INPUT));
        onView(withId(R.id.fab)).perform(click());
        inputText.check(matches(withText("")));
    }
}
