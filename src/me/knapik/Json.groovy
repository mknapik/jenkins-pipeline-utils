package me.knapik

import com.cloudbees.groovy.cps.NonCPS

class Json implements Serializable {
    @NonCPS
    static def parse(def json) {
        new groovy.json.JsonSlurperClassic().parseText(json)
    }
}
