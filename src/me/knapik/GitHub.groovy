package me.knapik

import org.jenkinsci.plugins.workflow.cps.CpsScript
import com.cloudbees.groovy.cps.NonCPS

class GitHub implements Serializable {
    @NonCPS
    static def findPullRequests(String jobName, String branchName, String githubAccessToken) {
        def (String owner, String repo) = jobName.split('/')
        
        String result = findByIdOrHead(owner, repo, branchName, githubAccessToken)

        def json = me.knapik.Json.parse(result)

        List<Integer> pullRequestIds = []
        List<String> targetBranchRefs = []

        for(int i = 0; i < json.size; ++i) {
            pullRequestIds << json[i]['number']
            targetBranchRefs << json[i]['base']['ref']
        }

        return [pullRequestIds: pullRequestIds, targetBranchRefs: targetBranchRefs]
    }
    
    @NonCPS
    static def findByIdOrHead(String owner, String repo, String branchName, String githubAccessToken) {
        def match = (branchName =~ /PR-([0-9]+)/)

        switch (match.count) {
        case 0:
            return findByHead(owner, repo, branchName, githubAccessToken)
        case 1:
            Integer id = Integer.parseInt(match[0][1])
            return findById(owner, repo, id, githubAccessToken)
        default:
            assert false
        }
    }
    
    @NonCPS
    static def findByHead(String owner, String repo, String branchName, String githubAccessToken) {
        String head = "${owner}:${branchName}"

        String result = get("https://api.github.com/repos/${owner}/${repo}/pulls?access_token=${githubAccessToken}&state=open&head=${head}")
        return result
    }

    @NonCPS
    static def findById(String owner, String repo, Integer id, String githubAccessToken) {
        String result = get("https://api.github.com/repos/${owner}/${repo}/pulls/${id}?access_token=${githubAccessToken}")
        return "[${result}]"
    }

    @NonCPS
    static def get(String url) {
        String result = new URL(url).
            getText(connectTimeout: 5000,
                    readTimeout: 10000,
                    useCaches: true,
                    allowUserInteraction: false,
                    requestProperties: ['Connection': 'close'])
        return result
    }
}
