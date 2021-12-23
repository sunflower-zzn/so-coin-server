node {
        stage('get code') {
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'xxx', url: 'http://xxx.git']]])
        }
        stage('build'){
             sh"sh run.sh"
        }
}
