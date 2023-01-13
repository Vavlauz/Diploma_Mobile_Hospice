package steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import screenElements.AboutAppScreen;

public class AboutAppSteps {

    public static void checkThatAboutBlockContentIsFull() {
        Allure.step("Проверка, что в блоке О Хосписе полный контент");
        AboutAppScreen.versionValue.check(matches(isDisplayed()));
        AboutAppScreen.privacyPolicyValue.check(matches(isDisplayed()));
        AboutAppScreen.termsOfUseValue.check(matches(isDisplayed()));
        AboutAppScreen.infoLabel.check(matches(isDisplayed()));
    }

    public static void goToPrivacyPolicy() {
        Allure.step("Переход к политике конфиденциальности");
        AboutAppScreen.privacyPolicyValue.perform(click());
    }

    public static void goToTermsOfUse() {
        Allure.step("Переход к пользовательскому соглашению");
        AboutAppScreen.termsOfUseValue.perform(click());
    }

    public static void goBack() {
        Allure.step("Назад в главный блок");
        AboutAppScreen.goBackButton.perform(click());
    }

}
