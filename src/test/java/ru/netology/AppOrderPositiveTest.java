package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.cssSelector;

class AppOrderPositiveTest {
   WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupAll() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSubmitAnApplication() {

        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Степанов Леонид");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79818874565");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
    }
}


