node('master') {
    cleanWorkspace()

    stage 'Checkout'
    checkout(scm)
    stash name: 'source'

    stage 'Deploy'
    dir("${env.HOME}/workflow-libs") {
        unstash 'source'
    }

    cleanWorkspace()
}

def cleanWorkspace() {
    step([$class: 'WsCleanup'])
}
