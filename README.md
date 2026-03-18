Test Técnico - Gestión de Autos (Frontend)

Aplicación web desarrollada en React + Vite para la gestión de vehículos asociados a usuarios.
Permite registrar usuarios, iniciar sesión y administrar autos mediante operaciones CRUD.

La aplicación se conecta a un backend desarrollado con Spring Boot, el cual maneja autenticación y persistencia de datos.

Tecnologías utilizadas

Frontend:

React

Vite

React Router

Axios

CSS

Backend (consumido por la aplicación):

Spring Boot

Spring Security

JWT

JPA / Hibernate

SQL Server

Funcionalidades

Autenticación:

Registro de usuarios

Inicio de sesión

Manejo de token JWT

Cierre de sesión

Gestión de autos:

Crear vehículo

Editar vehículo

Eliminar vehículo

Listar vehículos

Búsqueda y filtros:

Búsqueda por placa o modelo

Filtro por marca

Filtro por año

Extras:

Vista previa de imagen del vehículo (simulada en frontend)

Interfaz responsive

Mensajes de error y éxito

Estructura del proyecto
src
 ├─ pages
 │   ├─ Login.jsx
 │   ├─ Register.jsx
 │   └─ Cars.jsx
 │
 ├─ services
 │   └─ api.js
 │
 ├─ components
 │   └─ navbar.jsx
 │
 ├─ App.jsx
 ├─ main.jsx
 └─ index.css
Instalación del proyecto

Clonar el repositorio

git clone https://github.com/nestorcastelblanco/Test-Tecnico-Front.git

Entrar al proyecto

cd Test-Tecnico-Front/frontend-autos

Instalar dependencias

npm install

Ejecutar la aplicación

npm run dev

La aplicación se ejecutará en:

http://localhost:5173
Conexión con el backend

El frontend consume la API desde:

http://localhost:8080

Endpoints utilizados:

POST /auth/register
POST /auth/login

GET /cars
POST /cars
PUT /cars/{id}
DELETE /cars/{id}
Script de base de datos
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
    placa VARCHAR(20) NOT NULL,
    color VARCHAR(30),
    foto VARCHAR(255),

    user_id BIGINT NOT NULL,

    CONSTRAINT fk_cars_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

Relación:

1 usuario → muchos autos
Flujo de la aplicación

El usuario se registra

Inicia sesión

Recibe un token JWT

Accede al panel de autos

Puede crear, editar, eliminar y filtrar vehículos

Mejoras futuras

Subida real de imágenes

Validaciones avanzadas en formularios

Paginación de resultados

Dashboard con estadísticas

Modo oscuro
