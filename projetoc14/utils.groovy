def updateStageStatus(Map stageStatus, String stageName, String status = null) {
    stageStatus[stageName] = status ?: currentBuild.currentResult ?: 'SUCCESS'
}

def sendStatusEmail(String status, Map stageStatus, steps) {
    def defaultStages = ['Checkout', 'Test', 'Code Coverage', 'Integration Test', 'Build']
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