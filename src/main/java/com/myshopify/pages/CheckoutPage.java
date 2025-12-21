package com.myshopify.pages;

import com.myshopify.core.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    private static final Logger log = LogManager.getLogger(CheckoutPage.class);

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailContact;

    @FindBy(xpath = "//select[@name='countryCode']")
    private WebElement dropdownDeliveryCountry;

    @FindBy(xpath = "//select[@name='countryCode']/option[normalize-space()='Indonesia']")
    private WebElement selectDeliveryCountryOption;

    @FindBy(css = "input[name='firstName']:not([aria-hidden='true'])")
    private WebElement firstName;

    @FindBy(css = "input[name='lastName']:not([aria-hidden='true'])")
    private WebElement lastName;

    @FindBy(css = "input[name='company']:not([aria-hidden='true'])")
    private WebElement deliveryCompany;

    @FindBy(css = "input[name='address1']:not([aria-hidden='true'])")
    private WebElement deliveryAddress;

    @FindBy(css = "input[name='address2']:not([aria-hidden='true'])")
    private WebElement deliveryAddress2;

    @FindBy(css = "input[name='city']:not([aria-hidden='true'])")
    private WebElement deliveryCity;

    @FindBy(xpath = "//select[@name='zone']")
    private WebElement dropdownDeliveryProvince;

    @FindBy(xpath = "//select[@name='zone']/option[normalize-space()='Jakarta']")
    private WebElement selectDeliveryProvinceOption;

    @FindBy(css = "input[name='postalCode']:not([aria-hidden='true'])")
    private WebElement deliveryPortalCode;

    @FindBy(css = "input[name='phone']:not([aria-hidden='true'])")
    private WebElement phoneNumber;

    //Payment
    @FindBy(id= "number")
    private WebElement fieldCardNumber;

    @FindBy(id= "expiry")
    private WebElement fieldCExpiryCard;

    @FindBy(id= "verification_value")
    private WebElement fieldSecurityCode;

    @FindBy(id= "name-label")
    private WebElement fieldNameCard;

    public void inputContactOrder(){
        log.info("Input Contact Order");
        emailContact.sendKeys("adimas@gmail.com");
    }

    public void inputDeliveryOrder(){
        log.info("Input Delivery Order");
        emailContact.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        emailContact.sendKeys(Keys.BACK_SPACE);
        emailContact.sendKeys("adimas@gmail.com");
        dropdownDeliveryCountry.click();
        selectDeliveryCountryOption.click();
        firstName.sendKeys("Adimas");
        lastName.sendKeys("Sutrisno");
        deliveryCompany.sendKeys("Dibimbing");
        deliveryAddress.sendKeys("Jl. Melawai Raya No. 10");
        deliveryAddress2.sendKeys("Plaza Cityview");
        deliveryCity.sendKeys("Jakarta Selatan");
        dropdownDeliveryProvince.click();
        selectDeliveryProvinceOption.click();
        deliveryPortalCode.sendKeys("12510");
        phoneNumber.sendKeys("087772332033");
    }


    // IFRAME payment (Shopify biasanya pakai pattern "card-fields-*")
    private final By iframeCardNumber = By.cssSelector("iframe[name^='card-fields-number']");
    private final By iframeCardName   = By.cssSelector("iframe[name^='card-fields-name']");
    private final By iframeExpiry     = By.cssSelector("iframe[name^='card-fields-expiry']");
    private final By iframeCvv        = By.cssSelector("iframe[name^='card-fields-verification_value']");

    // INPUT di dalam iframe (biasanya name-nya konsisten)
    private final By inputCardNumber = By.cssSelector("input[name='number']");
    private final By inputCardName   = By.cssSelector("input[name='name']");
    private final By inputExpiry     = By.cssSelector("input[name='expiry']");
    private final By inputCvv        = By.cssSelector("input[name='verification_value']");

    // tombol Pay now (di luar iframe)
    private final By payNowButton = By.cssSelector("button[type='submit']");

    private void typeInIframe(By iframeLocator, By inputLocator, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // tunggu iframe muncul
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));

        // pindah ke iframe
        driver.switchTo().frame(iframe);

        // tunggu input bisa dipakai
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        input.click();
        input.clear();
        input.sendKeys(value);

        // balik ke halaman utama
        driver.switchTo().defaultContent();
    }

    public void inputPayment() {
        log.info("Input payment");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // pastikan sudah sampai section payment (kadang load agak lama)
        wait.until(ExpectedConditions.urlContains("/checkouts/"));

        // isi semua field card via iframe
        typeInIframe(iframeCardNumber, inputCardNumber, "5265183747589712");
//        typeInIframe(iframeExpiry,     inputExpiry,     "1230");

        // ===== EXPIRY FIELD (iframe) =====
        By iframeExpiry = By.cssSelector("iframe[id^='card-fields-expiry']");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeExpiry));

        By expiryInput = By.cssSelector("input[name='expiry']");
        WebElement expiry = wait.until(ExpectedConditions.elementToBeClickable(expiryInput));

        expiry.click();

        // clear (beberapa field ga bisa clear biasa, jadi pakai Ctrl/Cmd+A)
        expiry.sendKeys(Keys.chord(Keys.COMMAND, "a")); // Mac
        expiry.sendKeys(Keys.BACK_SPACE);

        // coba format yang paling umum di Shopify
        expiry.sendKeys("12");
        expiry.sendKeys("27");

        driver.switchTo().defaultContent();

        typeInIframe(iframeCvv,        inputCvv,        "136");
        typeInIframe(iframeCardName,   inputCardName,   "Daniel Peterson");

        // optional: klik Pay now kalau memang dibutuhkan
         wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
    }


//    public void inputPayment(){
//        log.info("Input Payment Order");
//        fieldCardNumber.sendKeys("5265183747589712");
//        fieldCExpiryCard.sendKeys("12/30");
//        fieldSecurityCode.sendKeys("136");
//        fieldNameCard.sendKeys("Daniel Peterson");
//    }

}
