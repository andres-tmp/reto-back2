pipeline {


    environment {
        registry = "andrestmp/reto-intercorp"
        registryCredential = 'dockerhub'
    }

    agent any

    stages {

        stage('Compile') {
            steps {
                sh "whoami"
                echo "-=- compiling project -=-"
                sh "./mvnw clean compile"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }

        stage('Package') {
            steps {
                echo "-=- packaging project -=-"
                sh "./mvnw package -DskipTests"
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }


        stage('Build and deploy Image') {
          steps{
            script {
              docker.withRegistry( '', registryCredential ) {
                docker.build(registry + ":$BUILD_NUMBER").push('latest')
                //app.push("${env.BUILD_NUMBER}")
                //app.push("latest")
              }
            }
          }
        }
        stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }

        stage ('Deploy') {
            steps{
                sshagent(credentials : ['ec2-app']) {
                    sh 'ssh -o StrictHostKeyChecking=no ubuntu@ec2-18-188-74-72.us-east-2.compute.amazonaws.com "cd /home/ubuntu" '
                    sh 'ssh ubuntu@ec2-18-188-74-72.us-east-2.compute.amazonaws.com sudo su -c "./reload-container" '

                }
            }
        }

        stage ("Wait prior starting test") {
            steps{
                //def time = params.SLEEP_TIME_IN_SECONDS
                echo "Waiting 30 seconds for deployment to complete"
                sleep(time:30,unit:"SECONDS")
            }
        }

        stage('Functional test') {
          steps{
            //withNPM(npmrcConfig:'my-custom-nprc') {
                //sh "npm install -g newman"
                sh "newman run test/customer_microservice.postman_collection.json -e test/stage-env.postman_environment.json --timeout-request 30000 -k"
            //}

          }
        }
    }
}