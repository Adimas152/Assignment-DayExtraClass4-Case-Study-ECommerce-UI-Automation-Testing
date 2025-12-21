package com.myshopify.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.assertj.core.api.Assertions;
import com.myshopify.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private static final Logger log = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@class,'toggle-drawer cart desktop')]")
    private WebElement iconCart;

    @FindBy(xpath = "//a[normalize-space()='Catalog']")
    private WebElement sectionCatalog;

    @FindBy(xpath = "(//section[contains(@class,'product-grid')]//a[contains(@href,'/products/')][not(.//div[contains(@class,'sold-out')])])[1]")
    private WebElement firstAvailableProduct;

    @FindBy(xpath = "//*[@id=\"add\"]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[@id='cart-target-desktop']/span")
    private WebElement verifyCart;

    @FindBy(xpath = "//a[@class='checkout']")
    private WebElement checkOutButton;

//    public void addToCart() {
//        log.info("Add to cart");
//        sectionCatalog.click();
//        firstAvailableProduct.click();
//        addToCartButton.click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.visibilityOf(verifyCart));   // nunggu cart count tampil
//
//        checkOutButton.click();
//    }

    private final By cartCount = By.id("cart-target-desktop"); // cukup outer span

    public void addToCart() {
        log.info("Add to cart");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        sectionCatalog.click();
        firstAvailableProduct.click();

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        // tunggu sampai count cart berubah (misal jadi (1))
        wait.until(d -> {
            String t = d.findElement(cartCount).getText().replaceAll("[^0-9]", "");
            return !t.isEmpty() && Integer.parseInt(t) > 0;
        });

        // kalau tombol checkout kamu ada di drawer, biasanya harus buka cart dulu
        iconCart.click();
        wait.until(ExpectedConditions.elementToBeClickable(checkOutButton)).click();
    }
}


