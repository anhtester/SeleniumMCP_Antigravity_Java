package com.antigravity.tests;

import com.antigravity.data.TestDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.Map;

@Feature("Book Management")
public class TestBookManagement extends BaseTest {

    @Test(priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    @Description("TC01: Verify Login Successfully")
    public void test_tc01_verify_login_successfully() {
        loginPage.navigateToLoginPage();
        loginPage.login("anhtester@example.com", "123456");
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 2)
    @Description("TC02: Navigate to Book Management")
    public void test_tc02_navigate_to_book_management() {
        loginPage.quickLogin();
        bookManagementPage.navigateToBookManagement();
        bookManagementPage.verifyPageLoaded();
    }

    @Test(priority = 3)
    @Description("TC03: Add New Book with Valid Data")
    public void test_tc03_add_new_book_with_valid_data() {
        loginPage.quickLogin();
        bookManagementPage.navigateToBookManagement();
        bookManagementPage.clickNewBook();
        addBookPage.waitForFormLoad();

        Map<String, String> data = TestDataGenerator.generateUniqueBookData();
        addBookPage.fillCompleteBookForm(data);
        addBookPage.clickCreate();

        bookManagementPage.verifyBookExists(data.get("book_name"));
    }

    @Test(priority = 4)
    @Description("TC04: Add New Book - Missing Required Fields")
    public void test_tc04_add_new_book_missing_required_fields() {
        loginPage.quickLogin();
        bookManagementPage.navigateToBookManagement();
        bookManagementPage.clickNewBook();
        addBookPage.waitForFormLoad();

        // Nút Create sẽ bị disable nếu thiếu trường bắt buộc
        addBookPage.verifyCreateButtonDisabled();
    }

    @Test(priority = 5)
    @Description("TC05: Add New Book - Invalid Price")
    public void test_tc05_add_new_book_invalid_price() {
        loginPage.quickLogin();
        bookManagementPage.navigateToBookManagement();
        bookManagementPage.clickNewBook();
        addBookPage.waitForFormLoad();

        Map<String, String> data = TestDataGenerator.generateInvalidBookData();
        addBookPage.fillCompleteBookForm(data);
        addBookPage.clickCreate();

        // Website thường bắt lỗi tại client hoặc server
        addBookPage.verifyValidationErrorVisible();
    }

    @Test(priority = 6)
    @Description("TC06: Verify Search Book Functionality")
    public void test_tc06_verify_search_book_functionality() {
        loginPage.quickLogin();
        bookManagementPage.navigateToBookManagement();

        Map<String, String> data = TestDataGenerator.generateUniqueBookData();
        bookManagementPage.clickNewBook();
        addBookPage.fillCompleteBookForm(data);
        addBookPage.clickCreate();

        bookManagementPage.searchBook(data.get("book_name"));
        bookManagementPage.verifyBookExists(data.get("book_name"));
    }

    @Test(priority = 7)
    @Description("TC07: Verify Reset Button Functionality")
    public void test_tc07_verify_reset_button_functionality() {
        loginPage.quickLogin();
        bookManagementPage.navigateToBookManagement();
        bookManagementPage.clickNewBook();

        Map<String, String> data = TestDataGenerator.generateUniqueBookData();
        addBookPage.fillCompleteBookForm(data);
        addBookPage.clickReset();

        addBookPage.verifyFormIsEmpty();
    }

    @Test(priority = 8)
    @Description("TC08: Verify Form Validation on Empty Submit")
    public void test_tc08_verify_form_validation_on_empty_submit() {
        loginPage.quickLogin();
        bookManagementPage.navigateToBookManagement();
        bookManagementPage.clickNewBook();
        addBookPage.waitForFormLoad();

        addBookPage.verifyCreateButtonDisabled();
    }
}
