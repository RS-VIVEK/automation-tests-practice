    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    git url: 'https://github.com/RS-VIVEK/automation-tests.git', branch: 'main'
                }
            }

            stage('Check Tools') {
                steps {
                    bat 'java -version'
                    bat 'mvn -version'
                }
            }

            stage('Build') {
                steps {
                    bat 'mvn clean install'
                }
            }

            stage('Run Tests') {
                steps {
                    bat 'mvn test -DsuiteXmlFile=testng.xml'
                }
            }

            stage('Publish Reports') {
                steps {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        post {
            always {
                echo "Build finished with status: ${currentBuild.currentResult}"
            }
            failure {
                emailext(
                    subject: "Automation Tests FAILED (Build #${env.BUILD_NUMBER})",
                    body: "Check Jenkins logs and reports for details.",
                    to: "your-team@example.com"
                )
            }
            success {
                emailext(
                    subject: "Automation Tests PASSED (Build #${env.BUILD_NUMBER})",
                    body: "All tests passed successfully.",
                    to: "your-team@example.com"
                )
            }
        }
    }
