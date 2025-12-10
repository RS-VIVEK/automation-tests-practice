pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/your-org/your-repo.git', branch: 'main'
            }
        }
        stage('Build Docker Image') {
            steps {
                bat 'docker build -t automation-tests .'
            }
        }
        stage('Run Tests in Docker') {
            steps {
                bat 'docker run --rm automation-tests'
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
