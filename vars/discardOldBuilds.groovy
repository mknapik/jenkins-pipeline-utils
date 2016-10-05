def call(Map<String, Integer> args = [:]) {
    def daysToKeepStr = "${args.days ?: 100}"
    def numToKeepStr = "${args.num ?: 30}"
    // artifactDaysToKeepStr: '0', artifactNumToKeepStr: '0', 
    def strategy = [$class: 'LogRotator', daysToKeepStr: daysToKeepStr, numToKeepStr: numToKeepStr]
    def property = [$class: 'BuildDiscarderProperty', strategy: strategy]
    properties([property])
}
