def call(String credential_id, Closure cl) {
    withCredentials(
        [[$class: 'StringBinding', credentialsId: credential_id, variable: 'CODECLIMATE_REPO_TOKEN']]
    ) {
        cl()
    }
}
