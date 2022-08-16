def gv

pipeline{
    agent any

    tools{
        maven 'maven-3.6'
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
                    gv.buildJar()
                }
            }
        }
        stage('buildImage'){
            steps{
                script{
                    gv.buildImage()
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