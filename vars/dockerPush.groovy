import com.example.Docker

def call(String imageName) {
    def docker = new Docker(this)

    docker.dockerLogin()
    docker.dockerPush(imageName)
}