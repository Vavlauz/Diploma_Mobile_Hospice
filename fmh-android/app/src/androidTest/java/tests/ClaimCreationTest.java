package tests;

import static steps.ClaimCreationSteps.checkMessageOfTimeInputError;
import static steps.ClaimCreationSteps.checkMessageThatFieldsShouldBeFilled;
import static steps.ClaimCreationSteps.saveClaim;
import static steps.ClaimCreationSteps.timeInput;

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
import steps.ClaimCreationSteps;
import steps.ClaimSteps;
import steps.ClaimsSteps;
import steps.ControlPanelSteps;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimCreationTest {

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

    @Test // тест длительно выполняется из-за огромного количества заявок
    @DisplayName("Создание заявки при вводе валидных данных во все поля (кириллические символы, текущая дата и текущее время в формате циферблата)")
    public void shouldCreateClaimWithValidData() throws InterruptedException {
        String emptyTitle = "no";
        String title = "Diplom QAm1";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Netology Diplom QAMID";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimsSteps.checkCreatedClaimInClaimsBlock(title);
    }

    @Test // тест длительно выполняется из-за огромного количества заявок
    @DisplayName("Ввод 49 символов в поле \"Тема\" при создании заявки")
    public void shouldInput49SymbolsInTitleDuringClaimCreation() throws InterruptedException {
        String emptyTitle = "no";
        String title49 = "DiplomQADiplomQADiplomQADiplomQADiplomQADiplom007";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Netology Diplom QAMID";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title49, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimsSteps.checkCreatedClaimInClaimsBlock(title49);
    }

    @Test // тест длительно выполняется из-за огромного количества заявок
    @DisplayName("Ввод 50 символов в поле \"Тема\" при создании заявки")
    public void shouldInput50SymbolsInTitleDuringClaimCreation() throws InterruptedException {
        String emptyTitle = "no";
        String title = "DiplomQADiplomQADiplomQADiplomQADiplomQADiplom0004";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Netology Diplom QAMID";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimsSteps.checkCreatedClaimInClaimsBlock(title);
    }

    @Test // тест падает из-за того, что программа не может ввести 51 символ
    @DisplayName("Попытка ввода 51 символа в поле \"Тема\" при создании заявки")
    public void shouldInput51SymbolsInTitleDuringClaimCreation() throws InterruptedException {
        String emptyTitle = "no";
        String title51 = "DiplomQADiplomQADiplomQADiplomQADiplomQADiploM00051";
        String titleOfCreatedClaim = "DiplomQADiplomQADiplomQADiplomQADiplomQADiploM0005";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Ivanov Ivan Ivanovich";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title51, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimsSteps.checkCreatedClaimInClaimsBlock(titleOfCreatedClaim);
    }

    @Test
    @DisplayName("Пустой ввод в поле \"Тема\" при создании заявки")
    public void shouldTryCreateClaimWithEmptyTitle() throws InterruptedException {
        String emptyTitle = "yes";
        String title = "no";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Смирнов Петр Петрович";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimCreationSteps.checkMessageThatFieldsShouldBeFilled(activityTestRule);
    }

    @Test
    @DisplayName("Пустой ввод в поле \"Описание\" при создании заявки")
    public void shouldTryCreateClaimWithEmptyDescription() throws InterruptedException {
        String emptyTitle = "no";
        String title = "QAMIDK7814";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Смирнов Петр Петрович";
        String executor = "yes";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "yes";
        String description = "-";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        checkMessageThatFieldsShouldBeFilled(activityTestRule);
    }

    @Test
    @DisplayName("Отмена выбора даты при создании заявки")
    public void shouldTryCreateClaimWithEmptyDate() throws InterruptedException {
        String emptyTitle = "no";
        String title = "QAMIDK78lFky";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Иванов Данил Данилович";
        String executor = "yes";
        String emptyDate = "yes";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        saveClaim();
        checkMessageThatFieldsShouldBeFilled(activityTestRule);
    }

    @Test
    @DisplayName("Отмена выбора времени в разделе циферблат при создании заявки")
    public void shouldTryCreateClaimWithCancelSavingOfTime() throws InterruptedException {
        String emptyTitle = "no";
        String title = "QAMIDK78gFct";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Ivanov Danil Danilovich";
        String executor = "yes";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "cancel";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimCreationSteps.checkMessageThatFieldsShouldBeFilled(activityTestRule);

    }

    @Test // тест длительно выполняется из-за огромного количества заявок, лучше запускать в одиночку
    @DisplayName("Пустой ввод в поле \"Исполнитель\" при создании заявки")
    public void shouldTryCreateClaimWithEmptyExecutor() throws InterruptedException {
        String emptyTitle = "no";
        String title = "QAMID345Hpik";
        String emptyExecutor = "yes";
        String choiceOfExecutor = "no";
        String chosenExecutor = "no";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimsSteps.checkCreatedClaimInClaimsBlock(title);
        Thread.sleep(3000);
        ClaimsSteps.goToCreatedClaim(title);
        Thread.sleep(3000);
        ClaimSteps.checkThatTheExecutorIsNotAssigned();
    }

    @Test // тест длительно выполняется из-за огромного количества заявок
    @DisplayName("Ручной ввод часов и минут при при создании заявки")
    public void shouldCreateClaimWithManualTimeInput() throws InterruptedException {
        String emptyTitle = "no";
        String title = "QAMID10s2";
        String emptyExecutor = "no";
        String choiceOfExecutor = "yes";
        String chosenExecutor = "Netology Diplom QAMID";
        String executor = "yes";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "text input";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        // будет введено время: 06:45 (заложено в методе, также в методе есть проверка, что введено именно это время)
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, choiceOfExecutor, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimsSteps.checkCreatedClaimInClaimsBlock(title);
        ClaimsSteps.checkTimeOfCreatedClaimInClaimsBlock(title, "06:45");
    }

    @Test
    @DisplayName("Ввод > 24 часов в поле часы при создании заявки")
    public void shouldInputMoreThan24HoursWhenClaimIsBeingCreated() {
        String invalidHours = "76";
        String validMinutes = "23";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        timeInput(invalidHours, validMinutes);
        checkMessageOfTimeInputError();
    }

    @Test
    @DisplayName("Ввод >60 в поле минуты при создании заявки")
    public void shouldInputMoreThan60MinutesWhenClaimIsBeingCreated() {
        String validHours = "22";
        String invalidMinutes = "68";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        timeInput(validHours, invalidMinutes);
        checkMessageOfTimeInputError();
    }
}
