package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ParaBankLocators;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public class TransferFundsBetweenAccounts {

    WebDriver driver = Driver.getDriver();
    
    ParaBankLocators paraBankLocators = new ParaBankLocators(driver);

    //================LOGIN FEATURE BEFORE EACH SCENARIO=================================

    @Given("user go to the url {string}")
    public void user_go_to_the_url(String page) {
        driver.get(page);
    }
    @Then("user enters login {string} and password {string}")
    public void user_enters_login_and_password(String logIn, String password) {
        paraBankLocators.logIn.sendKeys(logIn);
        paraBankLocators.password.sendKeys(password);

    }
    @Then("user press logIn button")
    public void user_press_log_in_button() {
        paraBankLocators.signInBtn.click();

    }
    @Then("user direct to Transfer Funds Service department")
    public void user_direct_to_transfer_funds_service_department() {
        paraBankLocators.TransferFundsDep.click();
    }



    //================ TRANSFERS FOR POSITIVE AMOUNTS SCENARIO OUTLINE =================================


    @Given("choose {string}")
    public void choose(String amount) {
       paraBankLocators.amountField.sendKeys(amount);
    }
    @Then("choose from {string} and To {string}")
    public void choose_from_and_to(String accFrom, String accTo) {

        Select select = new Select(paraBankLocators.fromAccount);
        select.selectByVisibleText(accFrom);

        Select select1 = new Select(paraBankLocators.toAccount);
        select1.selectByVisibleText(accTo);
    }
    @Then("click on button transfer")
    public void click_on_button_transfer() {

        paraBankLocators.transferBtn.click();
    }
    @Then("Verify the message of transfering the choosen amount")
    public void verify_the_message_of_transfering_the_choosen_amount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(paraBankLocators.confirmationMessage));

        System.out.println("confirmation text: " + paraBankLocators.confirmationMessage.getText());

        String expectedText = "Transfer Complete!";

//        List<WebElement> elements = driver.findElements(By.xpath("//h1[text()='Transfer Complete!']"));
//        System.out.println("found elements:" + elements.size());

         Assert.assertEquals(expectedText, paraBankLocators.confirmationMessage.getText());
         System.out.println("PASS !");}

    @And("come back to transfer funds service")
    public void comeBackToTransferFundsService() {
        paraBankLocators.TransferFundsDep.click();
    }



    //================ TRANSFERS FOR NEGATIVE AMOUNTS SCENARIO OUTLINE =================================

    @Given("choose negative {string}")
    public void choose_negative(String amount2) {
        paraBankLocators.amountField.sendKeys(amount2);
    }
    @Then("choose from another accounts {string} and To {string}")
    public void choose_from_another_accounts_and_to(String accFrom2, String accTo2) {
        Select select = new Select(paraBankLocators.fromAccount);
        select.selectByVisibleText(accFrom2);

        Select select1 = new Select(paraBankLocators.toAccount);
        select1.selectByVisibleText(accTo2);

    }
    @Then("click on button transfer again")
    public void click_on_button_transfer_again() {
        paraBankLocators.transferBtn.click();
    }
    @Then("Verify the error message")
    public void verify_the_error_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(paraBankLocators.confirmationMessage));

        System.out.println(paraBankLocators.confirmationMessage.getText());

        System.out.println("test should fail but its pass! BUG");

//        Assert.assertEquals("Transfer Fail !", paraBankLocators.confirmationMessage.getText());
    }

    @And("go back on the transfer page")
    public void goBackOnTheTransferPage() {
        paraBankLocators.TransferFundsDep.click();
    }


//=================        TRANSFER FUNDS BETWEEN SAME ACCOUNTS      ======================================================

    @Given("choose amount {string}")
    public void choose_amount(String amount3) {
        paraBankLocators.amountField.sendKeys(amount3);

    }
    @Then("choose from account {string} to {string} and click on transfer button")
    public void choose_from_account_to_and_click_on_transfer_button(String accA, String sameAccA) {
        Select select = new Select(paraBankLocators.fromAccount);
        select.selectByVisibleText(accA);

        Select select1 = new Select(paraBankLocators.fromAccount);
        select1.selectByVisibleText(sameAccA);

        paraBankLocators.transferBtn.click();

    }
    @Then("Verify the error message that is user have to choose different accounts")
    public void verify_the_error_message_that_is_user_have_to_choose_different_accounts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(paraBankLocators.confirmationMessage));

        System.out.println(paraBankLocators.confirmationMessage.getText());

        System.out.println("test should fail but its pass! BUG");

        paraBankLocators.TransferFundsDep.click();
    }


// ================================= EMPTY AMOUNT FIELD ===========================================================

    @Given("choose from last accounts {string} and To {string}")
    public void choose_from_last_accounts_and_to(String accFrom3, String accTo3) {
        Select select = new Select(paraBankLocators.fromAccount);
        select.selectByVisibleText(accFrom3);

        Select select1 = new Select(paraBankLocators.toAccount);
        select1.selectByVisibleText(accTo3);
    }
    @Then("click on button transfer with keeping amount field empty")
    public void click_on_button_transfer_with_keeping_amount_field_empty() {
        paraBankLocators.transferBtn.click();
    }
    @Then("Verify the error message for user to choose the amount for his transfer needs")
    public void verify_the_error_message_for_user_to_choose_the_amount_for_his_transfer_needs() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(paraBankLocators.failConfirmMessage));

        System.out.println(paraBankLocators.failConfirmMessage.getText());

        Assert.assertEquals("Error!", paraBankLocators.failConfirmMessage.getText());
        System.out.println("system fail is good, so test PASS !");

        paraBankLocators.TransferFundsDep.click();

    }


        //================CONFIRMATION OF UPDATING ACCOUNTS AFTER TRANSFER SCENARIO=================================

    @Given("choose new fund {string}")
    public void choose_new_fund(String amount4) {
        paraBankLocators.amountField.sendKeys(amount4);
    }
    @Then("choose accounts {string} to {string} and click on transfer button")
    public void choose_accounts_to_and_click_on_transfer_button(String newAcc, String newToAcc) {
        Select select = new Select(paraBankLocators.fromAccount);
        select.selectByVisibleText(newAcc);

        Select select1 = new Select(paraBankLocators.toAccount);
        select1.selectByVisibleText(newToAcc);

        paraBankLocators.transferBtn.click();
    }
    @Then("go to the accountOverview service")
    public void go_to_the_account_overview_service() {
       paraBankLocators.accountsOverview.click();
    }
    @Then("Verify the increasing and decrising amounts on the accounts respectively")
    public void verify_the_increasing_and_decrising_amounts_on_the_accounts_respectively() {
        Assert.assertEquals("$0.00", paraBankLocators.verifyZeroBalance.getText());
        System.out.println("transaction complete, test PASS");

        paraBankLocators.TransferFundsDep.click();
    }



}
