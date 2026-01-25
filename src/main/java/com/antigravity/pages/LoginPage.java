package com.antigravity.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {
    private final By emailInput = By.name("email");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".error, .alert-danger, [role='alert']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Điều hướng đến trang Login")
    public void navigateToLoginPage() {
        gotoUrl("https://book.anhtester.com/sign-in");
        waitForPageLoad();
    }

    @Step("Thực hiện đăng nhập với email: {email}")
    public void login(String email, String password) {
        fill(emailInput, email);
        fill(passwordInput, password);
        click(loginButton);
        waitForPageLoad();
    }

    @Step("Đăng nhập nhanh với tài khoản mặc định")
    public void quickLogin() {
        navigateToLoginPage();
        login("anhtester@example.com", "123456");
        verifyLoginSuccess();
    }

    @Step("Xác nhận đăng nhập thành công")
    public void verifyLoginSuccess() {
        wait.until(d -> d.getCurrentUrl().contains("book-management")
                || d.getCurrentUrl().equals("https://book.anhtester.com/"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.equals("https://book.anhtester.com/") || currentUrl.contains("book-management"),
                "Login failed or redirected to wrong URL: " + currentUrl);
    }

    @Step("Xác nhận đăng nhập thất bại")
    public void verifyLoginFailed() {
        Assert.assertTrue(isVisible(errorMessage), "Error message should be visible");
    }
}
