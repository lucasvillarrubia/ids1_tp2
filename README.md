## ⚽ Gestión y Reserva de Fútbol 5

Este repositorio contiene el código fuente desarrollado como parte del Trabajo Práctico de Ingeniería de Software. La plataforma permite la gestión de usuarios, equipos, canchas y reservas para partidos de fútbol 5.

---

## 🚀 ¿Cómo ejecutarlo?

1. **En una terminal, ubicarse en el directorio raíz del TP**

    ```bash
    cd <carpeta-del-proyecto-o-repositorio>

2. **Levantar los servicios con Docker:**

   ```bash
   docker-compose up

3. Iniciar la aplicación de Backend desde IntelliJ: **Ejecutar FutbolApplication**

4. **Ingresar a la [aplicación web](http://localhost:30003/) desde un navegador.**

5. **Puertos habilitados:**
    -  Documentación de API en [Swagger UI](http://localhost:30002/swagger-ui/index.html#/)
        - Primero se debe crear un usuario. Luego, para poder consumir los demás endpoints, es necesario cargar el accessToken en el botón Authorize.

    -  [Base de datos (PostgreSQL)](http://localhost:30004/api/v1/users/?pgsql=db)
        - Acceso rápido : 
                - Usuario: pguser
                - Contraseña: 123456

    -   [Mailpit](http://localhost:8026/#) (Server de correo electrónico)
        - Utilícese para verificar cuentas de usuario.


6. Ahora si! Ya podés disfrutar de nuestra plataforma.

## 🛠️ Tecnologías utilizadas: 

- Backend: Spring Boot
- Frontend: React + Vite
- Base de datos: PostgreSQL
- Comunicación: REST API
- Contenedores: Docker, Docker Compose
- Control de versiones: GitLab/GitHub
- Gestión de tareas: Jira
- Autenticación: JWT



## Git hooks

El repositorio está configurado para ejecutar los tests antes de realizar
un commit, si se ejecuta este comando:

```bash
git config --local --add core.hookspath git-hooks
```
