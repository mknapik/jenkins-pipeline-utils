node('master') {
    cleanWorkspace()

    checkout(scm)
    stash name: 'source'

    dir("${env.HOME}/workflow-libs") {
        echo pwd()
        unstash 'source'
    }

    cleanWorkspace()
}
