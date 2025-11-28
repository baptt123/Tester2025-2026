package org.example.Lap7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class HoHuuThuc_21130557_Lab7 {

    public static void main(String[] args) {

        System.out.println("===================== START LAB 7 =====================");

        // Test 1: Product Filter
        System.out.println("\n\n===================== TEST 1: PRODUCT FILTER =====================\n");
        testProductFilter();

        // Test 2: Chat + FAQ
        System.out.println("\n\n===================== TEST 2: CHAT & FAQ =====================\n");
        testChatFAQ();

        System.out.println("\n===================== FINISHED ALL TESTS =====================");
    }

    //  TEST 1 – PRODUCT FILTER (COPY Y NGUYÊN FILE 1 CỦA BẠN)
    public static void testProductFilter() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // 1. Mở trang chính
            driver.get("https://aristino.com/");
            Thread.sleep(1500);

            closePopupIfExists(driver);

            // 2. Mở trang Áo
            WebElement menuAoEl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(., 'Áo') and contains(@href, '/collections/tat-ca-ao')]")
            ));
            scrollAndClick(driver, menuAoEl);
            Thread.sleep(2000);

            // UI TEST
            System.out.println("\n===== UI TEST – Bộ Lọc =====");
            checkFilterVisible(driver, "Nhãn hàng");
            checkFilterVisible(driver, "Màu sắc");
            checkFilterVisible(driver, "Kích cỡ");
            checkFilterVisible(driver, "Sản phẩm");
            checkFilterVisible(driver, "Form dáng");
            checkFilterVisible(driver, "Giá");

            testResetButtonUI(driver);

            // Accordion
            System.out.println("\n===== UI TEST – Accordion =====");
            testAccordion(driver, "Nhãn hàng");
            testAccordion(driver, "Màu sắc");
            testAccordion(driver, "Kích cỡ");
            testAccordion(driver, "Sản phẩm");
            testAccordion(driver, "Form dáng");

            // FUNCTIONAL TEST
            System.out.println("\n===== FUNCTION TEST – Bộ Lọc =====");

            testFiltersIndividually(driver);
            testFiltersCombined(driver);
            testNoResult(driver);
            testFilterReset(driver);

            System.out.println("\n===== HOÀN THÀNH TẤT CẢ TEST FILTER =====");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { Thread.sleep(1200); } catch (InterruptedException ignored) {}
            driver.quit();
        }
    }

    //  Helper functions của Product Filter
    static void closePopupIfExists(WebDriver driver) {
        try {
            List<By> closeButtons = Arrays.asList(
                    By.cssSelector(".close"),
                    By.cssSelector(".modal-close"),
                    By.cssSelector(".btn.btn-close"),
                    By.cssSelector(".close-btn"),
                    By.xpath("//button[contains(text(),'Đóng') or contains(text(),'×')]")
            );
            for (By btn : closeButtons) {
                List<WebElement> popups = driver.findElements(btn);
                if (!popups.isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", popups.get(0));
                    Thread.sleep(300);
                }
            }
        } catch (Exception ignored) {}
    }

    static void scrollAndClick(WebDriver driver, WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        try { el.click(); }
        catch (ElementClickInterceptedException ex) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    static void checkFilterVisible(WebDriver driver, String label) {
        List<WebElement> els = driver.findElements(
                By.xpath("//div[contains(@class,'sidebar-box-subtitle')]//span[contains(text(),'" + label + "')]")
        );
        System.out.println(els.size() > 0 ? "PASS – Thấy mục: " + label : "FAIL – Không thấy mục " + label);
    }

    static int getProductCount(WebDriver driver) throws InterruptedException {
        int retries = 3;
        while (retries > 0) {
            try {
                List<WebElement> products = driver.findElements(By.cssSelector(".pro-tile.pro-loop"));
                return (int) products.stream()
                        .filter(WebElement::isDisplayed)
                        .count();
            } catch (StaleElementReferenceException e) {
                retries--;
                Thread.sleep(300);
            }
        }
        List<WebElement> products = driver.findElements(By.cssSelector(".pro-tile.pro-loop"));
        return (int) products.stream()
                .filter(WebElement::isDisplayed)
                .count();
    }

    static void waitForProductChanged(WebDriver driver, int before) throws InterruptedException {
        int tries = 0;
        while (tries < 15) {
            Thread.sleep(500);
            try {
                int now = getProductCount(driver);
                if (now != before) break;
            } catch (StaleElementReferenceException ignored) {}
            tries++;
        }
    }

    static void removeFilter(WebDriver driver) throws InterruptedException {
        try {
            List<WebElement> removeBtns = driver.findElements(By.cssSelector(".filter-tags-remove"));
            for (WebElement x : removeBtns) {
                scrollAndClick(driver, x);
                Thread.sleep(300);
            }

            List<WebElement> removeAllBtn = driver.findElements(By.cssSelector(".filter-tags.remove-all"));
            if (!removeAllBtn.isEmpty()) {
                scrollAndClick(driver, removeAllBtn.get(0));
                Thread.sleep(500);
            }
        } catch (Exception ignored) {}
    }

    static void testAccordion(WebDriver driver, String label) throws InterruptedException {
        WebElement title = driver.findElement(By.xpath("//span[text()='" + label + "']"));
        System.out.println("Test Accordion: " + label);

        scrollAndClick(driver, title);
        Thread.sleep(800);
        scrollAndClick(driver, title);
        Thread.sleep(800);

        System.out.println("PASS – Accordion hoạt động: " + label);
    }

    static void testResetButtonUI(WebDriver driver) {
        List<WebElement> btns = driver.findElements(By.cssSelector(".btn-clear-filter-js"));
        if (btns.isEmpty()) System.out.println("FAIL – Không thấy nút HUỶ");
        else System.out.println("PASS – Nút HUỶ hiển thị");
    }

    static void testFilterByGroup(WebDriver driver, String group, String option) throws InterruptedException {
        System.out.println("\nTest Filter: " + group + " → " + option);

        int before = getProductCount(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(),'" + option + "')]")
        ));

        scrollAndClick(driver, opt);
        waitForProductChanged(driver, before);

        System.out.println("PASS – Lọc " + option + " hiển thị " + getProductCount(driver) + " sản phẩm");
        removeFilter(driver);
    }

    static void testFilterColorAlone(WebDriver driver, String color) throws InterruptedException {
        System.out.println("\nTest Lọc riêng: Màu → " + color);

        int before = getProductCount(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(),'" + color + "')]")
        ));

        scrollAndClick(driver, opt);
        waitForProductChanged(driver, before);

        System.out.println("PASS – Chỉ lọc màu " + color + " hiển thị: " + getProductCount(driver));
        removeFilter(driver);
    }

    static void testFilterSizeAlone(WebDriver driver, String size) throws InterruptedException {
        System.out.println("\nTest Lọc riêng: Size → " + size);

        int before = getProductCount(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='" + size + "']")
        ));

        scrollAndClick(driver, opt);
        waitForProductChanged(driver, before);

        System.out.println("PASS – Chỉ lọc size " + size + " hiển thị: " + getProductCount(driver));
        removeFilter(driver);
    }

    static void testFilterPriceSlider(WebDriver driver) throws InterruptedException {
        System.out.println("\nTest Filter Giá – Slider");

        int before = getProductCount(driver);

        WebElement slider = driver.findElement(By.id("slider-range"));
        List<WebElement> handles = slider.findElements(By.cssSelector(".ui-slider-handle"));

        new Actions(driver).clickAndHold(handles.get(0)).moveByOffset(40, 0).release().perform();
        new Actions(driver).clickAndHold(handles.get(1)).moveByOffset(-40, 0).release().perform();

        waitForProductChanged(driver, before);

        System.out.println("PASS – Lọc giá bằng slider trả về: " + getProductCount(driver));
        removeFilter(driver);
    }

    static void testNoResult(WebDriver driver) throws InterruptedException {
        System.out.println("\nTest Không Có Kết Quả");

        scrollAndClick(driver, driver.findElement(By.xpath("//label[contains(text(),'Đỏ')]")));
        scrollAndClick(driver, driver.findElement(By.xpath("//label[contains(text(),'Áo sơ mi dài tay')]")));

        Thread.sleep(1500);

        int cnt = getProductCount(driver);

        if (cnt == 0)
            System.out.println("PASS – Hiển thị không có kết quả");
        else
            System.out.println("FAIL – Vẫn có sản phẩm khi đáng lẽ phải 0");

        removeFilter(driver);
    }

    static void testFilterReset(WebDriver driver) throws InterruptedException {
        System.out.println("\nTest Nút XOÁ BỘ LỌC");

        scrollAndClick(driver, driver.findElement(By.xpath("//label[contains(text(),'Đen')]")));
        Thread.sleep(600);

        List<WebElement> removeAllBtn = driver.findElements(By.cssSelector(".filter-tags.remove-all"));
        if (!removeAllBtn.isEmpty()) scrollAndClick(driver, removeAllBtn.get(0));

        Thread.sleep(800);

        if (driver.findElements(By.cssSelector(".filter-tags b")).stream().allMatch(e -> e.getText().isEmpty()))
            System.out.println("PASS – Nút XOÁ BỘ LỌC reset toàn bộ filter");
        else
            System.out.println("FAIL – Filter không reset");
    }

    static void testFiltersIndividually(WebDriver driver) throws InterruptedException {
        List<String[]> filters = Arrays.asList(
                new String[]{"Nhãn hàng", "Aristino"},
                new String[]{"Màu sắc", "Đen"},
                new String[]{"Kích cỡ", "L"},
                new String[]{"Sản phẩm", "Áo Polo dài tay"},
                new String[]{"Form dáng", "Slim fit"},
                new String[]{"Giá", "Slider"}
        );

        for (String[] f : filters) {
            String group = f[0];
            String option = f[1];

            removeFilter(driver);

            System.out.println("\n===== Test riêng: " + group + " → " + option + " =====");

            if (group.equals("Màu sắc")) testFilterColorAlone(driver, option);
            else if (group.equals("Kích cỡ")) testFilterSizeAlone(driver, option);
            else if (group.equals("Nhãn hàng") || group.equals("Sản phẩm") || group.equals("Form dáng"))
                testFilterByGroup(driver, group, option);
            else if (group.equals("Giá")) testFilterPriceSlider(driver);
        }
    }

    static void testFiltersCombined(WebDriver driver) throws InterruptedException {
        System.out.println("\n===== Test tất cả filter kết hợp =====");

        int before = getProductCount(driver);

        scrollAndClick(driver, driver.findElement(By.xpath("//label[contains(text(),'Aristino')]")));
        scrollAndClick(driver, driver.findElement(By.xpath("//label[contains(text(),'Đen')]")));
        scrollAndClick(driver, driver.findElement(By.xpath("//label[text()='L']")));
        scrollAndClick(driver, driver.findElement(By.xpath("//label[contains(text(),'Áo Polo dài tay')]")));
        scrollAndClick(driver, driver.findElement(By.xpath("//label[contains(text(),'Slim fit')]")));

        WebElement slider = driver.findElement(By.id("slider-range"));
        List<WebElement> handles = slider.findElements(By.cssSelector(".ui-slider-handle"));
        new Actions(driver).clickAndHold(handles.get(0)).moveByOffset(30, 0).release().perform();
        new Actions(driver).clickAndHold(handles.get(1)).moveByOffset(-30, 0).release().perform();

        waitForProductChanged(driver, before);

        System.out.println("PASS – Lọc tổng hợp trả về: " + getProductCount(driver));

        removeFilter(driver);
    }

    //  TEST 2 – CHAT + FAQ (COPY Y NGUYÊN FILE 2 CỦA BẠN)
    public static void testChatFAQ() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            Actions actions = new Actions(driver);

            // 1. TEST LIÊN HỆ
            driver.get("https://aristino.com/");
            Thread.sleep(1500);

            System.out.println("\n=== TEST LIÊN HỆ ===");

            By contactIconSelector = By.cssSelector(".box-item.box-contact .svgico");
            WebElement contactIcon = wait.until(ExpectedConditions.presenceOfElementLocated(contactIconSelector));

            if (contactIcon.isDisplayed()) {
                System.out.println("PASS - Icon liên hệ hiển thị");
            } else {
                System.out.println("FAIL - Không tìm thấy icon liên hệ");
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", contactIcon);
            Thread.sleep(1200);

            By menuSelector = By.cssSelector(".sidebar-item--icon.cta-submitform");
            WebElement menu = driver.findElement(menuSelector);
            if (menu.isDisplayed()) {
                System.out.println("PASS - Menu liên hệ mở");
            } else {
                System.out.println("FAIL - Menu liên hệ KHÔNG mở");
            }

            checkBtn(driver, "Facebook", "a[href*='facebook']");
            checkBtn(driver, "Zalo", "a[href*='zalo']");
            checkBtn(driver, "Khiếu nại", "a[href*='khieu']");
            checkBtn(driver, "Tìm cửa hàng", "a[href*='store']");

            // Đóng menu
            By closeMenuSelector = By.cssSelector(".svgico--close");
            WebElement closeMenu = driver.findElement(closeMenuSelector);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeMenu);
            System.out.println("PASS - Đã đóng menu liên hệ");

            // 2. TEST FAQ
            System.out.println("\n=== TEST FAQ ===");
            driver.get("https://aristino.com/pages/noi-dung-faq");
            Thread.sleep(1500);

            By questionSelector = By.cssSelector(".accordion-toggle");

            List<WebElement> questions = driver.findElements(questionSelector);

            if (questions.size() > 0) {
                System.out.println("PASS - FAQ hiển thị (" + questions.size() + " câu)");
            } else {
                System.out.println("FAIL - Không tìm thấy FAQ");
                return;
            }

            WebElement firstQuestion = questions.get(0);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstQuestion);
            Thread.sleep(300);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstQuestion);
            Thread.sleep(600);

            WebElement item = firstQuestion.findElement(By.xpath("./.."));
            WebElement firstAnswer = item.findElement(By.cssSelector(".accordion-panel"));

            String classVal = item.getAttribute("class");
            String answerText = firstAnswer.getText().trim();

            if (classVal.contains("active") && answerText.length() > 5) {
                System.out.println("PASS - Mở FAQ thành công");
                System.out.println("Nội dung: " + answerText);
            } else {
                System.out.println("FAIL - Không mở được FAQ");
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstQuestion);
            Thread.sleep(400);

            classVal = item.getAttribute("class");
            if (!classVal.contains("active")) {
                System.out.println("PASS - Đóng FAQ thành công");
            } else {
                System.out.println("FAIL - Không đóng được FAQ");
            }

            // 3. FAQ trống
            System.out.println("\n=== TEST FAQ TRỐNG ===");
            ((JavascriptExecutor) driver).executeScript(
                    "document.body.innerHTML = '<div class=\"faq-empty\">Không có FAQ</div>'"
            );

            WebElement faqEmpty = driver.findElement(By.xpath("//*[text()='Không có FAQ']"));
            if (faqEmpty.isDisplayed()) {
                System.out.println("PASS - Hiển thị FAQ trống");
            } else {
                System.out.println("FAIL - Không hiển thị FAQ trống");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            driver.quit();
        }
    }

    private static void checkBtn(WebDriver driver, String name, String selector) {
        List<WebElement> btns = driver.findElements(By.cssSelector(selector));
        if (!btns.isEmpty() && btns.get(0).isDisplayed()) {
            System.out.println("PASS - " + name + " hiển thị");
        } else {
            System.out.println("FAIL - " + name + " KHÔNG hiển thị");
        }
    }
}
