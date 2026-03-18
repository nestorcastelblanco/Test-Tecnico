# Test Técnico - Gestión de Autos

## Contexto del Proyecto

Aplicación web para que los usuarios registren y gestionen información de sus autos.  
Cada usuario puede tener uno o varios autos registrados.

El sistema incluye:

- autenticación
- gestión de usuarios
- gestión de autos
- buenas prácticas de desarrollo

---

## Requisitos Funcionales

### Autenticación

- Registro de usuario en base de datos
- Login con JWT
- Acceso a funciones solo con token válido

### Gestión de Autos

CRUD de autos para el usuario autenticado:

- Crear auto con:
  - marca
  - modelo
  - año
  - número de placa
  - color
- Listar autos
- Editar datos de un auto
- Eliminar auto

---

## Requisitos Técnicos

### Backend

- Spring Boot
- API REST con endpoints organizados
- Spring Security con JWT
- JPA / Hibernate
- SQL Server

### Frontend

- React
- Pantalla de inicio de sesión
- Pantalla de autos: listar, agregar, editar, eliminar
- Almacenamiento del token JWT

### Base de Datos

- Tabla `users`
- Tabla `cars`
- Relación por llave foránea `user_id`

---

## Funcionalidades adicionales implementadas

- Búsqueda por placa o modelo
- Filtrado por año o marca
- Subida de foto del auto como campo simulado
- Diseño responsive en frontend
- Mensajes de error y éxito en frontend y backend

---

## Tecnologías utilizadas

### Frontend
- React
- Vite
- React Router DOM
- Axios
- CSS

### Backend
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- SQL Server

---

Script de creación de tablas

Tabla users
CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT GETDATE()
);

Tabla cars
CREATE TABLE cars (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    placa VARCHAR(20) NOT NULL UNIQUE,
    color VARCHAR(30),
    foto VARCHAR(MAX),
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_cars_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

## Estructura general del proyecto

```text
frontend-autos/
 ├── src/
 │   ├── pages/
 │   │   ├── Login.jsx
 │   │   ├── Register.jsx
 │   │   └── Cars.jsx
 │   ├── services/
 │   │   └── api.js
 │   ├── App.jsx
 │   ├── main.jsx
 │   └── index.css
