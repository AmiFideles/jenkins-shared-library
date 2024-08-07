import groovy.json.JsonBuilder

def call(Map params) {
    def server = params.server ?: rabbitMQServer
    def port = params.port ?: rabbitMQPort
    def queue = params.queue ?: queueName
    def user = params.user ?: username
    def pass = params.pass ?: password
    def payload = params.payload ?: [:]

    def jsonPayload = new JsonBuilder(payload).toString()

    sh """
    curl -u ${user}:${pass} -X POST -H "Content-Type: application/json" \
        -d '${jsonPayload}' \
        http://${server}:${port}/api/exchanges/%2f/amq.default/publish \
        -d '{"vhost":"/","name":"${queue}","properties":{},"routing_key":"${queue}","delivery_mode":2,"payload":"${jsonPayload}","headers":{},"props":{},"payload_encoding":"string"}'
    """
}