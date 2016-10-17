def call(String credentialsId, Closure cl) {
    withCredentials([[$class: 'StringBinding', credentialsId: credentialsId, variable: 'GITHUB_ACCESS_TOKEN']]) {
        def result = me.knapik.GitHub.findPullRequests(env.JOB_NAME, env.BRANCH_NAME, env.GITHUB_ACCESS_TOKEN)
        String githubAccessToken = env.GITHUB_ACCESS_TOKEN

        def pullRequestIds = result['pullRequestIds']
        def targetBranchRefs = result['targetBranchRefs']

        for (int i = 0; i < pullRequestIds.size(); ++i) {
            cl(pullRequestIds[i], targetBranchRefs[i], githubAccessToken)
        }
    }
}
