/*
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/RS-VIVEK/automation-tests.git', branch: 'main'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t automation-tests .'
            }
        }

        stage('Run Tests in Docker') {
            steps {
                // Run Maven tests inside container and mount reports to Jenkins workspace
                bat 'docker run --rm -v %WORKSPACE%\\target:/app/target automation-tests mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Publish Reports') {
            steps {
                // If using Maven Surefire plugin (JUnit-style XMLs)
                junit '**//*
target/surefire-reports */
/*.xml'

                // If you want TestNG native reports instead:
                // 1. Install "TestNG Results Plugin" in Jenkins
                // 2. Uncomment below:
                // testng '**//*
test-output/testng-results.xml'
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
                 // testng '**/test-output/testng-results.xml'
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
