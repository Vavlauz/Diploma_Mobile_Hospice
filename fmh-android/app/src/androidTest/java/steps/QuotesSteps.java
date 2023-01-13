package steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;
import screenElements.QuotesScreen;

public class QuotesSteps {

    public static void openOrCloseFirstQuote() {
        Allure.step("Раскрыть/свернуть первую цитату");
        QuotesScreen.openOrCloseFirstQuoteButton.perform(click());
        // проверка шага не предусмотрена (результат зависит от исходногго состояния)
    }

    public static void checkThatFirstQuoteContentIsFull() {
        Allure.step("Проверить содержимое первой цитаты");
        QuotesScreen.firstMissionTitleValue.check(matches(isDisplayed()));
        QuotesScreen.firstDescriptionOfQuoteValue.check(matches(isDisplayed()));
    }

    public static void findQuoteWith(String title) {
        Allure.step("Поиск цитаты с заголовком");
        MainHelper.isDisplayedWithSwipe(onView(withText(title)), 3, true);
    }

}
