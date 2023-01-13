package steps;

import static org.hamcrest.core.AllOf.allOf;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static additional.MainHelper.isDisplayedWithSwipe;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screenElements.ClaimScreen;
import screenElements.CommentScreen;

public class ClaimSteps {

    public static void checkBasicElementsOfClaim() {
        Allure.step("Проверка, основных элементов заявки: тема, исполнитель, плановая дата");
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
        ClaimScreen.executorTextOfClaim.check(matches(isDisplayed()));
        ClaimScreen.planeDateOfClaim.check(matches(isDisplayed()));
    }

    public static void checkFullContentOfExpandedClaim() {
        Allure.step("Проверка, всех элементов заявки");
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
        ClaimScreen.executorTextOfClaim.check(matches(isDisplayed()));
        ClaimScreen.planeDateOfClaim.check(matches(isDisplayed()));
        ClaimScreen.planTimeOfClaim.check(matches(isDisplayed()));
        ClaimScreen.statusOfClaim.check(matches(isDisplayed()));
        ClaimScreen.descriptionOfClaim.check(matches(isDisplayed()));
        ClaimScreen.authorOfClaim.check(matches(isDisplayed()));
        ClaimScreen.creationDateOfClaim.check(matches(isDisplayed()));
        ClaimScreen.creationTimeOfClaim.check(matches(isDisplayed()));
        ClaimScreen.firstCommentatorNameOfClaim.check(matches(isDisplayed()));
        ClaimScreen.firstCommentDateOfClaim.check(matches(isDisplayed()));
        ClaimScreen.firstCommentTimeOfClaim.check(matches(isDisplayed()));
    }

    public static void scrollToLastComment() {
        Allure.step("Скрол к последнему комментарию (которого НЕТ)");
        isDisplayedWithSwipe(onView(withText("hjdwdwqfrgDEFEyveEUBU")), 4, true);
        ClaimScreen.buttonToAddComment.check(matches(isDisplayed()));
    }

    public static void initiateCommentCreation() {
        Allure.step("Нажать на кнопу добавления пустого комментария");
        ClaimScreen.buttonToAddComment.perform(click());
//        CommentScreen.saveButton.check(matches(isDisplayed()));
    }

    public static void isCommentDisplayed(String comment) {
        Allure.step("Поиск комментария");
        onView(withText(comment)).check(matches(isDisplayed()));
    }

    public static void commentDoesNotExist(String comment) {
        Allure.step("Поиск комментария");
        onView(withText(comment)).check(doesNotExist());
    }

    public static void initiateCommentEditing(String comment) {
        Allure.step("Переход к редактированию комментария");
        onView(allOf(withId(R.id.edit_comment_image_button), hasSibling(withText(comment)))).perform(click());
        CommentScreen.saveButton.check(matches(isDisplayed()));
    }

    public static void checkThatTheExecutorIsNotAssigned() {
        Allure.step("Проверка, что исполнитель не назначен");
        onView(withText("NOT ASSIGNED")).check(matches(isDisplayed()));
    }

}

