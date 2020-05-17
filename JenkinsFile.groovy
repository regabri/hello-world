node{
      def mvnHome = tool name: 'M2_HOME', type: 'maven' 
      stage('Checkout'){
         echo "KOU Checkout"
         git 'https://github.com/khalidOubelque/hello-world.git'
       
      }  
      stage('Build'){
         echo "KOU Maven Build"
        sh "${mvnHome}/bin/mvn clean package -Dmaven.test.skip=true"
      }
     stage ('Test-JUnit'){
          echo "KOU UT"
         sh "'${mvnHome}/bin/mvn' test surefire-report:report"
      }  
    
      stage('Deploy'){
            sshPublisher(publishers: 
                         [sshPublisherDesc(configName: 'dockerHost', 
                                           transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'apt-get update', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/home/dockeradmin/webapp/target', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '*.war')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
      }
      
 }
