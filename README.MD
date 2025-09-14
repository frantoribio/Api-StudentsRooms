# Sistema de Alquiler de Habitaciones

Aplicación full-stack para la gestión de alquiler de habitaciones, con soporte para roles de **alumno** y **propietario**, accesible desde web, móvil y escritorio.

## 🧱 Tecnologías


- **Frontend Android**: Kotlin + Retrofit
- **Frontend Escritorio**: JavaFX + HttpClient
- **Frontend Web**: React + Axios + Docker
- **Backend**: Spring Boot + Spring Security + JWT + Swagger
- **Base de datos**: PostgreSQL
- **Infraestructura**: Docker + Docker Compose + NGINX + Certbot (HTTPS)

## 🔐 Seguridad

- Autenticación con JWT
- Roles: `ALUMNO`, `PROPIETARIO`, `ADMIN`
- Swagger protegido por rol `ADMIN`
- HTTPS con certificados Let's Encrypt

## 🚀 Despliegue

```bash
docker-compose up --build
