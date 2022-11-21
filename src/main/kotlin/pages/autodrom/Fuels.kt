package pages.autodrom

import org.openqa.selenium.By

enum class Fuels(val by: By) {
    GASOLINE(By.xpath("//div[text()='Бензин']")),
    DIESEL(By.xpath("//div[text()='Дизель']")),
    ELECTRO(By.xpath("//div[text()='Электро']")),
    HYBRID(By.xpath("//div[text()='Гибрид']")),
    GAS(By.xpath("//div[text()='ГБО']")),
}