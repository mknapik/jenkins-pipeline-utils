package me.knapik

import org.jenkinsci.plugins.workflow.cps.CpsScript
import com.cloudbees.groovy.cps.NonCPS

class GitHub implements Serializable {
    @NonCPS
    static def findPullRequests(String jobName, String branchName, String githubAccessToken) {
        def (String owner, String repo) = jobName.split('/')
        String head = "${owner}:${branchName}"

        String result = new URL("https://api.github.com/repos/${owner}/${repo}/pulls?access_token=${githubAccessToken}&state=open&head=${head}").
            getText(connectTimeout: 5000,
                     readTimeout: 10000,
                     useCaches: true,
                     allowUserInteraction: false,
                     requestProperties: ['Connection': 'close'])

        def json = me.knapik.Json.parse(result)

        List<Integer> pullRequestIds = []
        List<String> targetBranchRefs = []

        for(int i = 0; i < json.size; ++i) {
            pullRequestIds << json[i]['number']
            targetBranchRefs << json[i]['base']['ref']
        }

        return [pullRequestIds: pullRequestIds, targetBranchRefs: targetBranchRefs]
    }
}
