package tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import steps.AuthorizationSteps;
import steps.ClaimsSteps;
import steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimsFilteringTests {

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
    @DisplayName("Выбран статус \"Открыта\" при фильтрации заявок")
    public void openStatusIsChosenDuringClaimsFiltering() throws InterruptedException {
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateClaimFiltering();
        ClaimsSteps.сhooseOnlyOpenStatusIfOpenAndInProgressStatusesAreChosenInitially();
        ClaimsSteps.checkThatFirstFiveClaimsHaveOpenStatus();
    }

}
