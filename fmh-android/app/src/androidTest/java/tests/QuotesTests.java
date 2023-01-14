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
import steps.ControlPanelSteps;
import steps.QuotesSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class QuotesTests {

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
    @DisplayName("Наличие цитат в блоке цитат о хосписе")
    public void quotesShouldBeVisibleInQuotesBlock() throws InterruptedException {
        String quoteTextTitle1 = "«Хоспис для меня - это то, каким должен быть мир.\"";
        String quoteTextTitle2 = "Хоспис в своем истинном понимании - это творчество";
        String quoteTextTitle3 = "“В хосписе не работают плохие люди” В.В. Миллионщикова\"";
        String quoteTextTitle4 = "«Хоспис – это философия, из которой следует сложнейшая наука медицинской помощи умирающим и искусство ухода, в котором сочетается компетентность и любовь» С. Сандерс";
        String quoteTextTitle5 = "Служение человеку с теплом, любовью и заботой";
        String quoteTextTitle6 = "\"Хоспис продлевает жизнь, дает надежду, утешение и поддержку.\"";
        String quoteTextTitle7 = "\"Двигатель хосписа - милосердие плюс профессионализм\"\n" +
                "А.В. Гнездилов, д.м.н., один из пионеров хосписного движения.";
        String quoteTextTitle8 = "Важен каждый!";
        ControlPanelSteps.goToQuotesBlock();
        QuotesSteps.findQuoteWith(quoteTextTitle1);
        Thread.sleep(3000);
        QuotesSteps.findQuoteWith(quoteTextTitle2);
        Thread.sleep(3000);
        QuotesSteps.findQuoteWith(quoteTextTitle3);
        Thread.sleep(3000);
        QuotesSteps.findQuoteWith(quoteTextTitle4);
        Thread.sleep(3000);
        QuotesSteps.findQuoteWith(quoteTextTitle5);
        Thread.sleep(3000);
        QuotesSteps.findQuoteWith(quoteTextTitle8);
        Thread.sleep(3000);
        QuotesSteps.findQuoteWith(quoteTextTitle7);
        Thread.sleep(3000);
        QuotesSteps.findQuoteWith(quoteTextTitle6);
    }

    @Test // в одиночку проходит
    @DisplayName("shouldBeFullContentInEachQuote")
    public void shouldBeFullContentInEachQuote() throws InterruptedException {
        ControlPanelSteps.goToQuotesBlock();
        QuotesSteps.openOrCloseFirstQuote();
        Thread.sleep(3000);
        QuotesSteps.checkThatFirstQuoteContentIsFull();
    }

}
