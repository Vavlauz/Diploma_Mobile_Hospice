package screenElements;

import androidx.test.espresso.ViewInteraction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import additional.MainHelper;
import ru.iteco.fmhandroid.R;

public class QuotesScreen {

    // Раздел цитат о хосписе
    public static ViewInteraction missionTitle = onView(withId(R.id.our_mission_title_text_view));
    public static ViewInteraction openOrCloseFirstQuoteButton = onView(MainHelper.withIndex(withId(R.id.our_mission_item_open_card_image_button), 0));
    public static ViewInteraction firstTitleOfQuote = onView(withId(R.id.our_mission_item_title_text_view));
    public static ViewInteraction firstDescriptionOfQuote = onView(withId(R.id.our_mission_item_description_text_view));
    public static ViewInteraction firstMissionTitleValue = onView(withText("«Хоспис для меня - это то, каким должен быть мир.\""));
    public static ViewInteraction firstDescriptionOfQuoteValue = onView(withText("\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер"));
}
