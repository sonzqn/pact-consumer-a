pipeline {
  agent any
  stages {
    stage('aaa') {
      steps {
        sh 'fdsfds'
      }
    }

    stage('bbb') {
      parallel {
        stage('bbb') {
          steps {
            isUnix()
            fileExists 'fdsfd'
          }
        }

        stage('ccc') {
          steps {
            sh 'fdsfds'
          }
        }

        stage('ddd') {
          steps {
            bat 'dfdf'
          }
        }

      }
    }

    stage('') {
      steps {
        retry(count: 4)
      }
    }

  }
}