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

import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import screenElements.ClaimCreationAndEditingScreen;
import screenElements.ClaimsScreen;

public class ClaimCreationSteps {

    public static void fillInTheClaimFields(String emptyTitle, String title, String emptyExecutor, String choiceOfExecutor, String chosenExecutor, String executor, String emptyDate, String emptyTime, String withDialPadOrTextInput, String saveOrCancelTime, String emptyDescription, String description) throws InterruptedException {
        Allure.step("Заполнение полей при создании заявки");
        // определяется позиция дочернего элемента
//        Integer executorPosition = null;
//        if (chosenExecutor == "Smirnov Petr Petrovich") {
//            executorPosition = 0;
//        } else if (chosenExecutor == "Ivanov Danil Danilovich") {
//            executorPosition = 1;
//        } else if (chosenExecutor == "Petrov Egor Egorovich") {
//            executorPosition = 2;
//        } else if (chosenExecutor == "Sidorov Dmitri Dmitrievich") {
//            executorPosition = 3;
//        } else if (chosenExecutor == "Uzhakin Vadim Vladimirovich") {
//            executorPosition = 4;
//        } else if (chosenExecutor == "Netology Diplom QAMID") {
//            executorPosition = 5;
//        }
        // заполнение поля "Тема"
        if (emptyTitle == "no") {
            ClaimCreationAndEditingScreen.titleTextInputOfClaim.perform(replaceText(title));
            Thread.sleep(2000);
//            ClaimCreationAndEditingScreen.titleTextInputOfClaim.check(matches(withText(title)));
        }
        // заполнение поля "Исполнитель"
        if (emptyExecutor == "no") {
            if (choiceOfExecutor == "yes") {
//                ClaimCreationAndEditingScreen.buttonForShowingDropdownMenu.perform(click());
                ClaimCreationAndEditingScreen.executorTextInput.perform(replaceText(chosenExecutor));
                Thread.sleep(2000);
//                Espresso.onData(Matchers.anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(executorPosition).perform(ViewActions.click());
            } else {
                ClaimCreationAndEditingScreen.executorTextInput.perform(replaceText(executor));
                Thread.sleep(2000);
                ClaimCreationAndEditingScreen.executorTextInput.check(matches(withText(executor)));
            }
        }
        // заполнение поля "Дата"
        if (emptyDate == "no") {
            ClaimCreationAndEditingScreen.dateInPlanOfClaim.perform(click());
            Thread.sleep(2000);
            ClaimCreationAndEditingScreen.okButton.perform(click());
        }
        // заполнение поля "Время"
        if (emptyTime == "no") {
            if (withDialPadOrTextInput == "dial") {
                ClaimCreationAndEditingScreen.timeInPlanOfClaim.perform(click());
                if (saveOrCancelTime == "save") {
                    ClaimCreationAndEditingScreen.okButton.perform(click());
                } else {
                    ClaimCreationAndEditingScreen.cancelButton.perform(click());
                }
            } else {
                ClaimCreationAndEditingScreen.timeInPlanOfClaim.perform(click());
                Thread.sleep(2000);
                ClaimCreationAndEditingScreen.buttonForSwitchToTextInput.perform(click());
                Thread.sleep(2000);
                onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(6, 45));
                Thread.sleep(2000);
                ClaimCreationAndEditingScreen.okButton.perform(click());
                Thread.sleep(2000);
                ClaimCreationAndEditingScreen.timeInPlanOfClaim.check(matches(withText("06:45")));
            }
        }
        // заполнение поля "Описание"
        if (emptyDescription == "no") {
            ClaimCreationAndEditingScreen.descriptionTextInputOfClaim.perform(replaceText(description));
            Thread.sleep(2000);
            ClaimCreationAndEditingScreen.descriptionTextInputOfClaim.check(matches(withText(description)));
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

    public static void saveClaim() throws InterruptedException {
        Allure.step("Сохранить заявку");
        ClaimCreationAndEditingScreen.saveButtonOfClaim.perform(click());
        Thread.sleep(2000);
//        ClaimsScreen.titleOfClaimsBlock.check(matches(isDisplayed()));
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
