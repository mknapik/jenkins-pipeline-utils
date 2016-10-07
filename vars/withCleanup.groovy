def call(Closure cl) {
    step([$class: 'WsCleanup'])
    try {
        cl()
    } finally {
        step([$class: 'WsCleanup'])
    }
}
