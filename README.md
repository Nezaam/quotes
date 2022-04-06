[![Quotes CICD Pipeline](https://github.com/Nezaam/quotes/actions/workflows/main.yml/badge.svg)](https://github.com/Nezaam/quotes/actions/workflows/main.yml)
[![Deployed on Heroku](https://img.shields.io/badge/heroku-deployed-blueviolet.svg?logo=heroku)](https://test-quotesapp.herokuapp.com/)
[![Pushed to Docker Hub](https://img.shields.io/badge/docker_hub-released-blue.svg?logo=docker)](https://hub.docker.com/repository/docker/nezaam/quotes)
# quotes
Simple Quotes Service Backend

A live deployment is available on Heroku: https://test-quotesapp.herokuapp.com/

### Available Services

| HTTP METHOD | PATH                         | USAGE                       |
|-------------|------------------------------|-----------------------------|
| POST        | /api/v1/accounts/            | create a new account        |
| GET         | /api/v1/accounts/            | get all accounts            |
| GET         | /api/v1/accounts/{accountId} | get account by accountId    | 
| PUT         | /api/v1/accounts/{accountId} | update account by accountId | 
| DELETE      | /api/v1/accounts/{accountId} | delete account by accountId | 
| POST        | /api/v1/quotes/              | create a new quote          | 
| GET         | /api/v1/quotes/              | get all quotes              | 
| GET         | /api/v1/quotes/{quoteId}     | get quote by id             | 
| PUT         | /api/v1/quotes/{quoteId}     | update quote by quoteId     | 
| DELETE      | /api/v1/quotes/{quoteId}     | delete quote by quoteId     | 
| POST        | /api/v1/quotes/upvote        | upvote quote                | 
| POST        | /api/v1/quotes/downvote      | downvote quote              | 
| GET         | /api/v1/quotes/top           | get top 10 quotes           | 
| GET         | /api/v1/quotes/flop          | get flop 10 quotes          | 

### Http Status
- 200 OK
- 201 Created
- 400 Bad Request
- 403 Forbidden
- 404 Not Found
- 500 Internal Server Error

Swagger UI available on: https://test-quotesapp.herokuapp.com/swagger-ui/index.html  
OpenAPI yaml-definitions available on: https://test-quotesapp.herokuapp.com/swagger-ui/index.html
