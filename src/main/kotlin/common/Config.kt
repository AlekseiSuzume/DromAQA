package common

object Config {

    /**
     * Specify the browser for the tests:
     * - CHROME
     * - FIREFOX
     * */
    const val BROWSER = "CHROME"

    /**
     * Start browser in headless mode
     * */
    const val HEADLESS_BROWSER = true

    /**
     *Close browser window after each test
     * */
    const val CLOSE_BROWSER = true

    /**
     * Clear browser cookies after each test
     * */
    const val CLEAR_COOKIES = true

    /**
     * Time variables
     * */
    object TimeVariables {
        const val IMPLICIT_WAIT = 5L
        const val EXPLICIT_WAIT = 5L
        const val PAGE_LOAD_TIMEOUT = 10L
    }

    /**
     * URLs for tests
     * */
    object URLs {
        const val AUTO_DROM_RU_PAGE = "https://auto.drom.ru"
        const val LOGIN_PAGE = "https://my.drom.ru/sign"
        const val BOOKMARK_PAGE = "https://my.drom.ru/personal/bookmark"
        const val PERSONAL_PAGE = "https://baza.drom.ru/personal/"
    }

    /**
     * Authentication data
     * */
    const val LOGIN = ""
    const val PASSWORD = ""
}