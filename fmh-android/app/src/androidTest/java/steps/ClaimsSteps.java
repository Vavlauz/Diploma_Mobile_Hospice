package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.Espresso;

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
        ClaimCreationAndEditingScreen.titleOfClaimsCreatingPage.check(matches(isDisplayed()));
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
        ClaimsScreen.firstClaimTopicInClaimsBlock.check(matches(isDisplayed()));
        ClaimsScreen.executorNameOfFirstClaim.check(matches(isDisplayed()));
        ClaimsScreen.planDateOfFirstClaim.check(matches(isDisplayed()));
        ClaimsScreen.planTimeOfFirstClaim.check(matches(isDisplayed()));
    }

    public static void goToFirstClaimFromClaimsBlock() {
        Allure.step("Переход к первой заявке из блока заявок");
        ClaimsScreen.firstClaimCard.perform(click());
//        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
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

    public static void checkThatFirstFiveClaimsHaveOpenStatus() throws InterruptedException {
        Allure.step("Проверка открытого статуса у заявок");
        // провека первых трех заявок
        for (int claimPosition = 0; claimPosition < 3; claimPosition++) {
            onView(MainHelper.withIndex(withId(R.id.claim_list_card), claimPosition)).perform(click());
            Thread.sleep(5000);
            ClaimScreen.openStatus.check(matches(isDisplayed()));
            Espresso.pressBack();
            Thread.sleep(5000);
        }
        // скрол до 3й заявки
        onView(allOf(withId(R.id.claim_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(2, swipeUp()));
        // проверка 4й заявки
        onView(MainHelper.withIndex(withId(R.id.claim_list_card), 3)).perform(click());
        Thread.sleep(5000);
//        ClaimScreen.openStatus.check(matches(isDisplayed()));
        Espresso.pressBack();
        Thread.sleep(5000);
        // повторный скрол до 3й заявки
        onView(allOf(withId(R.id.claim_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(2, swipeUp()));
        // проверка 5й заявки
        onView(MainHelper.withIndex(withId(R.id.claim_list_card), 4)).perform(click());
        Thread.sleep(5000);
        ClaimScreen.openStatus.check(matches(isDisplayed()));
        Espresso.pressBack();
        Thread.sleep(5000);
    }

}
