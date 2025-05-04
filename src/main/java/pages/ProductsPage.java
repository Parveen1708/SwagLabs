package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LoggerUtil;

public class ProductsPage {
    private WebDriver driver;

    private By productTitle = By.className("title");
    private By addToCartButton = By.cssSelector(".btn_inventory");
    private By shoppingCartBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageLoaded() {
        return driver.findElement(productTitle).isDisplayed();
    }

    public void addFirstProductToCart() {
        driver.findElement(addToCartButton).click();
        LoggerUtil.getLogger().info("Added product to cart");
    }

    public int getCartItemCount() {
        String countText = driver.findElement(shoppingCartBadge).getText();
        return Integer.parseInt(countText);
    }
}
