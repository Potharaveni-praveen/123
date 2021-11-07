pipeline{
    agent any
    tools{
        maven 'maven:3.8'
    }       
    stages{
        stage('increment version'){
            steps{
                script{
                    echo 'incrementing app version'
                    sh 'mvn build-helper:parse-version versions:set \
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVesrsion.nextIncrementalVersion} \
                    versions:commit'
                    def matcher = read file('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0] [1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage("build war") {
            steps{
                script{
                    echo "building application"
                    sh 'mvn clean package'
                }
            }
        }
        stage("build image") {
            steps{
                script{
                    echo "building docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]){
                       sh "docker build -f Dockerfile.dockerfile -t 8978677272/praveen:${IMAGE_NAME} ."
                       sh "echo $PASS | docker login -u $USER --password-stdin"
                       sh "docker push 8978677272/praveen:${IMAGE_NAME}"
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
