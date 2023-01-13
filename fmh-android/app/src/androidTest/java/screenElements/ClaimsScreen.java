package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import additional.MainHelper;
import ru.iteco.fmhandroid.R;

public class ClaimsScreen {

    // Название блока
    public static ViewInteraction titleOfClaimsBlock = onView(withText("Claims"));

    // Создание заявки
    public static ViewInteraction addNewClaimButton = onView(withId(R.id.add_new_claim_material_button));

    // Переход к заявке
    public static ViewInteraction firstClaimCard = onView(MainHelper.withIndex(withId(R.id.claim_list_card), 0));

    // Отдельная заявка (нераскрытая) в блоке заявок
    public static ViewInteraction firstClaimTopicInClaimsBlock = onView(MainHelper.withIndex(withId(R.id.description_material_text_view), 0));
    public static ViewInteraction secondClaimTopicInClaimsBlock = onView(MainHelper.withIndex(withId(R.id.description_material_text_view), 1));
    public static ViewInteraction thirdClaimTopicInClaimsBlock = onView(MainHelper.withIndex(withId(R.id.description_material_text_view), 2));
    public static ViewInteraction executorNameOfFirstClaim = onView(MainHelper.withIndex(withId(R.id.executor_name_material_text_view), 0));
    public static ViewInteraction planDateOfFirstClaim = onView(MainHelper.withIndex(withId(R.id.plan_date_material_text_view), 0));
    public static ViewInteraction planTimeOfFirstClaim = onView(MainHelper.withIndex(withId(R.id.plan_time_material_text_view), 0));

    // Разное
    public static ViewInteraction containerForClaims = onView(withId(R.id.claim_list_recycler_view));
    public static ViewInteraction filtersButton = onView(withId(R.id.filters_material_button));

    // Фильтрация заявок
    public static ViewInteraction buttonForClaimsFiltering = onView(withId(R.id.filters_material_button));
    public static ViewInteraction titleOfFilterDialog = onView(withId(R.id.claim_filter_dialog_title));
    public static ViewInteraction inProgress = onView(withId(R.id.item_filter_in_progress));
    public static ViewInteraction open = onView(withId(R.id.item_filter_open));
    public static ViewInteraction okButton = onView(withId(R.id.claim_list_filter_ok_material_button));

}
