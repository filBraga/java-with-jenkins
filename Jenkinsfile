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
        stage('test maven') {
            steps {
                sh '/opt/apache-maven-3.9.9/bin/mvn --version'
            }
        }
        stage('Build') {
            steps {
                sh '/opt/apache-maven-3.9.9/bin/mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh '/opt/apache-maven-3.9.9/bin/mvn test'
            }
        }
        stage('Package') {
            steps {
                sh '/opt/apache-maven-3.9.9/bin/mvn package'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                                artifacts: '**/target/*.war'
            }
        }
        stage('Deploy') {
            steps {
                sh 'sudo docker build -f Dockerfile -t myapp .'
                sh 'sudo docker rm -f "myappcontainer" || true'
                sh 'sudo docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
            }
        }
    }
}