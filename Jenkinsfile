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
        stage("increment app"){
            steps{
                script{
                    gv.incrementVersion()
                }
            }
        }
        
        stage('build app'){
            steps{
                script{
                    gv.buildJar()
                }
            }
        }
        stage("build image"){
            steps{
                script{
                    gv.buildImage()
                }
            }
        }
        stage('deploy'){
            steps{
                script{
                    gv.deployApp()
                }
            }
        }
    }
}