#!/user/bin/env groovy

def call() {
    echo 'Building the docker image...........'
    withCredentials(
            [
                    usernamePassword(
                            credentialsId: 'docker-hub-repo-asambataiden',
                            passwordVariable: 'DOCKERHUB_PASSWORD',
                            usernameVariable: 'DOCKERHUB_USERNAME')
            ]
    )
            {
                sh 'docker build -t asambataiden/demo-app:jma-2.0 .'
                sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin'
                sh 'docker push asambataiden/demo-app:jma-2.0'
            }
}
