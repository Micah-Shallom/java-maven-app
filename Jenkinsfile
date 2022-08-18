@Library("Jenkins-Shared-Library") //specify the jenkins-shared-library on jenkins server; provide path to the repo
def gv

pipeline{
    agent any

    tools{
        maven 'Maven'
    }

    stages{
        stage("init"){
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
                    buildImage 'mshallom/test-repo:jma-2.0'
                }
            }
        }
        stage('deployApp'){
            steps{
                script{
                    gv.deployApp()
                }
            }
        }
    }
}