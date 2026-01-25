package com.antigravity.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Map;

public class AddBookPage extends BasePage {
    private final By bookNameInput = By.name("name");
    private final By descriptionInput = By.cssSelector("textarea[name='description']");
    private final By priceInput = By.name("price");
    private final By pictureInput = By.name("picture");
    private final By categoryInput = By.cssSelector("input[role='combobox']");
    private final By createButton = By.xpath("//button[contains(.,'Create book')]");
    private final By resetButton = By.xpath("//button[contains(.,'Reset')]");
    private final By validationMessages = By.cssSelector("[class*='error'], [class*='invalid'], .text-danger");

    public AddBookPage(WebDriver driver) {
        super(driver);
    }

    @Step("Đợi form Add Book hiển thị")
    public void waitForFormLoad() {
        waitForVisible(bookNameInput);
    }

    @Step("Điền đầy đủ thông tin sách")
    public void fillCompleteBookForm(Map<String, String> bookData) {
        fill(bookNameInput, bookData.get("book_name"));
        fill(descriptionInput, bookData.get("description"));
        fill(priceInput, String.valueOf(bookData.get("price")));

        // Upload picture
        if (bookData.containsKey("picture") && !bookData.get("picture").isEmpty()) {
            File file = new File(bookData.get("picture"));
            if (file.exists()) {
                driver.findElement(pictureInput).sendKeys(file.getAbsolutePath());
            }
        }

        // Select category
        if (bookData.containsKey("category")) {
            WebElement catInput = driver.findElement(categoryInput);
            catInput.click();
            catInput.sendKeys(bookData.get("category"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            By option = By.xpath("//*[@role='option'][contains(.,'" + bookData.get("category") + "')]");
            waitForVisible(option);
            click(option);
        }
    }

    @Step("Click nút 'Create'")
    public void clickCreate() {
        click(createButton);
        waitForUrlContains("book-management");
    }

    @Step("Click nút 'Reset'")
    public void clickReset() {
        click(resetButton);
    }

    @Step("Xác nhận tạo sách thành công")
    public void verifyBookCreatedSuccessfully() {
        Assert.assertTrue(driver.getCurrentUrl().contains("book-management"),
                "Should redirect to management page after creation");
    }

    @Step("Kiểm tra có lỗi validate không")
    public void verifyValidationErrorVisible() {
        Assert.assertTrue(driver.findElements(validationMessages).size() > 0, "Validation messages should be visible");
    }

    @Step("Kiểm tra nút Create bị disable")
    public void verifyCreateButtonDisabled() {
        waitForVisible(createButton);
        Assert.assertFalse(driver.findElement(createButton).isEnabled(), "Create button should be disabled");
    }

    @Step("Kiểm tra form trống (sau Reset)")
    public void verifyFormIsEmpty() {
        Assert.assertEquals(driver.findElement(bookNameInput).getAttribute("value"), "", "Book name should be empty");
    }
}
