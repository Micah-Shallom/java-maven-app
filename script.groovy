def buildJar(){
    echo "Building the maven application"
    sh "mvn package"
}


def buildImage(){
    echo "Building the docker image from the dockerfile"
    withCredentials([
        usernamePassword(
            credentials:'dockerhub-credentials',
            usernameVariable: "USER"
            passwordVariable: "PASSWD"
        )
    ]) {
        sh "docker build -t mshallom/java-app:jma-2.0 ."
        sh "echo $PASSWD | docker login -u $USER --password-stdin"
        sh "docker push mshallom/java-app:jma-2.0"
    }
}

def deployApp(){
    echo "Deploying the application"
}