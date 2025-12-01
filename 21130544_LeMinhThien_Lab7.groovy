import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium
import org.openqa.selenium.WebDriver
import org.testng.asserts.SoftAssert

class Full_TestSuite {

    WebDriver driver
    WebDriverBackedSelenium selenium

    // ===================== Search Test Suite =====================

    // Valid Test Cases
    def Searching_1() {
        println "=== Running Searching_1 (Valid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://aristino.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://aristino.com/")

        selenium.open("https://aristino.com/")
        selenium.click("id=js-click-search")
        selenium.type("id=inputSearchAuto","quần")
        selenium.submit("id=searchform")
    }

    def Searching_2() {
        println "=== Running Searching_2 (Valid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://aristino.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://aristino.com/")

        selenium.open("https://aristino.com/")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='ARISTINO'])[1]/following::*[name()='svg'][1]")
        selenium.type("id=inputSearchAuto","ần")
        selenium.submit("id=searchform")
    }

    def Searching_3() {
        println "=== Running Searching_3 (Valid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://aristino.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://aristino.com/")

        selenium.open("https://aristino.com/")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='ARISTINO'])[1]/following::*[name()='svg'][1]")
        selenium.type("id=inputSearchAuto","áooo")
        selenium.submit("id=searchform")
    }

    def Searching_4() {
        println "=== Running Searching_4 (Valid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://aristino.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://aristino.com/")

        selenium.open("https://aristino.com/")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='ARISTINO'])[1]/following::*[name()='svg'][1]")
        selenium.type("id=inputSearchAuto","sui")
        selenium.submit("id=searchform")
    }

    // Invalid Test Cases
    def Searching_6() {
        println "=== Running Searching_6 (Invalid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://aristino.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://aristino.com/")

        selenium.open("https://aristino.com/")
        selenium.click("css=#js-click-search > svg > path")
        selenium.type("id=inputSearchAuto","qu ần")
        selenium.submit("id=searchform")
    }

    def Searching_7() {
        println "=== Running Searching_7 (Invalid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://aristino.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://aristino.com/")

        selenium.open("https://aristino.com/")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='ARISTINO'])[1]/following::*[name()='svg'][1]")
        selenium.type("id=inputSearchAuto","$uits")
        selenium.submit("id=searchform")
    }

    def Searching_8() {
        println "=== Running Searching_8 (Invalid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://aristino.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://aristino.com/")

        selenium.open("https://aristino.com/")
        selenium.click("id=js-click-search")
        selenium.type("id=inputSearchAuto","áo1")
        selenium.submit("id=searchform")
    }

    // Special Test Case
    def Searching_5() {
        println "=== Running Searching_5 (Special) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://aristino.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://aristino.com/")

        selenium.open("https://aristino.com/")
        selenium.click("css=#js-click-search > svg > path")
        selenium.click("id=inputSearchAuto")
        selenium.click("id=js-btn-search")
    }

    // ===================== Cart Sync Test Suite =====================

    // Valid
    def check_CartSync1() {
        println "=== Running check_CartSync1 (Valid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://www.google.com/')
        driver = DriverFactory.getWebDriver()
        selenium = new WebDriverBackedSelenium(driver, "https://www.google.com/")

        selenium.open("https://aristino.com/")
        selenium.click("css=li.action-account > a > svg > path")
        selenium.type("id=login-email", ("concocon198thien@gmail.com").toString())
        selenium.type("id=password", ("Minhthien123@").toString())
        selenium.click("xpath=//button[@value='ĐĂNG NHẬP']")
        selenium.click("link=TRANG PHỤC")
        selenium.click("xpath=//div[@id='collection-page']/div/div[2]/div/div/div[2]/div[2]/div/div[6]/div/div/a")
        selenium.click("xpath=//button[@id='btn-addtocart']/span")
        Thread.sleep(5000)
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Gửi cho chúng tôi'])[1]/following::*[name()='svg'][2]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='SUPPORT'])[1]/following::*[name()='svg'][4]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Xin Chào, Lê Thiện!'])[1]/following::span[1]")
    }

    def check_CartSync2() {
        println "=== Running check_CartSync2 (Valid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://www.google.com/')
        driver = DriverFactory.getWebDriver()
        String baseUrl = "https://www.google.com/"
        selenium = new WebDriverBackedSelenium(driver, baseUrl)

        selenium.open("https://aristino.com/")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='ARISTINO'])[1]/following::*[name()='svg'][3]")
        selenium.click("id=login-email")
        selenium.type("id=login-email", ("concocon198thien@gmail.com").toString())
        selenium.type("id=password", ("Minhthien123@").toString())
        selenium.click("xpath=//button[@value='ĐĂNG NHẬP']")
        selenium.click("xpath=//a[@id='js-click-cartmini']/span")
        Thread.sleep(3000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("link=TRANG PHỤC")
        selenium.click("xpath=//div[@id='collection-page']/div/div[2]/div/div/div[2]/div[2]/div/div[11]/div/div/a")
        selenium.click("id=btn-addtocart")
        Thread.sleep(5000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("link=TRANG PHỤC")
        selenium.click("xpath=//div[@id='collection-page']/div/div[2]/div/div/div[2]/div[2]/div/div[22]/div/div/a")
        selenium.click("id=btn-addtocart")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Áo Khoác 2 Lớp Nam Xanh Rêu Aristino Regular Fit AJK601EDP01'])[2]/following::*[name()='svg'][3]")
        Thread.sleep(3000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Gửi cho chúng tôi'])[1]/following::*[name()='svg'][2]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='SUPPORT'])[1]/following::*[name()='svg'][4]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Xin Chào, Lê Thiện!'])[1]/following::span[1]")
    }

    def check_CartSync3() {
        println "=== Running check_CartSync3 (Valid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://www.google.com/')
        driver = DriverFactory.getWebDriver()
        String baseUrl = "https://www.google.com/"
        selenium = new WebDriverBackedSelenium(driver, baseUrl)

        selenium.open("https://aristino.com/")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='ARISTINO'])[1]/following::*[name()='svg'][3]")
        selenium.click("id=login-email")
        selenium.type("id=login-email", ("concocon198thien@gmail.com").toString())
        selenium.click("id=password")
        selenium.type("id=password", ("Minhthien123@").toString())
        selenium.click("xpath=//button[@value='ĐĂNG NHẬP']")
        selenium.click("xpath=//a[@id='js-click-cartmini']/span")
        Thread.sleep(5000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("link=TRANG PHỤC")
        selenium.click("xpath=//div[@id='collection-page']/div/div[2]/div/div/div[2]/div[2]/div/div[6]/div/div/a")
        selenium.click("xpath=//button[@id='btn-addtocart']/span")
        Thread.sleep(3000);
        selenium.click("css=div.item-remove > a > svg > path")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='[Bigsize Us] Áo Polo Active thể thao Đen Nam Aristino tay ngắn phù hợp di chuyển APS003EGP01'])[2]/following::*[name()='svg'][3]")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Gửi cho chúng tôi'])[1]/following::*[name()='svg'][2]")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='SUPPORT'])[1]/following::*[name()='svg'][4]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Xin Chào, Lê Thiện!'])[1]/following::span[1]")
    }

    def check_CartSync4() {
        println "=== Running check_CartSync4 (Valid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://www.google.com/')
        driver = DriverFactory.getWebDriver()
        String baseUrl = "https://www.google.com/"
        selenium = new WebDriverBackedSelenium(driver, baseUrl)

        selenium.open("https://aristino.com/")
        selenium.click("xpath=//header[@id='mainHeader']/div/div/div/div[3]/div/ul/li[2]/a")
        selenium.click("id=login-email")
        selenium.type("id=login-email", ("concocon198thien@gmail.com").toString())
        selenium.type("id=password", ("Minhthien123@").toString())
        selenium.click("xpath=//button[@value='ĐĂNG NHẬP']")
        selenium.click("css=#js-click-cartmini > svg > path")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Áo Khoác 2 Lớp Nam Xanh Rêu Aristino Regular Fit AJK601EDP01'])[1]/following::*[name()='svg'][3]")
        Thread.sleep(5000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Xin Chào, Lê Thiện!'])[1]/following::span[1]")
    }

    // ===================== Invalid Test Cases =====================

    def check_CartSync5() {
        println "=== Running check_CartSync5 (Invalid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://www.google.com/')
        driver = DriverFactory.getWebDriver()
        String baseUrl = "https://www.google.com/"
        selenium = new WebDriverBackedSelenium(driver, baseUrl)

        selenium.open("chrome://newtab/")
        selenium.open("https://aristino.com/")
        selenium.click("link=TRANG PHỤC")
        selenium.click("xpath=//div[@id='collection-page']/div/div[2]/div/div/div[2]/div[2]/div/div[26]/div/div/a")
        selenium.click("xpath=//button[@id='btn-addtocart']/span")
        Thread.sleep(5000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("css=li.action-account > a > svg > path")
        selenium.deleteCookie("all", "")
        WebUI.executeJavaScript("window.localStorage.clear();", null)
        WebUI.executeJavaScript("window.sessionStorage.clear();", null)
        selenium.refresh()
        selenium.waitForPageToLoad("30000")
        selenium.click("id=login-email")
        selenium.type("id=login-email", ("concocon198thien@gmail.com").toString())
        selenium.type("id=password", ("Minhthien123@").toString())
        selenium.click("xpath=//button[@value='ĐĂNG NHẬP']")
        Thread.sleep(3000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='SUPPORT'])[1]/following::*[name()='svg'][6]")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Áo Elite Polo Nam Xanh Tím Than Aristino Cotton Organic APS165S3EC'])[1]/following::*[name()='svg'][2]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Áo Elite Polo Nam Xanh Tím Than Aristino Cotton Organic APS165S3EC'])[1]/following::*[name()='svg'][3]")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Gửi cho chúng tôi'])[1]/following::*[name()='svg'][2]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Xin Chào, Lê Thiện!'])[1]/following::span[1]")
    }

    def check_CartSync6() {
        println "=== Running check_CartSync6 (Invalid) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://www.google.com/')
        driver = DriverFactory.getWebDriver()
        String baseUrl = "https://www.google.com/"
        selenium = new WebDriverBackedSelenium(driver, baseUrl)

        selenium.open("chrome://newtab/")
        selenium.open("https://aristino.com/")
        selenium.click("xpath=//header[@id='mainHeader']/div/div/div/div[3]/div/ul/li[2]/a")
        selenium.click("id=login-email")
        selenium.type("id=login-email", ("concocon198thien@gmail.com").toString())
        selenium.type("id=password", ("Minhthien123@").toString())
        selenium.click("xpath=//button[@value='ĐĂNG NHẬP']")
        selenium.click("link=TRANG PHỤC")
        selenium.click("xpath=//div[@id='collection-page']/div/div[2]/div/div/div[2]/div[2]/div/div[6]/div/div/a")
        selenium.click("id=btn-addtocart")
        Thread.sleep(5000);
        selenium.click("css=button.qtyplus-mini.qty-btn > svg > path")
        Thread.sleep(2000);
        selenium.click("css=button.qtyplus-mini.qty-btn > svg > path")
        Thread.sleep(2000);
        selenium.click("css=button.qtyminus-mini.qty-btn > svg > path")
        Thread.sleep(2000);
        selenium.click("css=button.qtyminus-mini.qty-btn > svg > path")
        Thread.sleep(2000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Áo Khoác 2 Lớp Nam Xanh Rêu Aristino Regular Fit AJK601EDP01'])[1]/following::*[name()='svg'][1]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Gửi cho chúng tôi'])[1]/following::*[name()='svg'][2]")
        selenium.click("css=li.action-account > a > svg > path")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Xin Chào, Lê Thiện!'])[1]/following::span[1]")
        selenium.open("https://aristino.com/")
        selenium.click("css=li.action-account > a > svg > path")
        selenium.click("id=login-email")
        selenium.type("id=login-email", ("concocon198thien@gmail.com").toString())
        selenium.type("id=password", ("Minhthien123@").toString())
        selenium.click("xpath=//button[@value='ĐĂNG NHẬP']")
        Thread.sleep(2000);
        selenium.open("https://aristino.com/account?logged=true")
        selenium.click("xpath=//a[@id='js-click-cartmini']/span")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Áo Khoác 2 Lớp Nam Xanh Rêu Aristino Regular Fit AJK601EDP01'])[1]/following::*[name()='svg'][3]")
        Thread.sleep(3000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Gửi cho chúng tôi'])[1]/following::*[name()='svg'][2]")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Xin Chào, Lê Thiện!'])[1]/following::span[1]")
        selenium.open("https://aristino.com/")
    }

    // ===================== Special Test Case =====================

    def check_CartSync7() {
        println "=== Running check_CartSync7 (Special) ==="
        SoftAssert softAssertion = new SoftAssert()
        WebUI.openBrowser('https://www.google.com/')
        driver = DriverFactory.getWebDriver()
        String baseUrl = "https://www.google.com/"
        selenium = new WebDriverBackedSelenium(driver, baseUrl)

        selenium.open("https://aristino.com/")
        selenium.click("link=TRANG PHỤC")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='NEW'])[6]/following::*[name()='svg'][5]")
        Thread.sleep(5000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='NEW'])[16]/following::*[name()='svg'][5]")
        Thread.sleep(5000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='NEW'])[23]/following::*[name()='svg'][5]")
        Thread.sleep(7000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Áo Khoác 2 Lớp Nam Xanh Rêu Aristino Regular Fit AJK601EDP01'])[2]/following::*[name()='svg'][3]")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Áo Polo Nam Trắng Aristino Golf ALPG06BS2'])[2]/following::*[name()='svg'][3]")
        Thread.sleep(5000);
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Quần Jeans Nam Xanh Nhạt Hiệu Ứng Giặt Mài Aristino AJN0060S0'])[2]/following::*[name()='svg'][3]")
        Thread.sleep(5000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("xpath=//header[@id='mainHeader']/div/div/div/div[3]/div/ul/li[2]/a")
        selenium.click("id=login-email")
        selenium.type("id=login-email", ("concocon198thien@gmail.com").toString())
        selenium.type("id=password", ("Minhthien123@").toString())
        selenium.click("xpath=//button[@value='ĐĂNG NHẬP']")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='SUPPORT'])[1]/following::*[name()='svg'][6]")
        Thread.sleep(5000);
        selenium.click("css=button.close-sidebar > svg > path")
        selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Xin Chào, Lê Thiện!'])[1]/following::span[1]")
    }

    // ===================== Run full Combined Test Suite =====================
    static void main(String[] args) {
        Full_TestSuite suite = new Full_TestSuite()

        // ===================== Search Test Suite =====================
        // Valid
        suite.Searching_1()
        suite.Searching_2()
        suite.Searching_3()
        suite.Searching_4()
        // Invalid
        suite.Searching_6()
        suite.Searching_7()
        suite.Searching_8()
        // Special
        suite.Searching_5()
        println "=== Search Test Suite Completed ==="

        // ===================== Cart Sync Test Suite =====================
        // Valid
        suite.check_CartSync1()
        suite.check_CartSync2()
        suite.check_CartSync3()
        suite.check_CartSync4()
        // Invalid
        suite.check_CartSync5()
        suite.check_CartSync6()
        // Special
        suite.check_CartSync7()
        println "=== Cart Sync Test Suite Completed ==="
    }
}
