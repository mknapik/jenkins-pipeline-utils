def call(String slug, String credential_id, Closure cl) {
    withCredentials(
        [[$class: 'StringBinding', credentialsId: credential_id, variable: 'GEMNASIUM_TOKEN']]
    ) {
        withEnv(["GEMNASIUM_PROJECT_SLUG=${slug}"]) {
            cl()
        }
    }
}
