pipeline{
    agent any

    environment{
        NEW_VERSION='1.3.0'
        // SERVER_CREDENTIALS=credentials('server-credentials')
    }
    tools{
        maven 'maven-3.6'
    }
    parameters{
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }

    stages{
        stage('Build'){
            when{
                expression{
                    BRANCH_NAME=='jenkins-jobs'
                }
            }
            steps{
                echo "Building the application"
                echo " Application version is ${NEW_VERSION}"
                sh "mvn install"

            }
        }
        stage('Test'){
            when{
                expression{
                    params.executeTests 
                }
            }
            steps{
                echo "Testing the application"
            }
        }
        stage('Deploying'){
            steps{
                echo "Deploying the application" 
                echo "Deploying version ${params.VERSION}"
                withCredentials([
                    usernamePassword(credentials:'server-credentials', usernameVariable: USER , passwordVariable: PWD)
                ]){ 
                    sh "${USER} ${PWD}"
                }
            }
        }
    }
}