# Selenium Java Test Automation Architecture

## Overview
This project was developed using Selenium and Java, following SOLID principles and best coding practices. The goal is to provide a robust foundation for test automation, ensuring clean, readable, and maintainable code.

## Objectives
- **🔍 Automated Testing**: Implement an automated testing solution using Selenium and Java.
- **📊 Comprehensive Reports**: Generate detailed reports with ExtentReports.
- **🚀 Easy Integration**: Ensure the solution is easy to integrate into CI/CD pipelines, providing immediate feedback on UI changes.
- **🌐 Multi-browser Support**: Support for multiple browsers (Chrome, Firefox, Edge) including headless mode.

## Key Features
- **🖥️ Test Sessions with ExtentReports**: Initialization and closing of test sessions with ExtentReports, including WebDriver configuration.
- **📸 Visual Capture and Comparison**: Capture screenshots of web pages and compare them with a previously defined visual baseline.
- **🔧 WebDriver Configuration**: WebDriver configuration to support multiple browsers and headless mode.
- **🤖 Automation with Selenium**: Use Selenium to orchestrate the test flow, including opening browsers, navigating pages, and executing checkpoints.

## Technologies Used
- **☕ Java**: Programming language used to create and manage test cases.
- **🌐 Selenium**: Browser automation tool.
- **🧪 TestNG**: Testing framework used for managing and executing tests.
- **📊 ExtentReports**: Tool for generating test reports.
- **🔄 Jackson**: Library for handling JSON data.
- **✨ Lombok**: Library to reduce boilerplate code.


## SOLID Principles and Best Practices

This project was developed following SOLID principles and best coding practices to ensure a solid and maintainable foundation:

- **Single Responsibility Principle**: Each class has a single responsibility.
- **Open/Closed Principle**: Classes are open for extension but closed for modification.
- **Liskov Substitution Principle**: Subclasses should be able to replace their base classes without breaking the application.
- **Interface Segregation Principle**: Specific interfaces for the client, avoiding large, monolithic interfaces.
- **Dependency Inversion Principle**: Depend on abstractions, not on concrete implementations.


## Installation

1. **Clone the repository and navigate to the project directory**:
    ```bash
    git clone https://github.com/your-username/selenium_java_test_automation_ach.git
    cd selenium_java_test_automation_ach
    ```

2. **Install dependencies**:
    ```bash
    mvn clean install
    ```

3. **Configure WebDriver**:
    Download the browser drivers (ChromeDriver, GeckoDriver for Firefox, and EdgeDriver) compatible with your browser versions and place them in the `src/main/resources/drivers` directory.

4. **Update the `config.properties` file**:
    Edit the `config.properties` file in `src/main/resources` with your environment settings:

    ```properties
    # Base URL of the site to be tested
    base.url=https://www.saucedemo.com/v1/

    # WebDriver configurations
    webdriver.browser=chrome
    webdriver.headless=false

    # Paths to browser drivers
    webdriver.chrome.driver=src/main/resources/drivers/chromedriver
    webdriver.firefox.driver=src/main/resources/drivers/geckodriver
    webdriver.edge.driver=src/main/resources/drivers/edgedriver

    # Default timeout for explicit wait in seconds
    default.explicit.wait=10

    # Environment settings
    environment=development
    ```

## Running the Tests

1. **Run the tests**:
    ```bash
    mvn test
    ```

Test reports will be generated in the `test-output` directory and can be viewed by opening the `extent-report.html` file in a browser.

## Estrutura do Projeto

```plaintext
selenium_java_test_automation_arch/
│
src/
|-- main/
|   |-- java/
|   |   `-- com/jeissyguimaraes/
|   |       |-- models/
|   |       |   `-- User.java
|   |       |-- pages/
|   |       |   |-- BasePage.java
|   |       |   |-- LoginPage.java
|   |       |   |-- CartPage.java
|   |       |   |-- CheckoutPage.java
|   |       |   `-- ProductsPage.java
|   |       |-- selectors/
|   |       |   `-- LoginSelectors.java
|   |       `-- utils/
|   |           |-- ConfigLoader.java
|   |           |-- DataGenerator.java
|   |           |-- DriverManager.java
|   |           `-- ExtentReportManager.java
|   `-- resources/
|       `-- drivers/
|           `-- chromedriver
`-- test/
    |-- java/
    |   `-- com/jeissyguimaraes/
    |       `-- tests/
    |           |-- BaseTest.java
    |           `-- PurchaseTest.java
    `-- resources/
        `-- data/
            `-- login_data.json