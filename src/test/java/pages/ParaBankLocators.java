package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParaBankLocators {

    public WebDriver driver;

    public ParaBankLocators(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@type='text']")
    public WebElement logIn;


    @FindBy(xpath = "//input[@type='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement signInBtn;

    @FindBy(xpath = "//a[text()='Transfer Funds']")
    public WebElement TransferFundsDep;

    @FindBy(xpath = "//*[@id='amount']")
    public WebElement amountField;

    @FindBy(id = "fromAccountId")
    public WebElement fromAccount;

    @FindBy(id = "toAccountId")
    public WebElement toAccount;

    @FindBy(xpath = "//input[@class='button']")
    public WebElement transferBtn;

    @FindBy(xpath = "//h1[text()='Transfer Complete!']" )
    public WebElement confirmationMessage;

    @FindBy(xpath = "(//h1[@class='title'])[3]")
    public WebElement failConfirmMessage;

    @FindBy(xpath = "//a[text()='Accounts Overview']")
    public WebElement accountsOverview;

    @FindBy(xpath = "(//td[text()='$0.00'])[2]")
    public WebElement verifyZeroBalance;

}
