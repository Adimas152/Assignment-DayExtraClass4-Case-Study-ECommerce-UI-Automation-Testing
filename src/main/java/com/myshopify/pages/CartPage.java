package com.myshopify.pages;

import com.myshopify.core.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    private static final Logger log = LogManager.getLogger(CartPage.class);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By titleCart = By.xpath("//h1[normalize-space()='My Cart']");

    @FindBy(xpath = "//input[@id='checkout']\n")
    private WebElement checkoutButton;

    public void checkoutFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(titleCart));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void openCartPage(String baseUrl) {
        if (baseUrl.endsWith("/")) baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        driver.get(baseUrl + "/cart");
    }
}
