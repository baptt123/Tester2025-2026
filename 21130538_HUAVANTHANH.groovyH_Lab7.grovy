import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import java.util.Arrays
import com.kms.katalon.core.configuration.RunConfiguration
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.JavascriptExecutor

// ===========================================================
// HELPER FUNCTIONS
// ===========================================================

TestObject makeTo(String xpath) {
	TestObject to = new TestObject()
	to.addProperty("xpath", ConditionType.EQUALS, xpath)
	return to
}

void smartClick(TestObject to) {
	try {
		WebUI.click(to)
		println("LOG: Click success")
	} catch (Exception e) {
		println("WARNING: Click intercepted. Using JS Click...")
		try {
			WebElement element = WebUiCommonHelper.findWebElement(to, 5)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
			println("LOG: JS Click success")
		} catch (Exception ex) {
			println("FAIL: Unable to click element.")
		}
	}
}

void scrollFullPage() {
	WebUI.executeJavaScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});", null)
	WebUI.delay(4) 
	WebUI.executeJavaScript("window.scrollTo({top: 0, behavior: 'smooth'});", null)
	WebUI.delay(3)
}

void handlePopup() {
	println("--------------------------------------------------")
	println("CHECKING: Waiting 10s for popup...")
	WebUI.delay(10)
	
	WebDriver driver = DriverFactory.getWebDriver()
	boolean isPopupFound = false
	
	String xpathClose = "//div[contains(@class, 'template-close')] | //*[contains(@class, 'template-close')] | //div[contains(@class, 'boWLNi')]"
	String xpathOverlay = "//div[contains(@class, 'template-wrapper')]" 

	// 1. Scan Close Buttons on Main Page
	List<WebElement> closeButtons = driver.findElements(By.xpath(xpathClose))
	for (WebElement btn : closeButtons) {
		if (btn.isDisplayed()) {
			try {
				WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(btn))
				println("Popup's exist (Closed via Button)")
				isPopupFound = true
				return
			} catch (Exception e) {}
		}
	}
	
	// 2. Remove Overlay if Close Button not clickable
	if (!isPopupFound) {
		List<WebElement> overlays = driver.findElements(By.xpath(xpathOverlay))
		if (overlays.size() > 0) {
			for (WebElement overlay : overlays) {
				if (overlay.isDisplayed()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].remove();", overlay)
					println("Popup's exist (Overlay removed)")
					isPopupFound = true
				}
			}
		}
	}

	// 3. Scan Iframe
	if (!isPopupFound) {
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"))
		for (int i = 0; i < iframes.size(); i++) {
			try {
				driver.switchTo().frame(i)
				List<WebElement> iframeBtns = driver.findElements(By.xpath(xpathClose))
				if (iframeBtns.size() > 0 && iframeBtns.get(0).isDisplayed()) {
					WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(iframeBtns.get(0)))
					println("Popup's exist (Found in Iframe)")
					isPopupFound = true
					driver.switchTo().defaultContent()
					return
				}
				driver.switchTo().defaultContent()
			} catch (Exception e) {
				driver.switchTo().defaultContent()
			}
		}
	}
	
	if (!isPopupFound) {
		println("Popup is not exist")
	}
	println("--------------------------------------------------")
}

void runFilterScenario(String categoryName, TestObject menuLink, TestObject filterItem) {
	println("\n=== TEST CATEGORY: " + categoryName + " ===")
	
	handlePopup()
	
	println("STEP 1: Click menu " + categoryName)
	smartClick(menuLink)
	WebUI.waitForPageLoad(10)
	
	handlePopup()
	
	println("STEP 2: Apply Filter...")
	WebUI.scrollToElement(filterItem, 3)
	smartClick(filterItem)
	
	WebUI.delay(2)
	if (WebUI.getUrl().contains("?")) { 
		println("PASS: Filter applied for " + categoryName) 
	}
	
	scrollFullPage()
	
	println("STEP 3: Uncheck Filter...")
	WebUI.scrollToElement(filterItem, 3)
	smartClick(filterItem)
	WebUI.delay(2)
	println("=== END CATEGORY: " + categoryName + " ===\n")
}

// ===========================================================
// MAIN SCRIPT
// ===========================================================

Map<String, Object> chromePrefs = new HashMap<String, Object>()
chromePrefs.put("profile.default_content_setting_values.notifications", 2)
RunConfiguration.setWebDriverPreferencesProperty("prefs", chromePrefs)
RunConfiguration.setWebDriverPreferencesProperty("args", ["--disable-notifications"])

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://aristino.com/')
WebUI.waitForPageLoad(10)

// 1. Áo
TestObject menuAo = makeTo("//a[contains(@href, 'tat-ca-ao')]")
TestObject filterAo = makeTo("//label[contains(text(),'L') or contains(text(),'M') or contains(text(),'39') or contains(text(),'40')]")
runFilterScenario("ÁO", menuAo, filterAo)

// 2. Phụ Kiện
TestObject menuPhuKien = makeTo("//a[contains(@href, 'phu-kien')]")
TestObject filterPhuKien = makeTo("//label[contains(text(),'Đen') or contains(text(),'Nâu') or contains(text(),'Xanh') or contains(text(),'500.000')]")
runFilterScenario("PHỤ KIỆN", menuPhuKien, filterPhuKien)

// 3. Language
println("\n=== CHECK LANGUAGE ===")
TestObject googleLangDropdown = makeTo("//*[@id=':0.targetLanguage']/select")
if (WebUI.waitForElementPresent(googleLangDropdown, 5, FailureHandling.OPTIONAL)) {
	try { WebUI.selectOptionByValue(googleLangDropdown, 'en', false) } 
	catch (Exception e) { WebUI.selectOptionByLabel(googleLangDropdown, 'English', false) }
	scrollFullPage()
	try { WebUI.selectOptionByValue(googleLangDropdown, 'vi', false) } 
	catch (Exception e) { WebUI.selectOptionByLabel(googleLangDropdown, 'Vietnamese', false) }
	scrollFullPage()
}

WebUI.closeBrowser()
