def call(Closure cl) {
    def compose = new DockerCompose2("${env.EXECUTOR_NUMBER}", this)

    withEnv(["TMPDIR=${env.TMPDIR == null ? '/tmp' : env.TMPDIR}"]) {
        compose.within(cl)
    }
}