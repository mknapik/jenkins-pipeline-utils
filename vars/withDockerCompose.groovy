def call(String serviceName, Closure cl) {
    def compose = new me.knapik.DockerCompose("${env.JOB_NAME}-${env.BUILD_NUMBER}-${env.EXECUTOR_NUMBER}", serviceName, this)

    withinDockerCompose(compose, cl)
}

def call(Closure cl) {
    def compose = new me.knapik.DockerCompose("${env.JOB_NAME}-${env.BUILD_NUMBER}-${env.EXECUTOR_NUMBER}", this)

    withinDockerCompose(compose, cl)
}

def withinDockerCompose(me.knapik.DockerCompose compose, Closure cl) {
    withEnv(["TMPDIR=${env.TMPDIR == null ? '/tmp' : env.TMPDIR}"]) {
        compose.within(cl)
    }
}
