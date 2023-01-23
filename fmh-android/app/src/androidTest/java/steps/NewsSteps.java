package steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static screenElements.NewsCreationAndEditingScreen.publicationDateTextInputOfNews;
import static screenElements.NewsCreationAndEditingScreen.statusOfNewsSwitcher;
import static screenElements.NewsScreen.deleteFirstNewsButton;
import static screenElements.NewsScreen.lastNewsItemButton;
import static screenElements.NewsScreen.openFirstNewsInNewsBlock;

import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;
import screenElements.NewsCreationAndEditingScreen;
import screenElements.NewsScreen;

public class NewsSteps {

    public static void initiateTheCreationOfNews() {
        Allure.step("Начинаем создавать новость (переход к созданию новости)");
        NewsScreen.editNewsButton.perform(click());
        NewsScreen.addNewsButton.check(matches(isDisplayed()));
        NewsScreen.addNewsButton.perform(click());
        NewsCreationAndEditingScreen.titleOfNewsCreatingWindow.check(matches(isDisplayed()));
    }

    public static void initiateNewsEditing(String title) {
        Allure.step("Переход к редактированию ранее созданной новости");
        goToEditingModeForNews();
    }

    public static void checkNewsData(String title, String description) {
        Allure.step("Убеждаемся, что соданная новость содержит ранее введенные данные");
        goToEditingModeForNews();
        MainHelper.elementWaiting(withText(title + "2"), 3000);
        MainHelper.elementWaiting(withText(description), 3000);

    }

    public static void checkFirstNewsDataAfterEdit(String category, String description, String currentDate) {
        Allure.step("Провека первой новости после редактирования");
        NewsScreen.firstCardNews.perform(click());
        NewsScreen.firstNewsItemTitle.check(matches(withText(category + "2")));
        NewsScreen.firstNewsItemDescription.check(matches(withText(description)));
        NewsScreen.firstNewsItemDate.check(matches(withText(currentDate)));
    }

    public static void checkThatNewsDoesNotExist(String title, String description) {
        Allure.step("Проверка того, что новость не существует в блоке новостей");
        NewsScreen.editNewsButton.perform(click());
        NewsScreen.firstCardNews.perform(click());
        NewsScreen.firstNewsItemTitle.check(matches(not(withText(title + "2"))));
        NewsScreen.firstNewsItemDescription.check(matches(not(withText(description))));
    }

    public static void checkThatNewsDoesNotExistInNewsBlockWhenItHasNotActiveStatus(String category, String description) {
        Allure.step("Проверка того, что не активная новость не видна в блоке новостей ");
        NewsScreen.firstCardNews.perform(click());
        NewsScreen.firstNewsItemTitle.check(matches(not(withText(category))));
        NewsScreen.firstNewsItemDescription.check(matches(not(withText(description))));
    }

    public static void deleteNews(String title) {
        Allure.step("Удалить ранее созданную новость");
        MainHelper.elementWaiting(withText(title + "2"), 3000);
        deleteFirstNewsButton.perform(click());
        NewsScreen.okButton.check(matches(isDisplayed()));
        // подтверждаем удаление новости
        NewsScreen.okButton.perform(click());
        // проверка возвращения к новостям (видна кнопка добавления новости)
        NewsScreen.addNewsButton.check(matches(isDisplayed()));
    }

    //(Не стал включать в этот метод проверку title, т.к. приходится менять значение с каждым новым тестом)
    public static void checkNewsStatus(String title, String currentDate, String status) {
        Allure.step("Проверить статус новости");
        NewsScreen.editNewsButton.perform(click());
        lastNewsItemButton.perform(click());
        NewsCreationAndEditingScreen.titleOfEditingNewsWindow.check(matches(isDisplayed()));
        MainHelper.elementWaiting(withText(title + "2"), 3000);
        // проверяем статус
        statusOfNewsSwitcher.check(matches(withText(status)));
        // на всякий случай проверяем дату создания и публикации (текущая)
        publicationDateTextInputOfNews.check(matches(withText(currentDate)));
    }

    public static void expandFirstNewsInNewsBlock() {
        Allure.step("Раскрыть первую заявку в блоке новостей");
        openFirstNewsInNewsBlock.perform(click());
        // проверка шага проводится в тесте
    }

    public static void checkBasicContentOfFirstExpandedNewsInNewsBlock() {
        Allure.step("Проверить базовое содержимое раскрытой первой новости в блоке новостей");
        NewsScreen.firstNewsItemTitle.check(matches(isDisplayed()));
        NewsScreen.firstNewsItemDescription.check(matches(isDisplayed()));
        NewsScreen.firstNewsItemDate.check(matches(isDisplayed()));
    }

    public static void checkThatThereAreThreeNewsItemsInTheNewsBlock() {
        Allure.step("Проверка, что три новости имеются в блоке новостей");
        NewsScreen.firstCardNews.check(matches(isDisplayed()));
        NewsScreen.firstNewsItemTitle.check(matches(isDisplayed()));
        NewsScreen.secondCardNews.check(matches(isDisplayed()));
        NewsScreen.secondNewsItemTitle.check(matches(isDisplayed()));
        NewsScreen.thirdCardNews.check(matches(isDisplayed()));
        NewsScreen.thirdNewsItemTitle.check(matches(isDisplayed()));
    }

    public static void goToEditingModeForNews() {
        NewsScreen.editNewsButton.perform(click());
        NewsScreen.editFirstNewsItemButton.perform(click());
        NewsCreationAndEditingScreen.titleOfEditingNewsWindow.check(matches(isDisplayed()));
    }

}
