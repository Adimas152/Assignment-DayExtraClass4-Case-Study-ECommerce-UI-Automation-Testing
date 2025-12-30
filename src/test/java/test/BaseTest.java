package test;

import com.myshopify.core.ConfigReader;
import com.myshopify.core.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected static Properties config;

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite(alwaysRun = true)
    public void loadConfig() {
        String env = System.getProperty("env"); // -Penv=production
        env = (env == null || env.isEmpty()) ? "staging" : env;
        config = ConfigReader.loadProperties(env);

        log.info("Loaded config env: {}", env);
    }

    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
//    public void setUp(@Optional("chrome") String browser) {
//        DriverManager.initDriver(browser);
//        DriverManager.getDriver().manage().window().maximize();
//        DriverManager.getDriver().get(config.getProperty("baseUrl"));
//    }

    public void setUp(@Optional("chrome") String browser) {
        DriverManager.initDriver(browser);
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get(config.getProperty("baseUrl"));
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
