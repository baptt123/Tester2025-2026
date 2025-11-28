import {test, expect} from '@playwright/test';
/*
test case đăng nhập google thành công
 */
// --- PHẦN QUAN TRỌNG NHẤT ĐỂ QUA MẶT GOOGLE ---
// Cấu hình để Playwright giả làm trình duyệt Chrome thật
test.use({
    userAgent: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36',
    locale: 'en-US',
    // Ẩn dòng chữ "Chrome is being controlled by automated test software"
    launchOptions: {
        args: ['--disable-blink-features=AutomationControlled']
    }
});

test('test login google', async ({ page }) => {
    // --- 1. Đăng ký Handler: Nút "Bỏ qua" ---
    await page.addLocatorHandler(
        page.getByRole('button', { name: 'Bỏ qua' }),
        async () => {
            console.log('>>> Phát hiện nút Bỏ qua -> Click...');
            await page.getByRole('button', { name: 'Bỏ qua' }).click();
        }
    );

    // --- 2. Đăng ký Handler: Popup Quảng cáo ---
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            console.log('>>> Phát hiện Popup quảng cáo -> Đóng...');
            await popupCloseLoc.click({ force: true });
        }
    );

    // --- 3. Luồng chính ---

    await page.goto('https://aristino.com/');

    // Click vào icon tài khoản (Link thứ 5)
    await page.getByRole('link').nth(5).click();

    // Chờ 3 giây để đảm bảo popup ở trang đăng nhập hiện hết lên -> Handler tự xử lý
    await page.waitForTimeout(3000);

    // Chuẩn bị bắt sự kiện cửa sổ Google
    const page1Promise = page.waitForEvent('popup');

    // Click chọn "Đăng nhập Google"
    await page.getByRole('link', { name: 'Đăng nhập Google' }).click();

    const page1 = await page1Promise;

    // --- Thao tác trên cửa sổ Google ---

    // Điền Email
    await page1.getByRole('textbox', { name: 'Email or phone' }).fill('nttan123test@gmail.com');
    await page1.getByRole('button', { name: 'Next' }).click();
    // Điền Pass
    await page1.getByRole('textbox', { name: 'Enter your password' }).waitFor();
    await page1.getByRole('textbox', { name: 'Enter your password' }).fill('Test1234@');

    await page1.getByRole('button', { name: 'Next' }).click();

    // Chờ login xong
    await page1.waitForEvent('close');
});
/*
test case huỷ đăng nhập google
 */
// Thêm cấu hình này để tránh lỗi Google chặn (nếu bạn cần đăng nhập thật)
test.use({
    userAgent: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36',
    launchOptions: { args: ['--disable-blink-features=AutomationControlled'] }
});

test('test case cancel login google', async ({ page }) => {
    // --- 1. Đăng ký xử lý nút "Bỏ qua" ---
    await page.addLocatorHandler(
        page.getByRole('button', { name: 'Bỏ qua' }),
        async () => { await page.getByRole('button', { name: 'Bỏ qua' }).click(); }
    );

    // --- 2. Đăng ký xử lý nút đóng Popup quảng cáo ---
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => { await popupCloseLoc.click({ force: true }); }
    );

    // --- 3. Luồng chính (Giữ nguyên logic cũ của bạn) ---
    await page.goto('https://aristino.com/');

    // Click vào icon tài khoản (Link thứ 5)
    await page.getByRole('link').nth(5).click();

    // [QUAN TRỌNG] Thêm chờ 3 giây: Để cho 2 cái popup ở trang đăng nhập nó hiện hết lên
    // Handler ở trên sẽ tự động bắt và tắt chúng. Sau đó mới click được nút Google.
    await page.waitForTimeout(3000);

    const page1Promise = page.waitForEvent('popup');
    await page.getByRole('link', { name: 'Đăng nhập Google' }).click();
    const page1 = await page1Promise;

    // Giữ nguyên dòng này: Chờ popup đóng lại (do bạn tự tắt hoặc code kết thúc)
    await page1.close()
});
/*
test case đăng nhập google với mật khẩu sai
 */
// --- PHẦN QUAN TRỌNG NHẤT ĐỂ QUA MẶT GOOGLE ---
// Cấu hình để Playwright giả làm trình duyệt Chrome thật
test.use({
    userAgent: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36',
    locale: 'en-US',
    // Ẩn dòng chữ "Chrome is being controlled by automated test software"
    launchOptions: {
        args: ['--disable-blink-features=AutomationControlled']
    }
});

test('test aristino login google bypass security', async ({ page }) => {
    // --- 1. Đăng ký Handler: Nút "Bỏ qua" ---
    await page.addLocatorHandler(
        page.getByRole('button', { name: 'Bỏ qua' }),
        async () => {
            console.log('>>> Phát hiện nút Bỏ qua -> Click...');
            await page.getByRole('button', { name: 'Bỏ qua' }).click();
        }
    );

    // --- 2. Đăng ký Handler: Popup Quảng cáo ---
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            console.log('>>> Phát hiện Popup quảng cáo -> Đóng...');
            await popupCloseLoc.click({ force: true });
        }
    );

    // --- 3. Luồng chính ---

    await page.goto('https://aristino.com/');

    // Click vào icon tài khoản (Link thứ 5)
    await page.getByRole('link').nth(5).click();

    // Chờ 3 giây để đảm bảo popup ở trang đăng nhập hiện hết lên -> Handler tự xử lý
    await page.waitForTimeout(3000);

    // Chuẩn bị bắt sự kiện cửa sổ Google
    const page1Promise = page.waitForEvent('popup');

    // Click chọn "Đăng nhập Google"
    await page.getByRole('link', { name: 'Đăng nhập Google' }).click();

    const page1 = await page1Promise;

    // --- Thao tác trên cửa sổ Google ---

    // Điền Email
    await page1.getByRole('textbox', { name: 'Email or phone' }).fill('nttan123test@gmail.com');
    await page1.getByRole('button', { name: 'Next' }).click();
    // Điền Pass
    await page1.getByRole('textbox', { name: 'Enter your password' }).waitFor();
    await page1.getByRole('textbox', { name: 'Enter your password' }).fill('A9f!vR2$qL');

    await page1.getByRole('button', { name: 'Next' }).click();

    // Chờ login xong
    // await page1.waitForEvent('close');
});
/*
test case thêm sản phẩm
 */
test('add-product', async ({page}) => {
    // --- 1. Đăng ký xử lý nút "Bỏ qua" ---
    // Bất cứ khi nào nút này hiện lên chắn đường, nó sẽ tự bị click
    await page.addLocatorHandler(
        page.getByRole('button', {name: 'Bỏ qua'}),
        async () => {
            await page.getByRole('button', {name: 'Bỏ qua'}).click();
            console.log('>>> Đã tự động đóng nút Bỏ qua');
        }
    );

    // --- 2. Đăng ký xử lý nút đóng Popup (X) ---
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            await popupCloseLoc.click();
            console.log('>>> Đã tự động đóng Popup quảng cáo');
        }
    );

    // --- 3. Luồng chính (Sạch sẽ, không cần check popup thủ công) ---
    await page.goto('https://aristino.com/');

    // Click vào menu TRANG PHỤC
    // Nếu lúc này popup hiện lên, handler ở trên sẽ xử lý trước rồi mới click link này
    await page.getByRole('link', {name: 'TRANG PHỤC'}).click();

    // Click vào nút "Thêm vào giỏ" (Add to cart) của sản phẩm cụ thể
    // Selector này bạn lấy theo ID sản phẩm (35), Playwright sẽ tự xử lý popup nếu nó hiện lại ở trang này
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 5 > .pro-loop--wrap > .pro-loop--head > .pro-loop--buttons > .pro-action.add-to-cart').click();

    await page.locator('.item-remove > a').click();
    await page.locator('.close-sidebar').click();
});


/*
test case thêm sản phẩm từ trang chi tiết
 */
test('add product from product detail', async ({page}) => {
    // --- 1. Đăng ký tự động click nút "Bỏ qua" ---
    await page.addLocatorHandler(
        page.getByRole('button', {name: 'Bỏ qua'}),
        async () => {
            await page.getByRole('button', {name: 'Bỏ qua'}).click();
            console.log('>>> Đã tự động click Bỏ qua');
        }
    );

    // --- 2. Đăng ký tự động tắt Popup quảng cáo ---
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            await popupCloseLoc.click();
            console.log('>>> Đã tự động đóng Popup quảng cáo');
        }
    );

    // --- 3. Luồng chính (Đã xóa các bước click popup thủ công) ---

    await page.goto('https://aristino.com/');

    // Click vào menu TRANG PHỤC
    await page.getByRole('link', {name: 'TRANG PHỤC'}).click();

    // Click vào sản phẩm cụ thể (ID 20 - Selector của bạn)
    await page.locator('.pro-tile.pro-loop.pro-t1.\\32 0 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();

    // Click nút Thêm vào giỏ
    await page.locator('#btn-addtocart').click();
});
/*
test case thêm sản phẩm khi hết hàng
 */
test('add product when out of stock', async ({ page }) => {
    // --- 1. Đăng ký xử lý nút "Bỏ qua" ---
    // Mỗi khi nút này xuất hiện chặn đường, Playwright sẽ dừng lại để click nó
    await page.addLocatorHandler(
        page.getByRole('button', { name: 'Bỏ qua' }),
        async () => {
            await page.getByRole('button', { name: 'Bỏ qua' }).click();
            console.log('>>> Đã tự động click Bỏ qua');
        }
    );

    // --- 2. Đăng ký xử lý nút đóng Popup (X) ---
    // Sử dụng class bạn cung cấp. Lưu ý: Nếu web update code, class này có thể thay đổi.
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');

    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            // Cần đảm bảo nút này có thể click được trước khi click
            await popupCloseLoc.click();
            console.log('>>> Đã tự động tắt Popup quảng cáo');
        }
    );

    // --- 3. Luồng chính của test (Viết liền mạch) ---
    // Bạn không cần lo về popup ở dưới này nữa

    await page.goto('https://aristino.com/');

    // Click vào menu "Giày & Dép"
    await page.getByRole('link', { name: 'Giày & Dép' }).click();

    // Click vào link ảnh sản phẩm đầu tiên (Link rỗng text)
    await page.getByRole('link').filter({ hasText: /^$/ }).first().click();
});
/*
test case thêm cùng 1 sản phẩm khi vẫn ở trong trang chi tiết sản phẩm
 */
test('add product again from detail', async ({ page }) => {
    // --- 1. Đăng ký xử lý nút "Bỏ qua" ---
    // Tự động click khi nút "Bỏ qua" xuất hiện
    await page.addLocatorHandler(
        page.getByRole('button', { name: 'Bỏ qua' }),
        async () => {
            await page.getByRole('button', { name: 'Bỏ qua' }).click();
            console.log('>>> Đã tự động click Bỏ qua');
        }
    );

    // --- 2. Đăng ký xử lý nút đóng Popup quảng cáo ---
    // Tự động click khi popup quảng cáo xuất hiện
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            await popupCloseLoc.click();
            console.log('>>> Đã tự động đóng Popup quảng cáo');
        }
    );

    // --- 3. Luồng chính (Gọn gàng, không cần if/else popup nữa) ---

    // Vào trang chủ
    await page.goto('https://aristino.com/');

    // Click menu TRANG PHỤC
    await page.getByRole('link', { name: 'TRANG PHỤC' }).click();

    // Click vào sản phẩm cụ thể (ID 35)
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 5 > .pro-loop--wrap > .pro-loop--head > .pro-loop--link').click();

    // Click nút "Thêm vào giỏ" lần 1
    await page.locator('#btn-addtocart').click();

    // Click vào overlay (để đóng sidebar giỏ hàng vừa hiện ra?)
    // Lưu ý: Cần đảm bảo overlay đang visible thì mới click được
    await page.locator('.sidebar-overlay').click();

    // Click nút "Thêm vào giỏ" lần 2
    await page.locator('#btn-addtocart').click();
});
/*
test case xoá sản phẩm
 */

test('delete product', async ({page}) => {
    // --- 1. Đăng ký xử lý nút "Bỏ qua" ---
    // Bất cứ khi nào nút này hiện lên chắn đường, nó sẽ tự bị click
    await page.addLocatorHandler(
        page.getByRole('button', {name: 'Bỏ qua'}),
        async () => {
            await page.getByRole('button', {name: 'Bỏ qua'}).click();
            console.log('>>> Đã tự động đóng nút Bỏ qua');
        }
    );

    // --- 2. Đăng ký xử lý nút đóng Popup (X) ---
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            await popupCloseLoc.click();
            console.log('>>> Đã tự động đóng Popup quảng cáo');
        }
    );

    // --- 3. Luồng chính (Sạch sẽ, không cần check popup thủ công) ---
    await page.goto('https://aristino.com/');

    // Click vào menu TRANG PHỤC
    // Nếu lúc này popup hiện lên, handler ở trên sẽ xử lý trước rồi mới click link này
    await page.getByRole('link', {name: 'TRANG PHỤC'}).click();

    // Click vào nút "Thêm vào giỏ" (Add to cart) của sản phẩm cụ thể
    // Selector này bạn lấy theo ID sản phẩm (35), Playwright sẽ tự xử lý popup nếu nó hiện lại ở trang này
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 5 > .pro-loop--wrap > .pro-loop--head > .pro-loop--buttons > .pro-action.add-to-cart').click();
    // await page.pause();
    await page.locator('.item-remove > a').click();
    await page.locator('.close-sidebar').click();
    await page.getByRole('link').nth(3).click();
});
/*
test case cập nhật số lượng sản phẩm trong giỏ hàng
 */
test('update quantity', async ({page}) => {
    // --- 1. Đăng ký xử lý nút "Bỏ qua" ---
    // Bất cứ khi nào nút này hiện lên chắn đường, nó sẽ tự bị click
    await page.addLocatorHandler(
        page.getByRole('button', {name: 'Bỏ qua'}),
        async () => {
            await page.getByRole('button', {name: 'Bỏ qua'}).click();
            console.log('>>> Đã tự động đóng nút Bỏ qua');
        }
    );

    // --- 2. Đăng ký xử lý nút đóng Popup (X) ---
    const popupCloseLoc = page.locator('.styled__CloseButtonWrapper-sc-m09mre-0');
    await page.addLocatorHandler(
        popupCloseLoc,
        async () => {
            await popupCloseLoc.click();
            console.log('>>> Đã tự động đóng Popup quảng cáo');
        }
    );

    // --- 3. Luồng chính (Sạch sẽ, không cần check popup thủ công) ---
    await page.goto('https://aristino.com/');

    // Click vào menu TRANG PHỤC
    // Nếu lúc này popup hiện lên, handler ở trên sẽ xử lý trước rồi mới click link này
    await page.getByRole('link', {name: 'TRANG PHỤC'}).click();

    // Click vào nút "Thêm vào giỏ" (Add to cart) của sản phẩm cụ thể
    // Selector này bạn lấy theo ID sản phẩm (35), Playwright sẽ tự xử lý popup nếu nó hiện lại ở trang này
    await page.locator('.pro-tile.pro-loop.pro-t1.\\33 5 > .pro-loop--wrap > .pro-loop--head > .pro-loop--buttons > .pro-action.add-to-cart').click();
    // await page.pause();
    await page.getByRole('button').filter({ hasText: /^$/ }).nth(1).click();
    await page.locator('.close-sidebar').click();
    await page.getByRole('link').nth(3).click();
});
