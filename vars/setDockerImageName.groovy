def call() {
    env.DOCKER_IMAGE_NAME = replaceUnallowedCharacters(env.JOB_NAME)
}

def replaceUnallowedCharacters(String string) {
  string.replaceAll('%2F', '/').replaceAll(/[^a-z0-9-_.]/, '.')
}
