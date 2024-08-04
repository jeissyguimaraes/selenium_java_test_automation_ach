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
- **✨ Lombok**: Library to reduce boilerplate code.
- **⚙️ Jenkins**: Continuous integration and continuous deployment server.
- **🐳 Docker**: Platform for developing, shipping, and running applications in containers.

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

3. **Update the `config.properties` file**:
    Edit the `config.properties` with your environment settings:

    ```properties
   # Base URL of the site to be tested
   base.url=https://www.saucedemo.com/v1/

    # WebDriver configurations
    webdriver.browser=chrome
    webdriver.headless=false

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


## Explanation of Configuration Files

1. **Dockerfile**
    ```
    FROM debian:latest

    USER root

    # Install dependencies, OpenJDK, Git, Maven, Chrome, and ChromeDriver
    RUN apt-get update && \
        apt-get install -y gnupg2 curl git openjdk-17-jdk maven unzip wget && \
        curl -fsSL https://pkg.jenkins.io/debian/jenkins.io.key | apt-key add - && \
        sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list' && \
        apt-get update && \
        apt-get install -y jenkins && \
        rm -rf /var/lib/apt/lists/*

    # Install Google Chrome
    RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
        sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' && \
        apt-get update && \
        apt-get install -y google-chrome-stable && \
        rm -rf /var/lib/apt/lists/*

    # Install ChromeDriver
    ENV CHROME_DRIVER_VERSION=127.0.6533.72
    RUN wget -N https://storage.googleapis.com/chrome-for-testing-public/${CHROME_DRIVER_VERSION}/linux64/chromedriver-linux64.zip -P /tmp && \
        unzip /tmp/chromedriver-linux64.zip -d /tmp && \
        mv /tmp/chromedriver /usr/local/bin/chromedriver && \
        chown root:root /usr/local/bin/chromedriver && \
        chmod 0755 /usr/local/bin/chromedriver

    # Create directory for Jenkins and set permissions
    RUN mkdir -p /usr/share/jenkins && \
        chmod -R 777 /usr/share/jenkins

    # Download Jenkins WAR file
    RUN curl -o /usr/share/jenkins/jenkins.war -fsSL https://get.jenkins.io/war-stable/latest/jenkins.war

    # Ensure the Jenkins war file is present
    RUN ls -l /usr/share/jenkins

    # Start Jenkins
    CMD ["java", "-jar", "/usr/share/jenkins/jenkins.war"]

     ```
**Explanation:**

- **🛠 FROM debian**: Uses the latest Debian image as the base.
- **📦 RUN apt-get update && ...:**: Installs dependencies like Java, Git, Maven Chrome, and ChromeDriver.
- **🚀 CMD ["java", "-jar", "/usr/share/jenkins/jenkins.war"]:**: Starts Jenkins.


2. **docker-compose.yml**

 ```
    version: '3.8'

    services:
    jenkins:
        build: .
        ports:
        - "8080:8080"
        - "50000:50000"
        volumes:
        - jenkins_home:/var/jenkins_home

    volumes:
    jenkins_home:

 ```

**Explanation:**

- **📄 version: '3.8'**: Specifies the Docker Compose version.
- **🔧 services**: Defines the services to be run.
- **🏗 jenkins**: The Jenkins service.
     - **🔨 build**: Builds the Dockerfile in the current directory.
     - **🌐 ports**: Maps host ports to container ports.
     - **💾 volumes**: Defines a volume for Jenkins home directory persistence.


3. **Jenkinsfile**

- **Clean Workspace**: Deletes the workspace to start fresh.
- **Checkout**: Clones the Git repository.
- **Static Code Analysis**: Runs PMD for static code analysis and publishes the report.
- **Security Scanning**: Runs OWASP Dependency Check for security vulnerabilities.
- **Build**: Compiles the project.
- **Code Linting**: Runs Checkstyle for code linting and publishes the report.
- **Integration Tests**: Runs integration tests.
- **Test**: Runs tests with the regression tag.
- **Deploy to Staging**: Deploys the build to the staging environment (replace with actual deployment steps).
- **Archive Reports**: Archives the test reports.
- **Publish Test Reports**: Publishes the ExtentReports to Jenkins.


## Running Jenkins with Docker

1. **Build the Docker image:**
    ```
    docker-compose build
    ```
2. **Start Jenkins using Docker Compose:**
    ```
    docker-compose up -d
    ```
3. **Access Jenkins:**

   Open a browser and go to http://localhost:8080.

4. **Unlock Jenkins:**
    Follow the instructions to unlock Jenkins. You will need the initial admin password, which you can find with:
    ```
        docker exec -it <container_id> cat /var/jenkins_home/secrets/initialAdminPassword
    ```
5. **Install Suggested Plugins:**
    Follow the setup wizard to install suggested plugins.

6. **Create Admin User:**
    Create your first admin user and complete the setup.

7. **Use Jenkins CLI:**
    To use jenkins-cli.jar, follow these steps:
    ```
       java -jar jenkins-cli.jar -s http://localhost:8080 -auth <user>:<api_token> <command>

    ```
    Replace <user> and <api_token> with your Jenkins user and API token, and <command> with the Jenkins CLI command you want to execute.

## Jenkins Execution and ExtentReport
This project uses Jenkins for continuous integration and ExtentReports for generating detailed test reports. Below are the screenshots showing the execution of the job in Jenkins and the ExtentReport.

**Jenkins Execution**
The images below show the Jenkins interface after running the automated test job. You can observe the pipeline configuration, the status of previous runs, and the test results trend report:

<p align="center">
  <img src="./images/Captura de tela de 2024-08-04 18-13-40.png" alt="Jenkins Execution" width="600" style="border: 2px solid black; margin: 10px;">
</p>
<p align="center">
  <img src="./images/Captura de tela de 2024-08-04 18-13-29.png" alt="Jenkins Execution" width="600" style="border: 2px solid black; margin: 10px;">
</p>


**ExtentReport**
ExtentReport generates detailed reports with visualizations of the tests performed, indicating which passed, failed, or were skipped. Below is an example of a generated report:

<p align="center">
  <img src="./images/Captura de tela de 2024-08-04 18-16-07.png" alt="ExtentReport - Overview" width="600" style="border: 2px solid black; margin: 10px;">
</p>
<p align="center">
  <img src="./images/Captura de tela de 2024-08-04 18-16-15.png" alt="ExtentReport - Test Details" width="600" style="border: 2px solid black; margin: 10px;">
</p>


## Estrutura do Projeto

```plaintext
selenium_java_test_automation_arch/
 |-- jenkinsDocker
 |   |-- docker-compose.yml
 |   `-- Dockerfile
 |-- Jenkinsfile
 |-- src/
 |   |-- main/
 |   |   |-- java/
 |   |   |   `-- com/jeissyguimaraes/
 |   |   |       |-- models/
 |   |   |       |   `-- User.java
 |   |   |       |-- pages/
 |   |   |       |   |-- BasePage.java
 |   |   |       |   |-- LoginPage.java
 |   |   |       |   |-- CartPage.java
 |   |   |       |   |-- CheckoutPage.java
 |   |   |       |   `-- ProductsPage.java
 |   |   |       |-- selectors/
 |   |   |       |   `-- LoginSelectors.java
 |   |   |       `-- utils/
 |   |   |           |-- ConfigLoader.java
 |   |   |           |-- DataGenerator.java
 |   |   |           |-- DriverManager.java
 |   |   |           `-- ExtentReportManager.java
 |   |   `-- resources/
 |   |       `-- drivers/
 |   |           `-- chromedriver
 |   `-- test/
 |       |-- java/
 |       |   `-- com/jeissyguimaraes/
 |       |       `-- tests/
 |       |           |-- BaseTest.java
 |       |           `-- PurchaseTest.java
 |       `-- resources/
 |           |-- images/
 |           |   |-- Captura de tela de 2024-08-04 18-13-40.png
 |           |   |-- Captura de tela de 2024-08-04 18-13-29.png
 |           |   |-- Captura de tela de 2024-08-04 18-16-07.png
 |           |   `-- Captura de tela de 2024-08-04 18-16-15.png
 |           `-- config.properties
