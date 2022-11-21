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
    @Test
    fun authorize_and_add_to_favourites() {
        driver.navigate().to(LOGIN_PAGE)

        loginPage
            .setLogin(LOGIN)
            .setPassword(PASSWORD)
            .clickToSignIn()

        driver.navigate().to(AUTO_DROM_RU_PAGE)

        autoDromPage
            .addToFavourite()
    }

}