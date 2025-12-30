package com.myshopify.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IframeHelper {
    private IframeHelper(){}

    public static void typeInIframe(WebDriver driver, WebDriverWait wait,
                                    By iframeLocator, By inputLocator, String value) {
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
        driver.switchTo().frame(frame);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        input.click();
        // Clear (Mac)
        input.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(value);

        driver.switchTo().defaultContent();
    }
}
