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
    }
}