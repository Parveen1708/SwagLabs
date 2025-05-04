package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LoggerUtil;

public class CartPage {
    private WebDriver driver;

    private By checkoutButton = By.id("checkout");
    private By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
        LoggerUtil.getLogger().info("Clicked checkout");
    }

    public int getNumberOfItems() {
        return driver.findElements(cartItems).size();
    }
}
