package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.repeatedlyUntil;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;

import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screenElements.ClaimCreationAndEditingScreen;
import screenElements.ClaimScreen;
import screenElements.ClaimsScreen;

public class ClaimsSteps {

    public static void initiateTheCreationOfClaim() {
        Allure.step("Начинаем создавать заявку (переход в раздел создания заявки)");
        ClaimsScreen.addNewClaimButton.perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainHelper.elementWaiting(withText("Creating"), 3000);
    }

    public static void checkCreatedClaimInClaimsBlock(String title) {
        Allure.step("Проверка созданной заявки по заголовку");
        MainHelper.isDisplayedWithSwipe(onView(withText(title)), 2, true);
    }

    public static void checkTimeOfCreatedClaimInClaimsBlock(String title, String time) {
        Allure.step("Проверка времени ранее созданной заявки");
        onView(allOf(withText(title), hasSibling(withText(time))));
    }

    public static void goToCreatedClaim(String title) {
        Allure.step("Переход к созданной заявке");
        onView(allOf(withId(R.id.claim_list_card), hasDescendant(withText(title)))).perform(click());
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
    }

    public static void checkThatThereAreThreeClaimsItemsInTheClaimsBlock() {
        Allure.step("Проверка, что в блоке заявок имеется минимум 3 заявки");
        ClaimsScreen.firstClaimTopicInClaimsBlock.check(matches(isDisplayed()));
        ClaimsScreen.secondClaimTopicInClaimsBlock.check(matches(isDisplayed()));
        ClaimsScreen.thirdClaimTopicInClaimsBlock.check(matches(isDisplayed()));
    }

    public static void checkContentOfFirstClaimInClaimsBlock() {
        Allure.step("Проверка содержимого первой заявки в блоке заявок");
        MainHelper.elementWaiting(withId(R.id.description_text_view), 3000);
        MainHelper.elementWaiting(withId(R.id.executor_name_text_view), 3000);
        MainHelper.elementWaiting(withId(R.id.plane_date_text_view), 3000);
        MainHelper.elementWaiting(withId(R.id.plan_time_text_view), 3000);
    }

    public static void checkContentOfFirstClaimInClaimsBlockRolledUp() {
        Allure.step("Проверка содержимого первой заявки в блоке заявок в свернутом состоянии");
        MainHelper.elementWaiting(withId(R.id.description_material_text_view), 3000);
        MainHelper.elementWaiting(withId(R.id.executor_name_material_text_view), 3000);
        MainHelper.elementWaiting(withId(R.id.plan_date_material_text_view), 3000);
        MainHelper.elementWaiting(withId(R.id.plan_time_material_text_view), 3000);
    }

    public static void goToFirstClaimFromClaimsBlock() {
        Allure.step("Переход к первой заявке из блока заявок");
        ClaimsScreen.firstClaimCard.perform(click());
        MainHelper.elementWaiting(withId(R.id.title_text_view), 3000);
    }

    public static void initiateClaimFiltering() {
        Allure.step("Приступить к фильтрации заявок");
        ClaimsScreen.buttonForClaimsFiltering.perform(click());
        ClaimsScreen.titleOfFilterDialog.check(matches(isDisplayed()));
    }

    public static void сhooseOnlyOpenStatusIfOpenAndInProgressStatusesAreChosenInitially() {
        Allure.step("Выбрать только открытый статус при фильтрации");
        ClaimsScreen.inProgress.perform(click());
        ClaimsScreen.open.check(matches(isChecked()));
        ClaimsScreen.inProgress.check(matches(isNotChecked()));
        ClaimsScreen.okButton.perform(click());
        ClaimsScreen.titleOfClaimsBlock.check(matches(isDisplayed()));
    }

    public static void checkClaimStatus(String status) {
        Allure.step("Проверка статус претензии");
        String claimStatus = MainHelper.Text.getText(onView(withId(R.id.status_label_text_view)));
        assertEquals(status, claimStatus);
    }

    public String getClaimTime() {
        Allure.step("Получить время претензии");
        return MainHelper.Text.getText(onView(withId(R.id.plan_time_text_view)));
    }

    public static void checkThatFirstFiveClaimsHaveOpenStatus() throws InterruptedException {
        Allure.step("Проверка открытого статуса у заявок");
        // провека первых пяти заявок
        for (int claimPosition = 0; claimPosition < 3; claimPosition++) {
            onView(MainHelper.withIndex(withId(R.id.claim_list_card), claimPosition)).perform(click());
            MainHelper.elementWaiting(withId(R.id.status_icon_image_view), 3000);
            checkClaimStatus("Open");
            Espresso.pressBack();
        }
//        // скрол до 3й заявки
        onView(MainHelper.withIndex(withId(R.id.claim_list_card), 2)).perform(click());
        MainHelper.elementWaiting(withId(R.id.status_icon_image_view), 3000);
        checkClaimStatus("Open");
        Espresso.pressBack();
//        // проверка 4й заявки
        onView(MainHelper.withIndex(withId(R.id.claim_list_card), 3)).perform(click());
        MainHelper.elementWaiting(withId(R.id.status_icon_image_view), 3000);
        checkClaimStatus("Open");
        Espresso.pressBack();
//        // повторный скрол до 3й заявки
        onView(allOf(withId(R.id.claim_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(2, swipeUp()));
        // проверка 5й заявки
        onView(withId(R.id.claim_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(4),click());
        MainHelper.elementWaiting(withId(R.id.status_icon_image_view), 3000);
        checkClaimStatus("Open");
        Espresso.pressBack();
    }

}
