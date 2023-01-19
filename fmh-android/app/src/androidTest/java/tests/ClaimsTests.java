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
import steps.ClaimsSteps;
import steps.ControlPanelSteps;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimsTests {

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

    @Test
    @DisplayName("Наличие всех заявок в блоке \"Заявки\" (минимум 3)")
    public void shouldBeThreeClaimsInClaimsBlock() {
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.checkThatThereAreThreeClaimsItemsInTheClaimsBlock();
    }

    @Test
    @DisplayName("Полнота информации заявки (в свернутом состоянии) в блоке \"Заявки\"")
    public void shouldBeFullContentOfNotExpandedClaimInClaimsBlock() {
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.checkContentOfFirstClaimInClaimsBlockRolledUp();
    }

    @Test
    @DisplayName("Полнота информации раскрытой заявки в блоке \"Заявки\"")
    public void shouldBeFullContentOfExpandedClaimInClaimsBlock() {
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.goToFirstClaimFromClaimsBlock();
        ClaimsSteps.checkContentOfFirstClaimInClaimsBlock();
    }

}
