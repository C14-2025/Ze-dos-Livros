pipeline {
    agent any

    parameters {
        string(name: 'PROJECT_PATH', defaultValue: 'Ze-dos-Livros/projetoc14', description: 'Caminho do projeto')
        string(name: 'MAVEN_TOOL', defaultValue: 'Maven3', description: 'Ferramenta Maven a usar')
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Clonando repositório...."
                checkout scm
            }
        }

        stage('Test') {
            steps {
                echo "Executando testes unitários..."
                dir('Ze-dos-Livros/projetoc14') {
                    sh "rm -rf target || true"
                    sh "${tool 'Maven3'}/bin/mvn clean install"
                }
            }
            post {
                always {
                    junit 'Ze-dos-Livros/projetoc14/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'Ze-dos-Livros/projetoc14/target/surefire-reports/*', fingerprint: true
                }
            }
        }
    }
}
