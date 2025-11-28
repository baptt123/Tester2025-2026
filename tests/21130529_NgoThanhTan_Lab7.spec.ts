import { test, expect } from '@playwright/test';

// --- CẤU HÌNH CHUNG CHO TOÀN BỘ FILE ---
// Giả lập trình duyệt Chrome thật để tránh bị Google chặn và ẩn dòng Automation
test.use({
    userAgent: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36',
    locale: 'en-US',
    launchOptions: {
        args: ['--disable-blink-features=AutomationControlled']
    }
});

// --- XỬ LÝ POPUP TỰ ĐỘNG (ÁP DỤNG CHO MỌI TEST CASE) ---
test.beforeEach(async ({ page }) => {
    // 1. Handler cho nút "Bỏ qua"
    await page.addLocatorHandler(
        page.getByRole('button', { name: 'Bỏ qua' }),
        async () => {
            console.log('>>> [Auto] Phát hiện nút Bỏ qua -> Click...');
            await page.getByRole('button', { name: 'Bỏ qua' }).click();
        }
    );

    // 2. Handler cho Popup Quảng cáo (Nút X)
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            console.log('>>> [Auto] Phát hiện Popup quảng cáo -> Đóng...');
            await popupCloseLoc.click({ force: true });
        }
    );
});

// ============================================================
// CÁC TEST CASE (Đã được làm sạch, chỉ giữ lại logic chính)
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
    await page.waitForTimeout(3000);

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
    await page.waitForTimeout(3000);

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
    await page.waitForTimeout(3000);

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
    await page.goto('https://aristino.com/');

    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();

    // Click vào sản phẩm cụ thể
    await page.locator('.pro-tile.pro-loop.pro-t1.\\32 0 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();

    // Click nút Thêm vào giỏ
    await page.locator('#btn-addtocart').click();
});

/*
 * Test case thêm sản phẩm khi hết hàng
 */

test('test navigate product with auto-close popups', async ({ page }) => {
    // 1. Vào trang web
    await page.goto('https://aristino.com/');

    // 2. CÀI ĐẶT TỰ ĐỘNG XỬ LÝ POPUP ("Lính gác")
    // Bất cứ khi nào các nút này hiện lên chắn đường, Playwright sẽ tự dừng lại để click chúng

    // Xử lý nút "Bỏ qua"
    await page.addLocatorHandler(
        page.getByRole('button', { name: 'Bỏ qua' }),
        async () => {
            await page.getByRole('button', { name: 'Bỏ qua' }).click();
            console.log('>>> Đã tự động click Bỏ qua');
        }
    );

    // Xử lý nút đóng Popup quảng cáo (Dấu X)
    const popupClose = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupClose,
        async () => {
            await popupClose.click();
            console.log('>>> Đã tự động đóng Popup quảng cáo');
        }
    );

    // 3. LUỒNG CHÍNH (Sạch sẽ, không lo về popup nữa)
    await page.locator('a[data-title="phu-kien"]').hover();
    // Click menu "Giày & Dép"
    await page.getByRole('link', { name: 'Giày & Dép' }).click();

    // Click vào sản phẩm đầu tiên (Link ảnh không có text)
    await page.getByRole('link').filter({ hasText: /^$/ }).first().click();

    // Click vào link thứ 3 (Logo hoặc Home?)
    await page.getByRole('link').nth(3).click();
});

/*
 * Test case thêm cùng 1 sản phẩm khi vẫn ở trong trang chi tiết sản phẩm
 */
test('add product again from detail', async ({ page }) => {
    await page.goto('https://aristino.com/');

    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();

    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 5 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();

    // Click lần 1
    await page.locator('#btn-addtocart').click();

    // Click vào overlay để đóng sidebar
    await page.locator('.sidebar-overlay').click();

    // Click lần 2
    await page.locator('#btn-addtocart').click();
});

/*
 * Test case xoá sản phẩm
 */
test('delete product', async ({ page }) => {
    await page.goto('https://aristino.com/');

    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();

    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 5 > .pro-loop--wrap > .pro-loop--head > .pro-loop--buttons > .pro-action.add-to-cart').click();

    await page.locator('.item-remove > a').click();
    await page.locator('.close-sidebar').click();
    await page.getByRole('link').nth(3).click();
});

/*
 * Test case cập nhật số lượng sản phẩm trong giỏ hàng
 */
test('update quantity', async ({ page }) => {
    await page.goto('https://aristino.com/');

    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();

    // Click vào nút thêm giỏ hàng
    await page.locator('.pro-tile.pro-loop.pro-t1.\\32 9 > .pro-loop--wrap > .pro-loop--head > .pro-loop--buttons > .pro-action.add-to-cart').click();

    // Xử lý sidebar giỏ hàng
    await page.getByRole('button').filter({ hasText: /^$/ }).nth(1).click();
    await page.locator('.close-sidebar').click();
});