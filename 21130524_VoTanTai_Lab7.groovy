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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser("")

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://aristino.com/')

WebUI.waitForPageLoad(30)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_Thi trang nam cao cp  Thng hiu qun o n_04165e/button_Boqua'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_Thi trang nam cao cp  Thng hiu qun o n_04165e/scloseAD'))

WebUI.delay(3);

WebUI.click(findTestObject('Object Repository/Testhoi/Page_Thi trang nam cao cp  Thng hiu qun o n_04165e/aLogin'))

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_Ti khon  ARISTINO/sCloseAd'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_Ti khon  ARISTINO/input_Email_login-email'), 'mintaeyeon1978@gmail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/Testhoi/Page_Ti khon  ARISTINO/input_Mt Khu_password'), 'it+UEM7YdCc2dc6O4DE7BA==')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_Ti khon  ARISTINO/button_ng nhp'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_Ti khon  ARISTINO/input_Sinh nht_birthday'), 'abcs')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_Ti khon  ARISTINO/input_Sinh nht_birthday'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_ARISTINO/div_THNG TIN C NHN                        N_6c37e0'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_ARISTINO/input_Sinh nht_birthday'), '16/02/2003')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_ARISTINO/input_Sinh nht_birthday'))

WebUI.refresh()

WebUI.click(findTestObject('Object Repository/Testhoi/Page_ARISTINO/span_InforOrder'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/svg'))

// 1️⃣ Chọn Tỉnh
TestObject provinceObj = findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/select_Chn Tnh Thnh              H Ch MinhH_b0b973')
WebUI.waitForElementVisible(provinceObj, 10)
WebUI.waitForElementClickable(provinceObj, 10)
WebUI.scrollToElement(provinceObj, 5)
WebUI.selectOptionByValue(provinceObj, 'Bình Định', false)

// 2️⃣ Chọn Huyện
TestObject districtObj = findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/select_Chn Qun Huyn              Huyn An Lo_5faa01')
WebUI.waitForElementVisible(districtObj, 10)
WebUI.waitForElementClickable(districtObj, 10)
WebUI.scrollToElement(districtObj, 5)
WebUI.selectOptionByValue(districtObj, 'Huyện Phù Cát', false)

// 3️⃣ Chọn Xã
TestObject wardObj = findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/select_Chn Phng XTh trn Ng My_2a8c17')
WebUI.waitForElementVisible(wardObj, 10)
WebUI.waitForElementClickable(wardObj, 10)
WebUI.scrollToElement(wardObj, 5)
WebUI.selectOptionByValue(wardObj, 'Xã Cát Tân', false)

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/input_chooseaddress'), '15 Ngô Mây')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/input_chooseaddress'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/label_TypeAddress'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/input_CapNhatAddress'))

WebUI.refresh()

WebUI.delay(5)

WebUI.waitForPageLoad(20)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_a ch  ARISTINO/a_TRANG PHC'))

WebUI.waitForPageLoad(20)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_TRANG PHC  ARISTINO/a_Pre-Order_pro-loop--link'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/span_NhanXetDanhGia'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/button_WriteReview'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/rating'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_AboutProd'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_Channel (1)'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/textarea__crv-rating'), 
    'Sản phẩm rất tốt đẹp, rất mát')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_5'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_5_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/div_GuiDanhGia'))

WebUI.delay(5)

WebUI.refresh()

WebUI.waitForPageLoad(20)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/span_NhanXetDanhGia'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/button_WriteReview'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/sRating (1)'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_C_AboutProd'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_channel_answer'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/textarea__crv-rating'), 
    'Sản phẩm không đdược đẹp vải thì phai màu')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_5'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_5_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/div_GuiDanhGia'))

WebUI.delay(5)

WebUI.refresh()

WebUI.waitForPageLoad(20)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/span_NhanXetDanhGia'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/button_WriteReview'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_Bn c sn lng gii thiu sn phm ny khng_a_d7f540'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_ONLINE_answer'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/textarea__crv-rating'), 
    'Sản phẩm đẹp tôi rất thích')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_5'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_5_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/span_GuiDanhGia'))


WebUI.refresh()

WebUI.waitForPageLoad(20)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/span_NhanXetDanhGia'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/button_WriteReview'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/sRating'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_C_answer'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_Channel'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_2'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_2_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3_2'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3_3'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/div_GuiDanhGia'))

WebUI.delay(5)
WebUI.waitForPageLoad(15)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/div_o Khoc 2 lp Nam'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/div_Hy'))

WebUI.delay(5)
WebUI.waitForPageLoad(15)

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/a_Profile'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/Testhoi/Page_Ti khon  ARISTINO/span_Logout'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/Testhoi/Page_Thi trang nam cao cp  Thng hiu qun o n_04165e/a_TRANG PHC'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_TRANG PHC  ARISTINO/a_Pre-Order_pro-loop--link'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/span_NhanXetDanhGia'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/button_WriteReview'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/rating'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_customer__name'), 
    'Vương')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_customer__name'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_email'), 
    'mintaeyeon1978@gmail.com')

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_phone'), 
    '0562328851')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_GioiThieu'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/input_KenhMuaHang'))

WebUI.setText(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/textarea__crv-rating'), 
    'Sản phẩm rất tốt, thoải mái')

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_3_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_5'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/label_5_1'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/div_GuiDanhGia'))

WebUI.click(findTestObject('Object Repository/Testhoi/Page_o Khoc 2 lp Nam Xm Aristino Regular Fi_aaad23/div_thongbao'))

WebUI.closeBrowser()