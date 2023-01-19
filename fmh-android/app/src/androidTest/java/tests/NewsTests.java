package tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import additional.ExampleOfNews;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import steps.AuthorizationSteps;
import steps.ControlPanelSteps;
import steps.NewsCreationAndEditingSteps;
import steps.NewsSteps;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTests {

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
    @DisplayName("Наличие новостей в блоке \"Новости\" (минимум 3)")
    public void shouldBeThreeNewsInNewsBlock() {
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.checkThatThereAreThreeNewsItemsInTheNewsBlock();
    }

    @Test // в одиночку проходит
    @DisplayName("Полнота информации новостей (в развернутом состоянии) в блоке \"Новости\"")
    public void shouldBeFullContentOfFirstExpandedNewsInNewsBlock() {
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.expandFirstNewsInNewsBlock();
        NewsSteps.checkBasicContentOfFirstExpandedNewsInNewsBlock();
    }

    @Test // в одиночку проходит
    @DisplayName("Удаление новости")
    public void shouldDeleteNews() throws InterruptedException {
        String emptyCategory = "no";
        String choiceOfCategory = "yes";
        String chosenCategory = "Зарплата";
        String category = "no";
        String title = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String description = "New description";
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.initiateTheCreationOfNews();
        ExampleOfNews firstNew = new ExampleOfNews(emptyCategory,choiceOfCategory,chosenCategory,category,title,emptyDate,emptyTime,withDialPadOrTextInput,saveOrCancelTime,description);
        NewsCreationAndEditingSteps.fillInTheNewsFields(firstNew);
        NewsCreationAndEditingSteps.saveNews();
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.checkNewsData(chosenCategory, description);
        NewsCreationAndEditingSteps.saveNews();
        NewsSteps.deleteNews(chosenCategory);

    }
}

