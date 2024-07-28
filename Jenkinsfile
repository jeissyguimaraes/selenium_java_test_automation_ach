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
                timeout(time: 5, unit: 'MINUTES') {
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
        }
        /*
        stage('Security Scanning') {
            steps {
                echo "Performing security scanning with OWASP Dependency Check..."
                timeout(time: 10, unit: 'MINUTES') {
                    // Example with OWASP Dependency Check
                    sh 'mvn org.owasp:dependency-check-maven:check'
                    dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'
                }
            }
        }
        */
        stage('Build') {
            steps {
                echo "Building the project..."
                timeout(time: 10, unit: 'MINUTES') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Code Linting') {
            steps {
                echo "Performing code linting with Checkstyle..."
                timeout(time: 5, unit: 'MINUTES') {
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
        }
        stage('Integration Tests') {
            steps {
                echo "Running integration tests..."
                timeout(time: 10, unit: 'MINUTES') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh 'mvn verify'
                    }
                }
            }
        }
        stage('Test') {
            steps {
                echo "Running tests with tag 'regression_test'..."
                timeout(time: 10, unit: 'MINUTES') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh 'mvn test -Dgroups=regression_test'
                    }
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo "Deploying to staging environment..."
                timeout(time: 10, unit: 'MINUTES') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        // Example deployment step, replace with actual deployment command/script
                        sh 'ansible-playbook -i staging_inventory deploy_staging.yml'
                    }
                }
            }
        }
        stage('Archive Reports') {
            steps {
                echo "Archiving test reports..."
                timeout(time: 5, unit: 'MINUTES') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        archiveArtifacts artifacts: 'target/extent-reports/**', allowEmptyArchive: true
                    }
                }
            }
        }
        stage('Publish Test Reports') {
            steps {
                echo "Publishing test reports..."
                timeout(time: 5, unit: 'MINUTES') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
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
                        to: 'jeissguimaraes@gmail.com'
                    )
                }
            }
        }
    }
}
