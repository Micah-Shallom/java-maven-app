def incrementVersion(){
    echo "incrementing app version"
    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit"
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}  
def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t mshallom/java-app:${IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push mshallom/java-app:${IMAGE_NAME}"
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

def commitVersion(){
    echo "Commiting new software version to github"
    // withCredentials([usernamePassword(credentialsId:'github-credentials', passwordVariable: 'PASSWD', usernameVariable: 'USER')]){
    withCredentials([gitUsernamePassword(credentialsId: 'github-credentials', gitToolName: 'git-tool')]){
            sh "git config --global user.email 'micahshallom@gmail.com'"
            sh "git config --global user.name 'Shallom Micah'"

            sh 'git status'
            sh 'git branch'
            sh 'git config --list'

            // sh 'git remote set-url origin https://${USER}:${PASSWD}@github.com/Micah-Shallom/java-maven-app.git'
            sh 'git remote set-url origin git@github.com:Micah-Shallom/java-maven-app.git'
            sh "git add ."
            sh "git commit -m 'ci: version bump'"
            sh "git push origin HEAD:jenkins-versioning"
        }
    // withCredentials([gitUsernamePassword(credentialsId: 'github-credentials', gitToolName: 'git-tool')]){
    //         sh "git config --global user.email 'micahshallom@gmail.com'"
    //         sh "git config --global user.name 'Shallom Micah'"

    //         sh 'git status'
    //         sh 'git branch'
    //         sh 'git config --list'

    //         sh 'git remote set-url origin git@github.com:Micah-Shallom/java-maven-app.git'
    //         sh "git add ."
    //         sh "git commit -m 'ci: version bump'"
    //         sh "git push origin HEAD:jenkins-versioning
    //         // sh 'git remote set-url origin https://${USER}:${PASSWD}@github.com/Micah-Shallom/java-maven-app.git'
    //     }
}

return this