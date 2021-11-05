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
                    sh 'mvn package'
                }
            }
        }
        stage("build image") {
            steps{
                script{
                    echo "building docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]){
                       sh 'docker build -f Dockerfile.dockerfile -t 8978677272/praveen:pra-2.0 .'
                       sh 'echo $PASS | docker login -u $USER --password-stdin'
                       sh 'docker push 8978677272/praveen:pra-2.0'
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