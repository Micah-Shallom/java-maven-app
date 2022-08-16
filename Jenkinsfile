pipeline{
    agent none

    stages{
        stage("test"){
            steps{
                echo "Testing the branch for $BRANCH_NAME.................."
            }
        }
        stage("Build"){
            when{
                expression{
                    BRANCH_NAME == "main"
                }
            }
            steps{
                echo "Building the application for $BRANCH_NAME.................."
            }
        }
        stage("deploy"){
            when{
                expression{
                    BRANCH_NAME == "main"
                }
            }
            steps{
                echo "Deploying the application for $BRANCH_NAME................"
            }
        }
    }
}