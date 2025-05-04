package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LoggerUtil;

public class CheckoutPage {
    private WebDriver driver;

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By confirmationMessage = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCustomerDetails(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
        LoggerUtil.getLogger().info("Entered customer details");
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
        LoggerUtil.getLogger().info("Clicked continue");
    }

    public void finishCheckout() {
        driver.findElement(finishButton).click();
        LoggerUtil.getLogger().info("Clicked finish");
    }

    public boolean isOrderComplete() {
        return driver.findElement(confirmationMessage).isDisplayed();
    }
}
