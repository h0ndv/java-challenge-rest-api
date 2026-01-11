# JavaChallenge - API REST

API REST desarrollada con Spring Boot https://trello.com/b/9DeAlIsq/foro-hub-challenge-back-end

## 🛠️ Librerias

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway**
- **Lombok**
- **Maven**

## 📁 Estructura del Proyecto

```
JavaChallenge/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/javachallenge/JavaChallenge/
│   │   │       ├── JavaChallengeApplication.java
│   │   │       ├── config/
│   │   │       │   ├── JWTFilter.java
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── controller/
│   │   │       │   ├── LoginController.java
│   │   │       │   ├── TopicoController.java
│   │   │       │   └── UsuarioController.java
│   │   │       ├── dto/
│   │   │       │   ├── LoginDTO.java
│   │   │       │   ├── TopicoDTO.java
│   │   │       │   └── UsuarioDTO.java
│   │   │       ├── models/
│   │   │       │   ├── Curso.java
│   │   │       │   ├── Topico.java
│   │   │       │   └── Usuario.java
│   │   │       ├── repository/
│   │   │       │   ├── TopicoRepository.java
│   │   │       │   └── UsuarioRepository.java
│   │   │       └── service/
│   │   │           └── TokenService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/
│   └── test/
│       └── java/
└── pom.xml
```

## 🚀 Cómo Iniciar la Aplicación

### Requisitos Previos

- Java 21 o superior
- Maven 3.6 o superior
- PostgreSQL

### Configuración de Base de Datos

1. Crear la base de datos en PostgreSQL:
```sql
CREATE DATABASE JavaChallengeForo;
```

2. Configurar la base de datos en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/JavaChallengeForo
spring.datasource.username=postgres
spring.datasource.password=root
```

### Ejecutar la Aplicación

1. Clonar el repositorio:
```bash
git clone <url-del-repositorio>
cd JavaChallenge
```

2. Instalar dependencias y compilar:
```bash
mvn clean install
```

3. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📡 Endpoints de la API

### Autenticación

#### Login
```http
POST /login
Content-Type: application/json

{
  "username": "Seba",
  "password": "password123"
}
```

**Respuesta exitosa:**
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### Tópicos

#### Crear un tópico
```http
POST /topicos
Content-Type: application/json

{
  "titulo": "Spring Boot Java Challenge",
  "mensaje": "Final",
  "autor": "Seba",
  "curso": "Spring Boot"
}
```

#### Obtener todos los tópicos
```http
GET /topicos
```

#### Obtener un tópico por ID
```http
GET /topicos/{id}
```

#### Actualizar un tópico
```http
PUT /topicos/{id}
Content-Type: application/json

{
  "titulo": "Spring Boot Java Challenge",
  "mensaje": "Final",
  "autor": "Seba",
  "curso": "Spring Boot"
}
```

#### Eliminar un tópico
```http
DELETE /topicos/{id}
```

### Usuarios

#### Crear un usuario
```http
POST /usuarios
Content-Type: application/json

{
  "nombre": "Sil",
  "email": "correo@gmail.com",
  "password": "password123"
}
```

#### Obtener todos los usuarios
```http
GET /usuarios
```

#### Obtener un usuario por ID
```http
GET /usuarios/{id}
```

#### Actualizar un usuario
```http
PUT /usuarios/{id}
Content-Type: application/json

{
  "nombre": "Nombre actualizado",
  "email": "correo@example.com",
  "password": "newpassword123"
}
```

#### Eliminar un usuario
```http
DELETE /usuarios/{id}
```
