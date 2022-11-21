package tests.searchcars

import common.Config.URLs.AUTO_DROM_RU_PAGE
import org.testng.annotations.Test
import pages.autodrom.Fuels
import tests.base.BaseTest

class SearchCarTest : BaseTest() {

    @Test
    fun check_is_search_car() {
        driver.navigate().to(AUTO_DROM_RU_PAGE)

        autoDromPage
            .setAdvancedFilter(true)
            .setBrandlName("Toyota")
            .setModelName("Harrier")
            .setFuel(Fuels.HYBRID)
            .setMileageFrom(1)
            .setMinYear(2007)
            .setUnsold()
            .setPages(2)
            .clickToSearch()
            .checks(unsold = true, mileage = true, minYear = 2007)
    }

    @Test
    fun print_top_20() {
        driver.navigate().to(AUTO_DROM_RU_PAGE)

        autoDromPage
            .setCity("Приморский край")
            .brandAscendingFilter()
    }

}