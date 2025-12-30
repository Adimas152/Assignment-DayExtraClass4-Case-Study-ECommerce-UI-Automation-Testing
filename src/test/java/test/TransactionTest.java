package test;

import com.myshopify.core.DriverManager;
import com.myshopify.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransactionTest extends BaseTest{
    @Test(groups = {"smoke"})
    public void transactionInvalidCard_shouldShowErrorMessages() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // 1) Add product dari catalog/product page
        int cartQty = homePage.addToCart();
        Assert.assertTrue(cartQty > 0, "Cart count harus bertambah setelah Add to cart. Actual=" + cartQty);

        // 2) Buka cart page dan checkout
        cartPage.openCartPage(config.getProperty("baseUrl"));
        cartPage.checkoutFromCart();

        // 3) Isi contact + delivery (page checkout)
        checkoutPage.inputContactOrder();
        checkoutPage.inputDeliveryOrder();

        // 4) Payment invalid + klik Pay now
        CheckoutPaymentPage paymentPage = new CheckoutPaymentPage(driver, wait);

        paymentPage.inputCardNumber("5265183747589712"); // invalid (sesuai screenshot kamu)
        paymentPage.inputExpiry("1227");
        paymentPage.inputCvc("136");
        paymentPage.clickPayNow();

        // 5) Assertion error message display
        Assert.assertTrue(paymentPage.isCardNumberErrorDisplayed(),
                "Expected field error (error-for-number) tampil.");

        Assert.assertTrue(paymentPage.getCardNumberErrorText().toLowerCase().contains("valid card number"),
                "Expected text mengandung 'Enter a valid card number'. Actual: " + paymentPage.getCardNumberErrorText());

        Assert.assertTrue(paymentPage.isPaymentErrorBannerDisplayed(),
                "Expected banner PaymentErrorBanner tampil.");

        Assert.assertTrue(paymentPage.getPaymentErrorBannerText().toLowerCase().contains("couldn"),
                "Expected banner text mengandung 'couldn’t be verified'. Actual: " + paymentPage.getPaymentErrorBannerText());
    }
}
