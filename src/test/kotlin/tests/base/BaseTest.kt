package tests.base

import common.CommonActions
import common.Config.CLEAR_COOKIES
import common.Config.CLOSE_BROWSER
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeMethod
import pages.autodrom.AutoDromPage
import pages.login.LoginPage

open class BaseTest {

    protected lateinit var driver: WebDriver
    protected lateinit var autoDromPage: AutoDromPage
    protected lateinit var loginPage: LoginPage

    @BeforeMethod
    fun setupDriver() {
        driver = CommonActions.createDriver()
    }

    @BeforeMethod
    fun setupPages() {
        autoDromPage = AutoDromPage(driver)
        loginPage = LoginPage(driver)
    }

    /**
     * Clear cookies after each test method
     * */
    @AfterMethod
    fun clearCookies() {
        if (CLEAR_COOKIES) {
            driver.manage().deleteAllCookies()
        }
    }

    /**
     * Close browser after tests
     * */
    @AfterSuite(alwaysRun = true)
    fun quit() {
        if (CLOSE_BROWSER) {
            driver.quit()
        }
    }

}