package com.antigravity.tests;

import com.antigravity.pages.AddBookPage;
import com.antigravity.pages.BookManagementPage;
import com.antigravity.pages.LoginPage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected BookManagementPage bookManagementPage;
    protected AddBookPage addBookPage;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        // Chặn popup lưu mật khẩu, lưu địa chỉ, thanh toán, leak detection
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Chặn thanh thông báo tự động và các bubble popup
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-single-click-autofill");
        options.addArguments("--disable-autofill-keyboard-accessory-view");

        // Chặn triệt để các tính năng Autofill và Password Manager Onboarding qua
        // Features
        options.addArguments(
                "--disable-features=AutofillAddressEnabled,AutofillCreditCardEnabled,AutofillPasswordEnabled,PasswordManagerOnboarding");

        // Kiểm tra biến môi trường HEADLESS (thường dùng trên CI)
        String headless = System.getProperty("headless", "false");
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);

        // Initialize Pages
        loginPage = new LoginPage(driver);
        bookManagementPage = new BookManagementPage(driver);
        addBookPage = new AddBookPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            saveScreenshot(result.getName());
        }
        // Luôn đính kèm screenshot cho Allure Report (theo yêu cầu user: passed &
        // failed)
        saveScreenshot(result.getName() + "_Final");

        if (driver != null) {
            driver.quit();
        }
    }

    @Attachment(value = "Screenshot {name}", type = "image/png")
    public byte[] saveScreenshot(String name) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
