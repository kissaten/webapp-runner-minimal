# Sample webapp-runner app

## Using Git

```
$ mvn clean install
$ heroku local
$ heroku create
$ git push heroku master
```

## Using the CLI

```
$ mvn clean install
$ heroku plugins:install heroku-cli-deploy
$ heroku war:run target/*.war
$ heroku create
$ heroku war:deploy target/*.war
```

## License

MIT
