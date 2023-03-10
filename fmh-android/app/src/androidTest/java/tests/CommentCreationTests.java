package tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import steps.AuthorizationSteps;
import steps.ClaimSteps;
import steps.ClaimsSteps;
import steps.CommentSteps;
import steps.ControlPanelSteps;

@RunWith(AllureAndroidJUnit4.class)
public class CommentCreationTests {

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

    @Test // тест проходит, но падает при запуске всех тестов,также нужно постоянно изменять comment,
         // чтобы он отличался от других
    @DisplayName("Добавление нового комментария заявки с введением валидных данных")
    public void shouldCreateCommentWithValidData() {
        String comment = "QA Midgdsr8";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.goToFirstClaimFromClaimsBlock();
        ClaimSteps.scrollToLastComment();
        ClaimSteps.initiateCommentCreation();
        CommentSteps.fillInTheCommentField(comment);
        CommentSteps.saveComment();
        ClaimSteps.scrollToLastComment();
        ClaimSteps.isCommentDisplayed(comment);
    }

    @Test // нестабильный тест при запуске всех тестов в эмуляторе (отдельно проходит)
    @DisplayName("Пустой ввод при добавлении нового комментария к заявке")
    public void shouldTryCreateCommentWithEmptyField() {
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.goToFirstClaimFromClaimsBlock();
        ClaimSteps.scrollToLastComment();
        ClaimSteps.initiateCommentCreation();
        CommentSteps.notSaveComment();
        CommentSteps.checkMessageThatFieldShouldBeFilled(activityTestRule);
    }

    @Test  // нестабильный тест при запуске всех тестов на эмуляторе (отдельно проходит),также нужно постоянно изменять comment,
    // чтобы он отличался от других
    @DisplayName("Отмена добавления нового комментария")
    public void shouldCancelCommentCreation() {
        String comment = "QA Midfrr12";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.goToFirstClaimFromClaimsBlock();
        ClaimSteps.scrollToLastComment();
        ClaimSteps.initiateCommentCreation();
        CommentSteps.fillInTheCommentField(comment);
        CommentSteps.cancelCommentCreation();
        ClaimSteps.scrollToLastComment();
        ClaimSteps.commentDoesNotExist(comment);
    }

}
