package com.healthcare.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private Driver() {
    }

    private static final InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            String browserType = System.getProperty("browser");

            if (browserType == null || browserType.isBlank()) {
                browserType = ConfigurationReader.getProperty("browser");
            }

            String isCi = System.getenv("CI");

            switch (browserType.toLowerCase()) {

                case "chrome":

                    ChromeOptions chromeOptions = new ChromeOptions();

                    if ("true".equalsIgnoreCase(isCi)) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }

                    chromeOptions.addArguments("--force-device-scale-factor=1.2");
                    chromeOptions.addArguments("--use-fake-ui-for-media-stream");
                    chromeOptions.addArguments("--use-fake-device-for-media-stream");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-notifications");

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
                    prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
                    prefs.put("profile.default_content_setting_values.geolocation", 1);
                    prefs.put("profile.default_content_setting_values.notifications", 1);

                    chromeOptions.setExperimentalOption("prefs", prefs);

                    driverPool.set(new ChromeDriver(chromeOptions));
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                    if (!"true".equalsIgnoreCase(isCi)) {
                        driverPool.get().manage().window().maximize();
                    }

                    break;

                case "firefox":

                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    if ("true".equalsIgnoreCase(isCi)) {
                        firefoxOptions.addArguments("--headless");
                    }

                    driverPool.set(new FirefoxDriver(firefoxOptions));
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

                    if ("true".equalsIgnoreCase(isCi)) {
                        driverPool.get().manage().window().setSize(new Dimension(1920, 1080));
                    } else {
                        driverPool.get().manage().window().maximize();
                    }

                    break;

                default:
                    throw new RuntimeException("Invalid browser name: " + browserType);
            }
        }

        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}