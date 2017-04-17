package textmessage.szabados.alpar.textmessage;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class TextMessageSenderInstrumentedTest {
    private static final String INPUT = "Testing";
    private static final ViewInteraction TEXT_VIEW = onView(withId(R.id.customText));

    @Rule
    public ActivityTestRule<TextMessageSender> tMSTestRule = new ActivityTestRule<>(TextMessageSender.class, true, true);

    @Test
    public void testTextView() {
        ViewInteraction actual = TEXT_VIEW.perform(typeText(INPUT));
        actual.check(matches(withText(INPUT)));
    }
}
