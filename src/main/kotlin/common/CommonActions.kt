package common

import common.Config.BROWSER
import common.Config.HEADLESS_BROWSER
import common.Config.TimeVariables.IMPLICIT_WAIT
import common.Config.TimeVariables.PAGE_LOAD_TIMEOUT
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import java.time.Duration

object CommonActions {

    private lateinit var driver: WebDriver

    fun createDriver(): WebDriver {
        driver = when (BROWSER) {
            "CHROME" -> {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe")
                ChromeDriver(ChromeOptions().setHeadless(HEADLESS_BROWSER))
            }

            "FIREFOX" -> {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe")
                FirefoxDriver(FirefoxOptions().setHeadless(HEADLESS_BROWSER))
            }

            else -> error("Incorrect browser name: $BROWSER")
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
