def incrementApp(){
    echo "incrementing app version"
    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit"
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def deployImage(String IMAGE_NAME) {
    echo 'deploying the application...'

    def shellCMD = "bash ./command.sh ${IMAGE_NAME}"
    def ec2Instance = "ec2-user@34.228.40.92"
    sshagent(['ec2-server-key']) {
        sh "scp command.sh ${ec2Instance}:/home/ec2-user "
        sh "scp docker-compose.yaml ${ec2Instance}:/home/ec2-user "
        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCMD}"
    }
} 

def commitVersion(){
    withCredentials([usernamePassword(credentialsId:'github-credentials', passwordVariable: 'PASSWD', usernameVariable: 'USER')]){
            sh "git config --global user.email 'jenkins@example.com'"
            sh "git config --global user.name 'jenkins'"

            sh 'git status'
            sh 'git branch'
            sh 'git config --list'

            sh 'git remote set-url origin https://${USER}:${PASSWD}@github.com/Micah-Shallom/java-maven-app.git'
            sh "git add ."
            sh "git commit -m 'ci: version bump'"
            sh "git push origin HEAD:jenkins-versioning"
    }
}

return this