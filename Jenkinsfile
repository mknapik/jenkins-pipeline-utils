node('master') {
    cleanWorkspace()

    checkout(scm)
    stash name: 'source'

    dir("${env.HOME}/workflow-libs") {
        unstash 'source'
    }

    cleanWorkspace()
}

def cleanWorkspace() {
    step([$class: 'WsCleanup'])
}
