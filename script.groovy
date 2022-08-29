def deployImage(String IMAGE_NAME) {
    echo 'deploying the application...'

    def shellCMD = "bash ./command.sh ${IMAGE_NAME}"
    sshagent(['ec2-server-key']) {
        sh "scp command.sh ec2-user@34.228.40.92:/home/ec2-user "
        sh "scp docker-compose.yaml ec2-user@34.228.40.92:/home/ec2-user "
        sh "ssh -o StrictHostKeyChecking=no ec2-user@34.228.40.92 ${shellCMD}"
    }
} 

return this