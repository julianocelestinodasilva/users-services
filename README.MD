

Essa é a API Rest que expõe os serviços para consultar (GET), cadastrar (POST) e deletar (DELETE) os Usuarios (users).

Ela foi desenvolvida utilizando Java 8, Spring Boot e Postgres Data Base.

A aplicação está desenvolvida da seguinte maneira :

        - Classe Application responsável por subir a aplicação;
        - Classe UserServices responsável por expor os serviços REST;
        - Pacote "domain" aonde estão as classes de negócio (entidade e repositorio);
        - Src de Testes com apenas Testes Integrados (que batem nos end points dos erviços)
        
        
- Build da Aplicação :        

        Essa aplicação está conteinerizada com Docker (https://www.docker.com/) contendo 2  docker files 
        (Dockerfile, db/Dockerfile) e um arquivo docker-compose. O primeiro é o arquivo da aplicação (user-services), e
        o segundo é o arquivo do serviço do banco de dados (Postgres).
        Para realizar o build e subir a aplicação execute o comando "docker-compose up --build" (precisa ter o Docker instalado) na raiz da aplicação.


- End Points (172.19.0.2 - É o IP do Docker Container, podendo subir com IP diferente. Para verificar IP de um Docker Container - https://stackoverflow.com/questions/17157721/how-to-get-a-docker-containers-ip-address-from-the-host) :

        GET    : http://172.19.0.2:8080/users
        GET    : http://172.19.0.2:8080/users?email=(email para realizar a busca)
        GET    : http://172.19.0.2:8080/users?name=(name para realizar a busca)
        GET    : http://172.19.0.2:8080/users?username=(username para realizar a busca)
        POST   : http://172.19.0.2:8080/users {User Json no body}
        DELETE : http://172.19.0.2:8080/users