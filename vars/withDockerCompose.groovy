def call(Closure cl) {
    echo 'docker-compose up'
    def compose = new me.knapik.DockerCompose("${env.EXECUTOR_NUMBER}", this)

    withEnv(["TMPDIR=${env.TMPDIR == null ? '/tmp' : env.TMPDIR}"]) {
        try {
            compose.up()

            cl(compose)
        } finally {
            echo 'docker-compose down'
            compose.down()
        }
    }
}
