# Desafio GreenMile - Quixáda

API de registro de horas trabalhadas desenvolvido durante o desafio da GreenMile - Quixadá.

Há uma versão implementada utilizando microserviços, na branch de [microservices](https://github.com/robsonsilv4/desafio/tree/microservices), onde além da aplicação ter sido divida em serviços, a escrita do código foi padronizada com nomes em inglês e documentação e mensagens em português, baseadas na sugestão do [Filipe Deschamps
](https://www.youtube.com/watch?v=xWbuHZcS8gA).

Foram realizadas modificações que não estão inclusivas na branch principal, como entre os os commits [4627756](https://github.com/robsonsilv4/desafioGreenMile/commit/462775676fa31b2fa4934caa591eb6ec611601ad) e [630f5ce](https://github.com/robsonsilv4/desafioGreenMile/commit/630f5ce6b688474c614fc428047bbde33f9f31fa).

Confira também a versão deste projeto utilizando CQRS e Event Sourcing no [repositório](https://github.com/robsonsilv4/desafioSpringAxon).

Ok, agora vamos falar deste projeto...

## Utilizando a API
---

Estes são os exemplos de utilização báscia da API, contendo os comandos e as respostas geradas pelos mesmos.

### Cadastro de usuários

Para cadastrar um novo usuário, utilize o seguinte comando:

```sh
curl -X POST \
  http://localhost:8082/usuarios \
  -H 'Content-Type: application/json' \
  -d '{
    "nome": "Robson",
    "username": "robson@greenmile.com",
    "senha": "desafio"
}'
```

No cabeçalho da resposta, irá conter o "location" do usuário inserido:

```sh
# ...
location: http://localhost:8082/usuarios/2 
# ...
```
---

### Recursos de usuários

Para poder realizar o login, onde o username e senha devem pertencer a usuário já cadastradao anteriormente, utilize o seguinte comando:
```sh
curl -X POST \
  http://localhost:8082/api/login \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "robson@greenmile.com",
    "senha": "desafio"
}'
```

No cabeçalho da resposta, irá conter o token de autenticação:

```sh
Authorization:	Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2Jzb25AZ3JlZW5taWxlLmNvbSIsImV4cCI6MTU1Mjk2OTk0N30.VEjesYlH_jF8KNC7EUkXwk0L2wSZswA-uZ-kEsq0rb0eTYJ92h0q2EEWkn2dxpTHojjQftC_azJf9OcO8_ijvA
```
---

Para consultar todos usuários registrados, utilize o seguinte comando:

```sh
curl -X GET \
  http://localhost:8080/api/usuarios \
  -H 'Content-Type: application/json' \
```

Será retornada uma lista páginada com todos os registros de usuários:
```sh
{
  "content": [
    {
      "id": 1,
      "name": "Robson",
      "username": "robson@greenmile.com"
    },
    {
      "id": 2,
      "name": "Samuel",
      "username": "samuel@greenmile.com"
    },
    {
      "id": 3,
      "name": "Gabriel",
      "username": "gabriel@greenmile.com"
    },

    # ...

  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageSize": 10,
    "pageNumber": 0,
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "totalPages": 1,
  "totalElements": 2,
  "last": true,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 2,
  "size": 10,
  "number": 0,
  "empty": false
}
```
---

Para buscar o registro de um usuário específico, utilize o seguinte comando, onde em .../usuarios/id, deve ser utilizado o id de um usuário cadastrado previamente:

```
curl -X GET \
  http://localhost:8080/api/usuarios/1 \
  -H 'Content-Type: application/json'
```

Será retornado as informações relacionadas ao usuário com id equivalente ao buscado:

```sh
{
  "id": 1,
  "name": "Robson",
  "username": "robson@greenmile.com",
  "profile": [
    "USER"
  ]
}
```
---

### Recusos de horas trabalhadas

Para cadastrar um novo registro de horas trabalhadas, certifice-se de ter feito antes o login e adquirido o token de autorização, após isso, basta adicioná-lo no cabeçalho da requisição, utilizando o seguinte comando:

```sh
curl --request POST \
  --url http://localhost:8080/api/horas \
  --header 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2Jzb25AZ3JlZW5taWxlLmNvbSIsImV4cCI6MTU1MzA1Mzc4MH0.i6nb5riIxzgvh6xPWGihdQys5d9OA7rIyzqNbRzE1grCf2u134XoE7Qs7-rhILqrPq2NXvln4yaTpNo9ftpZzA' \
  --header 'content-type: application/json' \
  --data '{
	"data": "19/03/2019",
	"horaEntrada": "08:00",
	"horaSaida": "22:00"
}'
```

Onde a data utiliza o padrão dia/mês/ano (dd/mm/aa) e a hora de entrada e a saída hora:minunto (hh/mm).

---

Para consultar todos os registros de horas cadastradas e a quem elas estão relacionadas, utilize o seguinte comando:

```sh
curl -X GET \
  http://localhost:8080/api/horas \
  -H 'Content-Type: application/json' \
```

Será retornada uma lista páginada com todos os registros de usuários:
```sh
{
  "content": [
    {
      "id": 1,
      "data": "17/03/2019",
      "horaEntrada": "08:00",
      "horaSaida": "22:00",
      "horasTrabalhadas": 14,
      "usuario": {
        "id": 1,
        "nome": "Robson",
        "email": "robson@greenmile.com",
        "perfis": [
          "USUARIO"
        ]
      }
    },
    {
      "id": 2,
      "data": "18/03/2019",
      "horaEntrada": "08:00",
      "horaSaida": "22:00",
      "horasTrabalhadas": 14,
      "usuario": {
        "id": 1,
        "nome": "Robson",
        "email": "robson@greenmile.com",
        "perfis": [
          "USUARIO"
        ]
      }
    },

    # ...
    
    {
      "id": 3,
      "data": "19/03/2019",
      "horaEntrada": "08:00",
      "horaSaida": "22:00",
      "horasTrabalhadas": 14,
      "usuario": {
        "id": 1,
        "nome": "Robson",
        "email": "robson@greenmile.com",
        "perfis": [
          "USUARIO"
        ]
      }
    }
  ],
  "pageable": {
    "sort": {
      "unsorted": false,
      "sorted": true,
      "empty": false
    },
    "pageSize": 10,
    "pageNumber": 0,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "first": true,
  "sort": {
    "unsorted": false,
    "sorted": true,
    "empty": false
  },
  "numberOfElements": 1,
  "size": 10,
  "number": 0,
  "empty": false
}
```
---

### Instalando

É necessário ter o gerenciador de depências [Maven](https://maven.apache.org/) instalado!

```sh
mvn install
mvm spring-boot:run
```

## Rodando os testes

Para rodar os testes, basta entrar na pasta raíz do projeto e executar o comando:

``` sh
mvn test
```

## Estilo de código

Este projeto utiliza o guia de estilos para Java do Google ([Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)).

## Documentação

A documentação gerada pelo Swagger pode ser consultada em [api-docs](http://localhost:8080/api/v2/api-docs). Também é possivel utilizar a interface web disponibilizada pelo Swagger, para entender como os funcionam os recursos de forma interativa através do [endereço](http://localhost:8080/api/swagger-ui.html) ou importação e executando a coleção gerada pelo [Postman](https://www.getpostman.com/collections/7ccc7b4734892b5eaf2a).

## Desenvolvido com

* [OpenJDK 8](https://openjdk.java.net/projects/jdk8/)
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data](https://spring.io/projects/spring-data)
* [Spring Security](https://spring.io/projects/spring-security)
* [jjwt](https://github.com/jwtk/jjwt)
* [PostgreSQL](https://www.postgresql.org/)
* [H2](http://www.h2database.com/html/main.html)
* [LOMBOK](https://projectlombok.org/)
* [SpringFox](http://springfox.github.io/springfox/)
* ...

Utilizando parcialmente o padrão [CQRS](https://martinfowler.com/bliki/CQRS.html) (Command Query Responsibility Segregation).

## Autor

* [Robson Silva](https://github.com/robsonsilv4)

## Referências

* https://spring.io/guides/tutorials/rest/
* https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html

## Licença

Este projeto está licensiado sob os termos de licença do MIT - veja o [LICENSE.md](https://opensource.org/licenses/MIT) para mais detalhes.
