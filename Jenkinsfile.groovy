pipeline{
    agent any
    tools{
        maven 'maven'
    }       
    stages{
        stage("build jar") {
            steps{
                script{
                    echo "building application"
                    bat 'mvn package'
                }
            }
        }
        stage("build image") {
            steps{
                script{
                    echo "building docker image"
                    withCredentials([usernamepassword(credentialsid: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]){
                       bat 'docker build -f Dockerfile.dockerfile -t 8978677272/praveen:pra-2.0 .'
                       bat 'echo $PASS | docker login -u $USERNAME --password-stdin'
                       bat 'docker push 8978677272/praveen:pra-2.0'
                    }    
                }
            }
        }
        stage("deploy"){
            steps{
                script{
                    echo "deploying application"
                }
            }
        }
    }
}