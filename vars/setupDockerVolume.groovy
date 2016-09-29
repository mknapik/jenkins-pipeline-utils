def call(String name) {
    sh "(docker volume inspect ${name} > /dev/null 2>&1) || docker volume create --name=${name}"
}
