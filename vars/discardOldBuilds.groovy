def call(Map<String, Integer> args) {
    def daysToKeepStr = "${args.days ?: 100}"
    def numToKeepStr = "${args.num ?: 30}"
    def strategy = [$class: 'LogRotator', artifactDaysToKeepStr: '0', artifactNumToKeepStr: '0', daysToKeepStr: daysToKeepStr, numToKeepStr: numToKeepStr]
    def property = [$class: 'BuildDiscarderProperty', strategy: strategy]
    properties([property])
}
