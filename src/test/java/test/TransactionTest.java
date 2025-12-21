package test;

import com.myshopify.core.DriverManager;
import com.myshopify.pages.CartPage;
import com.myshopify.pages.CheckoutPage;
import com.myshopify.pages.HomePage;
import com.myshopify.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransactionTest extends BaseTest{
    @Test(groups = {"smoke"})
    public void transactionSuccess() {
        WebDriver driver = DriverManager.getDriver();
//        LoginPage loginPage = new LoginPage(driver); // memanggil login page
        HomePage homePage = new HomePage(driver); // memanggil home page
        CartPage cartPage = new CartPage(driver); // memanggil cart page
        CheckoutPage checkoutPage = new CheckoutPage(driver); // memanggil cart page

        homePage.addToCart(); // ini diambil dari home page
        cartPage.openCartPage(config.getProperty("baseUrl"));
        cartPage.checkoutFromCart();

        checkoutPage.inputContactOrder();
        checkoutPage.inputDeliveryOrder();
        checkoutPage.inputPayment();


    }

}
