pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {
        stage('Get Code from Github') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/SCBenson/CT5209_CA1.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/seanspetitions.war', fingerprint: true
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sh 'sudo cp target/seanspetitions.war /opt/tomcat9/webapps/'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}