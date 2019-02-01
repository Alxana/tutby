package com.automation.tutby.tests;

import com.automation.tutby.listeners.AllureListener;
import com.automation.tutby.pages.Homepage;
import com.automation.tutby.utils.SuiteConfiguration;
import com.automation.tutby.utils.WaitUtils;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.IOException;
import java.lang.reflect.Method;

@Listeners({AllureTestNg.class, AllureListener.class})
public abstract class TestBase {

    protected static String baseUrl;
    protected static int port;
    protected static String basePath;
    protected static Capabilities capabilities;
    protected Logger logger;
    protected WebDriver driver;

    @BeforeSuite
    public void initTestSuite() throws IOException {
        SuiteConfiguration config = new SuiteConfiguration();
        baseUrl = config.getProperty("site.url");
        port = Integer.valueOf(config.getProperty("server.port"));
        basePath = config.getProperty("server.base");
        capabilities = config.getCapabilities();
        setupWebDriver(config);
    }

    private void setupWebDriver(SuiteConfiguration config) {
    }

    @BeforeClass
    public void initTestClass() {
        logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Initialize test class");
    }

    @BeforeMethod
    public void prepareForTestMethod(Method method) {
        logger.info("Method name: " + method.getName());
        initWebDriver();
        openTargetPage();
        WaitUtils.forPageLoaded(driver, Homepage.class);
    }

    public abstract void openTargetPage();

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (!WebDriverPool.DEFAULT.isEmpty()) {
            logger.info("Close WebDriver");
            WebDriverPool.DEFAULT.dismissAll();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void initWebDriver() {
        driver = WebDriverPool.DEFAULT.getDriver(capabilities);
        driver.manage().window().maximize();
        logger.info("WebDriver initialized");
    }


}
