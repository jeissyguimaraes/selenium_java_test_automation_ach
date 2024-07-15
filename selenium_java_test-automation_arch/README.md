# Selenium Java Test Automation Architecture

## Overview
Este projeto de automação de testes foi desenvolvido utilizando Selenium e Java, seguindo os princípios de SOLID e boas práticas de código. O objetivo é fornecer uma base sólida para a automação de testes, garantindo código limpo, legível e manutenível.

## Objectives
- **🔍 Testes Automatizados**: Implementar uma solução automatizada de testes utilizando Selenium e Java.
- **📊 Relatórios Extensivos**: Geração de relatórios detalhados com ExtentReports.
- **🚀 Integração Fácil**: Assegurar que a solução seja fácil de integrar em pipelines CI/CD, fornecendo feedback imediato sobre mudanças na UI.

## Key Features
- **🖥️ Sessões de Teste com ExtentReports**: Inicialização e encerramento de sessões de teste com ExtentReports, incluindo configuração do WebDriver.
- **📸 Captura e Comparação Visual**: Captura de screenshots de páginas web e comparação com um baseline visual previamente definido.
- **🔧 Configuração de WebDriver**: Configuração do WebDriver para suportar múltiplos navegadores.
- **🤖 Automação com Selenium**: Utilização do Selenium para orquestrar o fluxo de testes, incluindo abertura de navegadores, navegação em páginas e execução de checkpoints.

## Tecnologias Utilizadas
- **☕ Java**: Linguagem de programação utilizada para criar e gerenciar os casos de teste.
- **🌐 Selenium**: Ferramenta de automação de navegador.
- **🧪 TestNG**: Framework de testes utilizado para gerenciamento e execução de testes.
- **📊 ExtentReports**: Ferramenta de geração de relatórios de testes.
- **🔄 Jackson**: Biblioteca para manipulação de dados JSON.
- **✨ Lombok**: Biblioteca para reduzir boilerplate de código.


## SOLID Principles and Best Practices

This project was developed following SOLID principles and best coding practices to ensure a solid and maintainable foundation:

- **Single Responsibility Principle**: Each class has a single responsibility.
- **Open/Closed Principle**: Classes are open for extension but closed for modification.
- **Liskov Substitution Principle**: Subclasses should be able to replace their base classes without breaking the application.
- **Interface Segregation Principle**: Specific interfaces for the client, avoiding large, monolithic interfaces.
- **Dependency Inversion Principle**: Depend on abstractions, not on concrete implementations.


## Instalação

1. **Clone o repositório e navegue até o diretório do projeto**:
    ```bash
    git clone https://github.com/seu-usuario/selenium-java-test-automation-arch.git
    cd selenium-java-test-automation-arch
    ```

2. **Instale as dependências**:
    ```bash
    mvn clean install
    ```

3. **Configure o WebDriver**:
    Baixe o [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) compatível com a versão do seu navegador e coloque-o no diretório `src/main/resources/drivers`.

4. **Atualize o arquivo `config.properties`**:
    Edite o arquivo `config.properties` em `src/main/resources` com as configurações do seu ambiente:

    ```properties
    # Base URL of the site to be tested
    base.url=https://www.saucedemo.com/v1/

    # WebDriver configurations
    webdriver.chrome.driver=src/main/resources/drivers/chromedriver

    # Default timeout for explicit wait in seconds
    default.explicit.wait=10

    # Environment settings
    environment=development
    ```

## Executando os Testes

1. **Execute os testes**:
    ```bash
    mvn test
    ```

Os relatórios de testes serão gerados no diretório `test-output` e podem ser visualizados abrindo o arquivo `extent-report.html` em um navegador.

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
