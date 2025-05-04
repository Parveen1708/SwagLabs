package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {
    private WebDriver driver;

    private By confirmationHeader = By.className("complete-header");

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderConfirmed() {
        return driver.findElement(confirmationHeader).isDisplayed();
    }
}
