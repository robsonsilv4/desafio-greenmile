# Desafio GreenMile - Quixáda

API de registro de horas trabalhadas desenvolvido durante o desafio da GreenMile - Quixadá.

## Começando

Adicionar descrição e exemplos de uso utilizando o cURL...

## Utilizando a API
---

Adicionar descrição para as requisições...

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

### Consultas de usuários

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

### Instalando

É necessário ter o Maven instalado!

```sh
mvn install
mvm spring-boot:run
```

## Rodando os testes

Para rodar os testes, basta entrar na pasta raíz do projeto e executar o comando:

``` sh
mvn test
```

### Estilo de código

Este projeto utiliza o guia de estilos para Java do Google ([Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)).

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

## Autor

* [Robson Silva](https://github.com/robsonsilv4)

## Licença

Este projeto está licensiado sob os termos de licença do MIT - veja o [LICENSE.md](https://opensource.org/licenses/MIT) para mais detalhes.
