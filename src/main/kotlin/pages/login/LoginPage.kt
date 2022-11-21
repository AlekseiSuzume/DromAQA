package pages.login

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pages.base.BasePage

class LoginPage(driver: WebDriver) : BasePage(driver) {

    private val login = By.id("sign")
    private val password = By.id("password")
    private val signBtn = By.id("signbutton")

    /**
     * Entering a login for authorization
     * @param login = phone number, email or login
     * */
    fun setLogin(login: String): LoginPage {
        driver.findElement(this.login).sendKeys(login)
        return this
    }

    /**
     * Entering a password for authorization
     * @param password = password
     * */
    fun setPassword(password: String): LoginPage {
        driver.findElement(this.password).sendKeys(password)
        return this
    }

    /**
     * Click for authorization
     * */
    fun clickToSignIn(): LoginPage {
        driver.findElement(signBtn).click()
        return this
    }

}