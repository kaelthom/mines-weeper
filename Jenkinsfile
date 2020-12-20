pipeline {
    agent any
    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE    = 'sqlite'
    }
    stages {
        stage('build Maven') {
            steps {
                bat 'java --version'
                echo "Database engine is ${DB_ENGINE}"
                echo "DISABLE_AUTH is ${DISABLE_AUTH}"
                bat 'mvn install'
            }
        }
        stage('build npm') {
            steps {
                bat 'node -v'
                bat 'npm -v'
            }
        }
    }
}
