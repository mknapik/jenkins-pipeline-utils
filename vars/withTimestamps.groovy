def call(Closure cl) {
    wrap([$class: 'TimestamperBuildWrapper']) {
        cl()
    }
}
