package com.myshopify.pages;

import com.myshopify.core.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public void inputPayment(){
        log.info("Input Payment Order");
        fieldCardNumber.sendKeys("5265183747589712");
        fieldCExpiryCard.sendKeys("12/30");
        fieldSecurityCode.sendKeys("136");
        fieldNameCard.sendKeys("Daniel Peterson");
    }

}
