package tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import steps.CommentSteps;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import steps.AuthorizationSteps;
import steps.ClaimSteps;
import steps.ClaimsSteps;
import steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class CommentEditingTests {

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

    @Test // проходит на эмуляторе, но падает, при запуске всех тестов
    @DisplayName("Редактирование комментария в заявке при валидных данных (кириллические символы)")
    public void shouldEditCommentOfClaim() throws InterruptedException {
        String comment = "QA Midd1088";
        String commentForEditing = "QA Midd222s8";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.goToFirstClaimFromClaimsBlock();
        Thread.sleep(3000);
        // приступаем к созданию комментария
        ClaimSteps.scrollToLastComment();
        ClaimSteps.initiateCommentCreation();
        CommentSteps.fillInTheCommentField(comment);
        CommentSteps.saveComment();
        Thread.sleep(3000);
        ClaimSteps.scrollToLastComment();
        ClaimSteps.isCommentDisplayed(comment);
        // приступаем к редактированию комментария
        ClaimSteps.scrollToLastComment();
        ClaimSteps.initiateCommentEditing(comment);
        CommentSteps.fillInTheCommentField(commentForEditing);
        CommentSteps.saveComment();
        Thread.sleep(3000);
        ClaimSteps.scrollToLastComment();
        ClaimSteps.isCommentDisplayed(commentForEditing);
    }

}
