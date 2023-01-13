package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screenElements.ClaimScreen;
import screenElements.MainScreen;

public class MainSteps {

    public static void goToFirstClaimFromMainBlock() {
        Allure.step("Переход к первой заявке из главного блока");
        MainScreen.firstClaim.perform(click());
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
    }

    public static void checkContentOfFirstClaimInMainBlock() {
        Allure.step("Проверка видимости содержимого первой заявки в главном блоке: описание, исполнитель, плановые дата и время");
        MainScreen.descriptionOfFirstClaim.check(matches(isDisplayed()));
        MainScreen.executorOfFirstClaim.check(matches(isDisplayed()));
        MainScreen.planeDateOfFirstClaim.check(matches(isDisplayed()));
        MainScreen.planeTimeOfFirstClaim.check(matches(isDisplayed()));
    }

    public static void expandFirstNewsInMainBlock() {
        Allure.step("Раскрыть первую новость в главном блоке");
        MainScreen.buttonToExpandFirstNews.perform(click());
        // проверка заложена в следующем методе
    }

    public static void checkDescriptionOfFirstNews() {
        Allure.step("Проверка видимости описания первой новости в главном блоке");
        MainScreen.descriptionOfFirstNews.check(matches(isDisplayed()));
    }

    public static void checkContentOfNotExpandedFirstNewsInMainBlock() {
        Allure.step("Проверка видимости содержимого не раскрытой первой новости в главном блоке");
        MainScreen.titleOfFirstNews.check(matches(isDisplayed()));
        MainScreen.dateOfFirstNews.check(matches(isDisplayed()));
    }

    public static void checkIfNewsPartitionExists() {
        Allure.step("Проверка видимости раздела новостей в главном блоке");
        MainScreen.containerListForNews.check(matches(isDisplayed()));
        onView(withText("News")).check(matches(isDisplayed()));
    }

    public static void checkIfClaimsPartitionExists() {
        Allure.step("Проверка видимости раздела заявок в главном блоке");
        MainScreen.containerListForClaims.check(matches(isDisplayed()));
        onView(withText("Claims")).check(matches(isDisplayed()));
    }

    public static void expandOrHideNewsPart() {
        Allure.step("Свернуть/раскрыть раздел с новостями");
        MainScreen.buttonToExpandOrHideNewsPart.perform(click());
        // проверка шага невозможна (результат разный в зависимости от предыдущего состояния)
    }

    public static void claimTitleIsDisplayedWithSwipe(Integer claimPosition) {
        Allure.step("Проверка, что виден заголовок заявки");
        onView((withId(R.id.claim_list_recycler_view))).perform(actionOnItemAtPosition(claimPosition, swipeUp()));
        onView(MainHelper.withIndex(withId(R.id.description_material_text_view), claimPosition)).check(matches(isDisplayed()));
    }
}
