def call(String credential_id, Closure cl) {
    withCredentials(
        [[$class: 'UsernamePasswordMultiBinding', credentialsId: credential_id,
            usernameVariable: 'MYSQL_USERNAME', passwordVariable: 'MYSQL_PASSWORD']]
    ) {
        withEnv([
            "MYSQL_DATABASE=database_${env.BUILD_NUMBER}_${env.EXECUTOR_NUMBER}",
            "MYSQL_HOST=127.0.0.1",
            "MYSQL_PORT=3306"
        ]) {
            cl()
        }
    }
}
