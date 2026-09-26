#!/user/bin/env groovy

package com.example

class Docker implements Serializable {
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
            script.echo "Building the docker image: ${imageName}"
            script.sh "docker build -t ${imageName} ."

    }

    def dockerLogin() {
        script.withCredentials([
                script.usernamePassword(
                        credentialsId: 'docker-hub-repo-asambataiden',
                        usernameVariable: 'DOCKERHUB_USERNAME',
                        passwordVariable: 'DOCKERHUB_PASSWORD'
                )
        ]) {
            script.sh '''
                echo "$DOCKERHUB_PASSWORD" | \
                docker login \
                  --username "$DOCKERHUB_USERNAME" \
                  --password-stdin
            '''
        }
    }


    def dockerPush(String imageName) {
        script.sh "docker push ${imageName}"
    }
}

