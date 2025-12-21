package com.myshopify.pages;

import com.myshopify.core.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Properties;

public class LoginPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(xpath = "//*[@id=\"customer_login_link\"]")
    private WebElement ctaLogin;

    @FindBy(xpath = "//*[@id=\"customer_email\"]")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"customer_password\"]")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Sign In']")
    private WebElement loginButton;


    @FindBy(xpath = "//a[normalize-space()='My Account']")
    private WebElement verifyLogin;

    private final By myAccountLink = By.cssSelector("a[href='/account']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        log.info("Logging in with email: {}", email);

        ctaLogin.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountLink));

    }



}
