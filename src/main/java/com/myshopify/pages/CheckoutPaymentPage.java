package com.myshopify.pages;

import com.myshopify.utils.IframeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CheckoutPaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPaymentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ===== IFRAME (Shopify card fields) =====
    private final By iframeCardNumber = By.cssSelector("iframe[id^='card-fields-number'], iframe[name^='card-fields-number']");
    private final By iframeExpiry     = By.cssSelector("iframe[id^='card-fields-expiry'], iframe[name^='card-fields-expiry']");
    private final By iframeCvc        = By.cssSelector("iframe[id^='card-fields-verification_value'], iframe[name^='card-fields-verification_value']");

    // input di dalam iframe
    private final By inputNumber = By.cssSelector("input#number, input[name='number']");
    private final By inputExpiry = By.cssSelector("input#expiry, input[name='expiry']");
    private final By inputCvc    = By.cssSelector("input#verification_value, input[name='verification_value']");

    // ===== ERROR  =====
    private final By paymentErrorBanner = By.id("PaymentErrorBanner"); // "Your payment details couldn't be verified..."
    private final By cardNumberError    = By.id("error-for-number");   // "Enter a valid card number"

    // action
    private final By payNowButton = By.xpath("//button[contains(normalize-space(),'Pay now')]");

    public void inputCardNumber(String value) {
        IframeHelper.typeInIframe(driver, wait, iframeCardNumber, inputNumber, value);
    }

    public void inputExpiry(String mmYY) {
        // ketik khusus expiry (masked)
        IframeHelper.typeExpiryInIframe(driver, wait, iframeExpiry, inputExpiry, mmYY);
    }


    public void inputCvc(String value) {
        IframeHelper.typeInIframe(driver, wait, iframeCvc, inputCvc, value);
    }

    public void clickPayNow() {
        wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
    }

    // ===== Assertion helper =====
    public boolean isCardNumberErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberError)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getCardNumberErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberError)).getText().trim();
    }

    public boolean isPaymentErrorBannerDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentErrorBanner)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getPaymentErrorBannerText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentErrorBanner)).getText().trim();
    }
}
