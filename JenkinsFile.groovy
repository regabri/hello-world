pipeline{
      agent any
      environment {
            def mvnHome = tool name: 'M2_HOME', type: 'maven' 
      }

      stages{
             stage('Checkout'){
                   steps{
                        echo "KOU Checkout"
                       // git 'https://github.com/khalidOubelque/hello-world.git'
                   }               
             }
       
            
            stage('Build'){
                  steps{
                     echo "KOU Maven Build"
                    // sh "${mvnHome}/bin/mvn clean package -Dmaven.test.skip=true"
                  }
            }
            
             stage ('Test-JUnit'){
                   steps{
                     echo "KOU UT"
                   //  sh "'${mvnHome}/bin/mvn' test surefire-report:report"
                   }
            }
            
            stage('Deploy') { 
                  steps{
                        echo "KOU Deploy"
                        withCredentials([
                              usernamePassword(credentialsId:'Jenkins_to_Docker', usernameVariable: user, passwordVariable: pwd)
                        ]){
                           //   sh "sshpass -p khalidoubelque" 
                             // sh "scp StrictHostKeyChecking=no webapp/target/*.war dockeradmin@172.31.35.165:/home/dockeradmin/webapp/target"
                              echo "KOU Test credentials username  $user  and password  $pwd "

                        }
                  }
            }
            
            
      } 
    
 }
      

