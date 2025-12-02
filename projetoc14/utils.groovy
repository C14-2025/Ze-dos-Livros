def updateStageStatus(Map stageStatus, String stageName, String status = null) {
    stageStatus[stageName] = status ?: currentBuild.currentResult ?: 'SUCCESS'
}

def generateCoverage(Map stageStatus, steps, String projectPath = 'Ze-dos-Livros/projetoc14') {
    try {
        steps.echo "Gerando relatório de cobertura Jacoco..."
        steps.dir(projectPath) {
            steps.sh "rm -rf target || true"
            // Gera o relatório, mesmo se testes falharem parcialmente
            steps.sh "${steps.tool('Maven3')}/bin/mvn test jacoco:report || true"
        }
        // Atualiza stageStatus de Coverage
        stageStatus['Code Coverage'] = 'SUCCESS'
    } catch (Exception e) {
        steps.echo "Falha ao gerar relatório de cobertura: ${e.message}"
        stageStatus['Code Coverage'] = 'FAILED'
    }
}


def sendStatusEmail(String status, Map stageStatus, steps) {
    def defaultStages = ['Build Once', 'Test', 'Code Coverage', 'Integration Test', 'Build']
    defaultStages.each { name -> stageStatus.putIfAbsent(name, 'SKIPPED') }

    def overview = defaultStages.collect { name -> "${name}: ${stageStatus[name]}" }.join("\n")

    def coverageInfo = ''
    if(stageStatus['Code Coverage'] == 'SUCCESS') {
        coverageInfo = "\nRelatorio de cobertura disponivel em ${steps.env.BUILD_URL}jacoco/"
    }

    steps.emailext(
            subject: "CI/CD • ${steps.env.JOB_NAME} • Build #${steps.env.BUILD_NUMBER} • ${status}",
            mimeType: 'text/plain',
            body: """\
========== NOTIFICAÇÃO CI/CD ==========
Job: ${steps.env.JOB_NAME}
Build: ${steps.env.BUILD_NUMBER}
Status: ${status}

Overview do Pipeline:
${overview}
${coverageInfo}
Veja resultados:
${steps.env.BUILD_URL}
=========================================
""".stripIndent(),
            recipientProviders: [
                    [$class: 'DevelopersRecipientProvider'],
                    [$class: 'RequesterRecipientProvider']
            ]
    )
}
return this
