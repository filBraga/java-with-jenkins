pipeline {
    agent any

    tools{
        git 'Default'
    }
    stages {
        stage('GetProject') {
            steps {
                git branch: 'main', url: 'https://github.com/filBraga/java-with-jenkins.git'
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
                sh 'mvn package'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                                artifacts: '**/target/*.war'
            }
        }
        stage('Approve Deployment') {
            steps {
                input message: 'Deploy to production?', ok: 'Deploy'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -f Dockerfile -t myapp .'
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
            }
        }
    }
}