library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'https://github.com/Micah-Shallom/Jenkins-shared-library.git',
     credentialsId: 'github-credentials'
    ]
)

def gv

pipeline{
    agent any

    tools{
        maven 'Maven'
    }
    stages{
        stage('init'){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage("Increment App Version"){
            steps{
                script{
                    gv.incrementApp()
                }
            }
        }
        stage('build jar'){
            steps{
                script{
                    buildJar()
                }
            }
        }
        stage('buildImage'){
            steps{
                script{
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
        }
        stage('deployToServer'){
            steps{
                script{
                    gv.deployImage(env.IMAGE_NAME)
                }
            }
        }
        stage("Commit app version update to github"){
            steps{
                script{
                    gv.commitVerion()
                }
            }
        }
    }
}