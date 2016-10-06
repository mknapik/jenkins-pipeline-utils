def call() {
    if(env.BRANCH_NAME == null) {
        env.DOCKER_IMAGE_TAG = 'latest'
        env.DOCKER_IMAGE_NAME = replaceUnallowedCharacters(env.JOB_NAME)
    } else {
        def tag = replaceUnallowedCharacters(env.BRANCH_NAME)
        def job_name = replaceUnallowedCharacters(env.JOB_NAME)
        def image_name = job_name.replaceAll(/.${tag}^/, '')
        env.DOCKER_IMAGE_TAG = tag
        env.DOCKER_IMAGE_NAME = image_name
    }
}

def replaceUnallowedCharacters(String string) {
  string.replaceAll('%2F', '/').replaceAll(/[^a-z0-9-_.]/, '.')
}
