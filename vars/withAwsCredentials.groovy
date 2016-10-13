def call(String credentialsId, Closure cl) {
    withCredentials(
                [[$class          : 'UsernamePasswordMultiBinding', credentialsId: credentialsId,
                  usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]
        ) {
        cl()
    }
}
