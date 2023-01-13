package tests;

import androidx.test.espresso.NoMatchingViewException;

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

import androidx.test.rule.ActivityTestRule;

import ru.iteco.fmhandroid.ui.AppActivity;

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

    @Test // тест нестабильный при запуске всех тестов в эмуляторе (отдельно проходит)
    @DisplayName("Полнота информации заявок (в свернутом состоянии) в блоке \"Заявки\"")
    public void shouldBeFullContentOfNotExpandedClaimInClaimsBlock() throws InterruptedException {
        ControlPanelSteps.goToClaimsBlock();
        Thread.sleep(3000);
        ClaimsSteps.checkContentOfFirstClaimInClaimsBlock();
    }

    @Test
    @DisplayName("Полнота информации раскрытой заявки в блоке \"Заявки\"")
    public void shouldBeFullContentOfExpandedClaimInClaimsBlock() throws InterruptedException {
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.goToFirstClaimFromClaimsBlock();
        Thread.sleep(3000);
        ClaimSteps.checkFullContentOfExpandedClaim();
    }

}
