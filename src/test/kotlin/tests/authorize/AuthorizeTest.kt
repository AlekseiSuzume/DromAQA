package tests.authorize

import common.Config.LOGIN
import common.Config.PASSWORD
import common.Config.URLs.AUTO_DROM_RU_PAGE
import common.Config.URLs.LOGIN_PAGE
import org.testng.annotations.Test
import tests.base.BaseTest

class AuthorizeTest : BaseTest() {


    /**
     * Before start test setup login and password in Config file
     * */
    @Test(testName = "Authorize and add car to favourite")
    fun authorizeAndAddToFavourites() {
        driver.navigate().to(LOGIN_PAGE)

        loginPage
            .setLogin(LOGIN)
            .setPassword(PASSWORD)
            .clickToSignIn()
            .checkRedirectToPersonalPage()

        driver.navigate().to(AUTO_DROM_RU_PAGE)

        autoDromPage
            .addToFavourite()
    }

}