pipeline {
    agent any

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir() // Cleans the workspace before starting the build
            }
        }
        stage('Checkout') {
            steps {
                git url: 'https://github.com/jeissyguimaraes/selenium_java_test_automation_ach.git', branch: 'main', credentialsId: 'github-token'
            }
        }
        stage('Static Code Analysis') {
            steps {
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
        stage('Security Scanning') {
            steps {
                // Example with OWASP Dependency Check
                sh 'mvn org.owasp:dependency-check-maven:check'
                dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Code Linting') {
            steps {
                // Example with Checkstyle
                sh 'mvn checkstyle:check'
                publishHTML(target: [
                    reportName: 'Checkstyle Report',
                    reportDir: 'target/site',
                    reportFiles: 'checkstyle.html',
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }
        stage('Integration Tests') {
            steps {
                sh 'mvn verify'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Dgroups=regression_test'
            }
        }
        stage('Deploy to Staging') {
            steps {
                // Example deployment step, replace with actual deployment command/script
                sh 'ansible-playbook -i staging_inventory deploy_staging.yml'
            }
        }
        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/extent-reports/**', allowEmptyArchive: true
            }
        }
        stage('Publish Test Reports') {
            steps {
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
            junit '**/target/surefire-reports/*.xml'
            script {
                if (currentBuild.result == 'FAILURE') {
                    emailext(
                        subject: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${currentBuild.result})",
                        body: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${currentBuild.result}) \nMore info at: ${env.BUILD_URL}",
                        to: 'you@example.com'
                    )
                }
            }
        }
    }
}
