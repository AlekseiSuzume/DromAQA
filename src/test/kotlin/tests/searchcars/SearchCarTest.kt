package tests.searchcars

import common.Config.URLs.AUTO_DROM_RU_PAGE
import org.testng.annotations.Test
import pages.autodrom.Fuels
import tests.base.BaseTest

class SearchCarTest : BaseTest() {

    @Test(testName = "Filtering and checking the received data")
    fun checkForSearchCar() {
        driver.navigate().to(AUTO_DROM_RU_PAGE)

        autoDromPage
            .setAdvancedFilter(true)
            .setBrandlName("Toyota")
            .setModelName("Harrier")
            .setFuel(Fuels.HYBRID)
            .setMinMileage(1)
            .setMinYear(2007)
            .setUnsoldCheckbox()
            .setPages(2)
            .clickToSearch()
            .checks(unsold = true, mileage = true, minYear = 2007)
    }

    @Test(testName = "Print top 20 brands")
    fun printTop20() {
        driver.navigate().to(AUTO_DROM_RU_PAGE)

        autoDromPage
            .setCity("Приморский край")
            .brandAscendingFilter()
    }

}