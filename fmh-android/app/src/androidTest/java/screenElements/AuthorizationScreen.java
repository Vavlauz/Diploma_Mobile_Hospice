package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthorizationScreen {

    String authorizationImageButton = "authorization_image_button";

    public static ViewInteraction authorization = onView(withText("Authorization"));
    public static ViewInteraction loginInput = onView(withId(R.id.login_test_input_edit_text));
    public static ViewInteraction passwordInput = onView(withId(R.id.pass_test_input_edit_text));
    public static ViewInteraction signInButton = onView(withId(R.id.enter_button));
    public static ViewInteraction loginField = onView(withId(R.id.login_text_input_layout));
    public static ViewInteraction passwordField = onView(withId(R.id.password_text_input_layout));
    public static ViewInteraction dialogWindowWithError = onView(withText("Wrong login or password"));

}
