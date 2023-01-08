package pages.autodrom

import common.Config.URLs.BOOKMARK_PAGE
import org.openqa.selenium.*
import org.testng.Assert
import pages.base.BasePage

class AutoDromPage(driver: WebDriver) : BasePage(driver) {

    private val brandInput = By.xpath("//input[@placeholder='Марка']")
    private val modelInput = By.xpath("//input[@placeholder='Модель']")
    private val fuelInput = By.xpath("//button[text()='Топливо']")
    private val mileageFromInput = By.xpath("//input[@placeholder=\"от, км\"]")
    private val minYearInput = By.xpath("//button[text()='Год от']")
    private val advancedFilter = By.xpath("//button[@data-ftid='sales__filter_advanced-button']")
    private val unsoldCheckbox = By.xpath("//input[@data-ftid='sales__filter_unsold']/following-sibling::*")
    private val advertisement = By.xpath("//a[@class='css-xb5nz8 ewrty961']")
    private val searchBtn = By.xpath("//div[text()='Показать']")
    private val brandInList = By.xpath("//div[@class='css-1r0zrug e1uu17r80']//div[@role='option']")
    private val notFavAdvertisement = By.xpath("//div[@class='css-cvu2h3 ecjvn7j0']//ancestor::a")
    private val favouriteIcon = By.xpath("//div[@class='css-cvu2h3 ecjvn7j0']")
    private val cityPicker = By.xpath("//a[@href='https://auto.drom.ru/cities/']")
    private val cityInput = By.xpath("//input[contains(@placeholder, 'поиск города')]")

    private var pages = 1
    private var elements = mutableListOf<WebElement>()

    /**
     * Set filter options to advanced mode
     * */
    fun setAdvancedFilter(isEnabled: Boolean): AutoDromPage {
        val advancedBtn = driver.findElement(advancedFilter)
        if (isEnabled && advancedBtn.text == "Расширенный поиск") {
            advancedBtn.click()
        } else if (!isEnabled && advancedBtn.text == "Обычный поиск") {
            advancedBtn.click()
        }
        return this
    }

    /**
     * Set brand name for search
     * @param brand brand name
     * */
    fun setBrandName(brand: String): AutoDromPage {
        driver.findElement(brandInput).apply {
            click()
            sendKeys(brand)
            waitElementToBeClickable(
                findElement(By.xpath("//div[@aria-label='Марка']//child::div[contains(text(), '$brand')]"))
            ).click()
        }
        return this
    }

    /**
     * Set model name for search
     * @param model model name
     * */
    fun setModelName(model: String): AutoDromPage {
        waitElementToBeClickable(driver.findElement(modelInput)).apply {
            click()
            sendKeys(model)
            waitElementToBeClickable(
                findElement(By.xpath("//div[@aria-label='Модель']//child::div[contains(text(), '$model')]"))
            ).click()
        }
        return this
    }

    /**
     * Set fuel type
     * @param fuel fuel type (pick from constants)
     * */
    fun setFuel(fuel: Fuels): AutoDromPage {
        driver.findElement(this.fuelInput).click()
        driver.findElement(fuel.by).click()
        return this
    }

    /**
     * Set checkbox for search unsold cars
     * */
    fun setUnsoldCheckbox(): AutoDromPage {
        val unsoldCheckbox = driver.findElement(unsoldCheckbox)
        unsoldCheckbox.click()
        return this
    }

    /**
     * Set the minimum mileage of the car
     * @param km minimum mileage
     * */
    fun setMinMileage(km: Int): AutoDromPage {
        driver.findElement(mileageFromInput).sendKeys("$km")
        return this
    }

    /**
     * Set the minimum year of manufacture of the car
     * @param year minimum year
     * */
    fun setMinYear(year: Int): AutoDromPage {
        driver.findElement(minYearInput).apply {
            click()
            sendKeys("$year")
            waitElementToBeClickable(
                findElement(By.xpath("//div[@aria-label='Год от']//child::div[contains(text(), '$year')]"))
            ).click()
        }
        return this
    }

    /**
     * Set the number of pages to be checked
     * @param value number of pages (default value = 1)
     * */
    fun setPages(value: Int = 1): AutoDromPage {
        this.pages = value
        return this
    }

    /**
     * Select what to check:
     * @param unsold check for cars not sold out
     * @param mileage check for cars have mileage
     * @param minYear check for cars year manufactured >= set minimum year for search
     * */
    fun checks(unsold: Boolean, mileage: Boolean, minYear: Int): AutoDromPage {
        for (i in 2..pages + 1) {
            elements = driver.findElements(advertisement)
            elements.forEach { element ->
                if (unsold) {
                    checkUnsold(element)
                }
                if (mileage) {
                    checkMileage(element)
                }
                checkMinYear(element, minYear)
            }
            try {
                driver.findElement(By.xpath("//a[@class='css-1jjais5 ena3a8q0' and text()='$i']")).click()
            } catch (e: NoSuchElementException) {
                println("Out of pages: $i")
                return this
            }
        }
        return this
    }

    /**
     * Check for car not sold out
     * */
    private fun checkUnsold(element: WebElement) {
        var actTextDecoration = ""
        val soldCarLocator = By.xpath(".//div[contains(@class, 'css-r91w5p')]")
        if (isElementContains(element, soldCarLocator)) {
            actTextDecoration = element.findElement(soldCarLocator).getCssValue("textDecorationLine")
            Assert.assertEquals(actTextDecoration, "none", "Fail in element: ${element.getAttribute("href")}")
        }
    }

    /**
     * Check that the year of manufacture of the car is >= than the set value
     * */
    private fun checkMinYear(element: WebElement, expMinYear: Int) {
        val actMinYear = element.text.substringAfter(", ").substring(0, 4).toInt()
        Assert.assertTrue(actMinYear >= expMinYear, "Fail in element: ${element.getAttribute("href")}")
    }

    /**
     * Check for car have mileage
     * */
    private fun checkMileage(element: WebElement) {
        waitElementToBeVisible(element.findElement(By.xpath(".//span[5]")))
    }

    /**
     * Select city for the search
     * @param city city name
     * */
    fun setCity(city: String): AutoDromPage {
        driver.findElement(cityPicker).click()
        waitElementToBeVisible(driver.findElement(cityInput)).sendKeys(city, Keys.ENTER)
        return this
    }

    /**
     * Add car to favourite
     * */
    fun addToFavourite(): AutoDromPage {
        val notFavAdvertisement = driver.findElement(notFavAdvertisement)
        val link = notFavAdvertisement.getAttribute("href")
        driver.findElement(favouriteIcon).click()
        driver.navigate().to(BOOKMARK_PAGE)
        val lastFavItem = driver.findElement(By.xpath("//table//tr[1]"))
        val lastFavItemLink = lastFavItem.findElement(By.xpath(".//a")).getAttribute("href")
        Assert.assertEquals(lastFavItemLink, link)
        return this
    }

    /**
     * Print top 20 brands with the largest number of submitted advertisement
     * */
    fun brandAscendingFilter() {
        val brandSet = mutableSetOf<String>()
        val js = driver as JavascriptExecutor

        waitForNotElement(By.xpath("//div[@class='css-w6jy93 e5gpnci0']"))
        waitElementToBeClickable(driver.findElement(brandInput)).click()

        driver.findElements(brandInList)
            .forEach {
                brandSet.add(it.text)
            }

        repeat(16) {
            js.executeScript("document.querySelector('.css-u25ii9.e154wmfa0').scrollTop=${it * 350}")
            Thread.sleep(500)

            driver.findElements(brandInList)
                .forEach {
                    brandSet.add(it.text)
                }
        }

        val brandMap = mutableMapOf<Int, String>().apply {
            brandSet.forEach {
                if (it.contains("(")) {
                    put(
                        it.substringAfter("(").substringBefore(")").toInt(),
                        it.substringBefore(" ")
                    )
                }
            }
        }.toSortedMap(compareByDescending { it })

        println("-------------------------------------------")
        println(String.Companion.format("| %-14s | %-22s |", "Фирма", "Количество объявлений"))
        println("-------------------------------------------")
        brandMap
            .asSequence()
            .take(20)
            .forEach {
                println(String.Companion.format("| %-14s | %-22d |", it.value, it.key))
            }
        println("-------------------------------------------")
    }

    /**
     * Click for start to search
     * */
    fun clickToSearch(): AutoDromPage {
        driver.findElement(searchBtn).click()
        return this
    }

}