# <img src="https://www.selenium.dev/favicons/favicon.ico" width="32"> Selenium Java build by Antigravity AI - Book Management 🚀

<p align="center">
  <img src="https://github.com/anhtester/SeleniumMCP_Antigravity_Java/actions/workflows/maven.yml/badge.svg" alt="Java CI">
  <img src="https://img.shields.io/badge/Java-17-orange" alt="Java">
  <img src="https://img.shields.io/badge/Selenium-4.x-green" alt="Selenium">
  <img src="https://img.shields.io/badge/Allure-Report-yellow" alt="Allure">
</p>


Project này được xây dựng bằng **Selenium WebDriver v4** và **Java 17**, sử dụng mô hình **Page Object Model (POM)** kết hợp với **TestNG**. Dự án này được thiết kế để kiểm thử tự động hệ thống Quản lý sách (Book Management System).

## 🚀 Tính năng nổi bật

- 🏗️ **Kiến trúc POM (Page Object Model)**: Code sạch, dễ bảo trì, tách biệt logic test và định nghĩa trang.
- ☕ **Java 17**: Tận dụng các tính năng mới nhất của Java.
- 🧪 **TestNG**: Quản lý Test Cases mạnh mẽ, hỗ trợ chạy song song và phân nhóm test.
- 🌍 **Cross-Browser & Headless Support**: Chạy tốt trên Chrome, hỗ trợ chế độ Headless cho CI/CD.
- 📊 **Allure Report**: Báo cáo test đẹp, chi tiết, tự động đính kèm Screenshot cho cả test Pass và Fail.
- 🔧 **Tương tác nâng cao**: Xử lý tốt các components khó như Material UI Dropdown, File Upload, Textarea ẩn.
- 🤖 **GitHub Actions CI/CD**: Tích hợp pipeline tự động chạy test mỗi khi có Push hoặc Pull Request.

## 📋 Yêu cầu hệ thống

- **Java JDK 17** trở lên.
- **Maven 3.8+**.
- **Google Chrome** (phiên bản mới nhất).
- **Git**.

## 📦 Hướng dẫn cài đặt

```bash
# 1. Clone dự án
git clone https://github.com/anhtester/SeleniumMCP_Antigravity_Java.git
cd SeleniumMCP_Antigravity_Java

# 2. Cài đặt dependencies
mvn clean install -DskipTests
```

## 🏗️ Cấu trúc dự án

```text
SeleniumMCP_Antigravity_Java/
├── .github/
│   └── workflows/
│       └── maven.yml         # GitHub Actions CI Configuration
├── src/
│   ├── main/java/com/antigravity/
│   │   ├── data/             # Data Utils (Data Generation, Faker)
│   │   └── pages/            # Page Objects (Locators & Methods)
│   └── test/java/com/antigravity/
│       └── tests/            # Test Classes (Test Cases execution)
├── test-data/                # Test Resources (Images, Files for upload)
├── target/                   # Build artifacts & Test Reports (Allure)
├── .gitignore                # Git ignore rules
├── pom.xml                   # Maven Dependencies & Configuration
└── README.md                 # Project Documentation
```

## 🧪 Hướng dẫn chạy Test

### 1. Chạy trên máy local (có giao diện trình duyệt)
```bash
mvn clean test
```

### 2. Chạy chế độ Headless (không giao diện - thích hợp cho CI/CD)
```bash
mvn clean test -Dheadless=true
```

### 3. Xem báo cáo Allure
Sau khi chạy test xong, sử dụng lệnh sau để mở báo cáo trên trình duyệt:
```bash
mvn allure:serve
```

## 📝 Danh sách Test Cases

| ID | Tên Test Case | Mô tả |
|----|--------------|-------|
| **TC01** | Verify Login Successfully | Đăng nhập thành công với tài khoản hợp lệ. |
| **TC02** | Navigate to Book Management | Chuyển trang đến màn hình quản lý sách. |
| **TC03** | Add New Book - Valid Data | Thêm sách mới thành công (gồm upload ảnh, chọn category). |
| **TC04** | Add New Book - Missing Fields | Kiểm tra validate khi thiếu trường bắt buộc (nút Create bị disable). |
| **TC05** | Add New Book - Invalid Price | Kiểm tra lỗi khi nhập giá trị âm. |
| **TC06** | Verify Search Functionality | Tìm kiếm sách mới tạo để xác nhận dữ liệu đã lưu. |
| **TC07** | Verify Reset Button | Kiểm tra chức năng làm mới form về trạng thái ban đầu. |
| **TC08** | Verify Empty Submit | Xác nhận không thể submit form khi chưa nhập liệu. |

## 🤖 CI/CD Pipeline

Dự án đã được cấu hình GitHub Actions (`.github/workflows/maven.yml`):
1. **Trigger**: Tự động chạy khi có code push lên nhánh `main`.
2. **Setup**: Cài đặt JDK 17.
3. **Execute**: Chạy `mvn clean test -Dheadless=true`.
4. **Report**: Lưu trữ báo cáo Allure dưới dạng Artifacts để tải về xem sau.

---

## 👥 Author

**Author: [Anh Tester](https://anhtester.com)**

*Developed by Antigravity AI*

