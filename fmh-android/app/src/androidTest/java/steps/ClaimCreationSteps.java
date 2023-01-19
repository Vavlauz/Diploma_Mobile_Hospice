package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;

import additional.ExampleOfClaims;
import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import screenElements.ClaimCreationAndEditingScreen;
import screenElements.ClaimsScreen;

public class ClaimCreationSteps {

    public enum Claim {

    }

    public static void fillInTheClaimFields(ExampleOfClaims exampleOfClaims) throws InterruptedException {
        Allure.step("Заполнение полей при создании заявки");
        // заполнение поля "Тема"
        if (exampleOfClaims.title != null) {
            ClaimCreationAndEditingScreen.titleTextInputOfClaim.perform(replaceText(exampleOfClaims.title));
            MainHelper.elementWaiting(withText(exampleOfClaims.title), 3000);
        }
        // заполнение поля "Исполнитель"
            if (exampleOfClaims.chosenExecutor != null) {
                ClaimCreationAndEditingScreen.executorTextInput.perform(replaceText(exampleOfClaims.chosenExecutor));
            } else {
                ClaimCreationAndEditingScreen.executorTextInput.perform(replaceText(exampleOfClaims.executor));
                ClaimCreationAndEditingScreen.executorTextInput.check(matches(withText(exampleOfClaims.executor)));
            }

        // заполнение поля "Дата"
        if (exampleOfClaims.emptyDate == "no") {
            ClaimCreationAndEditingScreen.dateInPlanOfClaim.perform(click());
            ClaimCreationAndEditingScreen.okButton.perform(click());
        }
        // заполнение поля "Время"
        if (exampleOfClaims.emptyTime == "no") {
            if (exampleOfClaims.withDialPadOrTextInput == "dial") {
                ClaimCreationAndEditingScreen.timeInPlanOfClaim.perform(click());
                if (exampleOfClaims.saveOrCancelTime == "save") {
                    ClaimCreationAndEditingScreen.okButton.perform(click());
                } else {
                    ClaimCreationAndEditingScreen.cancelButton.perform(click());
                }
            } else {
                ClaimCreationAndEditingScreen.timeInPlanOfClaim.perform(click());
                ClaimCreationAndEditingScreen.buttonForSwitchToTextInput.perform(click());
                onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(6, 45));
                ClaimCreationAndEditingScreen.okButton.perform(click());
                ClaimCreationAndEditingScreen.timeInPlanOfClaim.check(matches(withText("06:45")));
            }
        }
        // заполнение поля "Описание"
        if (exampleOfClaims.description != null) {
            ClaimCreationAndEditingScreen.descriptionTextInputOfClaim.perform(replaceText(exampleOfClaims.description));
            ClaimCreationAndEditingScreen.descriptionTextInputOfClaim.check(matches(withText(exampleOfClaims.description)));
        }
    }

    public static void timeInput(String hours, String minutes) {
        Allure.step("Ввод времени вручную");
        ClaimCreationAndEditingScreen.timeInPlanOfClaim.perform(click());
        ClaimCreationAndEditingScreen.buttonForSwitchToTextInput.perform(click());
        onView(MainHelper.withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 0)).perform(replaceText(hours));
        onView(MainHelper.withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 1)).perform(replaceText(minutes));
        ClaimCreationAndEditingScreen.okButton.perform(click());
    }

    public static void checkMessageOfTimeInputError() {
        Allure.step("Проверка, что появляется предупреждение о необходимости заполнить поля времени валидными данными");
        onView(withText("Enter a valid time")).check(matches(isDisplayed()));
    }

    public static void saveClaim() {
        Allure.step("Сохранить заявку");
        ClaimCreationAndEditingScreen.saveButtonOfClaim.perform(click());
        MainHelper.elementWaiting(withText("Claims"), 3000);
    }

    public static void cancelSavingOfClaim() {
        Allure.step("Отмена сохранения заявки");
        ClaimCreationAndEditingScreen.cancelButtonOfClaim.perform(click());
        ClaimCreationAndEditingScreen.okButton.perform(click());
        ClaimsScreen.titleOfClaimsBlock.check(matches(isDisplayed()));
    }

    public static void checkMessageThatFieldsShouldBeFilled(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Fill empty fields")));
    }
}
