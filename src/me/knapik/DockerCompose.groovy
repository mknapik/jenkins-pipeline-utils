package me.knapik

import org.jenkinsci.plugins.workflow.cps.CpsScript

class DockerCompose implements Serializable {
    private final String projectName;
    private CpsScript script;
    private Integer uid;
    private Integer gid;
    private String group;

    def DockerCompose(String projectName, CpsScript script) {
        this.projectName = projectName
        this.script = script
    }

    def createLocalUser(String service) {
        execRoot(service, "addgroup --quiet --gid ${getGid()} ${getGroup()}")
        execRoot(service, "adduser  --quiet --no-create-home --disabled-password --gecos '' `whoami` --uid ${getUid()} --gid ${getGid()}")
    }

    def exec(String service, String cmd) {
        exec(service, getUid(), cmd)
    }

    def execRoot(String service, String cmd) {
        exec(service, 'root', cmd)
    }

    def exec(String service, String user, String cmd) {
        script.sh """
            docker-compose -p $projectName exec --user $user -T $service bash -c "$cmd"
        """
    }

    def up() {
        script.sh "docker-compose -p $projectName up -d"
    }

    def down() {
        script.sh "docker-compose -p $projectName down --rmi local"
    }

    private Integer getUid() {
        if(uid != null) {
            return uid
        }
        this.uid = detectUid()
    }

    private Integer getGid() {
        if(gid != null) {
            return gid
        }
        this.gid = detectGid()
    }

    private String getGroup() {
        if(group != null) {
            return group
        }
        this.group = detectGroup()
    }

    private Integer detectUid() {
        String tmpFile = ".jenkins_uid_${uuid()}"
        try {
            script.sh "id -u > $tmpFile"
            Integer.parseInt(script.readFile(tmpFile).trim())
        } finally {
            script.sh "rm -f $tmpFile"
        }
    }

    private Integer detectGid() {
        String tmpFile = ".jenkins_gid_${uuid()}"
        try {
            script.sh "id -g > $tmpFile"
            Intger.parseInt(script.readFile(tmpFile).trim())
        } finally {
            script.sh "rm -f $tmpFile"
        }
    }

    private String detectGroup() {
        String tmpFile = ".jenkins_group_${uuid()}"
        try {
            script.sh "getent group ${getGid()} | cut -d: -f1 > $tmpFile"
            script.readFile(tmpFile).trim()
        } finally {
            script.sh "rm -f $tmpFile"
        }
    }

    private java.util.UUID uuid() {
        java.util.UUID.randomUUID()
    }
}