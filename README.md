# Implementing CI/CD on a Java Based Application 

In this project, I setup a CICD pipeline using Jenkins.

Abstractions of the code can be found in the Jenkins Shared Repository as the concept of the Jenkins Shared Library was used ![JSL_Repo](https://github.com/Micah-Shallom/Jenkins-shared-library)

Major Build Jobs in this pipeline includes:

- building the Java application
- containerizing the jar file using docker
- carrying out semantic app versioning of the java application before pushing to the docker repository
- pushing java application artifact to dockerhub


