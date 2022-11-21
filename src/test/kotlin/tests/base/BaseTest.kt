package tests.base

import common.CommonActions
import common.Config.CLEAR_COOKIES
import common.Config.CLOSE_BROWSER
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterSuite
import pages.autodrom.AutoDromPage
import pages.login.LoginPage

open class BaseTest {

    val driver = CommonActions.createDriver()
    protected val autoDromPage = AutoDromPage(driver)
    protected val loginPage = LoginPage(driver)


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