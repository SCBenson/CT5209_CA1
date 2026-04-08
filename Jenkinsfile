pipeline {
    agent none

    tools {
        maven 'Maven'  // Must match the name configured in Jenkins Global Tool Config
        jdk 'JDK17'    // Must match the name configured in Jenkins Global Tool Config
    }

    stages {
        stage('Get Code from Github') {
            agent any
            steps {
                git branch: 'main',
                    url: 'https://github.com/SCBenson/CT5209_CA1.git'
            }
        }

        stage('Build') {
            agent any
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            agent any
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            agent any
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/seanspetitions.war', fingerprint: true
            }
        }

        stage('Approve Deployment'){
            steps{
                input message: 'Deploy to Production', ok: 'Yes, deploy!'
            }
        }

        stage('Deploy to Tomcat') {
            agent any
            steps {
                sh 'sudo cp target/seanspetitions.war /opt/tomcat/webapps/'
            }
        }
    }

    post {
        success {
            node(''){
                echo 'Pipeline completed successfully!'
            }

        }
        failure {
            node(''){
                echo 'Pipeline failed!'
            }

        }
    }
}