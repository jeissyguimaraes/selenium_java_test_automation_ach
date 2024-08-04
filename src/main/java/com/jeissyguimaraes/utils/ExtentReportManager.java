package com.jeissyguimaraes.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static void setup() {
        // Definindo o caminho para salvar o relatório no diretório target/extent-reports
        String reportPath = System.getProperty("user.dir") + "/target/extent-reports/extent-report.html";
        
        // Configurando o ExtentSparkReporter
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Functional Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("UTF-8");  // Garantindo a codificação correta

        // Configurando o ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User Name", "jeissyguimaraes");
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
