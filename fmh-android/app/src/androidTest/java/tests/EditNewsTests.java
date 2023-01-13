package tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static screenElements.NewsCreationAndEditingScreen.publicationDateTextInputOfNews;
import static screenElements.NewsCreationAndEditingScreen.statusOfNewsSwitcher;
import static screenElements.NewsScreen.editNewsButton;


import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

import additional.MainHelper;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import screenElements.NewsCreationAndEditingScreen;
import screenElements.NewsScreen;
import steps.AuthorizationSteps;
import steps.ControlPanelSteps;
import steps.NewsCreationAndEditingSteps;
import steps.NewsSteps;

import ru.iteco.fmhandroid.R;

@RunWith(AllureAndroidJUnit4.class)
public class EditNewsTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        Thread.sleep(7000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.logIn("login2", "password2");
    }

    @Test // в одиночку проходит
    @DisplayName("Создание новости с активным статусом")
    public void createNewsWithActiveStatus() throws InterruptedException {
        Allure.step("Создание новости с активным статусом");
        // общие параметры для создания/редактирования новости
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String category = "no";
        String title = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        // параметры новости
        String chosenCategory = "Зарплата";
        String description = "Description";
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.initiateTheCreationOfNews();
        NewsCreationAndEditingSteps.fillInTheNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsCreationAndEditingSteps.saveNews();
    }

    @Test // не стабилен из-за проверки по title в методе checkNewsStatus
    @DisplayName("Создание новости с НЕактивным статусом")
    public void createNewsWithNotActiveStatus() throws InterruptedException {
        Allure.step("Создание заявки с НЕактивным статусом");
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        // параметры новости (должны совпадать с параметрами пердварительно созданной новости!!!!!)
        String chosenCategory = "Зарплата";
        String description = "Description";
        String finalStatus = "Not active";
        createNewsWithActiveStatus();
        ControlPanelSteps.goToNewsBlock();
        // проверяем,что новость, действительно, создана
        NewsSteps.checkNewsData(chosenCategory, description);
        // убеждаемся, что для изменения статуса выбрана именно ранее созданная новость
        NewsCreationAndEditingSteps.checkNewsInEditMode(chosenCategory, currentDate, description);
        // изменение статуса
        NewsCreationAndEditingSteps.changeNewsStatus();
        NewsCreationAndEditingSteps.saveNews();
        Thread.sleep(5000);
        ControlPanelSteps.goToNewsBlock();
        // проверка, что новость имеет статус "Не активна"
        NewsSteps.checkNewsStatus(chosenCategory,currentDate, finalStatus);
    }

    @Test // Удаление убрал, иначе не пройдет последний тест
    @DisplayName("Редактирование новости при заполнении всех полей валидными данными (кирилические символы, текущая дата, текущее время в формате циферблата)")
    public void editNewsWithValidData() throws InterruptedException {
        // общие параметры для создания/редактирования новости
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String category = "no";
        String title = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        // параметры старой новости
        String chosenCategory = "Зарплата";
        String description = "Description";
        // параметры для редактирования новости
        String newChosenCategory = "Массаж";
        String newDescription = "New description";
        // создаем новость
        createNewsWithActiveStatus();
        ControlPanelSteps.goToNewsBlock();
        // проверяем,что новость, действительно, создана
        NewsSteps.checkNewsData(chosenCategory, description);
        // редактирование новости
        NewsCreationAndEditingSteps.fillInTheNewsFields(emptyCategory, withCategoryChoice, newChosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, newDescription);
        NewsCreationAndEditingSteps.saveNews();
        ControlPanelSteps.goToNewsBlock();
        // проверяем,что новость, действительно, отредактирована (данные обновились)
        NewsSteps.checkFirstNewsDataAfterEdit(newChosenCategory, newDescription, currentDate);
        Thread.sleep(3000);
        editNewsButton.perform(click());
        // удаление новости
//        NewsSteps.deleteNews(newChosenCategory);
    }

    @Test  // Удаление убрал, иначе не пройдет последний тест
    @DisplayName("Изменение статуса с \"Активна\" на \"Не активна\" при редактировании новости")
    public void shouldChangeNewsStatusToNotActive() throws InterruptedException {
        // общие параметры для редактирования новости
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        // параметры новости (должны совпадать с параметрами пердварительно созданной новости!!!!!)
        String chosenCategory = "Зарплата";
        String description = "Description";
        String finalStatus = "Not active";
        // создаем новость
        createNewsWithActiveStatus();
        ControlPanelSteps.goToNewsBlock();
        // проверяем,что новость, действительно, создана
        NewsSteps.checkNewsData(chosenCategory, description);
        // убеждаемся, что для изменения статуса выбрана именно ранее созданная новость
        NewsCreationAndEditingSteps.checkNewsInEditMode(chosenCategory, currentDate, description);
        // изменение статуса
        NewsCreationAndEditingSteps.changeNewsStatus();
        NewsCreationAndEditingSteps.saveNews();
        Thread.sleep(5000);
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.checkNewsStatus(chosenCategory, currentDate, finalStatus);
        NewsCreationAndEditingSteps.saveNews();
        // удаление новости
//        NewsSteps.deleteNews(chosenCategory);
    }

    @Test // Нестабильный тест (последнюю часть кода не получилось скрыть в методе т.к. не находится нужный локатор)
    @DisplayName("Изменение статуса с \"Не активна\" на \"Активна\" при редактировании новости")
    public void shouldChangeNewsStatusToActive() throws InterruptedException {
        String finalStatus = "Active";
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        createNewsWithNotActiveStatus();
        NewsCreationAndEditingSteps.saveNews();
        ControlPanelSteps.goToNewsBlock();
        editNewsButton.perform(click());
        Thread.sleep(3000);
//        lastNewsItemButton.perform(click());
        onView(MainHelper.withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click());
        Thread.sleep(3000);
        NewsCreationAndEditingSteps.changeNewsStatus();
        NewsCreationAndEditingSteps.saveNews();
        ControlPanelSteps.goToNewsBlock();
        NewsScreen.editNewsButton.perform(click());
//        editLastNewsWithFinalStatusAndCurrentDate(finalStatus,currentDate);
        onView(MainHelper.withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click());
        NewsCreationAndEditingScreen.titleOfEditingNewsWindow.check(matches(isDisplayed()));
        // проверяем статус
        statusOfNewsSwitcher.check(matches(withText(finalStatus)));
        // на всякий случай проверяем дату создания и публикации (текущая)
        publicationDateTextInputOfNews.check(matches(withText(currentDate)));

    }

}

