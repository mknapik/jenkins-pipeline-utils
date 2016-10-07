def isMasterBranch = env.BRANCH_NAME == 'master'

node('master') {
    cleanWorkspace()

    stage 'Checkout'
    checkout(scm)
    stash name: 'source'

    if(isMasterBranch) {
        stage name: 'Deploy', concurrency: 1
        dir("${env.HOME}/workflow-libs") {
            unstash 'source'
        }
    }

    cleanWorkspace()
}

def cleanWorkspace() {
    step([$class: 'WsCleanup'])
}
