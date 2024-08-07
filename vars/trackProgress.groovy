def call(String stepName) {
    script {
        def payload = [
                jobName: env.JOB_NAME,
                buildNumber: env.BUILD_NUMBER,
                stepName: stepName,
                status: 'IN_PROGRESS',
                timestamp: new Date().toString()
        ]
        sendToRabbitMQ(payload: payload)
    }
}

def postStep(String stepName, String status) {
    script {
        def payload = [
                jobName: env.JOB_NAME,
                buildNumber: env.BUILD_NUMBER,
                stepName: stepName,
                status: status,
                timestamp: new Date().toString()
        ]
        sendToRabbitMQ(payload: payload)
    }
}
