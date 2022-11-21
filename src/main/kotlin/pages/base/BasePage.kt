package pages.base

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
     * Wait for element to be clickable in DOM model
     */
    fun waitElementToBeClickable(element: WebElement): WebElement {
        WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
            .until(ExpectedConditions.elementToBeClickable(element))
        return element
    }

    /**
     * Wait for element to be visible in DOM model
     */
    fun waitElementIsVisible(element: WebElement): WebElement {
        WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
            .until(ExpectedConditions.visibilityOf(element))
        return element
    }

    /**
     * Wait for element to be NOT visible in DOM model
     */
    fun waitForNotElement(locator: By) {
        WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
            .until(not(ExpectedConditions.visibilityOfElementLocated(locator)))
    }

}