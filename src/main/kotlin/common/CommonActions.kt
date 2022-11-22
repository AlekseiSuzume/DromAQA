package common

import common.Config.BROWSER
import common.Config.TimeVariables.IMPLICIT_WAIT
import common.Config.TimeVariables.PAGE_LOAD_TIMEOUT
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.Assert
import java.time.Duration

object CommonActions {

    private lateinit var driver: WebDriver

    fun createDriver(): WebDriver {
        when (BROWSER) {
            "CHROME" -> {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe")
                driver = ChromeDriver()
            }
            "FIREFOX" -> {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe")
                driver = FirefoxDriver()
            }
            else -> Assert.fail("Incorrect browser name: $BROWSER")
        }

        driver.manage().run {
            window().maximize()
            timeouts()
                .implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT))
                .pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT))
        }
        return driver
    }
}
