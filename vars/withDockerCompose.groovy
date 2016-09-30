def call(Closure cl) {
    def compose = new me.knapik.DockerCompose("${env.EXECUTOR_NUMBER}", this)

    withEnv(["TMPDIR=${env.TMPDIR == null ? '/tmp' : env.TMPDIR}"]) {
        compose.within(cl)
    }
}
