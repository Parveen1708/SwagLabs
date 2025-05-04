package test;

import base.BaseTest;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SwagLabsTests extends BaseTest {

	@Test(priority = 1, description = "Login with valid credentials and verify products page")
	public void testLoginWithValidCredentials() throws Exception {
	    extentTest = extentReports.createTest("Login with valid credentials");

	    try {
	        driver.get("https://www.saucedemo.com");
	        extentTest.info("Navigated to Swag Labs login page");

	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("standard_user", "secret_sauce");
	        extentTest.info("Entered credentials and submitted login");

	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertTrue(productsPage.isPageLoaded(), "Products page did not load after login");

	        extentTest.pass("Successfully logged in and loaded products page");

	    } catch (Exception e) {
	        extentTest.fail("❌ [Unexpected Error] An exception occurred during login test: ");
	        
	        String screenshotPath = takeScreenshot("testLoginWithValidCredentials");
	        extentTest.fail("Screenshot of failure").addScreenCaptureFromPath(screenshotPath);
	        
	        throw e;
	    }
	}


	@Test(priority = 2, description = "Login with invalid credentials shows error message")
	public void testLoginWithInvalidCredentials() throws Exception {
	    extentTest = extentReports.createTest("Login with invalid credentials");

	    try {
	        driver.get("https://www.saucedemo.com");
	        extentTest.info("Navigated to Swag Labs login page");

	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("locked_out_user", "secret_sauce");
	        extentTest.info("Entered invalid credentials and attempted login");

	        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid login");

	        extentTest.pass("Error message displayed as expected for invalid login");

	    } catch (Exception e) {
	        extentTest.fail("❌ [Unexpected Error] An exception occurred during invalid login test: " + e.getMessage());

	        String screenshotPath = takeScreenshot("testLoginWithInvalidCredentials");
	        extentTest.fail("Screenshot of failure").addScreenCaptureFromPath(screenshotPath);

	        throw e;
	    }
	}


	@Test(priority = 3, description = "Add product to cart and verify cart item count")
	public void testAddProductToCart() throws Exception {
	    extentTest = extentReports.createTest("Add product to cart");

	    try {
	        driver.get("https://www.saucedemo.com");
	        extentTest.info("Navigated to Swag Labs login page");

	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("standard_user", "secret_sauce");
	        extentTest.info("Logged in with valid credentials");

	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertTrue(productsPage.isPageLoaded(), "Products page did not load");
	        extentTest.info("Products page loaded successfully");

	        productsPage.addFirstProductToCart();
	        extentTest.info("Added first product to cart");

	        int itemCount = productsPage.getCartItemCount();
	        Assert.assertEquals(itemCount, 1, "Cart item count is not 1 after adding a product");

	        extentTest.pass("Product added to cart and item count verified");

	    } catch (Exception e) {
	        extentTest.fail("❌ [Unexpected Error] An exception occurred during add to cart test: " + e.getMessage());

	        String screenshotPath = takeScreenshot("testAddProductToCart");
	        extentTest.fail("Screenshot of failure").addScreenCaptureFromPath(screenshotPath);

	        throw e;
	    }
	}


	@Test(priority = 4, description = "Complete checkout flow and verify order confirmation")
	public void testCheckoutFlow() throws Exception {
	    extentTest = extentReports.createTest("Complete checkout flow");

	    try {
	        driver.get("https://www.saucedemo.com");
	        extentTest.info("Navigated to Swag Labs login page");

	        // Login
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("standard_user", "secret_sauce");
	        extentTest.info("Logged in with valid credentials");

	        // Add product
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertTrue(productsPage.isPageLoaded(), "Products page did not load");
	        extentTest.info("Products page loaded successfully");

	        productsPage.addFirstProductToCart();
	        extentTest.info("Added first product to cart");

	        // Go to cart
	        driver.findElement(By.className("shopping_cart_link")).click();
	        extentTest.info("Navigated to cart page");

	        // Proceed to checkout
	        CartPage cartPage = new CartPage(driver);
	        Assert.assertEquals(cartPage.getNumberOfItems(), 1, "Cart does not have 1 item");
	        extentTest.info("Verified 1 item in the cart");
	        cartPage.clickCheckout();
	        extentTest.info("Clicked checkout");

	        // Checkout details
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.enterCustomerDetails("John", "Doe", "12345");
	        extentTest.info("Entered customer details");

	        checkoutPage.clickContinue();
	        extentTest.info("Proceeded to final checkout step");

	        // Finish order
	        checkoutPage.finishCheckout();
	        extentTest.info("Finished checkout");

	        // Confirm order
	        OrderConfirmationPage orderPage = new OrderConfirmationPage(driver);
	        Assert.assertTrue(orderPage.isOrderConfirmed(), "Order confirmation not displayed");

	        extentTest.pass("Checkout flow completed and order confirmed");

	    } catch (Exception e) {
	        extentTest.fail("❌ [Unexpected Error] An exception occurred during checkout flow test: " + e.getMessage());

	        String screenshotPath = takeScreenshot("testCheckoutFlow");
	        extentTest.fail("Screenshot of failure").addScreenCaptureFromPath(screenshotPath);

	        throw e;
	    }
	}

}
