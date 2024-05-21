node {
  
  stage('Clone') {
      dir('.') {
          git branch: 'main', credentialsId: 'github_com', url: 'https://github.com/VitorFaccio1/Projeto-Employees'
      }    
  }       

  stage('Build') {
      withGradle {
        sh './gradlew clean build --stacktrace -i'
      }  
  }  
}
