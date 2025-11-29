import { test, expect } from '@playwright/test';

// --- CẤU HÌNH CHUNG CHO TOÀN BỘ FILE ---
test.use({
    userAgent: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36',
    locale: 'en-US',
    launchOptions: {
        args: ['--disable-blink-features=AutomationControlled']
    }
});

// --- XỬ LÝ POPUP TỰ ĐỘNG (ÁP DỤNG CHO MỌI TEST CASE) ---
test.beforeEach(async ({ page }) => {
    // --- 1. Handler cho nút "Bỏ qua" (Thường là intro/tutorial) ---
    // Mẹo: Dùng .first() hoặc .last() nếu có nhiều nút trùng tên ẩn bên dưới
    const skipButton = page.getByRole('button', { name: 'Bỏ qua' });

    await page.addLocatorHandler(skipButton, async () => {
        try {
            console.log('>>> [Auto] Phát hiện nút "Bỏ qua" -> Đang xử lý...');
            // Click và đợi nút này biến mất để đảm bảo overlay đã tắt hẳn
            await skipButton.click();
            await skipButton.waitFor({ state: 'hidden', timeout: 5000 });
            console.log('>>> [Auto] Đã bỏ qua thành công.');
        } catch (error) {
            console.log('>>> [Auto] Nút "Bỏ qua" đã tự biến mất hoặc không click được.');
        }
    });

    // --- 2. Handler cho Popup Quảng cáo (Nút X) ---
    // LƯU Ý: Class 'sc-m09mre-0' rất dễ thay đổi. Nên tìm selector cha hoặc thuộc tính khác.
    // Ví dụ: Tìm nút đóng nằm trong modal quảng cáo, hoặc dùng CSS attribute selector
    // Dưới đây tôi dùng selector gộp (class có chứa chuỗi CloseButton...) để bền hơn.
    const closeAdButton = page.locator('button[class*="CloseButtonWrapper"], .styled__CloseButtonWrapper-sc-m09mre-0');

    await page.addLocatorHandler(closeAdButton, async () => {
        try {
            console.log('>>> [Auto] Phát hiện Popup Quảng cáo -> Đang đóng...');

            // Dùng force: true vì đôi khi nút X bị hiệu ứng làm mờ che khuất
            if (await closeAdButton.isVisible()) {
                await closeAdButton.click({ force: true });
                // Quan trọng: Đợi nút X biến mất để xác nhận popup đã đóng
                await closeAdButton.waitFor({ state: 'hidden', timeout: 5000 });
            }
            console.log('>>> [Auto] Đã đóng popup quảng cáo.');
        } catch (error) {
            console.log('>>> [Auto] Popup quảng cáo đã tự tắt hoặc lỗi click.');
        }
    });
});

// ============================================================
// CÁC TEST CASE (GIỮ NGUYÊN NHƯ CŨ)
// ============================================================

/*
 * Test case đăng nhập Google thành công
 */
test('test login google', async ({ page }) => {
    // --- Luồng chính ---
    await page.goto('https://aristino.com/');

    // Click vào icon tài khoản (Link thứ 5)
    await page.getByRole('link').nth(5).click();

    // Chờ 3 giây để popup ở trang login hiện lên -> Handler ở beforeEach sẽ tự xử lý
    await page.waitForTimeout(6000);

    // Chuẩn bị bắt sự kiện cửa sổ Google
    const page1Promise = page.waitForEvent('popup');

    // Click chọn "Đăng nhập Google"
    await page.getByRole('link', { name: 'Đăng nhập Google' }).click();

    const page1 = await page1Promise;

    // --- Thao tác trên cửa sổ Google ---
    await page1.getByRole('textbox', { name: 'Email or phone' }).fill('nttan123test@gmail.com');
    await page1.getByRole('button', { name: 'Next' }).click();

    await page1.getByRole('textbox', { name: 'Enter your password' }).waitFor();
    await page1.getByRole('textbox', { name: 'Enter your password' }).fill('Test1234@');

    await page1.getByRole('button', { name: 'Next' }).click();

    // Chờ login xong
    await page1.waitForEvent('close');
});

/*
 * Test case huỷ đăng nhập Google
 */
test('test case cancel login google', async ({ page }) => {
    await page.goto('https://aristino.com/');

    await page.getByRole('link').nth(5).click();

    // Chờ popup login hiện và tự tắt
    await page.waitForTimeout(10000);

    const page1Promise = page.waitForEvent('popup');
    await page.getByRole('link', { name: 'Đăng nhập Google' }).click();
    const page1 = await page1Promise;

    // Đóng cửa sổ Google
    await page1.close();
});

/*
 * Test case đăng nhập Google với mật khẩu sai
 */
test('test aristino login google bypass security', async ({ page }) => {
    await page.goto('https://aristino.com/');

    await page.getByRole('link').nth(5).click();

    // Chờ popup login hiện và tự tắt
    await page.waitForTimeout(10000);

    const page1Promise = page.waitForEvent('popup');
    await page.getByRole('link', { name: 'Đăng nhập Google' }).click();

    const page1 = await page1Promise;

    // --- Thao tác trên cửa sổ Google ---
    await page1.getByRole('textbox', { name: 'Email or phone' }).fill('nttan123test@gmail.com');
    await page1.getByRole('button', { name: 'Next' }).click();

    await page1.getByRole('textbox', { name: 'Enter your password' }).waitFor();
    await page1.getByRole('textbox', { name: 'Enter your password' }).fill('A9f!vR2$qL'); // Pass sai

    await page1.getByRole('button', { name: 'Next' }).click();
});

/*
 * Test case thêm sản phẩm
 */
test('add-product', async ({ page }) => {
    await page.goto('https://aristino.com/');

    // Click vào menu TRANG PHỤC
    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();

    // Click vào nút "Thêm vào giỏ"
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 5 > .pro-loop--wrap > .pro-loop--head > .pro-loop--buttons > .pro-action.add-to-cart').click();

    await page.locator('.item-remove > a').click();
    await page.locator('.close-sidebar').click();
});

/*
 * Test case thêm sản phẩm từ trang chi tiết
 */
test('add product from product detail', async ({ page }) => {
    // 1. Vào trang chủ
    await page.goto('https://aristino.com/');

    // 2. Click vào link Trang phục
    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();

    // 3. Click vào sản phẩm cụ thể
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 2 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();

    // 4. Click nút Thêm vào giỏ
    await page.locator('#btn-addtocart').click();
});

/*
 * Test case thêm sản phẩm khi hết hàng
 */
test('add-product-out-of-stock', async ({ page }) => {
    // 1. Vào trang web
    await page.goto('https://aristino.com/');
    // 3. LUỒNG CHÍNH
    await page.locator('a[data-title="phu-kien"]').hover();
    // Click menu "Giày & Dép"
    await page.getByRole('link', { name: 'Giày & Dép' }).click();

    // Click vào sản phẩm đầu tiên
    await page.getByRole('link').filter({ hasText: /^$/ }).first().click();

    // Click vào link thứ 3
    await page.getByRole('link').nth(3).click();
});

/*
 * Test case thêm cùng 1 sản phẩm khi vẫn ở trong trang chi tiết sản phẩm
 */
test('add-2-products', async ({ page }) => {
    await page.goto('https://aristino.com/');
    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();
    await page.locator('.pro-tile.pro-loop.pro-t1.\\32 9 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();
    await page.locator('#btn-addtocart').click();
    await page.getByRole('link', { name: 'TRANG PHỤC', exact: true }).click();
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 5 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();
    await page.locator('#btn-addtocart').click();
});

/*
 * Test case xoá sản phẩm
 */
// --- TEST CASE CHÍNH (Đã làm sạch các bước click popup thủ công) ---
test('test add and remove product', async ({ page }) => {
    // 1. Vào trang chủ
    await page.goto('https://aristino.com/');

    // *Lưu ý: Không cần viết lệnh click popup nữa, beforeEach sẽ tự canh chừng

    // 2. Click menu Trang Phục
    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();

    // 3. Chọn sản phẩm (Selector từ code gốc của bạn)
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 6 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();

    // 4. Thêm vào giỏ hàng
    await page.locator('#btn-addtocart').click();

    // 5. Xóa sản phẩm khỏi giỏ (Icon thùng rác)
    await page.locator('.item-remove > a').click();

    // 6. Đóng sidebar giỏ hàng
    await page.locator('.close-sidebar').click();
});

/*
 * Test case cập nhật số lượng sản phẩm trong giỏ hàng
 */
test('update-quantity', async ({ page }) => {
    await page.goto('https://aristino.com/');
    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 6 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();
    await page.locator('#btn-addtocart').click();
    await page.getByRole('button').filter({ hasText: /^$/ }).nth(1).click();
    await page.locator('.close-sidebar').click();
});