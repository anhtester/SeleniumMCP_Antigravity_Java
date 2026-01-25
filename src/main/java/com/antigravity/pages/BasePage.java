package com.antigravity.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Đi tới URL: {url}")
    public void gotoUrl(String url) {
        driver.get(url);
    }

    @Step("Đợi phần tử hiển thị")
    public void waitForVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Click vào phần tử")
    public void click(By locator) {
        waitForVisible(locator);
        driver.findElement(locator).click();
    }

    @Step("Nhập văn bản: {text}")
    public void fill(By locator, String text) {
        waitForVisible(locator);
        WebElement element = driver.findElement(locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(text);
    }

    public void clickJS(By locator) {
        waitForVisible(locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Lấy văn bản từ phần tử")
    public String getText(By locator) {
        waitForVisible(locator);
        return driver.findElement(locator).getText();
    }

    @Step("Kiểm tra phần tử có hiển thị không")
    public boolean isVisible(By locator) {
        try {
            wait.withTimeout(Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    @Step("Gửi phím: {key}")
    public void pressKey(By locator, Keys key) {
        driver.findElement(locator).sendKeys(key);
    }

    public void waitForPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                .equals("complete"));
    }
}
