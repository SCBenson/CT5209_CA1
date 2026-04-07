pipeline {
    agent any

    tools {
        maven 'Maven'  // Must match the name configured in Jenkins Global Tool Config
        jdk 'JDK17'    // Must match the name configured in Jenkins Global Tool Config
    }

    stages {
        stage('Get Code from Github') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/SCBenson/seanspetitions.git'
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
                input message: 'Deploy to production?', ok: 'Yes, deploy!'
                deploy adapters: [
                    tomcat9(
                        url: 'http://localhost:9090',
                        credentialsId: 'tomcat-deployer',
                        path: ''
                    )
                ],
                contextPath: '/seanspetitions',
                war: 'target/seanspetitions.war'
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