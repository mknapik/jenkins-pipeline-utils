def call(String credential_id, Closure cl) {
    withCredentials(
        [[$class: 'UsernamePasswordMultiBinding', credentialsId: "${credential_id}",
            usernameVariable: 'MYSQL_USERNAME', passwordVariable: 'MYSQL_PASSWORD']]
    ) {
        cl()
    }
}