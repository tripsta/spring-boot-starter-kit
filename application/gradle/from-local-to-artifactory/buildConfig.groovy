binaryRepository {
    url = !System.getProperty('repo.url') ? 'http://jenkins.tripsta.net:8081/artifactory' : System.getProperty('repo.url')
    username = !System.getProperty('repo.username') ? 'admin' : System.getProperty('repo.username')
    password = !System.getProperty('repo.password') ? '7WN8^UbSqk-Kbe' : System.getProperty('repo.password')
    name = !System.getProperty('repo.name') ? 'libs-release-local' : System.getProperty('repo.name')
}
