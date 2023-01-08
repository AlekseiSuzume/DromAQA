package pages.base

import common.Config
import common.Config.TimeVariables.EXPLICIT_WAIT
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.ExpectedConditions.not
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

open class BasePage(protected val driver: WebDriver) {

    /**
     * Waiting for element to be clickable in DOM model
     */
    fun waitElementToBeClickable(element: WebElement): WebElement {
        WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
            .until(ExpectedConditions.elementToBeClickable(element))
        return element
    }

    /**
     * Waiting for element to be visible in DOM model
     */
    fun waitElementToBeVisible(element: WebElement): WebElement {
        WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
            .until(ExpectedConditions.visibilityOf(element))
        return element
    }

    /**
     * Waiting for element to be NOT visible in DOM model
     */
    fun waitForNotElement(locator: By) {
        WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
            .until(not(ExpectedConditions.visibilityOfElementLocated(locator)))
    }

    /**
     * Checking for element content in an element
     * @param element Root element
     * @param locator Locator of the element you are looking for
     **/
    fun isElementContains(element: WebElement, locator: By): Boolean {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO)
        return try {
            element.findElement(locator)
            true
        } catch (_: Exception) {
            false
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Config.TimeVariables.IMPLICIT_WAIT))
        }
    }

}