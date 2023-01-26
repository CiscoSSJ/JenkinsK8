podTemplate(inheritFrom: 'icagent01') {
  node(POD_LABEL) {
    stage('Checkout') {
        container('git') {
            sh 'git clone '
        }
    }
    stage('Info') {
        container('jenkins-slave') {
            sh 'pwd'
            sh 'ls'
        }
    }
    stage('Build') {
        container('maven') {
            sh 'mvn -B -DskipTests clean package'
        }
    }
    stage('Test') {
        container('maven') {
            sh 'mvn test'
        }
    }
    stage('Kubectl') {
        container('kubectl') {
            sh 'kubectl --version'
        }
    }
  }
}