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
    environment{
        IMAGE_NAME = "mshallom/maven-app:1.0"
    }

    stages{
        stage('init'){
            steps{
                script{
                    gv = load "script.groovy"
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
    }
}