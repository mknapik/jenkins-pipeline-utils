def call(String task) {
    sh "[[ -s Gemfile ]] && (bundle exec rake $task) || (rake $task)"
}