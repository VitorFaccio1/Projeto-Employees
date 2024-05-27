node {
  
  stage('Clone') {
      dir('.') {
          git branch: 'main', credentialsId: 'github_com', url: 'https://github.com/VitorFaccio1/Projeto-Employees'
      }    
  }       

  stage('Build') {
      steps {
          sh 'gradle clean build'
      }
  }

  stage('Test') {
      steps {
          sh 'gradle test'
      }
  }

  stage('Deploy') {
      steps {
          sh 'gradle deploy'
      }
  }

  post {
      success {
          echo 'Publicacao bem sucedida!!!'
      }
      failure {
          echo 'Publicacao falhou!!!'
      }
  }
}
