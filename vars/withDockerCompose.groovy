def call(Closure cl) {
    stage 'docker-compose up'
    def compose = new me.knapik.DockerCompose("${env.EXECUTOR_NUMBER}", this)

    withEnv(["TMPDIR=${env.TMPDIR == null ? '/tmp' : env.TMPDIR}"]) {
        try {
            compose.up()

            cl(compose)
        } finally {
            stage 'docker-compose down'
            compose.down()
        }
    }
}