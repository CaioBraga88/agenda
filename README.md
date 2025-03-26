# 📒 Agenda

Agenda é uma aplicação fullstack desenvolvida com **Java + Spring Boot** no backend e **HTML + CSS + Javascript** no frontend, com objetivo de gerenciar clientes e seus respectivos contatos. O sistema permite o cadastro, edição, listagem e remoção de clientes, assim como a associação de múltiplos contatos por cliente.

---

## 🚀 Tecnologias utilizadas

### Backend
- Java 17
- Spring Boot 3.4.4
- Spring Web
- Spring Data JPA
- Spring Boot Validation
- PostgreSQL
- Maven

### Frontend
- HTML5
- CSS3
- Javascript

---

## 📁 Estrutura do projeto
Agenda/ ├── frontend/ │ ├── index.html │ └── contatos.html ├── postman/ │ └── java.postman_collection.json ├── src/ │ └── main/ │ ├── java/com/example/demo/ │ │ ├── controller/ # Controllers REST para Cliente e Contato │ │ ├── exception/ # Manipulação global de exceções │ │ ├── model/ # Entidades JPA: Cliente, Contato │ │ └── repository/ # Interfaces do JPA │ ├── resources/ │ │ ├── static/ # Recursos estáticos │ │ ├── templates/ # (Não utilizado) │ │ └── application.properties │ └── test/ │ └── java/com/example/demo/ │ └── DemoApplicationTests.java


---

## ⚙️ Configurações

No arquivo `application.properties`, configure sua conexão com o PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/agenda
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

📦 Dependências (pom.xml)
xml
Copy
Edit
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

▶️ Como executar o projeto
Backend
# Clone o repositório
git clone https://github.com/seu-usuario/agenda.git

# Acesse o diretório do projeto
cd agenda

# Compile e execute o projeto com Maven
./mvnw spring-boot:run

Certifique-se de que o banco PostgreSQL esteja rodando localmente com o nome agenda.

Frontend
Abra o arquivo frontend/index.html diretamente no navegador para usar a interface.

📫 Contato
Desenvolvido por Caio Braga – GitHub
