## Aula 05 - Publicando nossa API no Cloud 

Colocando a aplicação na  internet 
  

### Tecnologias
- Versão do Java: openjdk "17.0.3" 2022-04-19 LTS

- Framework: Spring Boot (v3.0.0-M3)

- Banco de dados: Mongo Atlas

- Plataforma nuvem: Heroku

 - GIT: git version 2.37.1.windows.1

 - heroku CLI:   heroku/7.53.0 win32-x64 node-v12.21.0





### Passo a Passo para  fazer o deploy no Heroku

#### Preparando o projeto

- Na pasta do projeto, abra o terminal e  escreva o seguinte  comando: ./mvnw package
  Irá complilar e gerar um .jar na pasta target
 - Para testar o jar gerado, entre na pasta onde ele está e escreva o comando:  java -jar nomedoarquivo.jar
 - Verifique se a aplicação está criando o JSON: http://localhost:8080/linguagens


####  Fazendo deploy

 - Criar uma conta em um provedor
 - Criar um projeto no provedor
 
 #### No terminal da applicação faça:
 - Faça o login no Heroku: heroku login
 - Na pasta do projeto crie um repositório git, caso não exista com o comando git init
 - heroku git:remote -a nomeDoProjeto
 - git add .
 - git commit -m "Deploy projeto"
 - git push heroku main
 
 
 ### Caso aconteça algum erro 

-  Verifique onde ocorreu e, se for uma configuração faça:
-  Crie um arquivo com nome: system.properties
-  Caso seja a versão do Java, escreva no arquivo:java.runtime.version=17(sua versão da JDK)
 


### Resultado: [API](https://lista-linguagens.herokuapp.com/linguagens)



 






 
 
