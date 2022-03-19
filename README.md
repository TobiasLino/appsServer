# DOODLE <h1>
O Doodle é um servidor de aplicações local, onde o usuário pode realizar o deploy de diversos tipos de aplicações e até servidores de banco de dados, sendo todos gerenciados pelo servidor e criados como containeres Docker.

### [Trello Oficial][trello]

## Planejamento dos entregáveis
### Sprint 1
O usuário deve ser capaz de realizar o deploy de diversas aplicações com a tecnologia Java e Vue.js.
O sistema deve permitir o gerenciamento e configuração de cada aplicação em um ambiente amigável.
O sistema deve mostrar o estado e o log de build.
O sistema deve permitir a criação de variáveis de ambiente.
### Sprint 2
O usuário deve ser capaz de adicionar adds independentes para cada aplicação, como servidores de banco de dados, servidores AMQP, etc.
O sistema deve ser capaz de realizar o rollback de aplicações.
O usuário administrador deve ser capaz de executar o sistema através de um executável(Windows) ou um script (Linux), podendo definir as configurações de execução da JVM (como tamanho de memória a ser utilizado).
### Sprint 3
### Sprint 4

### Como compilar o sistema
O Doodle foi desenvolvido em Java utilizando o framework Spring Boot e Maven (build) e Vue.js para as telas, portanto é possível executá-lo de forma simples.
#### Windows:
> com usuário ADMINISTRADOR executar o seguinte comando:

> `mvn clean spring-boot:run`

> O sistema deve subir tanto o backend, quanto as telas na porta padrão 8080.
#### Linux:
> com usuário `sudo` executar o seguinte comando:

> `sudo mvn clean spring-boot:run`

> O sistema deve subir tanto o backend, quanto as telas na porta padrão 8080.


[trello]:https://trello.com/b/S7Vku5Py/todo
