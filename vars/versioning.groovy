def call() {
    def buildNumber = env.BUILD_NUMBER ?: '0'
    def version = "1.0.${buildNumber}"
    env.BUILD_VERSION = version
    return version
}