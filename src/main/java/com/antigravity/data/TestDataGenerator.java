package com.antigravity.data;

import com.github.javafaker.Faker;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static String getTestImagePath() {
        File file = new File("test-data/sample-book-cover.jpg");
        return file.getAbsolutePath();
    }

    public static Map<String, String> generateUniqueBookData() {
        Map<String, String> data = new HashMap<>();
        String bookName = "JavaTest-" + System.currentTimeMillis();
        data.put("book_name", bookName);
        data.put("description", faker.lorem().sentence());
        data.put("price", String.valueOf(faker.number().numberBetween(1000, 5000)));
        data.put("category", "Tây Nam Bộ");
        data.put("picture", getTestImagePath());
        return data;
    }

    public static Map<String, String> generateInvalidBookData() {
        Map<String, String> data = new HashMap<>();
        data.put("book_name", "Invalid Price Book");
        data.put("description", "Negative price test");
        data.put("price", "-500");
        data.put("category", "Tây Nam Bộ");
        data.put("picture", "");
        return data;
    }
}
