pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/RS-VIVEK/automation-tests-practice.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                // Run TestNG suite via Maven
                bat 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Publish Reports') {
            steps {
                // Publish Allure results from target/allure-results
                allure([
                    includeProperties: false,
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            // Publish Allure report after every build
          //  allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
        success {
            // Send email if build succeeds
            emailext (
                to: 'vivekvivekvivekrs@gmail.com',
                subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """Hello Vivek,

The build ${env.BUILD_NUMBER} for job ${env.JOB_NAME} finished successfully.
You can view the Allure report here: ${env.BUILD_URL}allure

Regards,
Jenkins"""
            )
        }
        failure {
            // Send email if build fails
            emailext (
                to: 'vivekvivekvivekrs@gmail.com',
                subject: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """Hello Vivek,

The build ${env.BUILD_NUMBER} for job ${env.JOB_NAME} has FAILED.
You can view the Allure report here: ${env.BUILD_URL}allure

Regards,
Jenkins""",
                attachmentsPattern: "target/allure-results/**"
            )
        }
    }
}
