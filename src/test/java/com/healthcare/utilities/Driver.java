package com.healthcare.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    /*
     Private constructor prevents creation of Driver objects.
     This ensures the class behaves like a utility class.
     */
    private Driver() {
    }

    /*
     Thread-safe WebDriver storage.
     Each thread (test execution) gets its own driver instance.
     This is important when running tests in parallel.
     */
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
     Main method used across the framework to get the WebDriver instance.

     If driver does not exist → create it.
     If driver already exists → return the same instance.

     This prevents creating multiple drivers for the same test.
     */
    public static WebDriver getDriver() {

        // If driver is not created yet
        if (driverPool.get() == null) {

            /*
             First check if browser is provided from command line.
             Example:
             mvn test -Dbrowser=chrome

             If not provided, read it from configuration.properties.
             */
            String browserType = System.getProperty("browser");

            if (browserType == null || browserType.isBlank()) {
                browserType = ConfigurationReader.getProperty("browser");
            }

            /*
             Switch statement decides which browser to open
             based on configuration value.
             */
            switch (browserType.toLowerCase()) {

                case "chrome":

                    /*
                     Create ChromeOptions object to customize
                     Chrome browser behavior.
                     */
                    ChromeOptions chromeOptions = new ChromeOptions();

                    /*
                     GitHub Actions runs on Linux without a display.
                     These arguments allow Chrome to run in CI environments.
                     */
                    String isCi = System.getenv("CI");
                    if ("true".equalsIgnoreCase(isCi)) {
                        chromeOptions.addArguments("--headless=new"); // run browser without UI
                        chromeOptions.addArguments("--no-sandbox"); // required for Linux containers
                        chromeOptions.addArguments("--disable-dev-shm-usage"); // prevents memory issues
                        chromeOptions.addArguments("--window-size=1920,1080"); // set screen size
                    }

                    /*
                     Adjust browser scale factor for UI consistency
                     (helps with visual layout issues in some environments).
                     */
                    chromeOptions.addArguments("force-device-scale-factor=1.2");

                    /*
                     Configure browser permissions automatically
                     so tests do not stop due to permission popups.
                     */
                    Map<String, Object> prefs = new HashMap<>();

                    prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
                    prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
                    prefs.put("profile.default_content_setting_values.geolocation", 1);
                    prefs.put("profile.default_content_setting_values.notifications", 1);

                    chromeOptions.setExperimentalOption("prefs", prefs);

                    /*
                     Disable various Chrome popups and permission dialogs
                     that could interfere with automated testing.
                     */
                    chromeOptions.addArguments("--use-fake-ui-for-media-stream");
                    chromeOptions.addArguments("--use-fake-device-for-media-stream");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-notifications");

                    /*
                     Create the ChromeDriver instance with configured options.
                     */
                    driverPool.set(new ChromeDriver(chromeOptions));

                    /*
                     Configure driver timeout settings.
                     */
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                    /*
                     Maximize window only when NOT running in CI.
                     Headless mode does not support maximize().
                     */
                    if (!"true".equalsIgnoreCase(isCi)) {
                        driverPool.get().manage().window().maximize();
                    }

                    break;

                case "firefox":

                    /*
                     Create FirefoxOptions object for browser configuration.
                     */
                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    /*
                     Run Firefox in headless mode when executing in CI.
                     */
                    if ("true".equalsIgnoreCase(System.getenv("CI"))) {
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.addArguments("--width=1920");
                        firefoxOptions.addArguments("--height=1080");
                    }

                    /*
                     Initialize Firefox driver with configured options.
                     */
                    driverPool.set(new FirefoxDriver(firefoxOptions));

                    /*
                     Configure implicit wait timeout.
                     */
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                    /*
                     Maximize window locally (not in CI).
                     */
                    if (!"true".equalsIgnoreCase(System.getenv("CI"))) {
                        driverPool.get().manage().window().maximize();
                    }

                    break;

                default:
                    /*
                     If invalid browser name is provided,
                     throw an error to notify the user.
                     */
                    throw new RuntimeException("Invalid browser name: " + browserType);
            }
        }

        /*
         Return the driver instance for the current thread.
         */
        return driverPool.get();
    }


    /*
     Close the WebDriver instance after test execution.
     quit() closes all browser windows and ends the session.
     remove() clears the thread-local storage so a new driver
     can be created for the next test if needed.
     */
    public static void closeDriver() {

        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }

}