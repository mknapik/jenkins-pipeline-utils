def call(Closure cl) {
    try {
        cl()
    } finally {
        step([$class: 'WsCleanup'])
    }
}
