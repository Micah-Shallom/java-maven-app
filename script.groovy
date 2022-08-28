def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def deployImage(String IMAGE_NAME) {
    echo 'deploying the application...'
    def dockerCMD = "docker run -d -p 3000:80 ${IMAGE_NAME}"
    sshagent(['ec2-server-key']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@34.228.40.92 ${dockerCMD}"
    }
} 

return this