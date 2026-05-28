import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

public class ScooterOrderTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Config.pageUrl);
    }

    @AfterEach
    public void cleanUp() {
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("orderData")
    public void ScooterOrderTopButton(String name, String surname, String address, String metro, String phone,
                                      int date, int days, String color, String comment, boolean confirm) {
        ScooterOrderPage orderPage = new ScooterOrderPage(driver);
        HomePageQaScooter homePage = new HomePageQaScooter(driver);
        homePage.cookiesButton();
        homePage.clickOrderTopButton();
        orderPage.formForWhom(name, surname, address, metro, phone);
        orderPage.formOfRent(date, days, color, comment, confirm);
    }

    @ParameterizedTest
    @MethodSource("orderData")
    public void ScooterOrderBottomButton(String name, String surname, String address, String metro, String phone,
                                         int date, int days, String color, String comment, boolean confirm) {
        ScooterOrderPage orderPage = new ScooterOrderPage(driver);
        HomePageQaScooter homePage = new HomePageQaScooter(driver);
        homePage.cookiesButton();
        homePage.clickOrderBottomButton();
        orderPage.formForWhom(name, surname, address, metro, phone);
        orderPage.formOfRent(date, days, color, comment, confirm);
    }

    private static Stream<Arguments> orderData() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Пушкина дом 12", "Сокольники", "89992223355", 21, 4, "black", "Оставить у двери", true),
                Arguments.of("Петр", "Петров", "Ленина 5", "Китай-город", "89991112233", 25, 7, "grey", "Позвонить за час", true),
                Arguments.of("Семен", "Семенов", "Горького 56", "Домодедовская", "+79991112233", 6, 1, "grey", "Не звонить", true)
        );
    }
}
