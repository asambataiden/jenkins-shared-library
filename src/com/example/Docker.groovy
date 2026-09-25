#!/user/bin/env groovy

package com.example

class Docker implements Serializable {
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "Building the docker image: ${imageName}"
        script.withCredentials([
            script.usernamePassword(
                credentialsId: 'docker-hub-repo-asambataiden',
                passwordVariable: 'DOCKERHUB_PASSWORD',
                usernameVariable: 'DOCKERHUB_USERNAME'
            )
        ]) {
            script.sh "docker build -t ${imageName} ."
            script.sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin"
            script.sh "docker push ${imageName}"
        }
    }
}

