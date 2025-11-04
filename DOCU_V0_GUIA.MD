# 🏠 Proyecto: Sistema de Alquiler de Habitaciones

Aplicación full-stack para la gestión de alquiler de habitaciones, con soporte para roles de **alumno**, **propietario** y **administrador**. Incluye backend en Spring Boot y cliente Android en Jetpack Compose.

---

## 📦 Repositorios

| Componente       | Descripción                      | Enlace                                                                 |
|------------------|----------------------------------|------------------------------------------------------------------------|
| API Backend      | Spring Boot + JWT + PostgreSQL  | [demo](https://github.com/frantoribio/demo)                           |
| App Android      | Jetpack Compose + Retrofit      | [alquilerapp-compose](https://github.com/frantoribio/alquilerapp-compose) |

---

## 🧰 Tecnologías Utilizadas

- **Backend**: Spring Boot, Spring Security, JWT, Swagger  
- **Base de datos**: PostgreSQL  
- **Infraestructura**: Docker, Docker Compose, NGINX, Certbot  
- **Frontend Android**: Kotlin, Jetpack Compose, Retrofit  
- **Autenticación**: JWT con roles (`ALUMNO`, `PROPIETARIO`, `ADMIN`)  
- **Persistencia móvil**: DataStore Preferences  

---

## 🚀 Guía de Instalación

### 1. Backend Spring Boot

#### Requisitos

- Java 17+
- Maven
- Docker + Docker Compose
- PostgreSQL

#### Pasos

```bash
# Clona el repositorio
git clone https://github.com/frantoribio/demo
cd demo
```
### 🛠️ Construcción y arranque del proyecto

Para iniciar el backend, abre el proyecto `demo` en **Visual Studio Code** y asegúrate de tener la extensión de **Docker** instalada y activa.

1. Abre el archivo `compose.yaml` ubicado en la raíz del proyecto.
2. Desde la pestaña **Docker** en VS Code, selecciona el servicio correspondiente y haz clic en **"Compose Up"**.
3. Espera a que los contenedores se levanten correctamente.

Esto iniciará:

- La API en `http://localhost:8080`
- PostgreSQL con datos persistentes
- NGINX con HTTPS si configuras Certbot

> 💡 Nota: No es necesario usar el comando `docker-compose up --build` manualmente, ya que Visual Studio Code gestiona el proceso con el archivo `compose.yaml`.
También se pueden omitir estos pasos ejecutando el proyecto desde el archivo `DemoApplication.java`.
## 🚀 Esto iniciará

- API en `http://localhost:8080`
- PostgreSQL con datos persistentes
- NGINX con HTTPS si configuras Certbot



## 📱 App Android

### Requisitos

- Android Studio
- Emulador Android configurado

### Pasos

```bash
# Clona el repositorio
git clone https://github.com/frantoribio/alquilerapp-compose
```
## 📱 App Android

### Pasos

- Abre el proyecto en Android Studio  
- Asegúrate de que el backend esté corriendo en tu máquina local (`http://localhost:8080`)  
- Ejecuta la app en el emulador Android *(usa `10.0.2.2` para acceder a localhost desde el emulador)*

---

## 📱 Estructura de la App Android

- `MainActivity.kt`: punto de entrada  
- `ApiService.kt`: definición de endpoints  
- `RetrofitClient.kt`: configuración de red  
- `model/*.kt`: modelos de datos (Usuario, Reserva, etc.)  
- `repository/*.kt`: lógica de acceso a datos  
- `viewmodel/*.kt`: lógica de presentación  
- `ui/screens/*.kt`: pantallas de la app  

---

## 🔐 Seguridad

- Autenticación con JWT  
- Roles con acceso diferenciado  
- Protección de endpoints sensibles  
- HTTPS con certificados Let’s Encrypt  

---

## 📄 Descripción Funcional

### Funcionalidades principales

- Registro y login de usuarios  
- Visualización de habitaciones disponibles  
- Gestión de reservas  
- Panel de administración para usuarios y habitaciones  

---

## 🧠 Conclusiones

Este proyecto ha permitido aplicar conocimientos de desarrollo backend y frontend, así como prácticas de seguridad y despliegue. La integración entre tecnologías modernas como Spring Boot y Jetpack Compose ha resultado en una solución robusta y escalable. Además, el uso de Docker y NGINX ha facilitado el despliegue seguro del sistema.

## 📚 Bibliografía

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)  
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)  
- [PostgreSQL Manual](https://www.postgresql.org/docs/)  
- [Swagger OpenAPI Docs](https://swagger.io/docs/)  
- [Docker Documentation](https://docs.docker.com/)  
- [NGINX Docs](https://nginx.org/en/docs/)  
- [Certbot Documentation](https://certbot.eff.org/docs/)  
- [JWT Introduction](https://jwt.io/introduction)  
- [Android DataStore Preferences](https://developer.android.com/topic/libraries/architecture/datastore)