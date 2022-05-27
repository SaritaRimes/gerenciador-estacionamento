## Gerenciador de estacionamento de carros e motos 

![image](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)![image](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)![image](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)![image](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)![image](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)![image](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)![image](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)![image](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

_____

### Programas necessários para a execução

Para executar a aplicação, você vai precisar de alguns programas. Caso não tenha, aqui vão alguns links que podem te ajudar.

:arrow_right: [Link para download do PostgreSQL]([PostgreSQL: Downloads](https://www.postgresql.org/download/))

Nesse link, você vai encontrar as versões de download do PostgreSQL para todos os Sistemas Operacionais: Windows, Linux e macOS.

:arrow_right: [Link para download do pgAdmin]([Download (pgadmin.org)](https://www.pgadmin.org/download/))

Nesse link, você vai encontrar as versões de download do pgAdmin para todos os Sistemas Operacionais. Windows, Linux e macOS. O pgAdmin é um software que fornece uma interface gráfica para manipulação de banco de dados e tabelas no PostgreSQL.

:arrow_right: [Link para download do OpenJDK]([Archived OpenJDK GA Releases (java.net)](https://jdk.java.net/archive/))

Nesse link, você vai encontrar todas as versões do OpenJDK disponíveis para download. Escolha a que preferir, mas, aqui, utilizamos o OpenJDK 17. Para lhe auxiliar, vou deixar aqui alguns tutoriais de instalação do OpenJDK. Esses tutoriais são referentes ao OpenJDK 17, mas é similar para outras versões.

[Nesse link]([How To Install OpenJDK 17 On Windows | Tutorials24x7](https://java.tutorials24x7.com/blog/how-to-install-openjdk-17-on-windows)), você encontra um tutorial para instalação do OpenJDK no Windows.

[Nesse link]([How To Install OpenJDK 17 On Ubuntu 20.04 LTS | Tutorials24x7](https://java.tutorials24x7.com/blog/how-to-install-openjdk-17-on-ubuntu-20-04-lts)), você encontra um tutorial para instalação do OpenJDK no Ubuntu.

[Nesse link]([How To Install OpenJDK 17 On Mac | Tutorials24x7](https://java.tutorials24x7.com/blog/how-to-install-openjdk-17-on-mac)), você encontra um tutorial para instalação do OpenJDK no macOS.

Agora estamos preparados para iniciar nossa configuração para executar a aplicação! :happy:

### Execução da aplicação em servidor local

:elephant: Antes de qualquer ação, um banco de dados com o nome *gerenciador-estacionamento* deve ser criado no PostgreSQL. Você precisa ter, também, um usuário, com nome e senha, e modificar essas informações no arquivo `application.properties` nas seguintes linhas:

```properties
spring.datasource.username=nome_de_usuario
spring.datasource.password=senha
```

:coffee: Como dito anteriormente, o Java utilizado para executar a aplicação é o OpenJDK 17. Se você possuir outra versão no seu computador, vai precisar modificar a versão do Java no arquivo `pom.xml`. Altere este bloco para a sua versão correspondente:

```html
<properties>
   <java.version>17</java.version>
</properties>
```

:wrench: Para executar a aplicação no IntelliJ, procure pelo arquivo `GerenciadorEstacionamentoApplication.java` e, dentro dele, clique no triângulo verde localizado na linha de início da classe. Para visualizar o Swagger, o endereço http://localhost:8080/swagger-ui/#/ deve ser acessado em qualquer navegador enquanto a aplicação estiver em execução na IDE.
