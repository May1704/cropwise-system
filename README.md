# 🌱 Cropwise System

Sistema de monitoreo y gestión de cultivos hidropónicos (**simulado**) desarrollado con **Java 21 + Spring Boot**, que implementa autenticación con **JWT**, documentación con **Swagger**, pruebas con **Postman** y arquitectura **MVC**.  
El sistema permite a los usuarios registrar cultivos, gestionarlos y acceder a datos simulados de sensores.

---

## 🚀 Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3**
- **MySQL**
- **JPA/Hibernate**
- **JWT (JSON Web Token)** para autenticación y autorización
- **Swagger UI** para documentación de la API
- **Postman** para pruebas
- **Docker** (puerto expuesto: **8081**)
- Arquitectura **MVC**

---

## 📂 Estructura del repositorio

Repositorio disponible en: [Cropwise System](https://github.com/May1704/cropwise-system.git)

```
cropwise-system/
├── src/
│   ├── main/
│   │   ├── java/com/cropwise
│   │   │   ├── controllers/        # Controladores REST
│   │   │   ├── dto/               # Request y Response DTOs
│   │   │   ├── entities/          # Entidades JPA
│   │   │   ├── mapper/            # Mappers
│   │   │   ├── repositories/      # Repositorios JPA
│   │   │   └── services/          # Servicios con lógica de negocio
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql           # Datos iniciales (usuarios, roles, etc.)
├── Dockerfile
└── docker-compose.yml
```

---

## 🔑 Credenciales de acceso

Para probar los endpoints en **Swagger** o **Postman**, primero debes hacer **login** con alguno de estos usuarios:

| Rol   | Usuario   | Contraseña |
|-------|-----------|------------|
| Admin | `admin`   | `admin123` |
| User1 | `user1`   | `user123`  |
| User2 | `user2`   | `user123`  |

📌 **Endpoint de login:**

```
POST http://localhost:8080/api/auth/login
```

Ejemplo de body para login en **Postman** o Swagger:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

La respuesta devolverá un **JWT token**, que luego debes usar en los endpoints protegidos agregando en los headers:

```
Authorization: Bearer <TOKEN>
```

---

## 📖 Documentación de la API

La API está documentada con **Swagger** y disponible en la siguiente URL:

👉 **http://localhost:8080/swagger-ui/index.html#/**

---

## 🐳 Docker

Para ejecutar el proyecto en un contenedor:

```bash
docker build -t cropwise:v2 .
docker run -p 8081:8080 --name cropwise-springboot cropwise:v2
```

El sistema quedará disponible en:

```
http://localhost:8081
```

---

## 📌 Funcionalidades principales

- **Gestión de usuarios** con roles (Admin, User).
- **Autenticación JWT** para proteger endpoints.
- **Gestión de cultivos** (crear, listar, actualizar, eliminar).
- **Asignación de cultivos a usuarios**.
- **Simulación de sensores** con valores ideales por tipo de cultivo.
- **Documentación y pruebas** con Swagger y Postman.

---

## 🧪 Pruebas con Postman

1. Login con uno de los usuarios.
2. Copiar el token JWT de la respuesta.
3. Usarlo en los headers (`Authorization: Bearer <TOKEN>`).
4. Probar los endpoints de **Crop** (`/api/crops`) y **User** (`/api/users`).

---



---

## 👩‍💻 Autor

Proyecto desarrollado por **Mayleris Vergara**

📌 **Proyecto académico - Nivel Junior - 2025**