pipeline {
    agent any

    stages {
        stage('Clean Workspace') {
            steps {
                echo "Cleaning the workspace..."
                deleteDir() // Cleans the workspace before starting the build
            }
        }
        stage('Checkout') {
            steps {
                echo "Checking out the repository..."
                git url: 'https://github.com/jeissyguimaraes/selenium_java_test_automation_ach.git', branch: 'main', credentialsId: 'github-token'
            }
        }
        stage('Static Code Analysis') {
            steps {
                echo "Performing static code analysis with PMD..."
                // Example with PMD
                sh 'mvn pmd:pmd'
                publishHTML(target: [
                    reportName: 'PMD Report',
                    reportDir: 'target/site',
                    reportFiles: 'pmd.html',
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }
        stage('Build') {
            steps {
                echo "Building the project..."
                sh 'mvn clean install'
            }
        }
        stage('Integration Tests') {
            steps {
                echo "Running integration tests..."
                sh 'mvn verify'
            }
        }
        stage('Test') {
            steps {
                echo "Running tests ..."
                sh 'mvn clean test'
            }
        }
        stage('Archive Reports') {
            steps {
                echo "Archiving test reports..."
                // Verify if reports are generated before archiving
                script {
                    def reportDir = 'target/extent-reports'
                    if (fileExists(reportDir)) {
                        echo "Archiving reports from ${reportDir}"
                        archiveArtifacts artifacts: "${reportDir}/**", allowEmptyArchive: true
                    } else {
                        error "Report directory ${reportDir} does not exist"
                    }
                }
            }
        }
        stage('Publish Test Reports') {
            steps {
                echo "Publishing test reports..."
                publishHTML(target: [
                    reportName: 'ExtentReports',
                    reportDir: 'target/extent-reports',
                    reportFiles: 'extent-report.html',
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }
    }
    post {
        always {
            echo "Running post-build actions..."
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
