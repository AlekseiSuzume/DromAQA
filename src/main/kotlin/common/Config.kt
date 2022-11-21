package common

object Config {

    /**
     * Specify the browser for the tests:
     * CHROME
     * FIREFOX
     * */
    const val BROWSER = "CHROME"

    /**
     * Time variables
     * */
    object TimeVariables {
        const val IMPLICIT_WAIT = 5L
        const val EXPLICIT_WAIT = 5L
        const val PAGE_LOAD_TIMEOUT = 10L
    }

    /**
     *Close browser window after each test
     * */
    const val CLOSE_BROWSER = true

    /**
     * Clear browser cookies after each test
     * */
    const val CLEAR_COOKIES = true

    /**
     * URLs for test
     * */
    object URLs {
        const val AUTO_DROM_RU_PAGE = "https://auto.drom.ru"
        const val LOGIN_PAGE = "https://my.drom.ru/sign"
        const val BOOKMARK_PAGE = "https://my.drom.ru/personal/bookmark"
    }

    /**
     * Authentication data
     * */
    const val LOGIN = ""
    const val PASSWORD = ""
}