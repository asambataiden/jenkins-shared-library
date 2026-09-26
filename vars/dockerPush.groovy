import com.example.DockerService

def call(String imageName) {
    def docker = new DockerService(this)

    docker.dockerLogin()
    docker.dockerPush(imageName)
}