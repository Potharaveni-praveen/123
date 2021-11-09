pipeline {
    agent any
    tools {
        maven 'maven:3.8'
    }       
    stages {
        stage('increment version') {
            steps{
                script{
                    echo 'incrementing app version'
                    sh 'mvn build-helper:parse-version versions:set \
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion} \
                    versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage("build war") {
            steps {
                script {
                    echo "building application"
                    sh 'mvn clean package'
                }
            }
        }
        stage("build image") {
            steps { 
                script {
                    echo "building docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                       sh "docker build -f Dockerfile.dockerfile -t 8978677272/praveen:${IMAGE_NAME} ."
                       sh "echo $PASS | docker login -u $USER --password-stdin"
                       sh "docker push 8978677272/praveen:${IMAGE_NAME}"
                    }    
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying application"
                }
            }
        }
            stage('commit the update'){
                steps{
                    script{
                        withCredentials([usernamePassword(credentialsId: 'Pra', passwordVariable: 'PASS', usernameVariable: 'USER')]){
                    
                            sh 'git status'
                            sh 'git branch'
                            sh 'git config --list'
                   
                            sh "git remote set-url origin https://${USER}:${PASS}@github.com/Potharaveni-praveen/123.git"
                            sh 'git add .'
                            sh 'git commit -m "new version"'
                            sh 'git push -u origin master'
                        }
                    }
                }
            }
        }
    }

