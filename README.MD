

Essa � a API Rest que exp�e os servi�os para consultar (GET), cadastrar (POST) e deletar (DELETE) os Usuarios (users).

Ela foi desenvolvida utilizando Java 8, Spring Boot e Postgres Data Base.

A aplica��o est� desenvolvida da seguinte maneira :

        - Classe Application respons�vel por subir a aplica��o;
        - Classe UserServices respons�vel por expor os servi�os REST;
        - Pacote "domain" aonde est�o as classes de neg�cio (entidade e repositorio);
        - Src de Testes com apenas Testes Integrados (que batem nos end points dos ervi�os)
        
        
- Build da Aplica��o :        

        Essa aplica��o est� conteinerizada com Docker (https://www.docker.com/) contendo 2  docker files 
        (Dockerfile, db/Dockerfile) e um arquivo docker-compose. O primeiro � o arquivo da aplica��o (user-services), e
        o segundo � o arquivo do servi�o do banco de dados (Postgres).
        Para realizar o build e subir a aplica��o execute o comando "docker-compose up --build" (precisa ter o Docker instalado) na raiz da aplica��o.


- End Points (172.19.0.2 - � o IP do Docker Container, podendo subir com IP diferente. Para verificar IP de um Docker Container - https://stackoverflow.com/questions/17157721/how-to-get-a-docker-containers-ip-address-from-the-host) :

        GET    : http://172.19.0.2:8080/users
        GET    : http://172.19.0.2:8080/users?email=(email para realizar a busca)
        GET    : http://172.19.0.2:8080/users?name=(name para realizar a busca)
        GET    : http://172.19.0.2:8080/users?username=(username para realizar a busca)
        POST   : http://172.19.0.2:8080/users {User Json no body}
        DELETE : http://172.19.0.2:8080/users