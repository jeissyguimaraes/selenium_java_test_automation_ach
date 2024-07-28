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
                echo "Running tests with tag 'regression_test'..."
                sh 'mvn test -Dgroups=regression_test'
            }
        }
        stage('Archive Reports') {
            steps {
                echo "Archiving test reports..."
                archiveArtifacts artifacts: 'target/extent-reports/**', allowEmptyArchive: true
            }
        }
        stage('Publish Test Reports') {
            steps {
                echo "Publishing test reports..."
                publishHTML(target: [
                    reportName: 'ExtentReports',
                    reportDir: 'target/extent-reports',
                    reportFiles: 'index.html',
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
            script {
                if (currentBuild.result == 'FAILURE') {
                    echo "Build failed. Sending email notification..."
                    emailext(
                        subject: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${currentBuild.result})",
                        body: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${currentBuild.result}) \nMore info at: ${env.BUILD_URL}",
                        to: 'jeissyguimaraes@gmail.com'
                    )
                }
            }
        }
    }
}
