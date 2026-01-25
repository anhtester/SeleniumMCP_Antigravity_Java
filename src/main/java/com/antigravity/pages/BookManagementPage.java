package com.antigravity.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BookManagementPage extends BasePage {
    private final By bookMenuItem = By.xpath("//a[contains(.,'Book')] | //button[contains(.,'Book')]");
    private final By newBookButton = By
            .xpath("//button[contains(.,'New book')] | //a[contains(.,'New book')]");
    private final By searchInput = By.cssSelector("input.search__input, input[placeholder*='Search']");
    private final By bookTitles = By.cssSelector("[class*='title'], [class*='name'], h3, h4, h5, h6");

    public BookManagementPage(WebDriver driver) {
        super(driver);
    }

    @Step("Điều hướng đến trang Quản lý sách")
    public void navigateToBookManagement() {
        click(bookMenuItem);
        waitForPageLoad();
    }

    @Step("Click nút 'New Book'")
    public void clickNewBook() {
        click(newBookButton);
        waitForPageLoad();
    }

    @Step("Kiểm tra trang Quản lý sách đã tải xong")
    public void verifyPageLoaded() {
        waitForUrlContains("book-management");
        Assert.assertTrue(driver.getCurrentUrl().contains("book-management"),
                "Not on book management page: " + driver.getCurrentUrl());
        Assert.assertTrue(isVisible(newBookButton), "New book button should be visible");
    }

    @Step("Tìm kiếm sách với từ khóa: {bookName}")
    public void searchBook(String bookName) {
        if (!isVisible(searchInput)) {
            By filterBtn = By.xpath("//button[contains(.,'Filter')]");
            if (isVisible(filterBtn)) {
                click(filterBtn);
            }
        }
        fill(searchInput, bookName);
        pressKey(searchInput, Keys.ENTER);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Xác nhận sách '{bookName}' có tồn tại")
    public void verifyBookExists(String bookName) {
        searchBook(bookName);
        List<WebElement> elements = driver.findElements(bookTitles);
        boolean found = false;
        for (WebElement element : elements) {
            if (element.getText().toLowerCase().contains(bookName.toLowerCase())) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Book '" + bookName + "' not found in list");
    }

    @Step("Xác nhận sách '{bookName}' KHÔNG tồn tại")
    public void verifyBookNotExists(String bookName) {
        searchBook(bookName);
        List<WebElement> elements = driver.findElements(bookTitles);
        boolean found = false;
        for (WebElement element : elements) {
            if (element.getText().toLowerCase().contains(bookName.toLowerCase())) {
                found = true;
                break;
            }
        }
        Assert.assertFalse(found, "Book '" + bookName + "' should not exist");
    }
}
