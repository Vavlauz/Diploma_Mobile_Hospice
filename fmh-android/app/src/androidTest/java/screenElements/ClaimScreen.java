package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import additional.MainHelper;
import ru.iteco.fmhandroid.R;

public class ClaimScreen {

    // Элементы раскрытой заявки (предварительно перейти в заявку)
    public static ViewInteraction titleTextOfClaim = onView(withId(R.id.title_text_view));
    public static ViewInteraction executorTextOfClaim = onView(withId(R.id.executor_name_text_view));
    public static ViewInteraction planeDateOfClaim = onView(withId(R.id.plane_date_text_view));
    public static ViewInteraction planTimeOfClaim = onView(withId(R.id.plan_time_text_view));
    public static ViewInteraction statusOfClaim = onView(withId(R.id.status_label_text_view));
    public static ViewInteraction openStatus = onView(withText("Open"));
    public static ViewInteraction descriptionOfClaim = onView(withId(R.id.description_text_view));
    public static ViewInteraction authorOfClaim = onView(withId(R.id.author_name_text_view));
    public static ViewInteraction creationDateOfClaim = onView(withId(R.id.create_data_text_view));
    public static ViewInteraction creationTimeOfClaim = onView(withId(R.id.create_time_text_view));
    public static ViewInteraction firstCommentDescriptionOfClaim = onView(MainHelper.withIndex(withId(R.id.comment_description_text_view), 0));
    public static ViewInteraction firstCommentatorNameOfClaim = onView(MainHelper.withIndex(withId(R.id.commentator_name_text_view), 0));
    public static ViewInteraction firstCommentDateOfClaim = onView(MainHelper.withIndex(withId(R.id.comment_date_text_view), 0));
    public static ViewInteraction firstCommentTimeOfClaim = onView(MainHelper.withIndex(withId(R.id.comment_time_text_view), 0));

    // Другоое
    public static ViewInteraction buttonToAddComment = onView(withId(R.id.add_comment_image_button));
}
