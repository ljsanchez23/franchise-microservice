# **Nequi Backend Challenge**

Este es un Microservicio desarrollado como parte del Nequi Backend Challenge.  
Implementado en Java y Spring Boot, empaquetado con Docker para facilitar su despliegue en entornos locales o en la nube.

---

## **Cómo Desplegar la Aplicación en Local**

Sigue estos pasos para ejecutar y probar la aplicación en tu entorno local.

---

### **Requisitos Previos**
Antes de comenzar, asegúrate de tener los siguientes componentes instalados y configurados:
- **Java:** JDK 17 o superior.
- **Docker:** Última versión instalada.
- **Gradle:** Para compilar el proyecto.
- **Base de Datos:** RDS.

---

### **Pasos para Desplegar Localmente**

1. **Clonar el Repositorio**
   Descarga el proyecto desde el repositorio GitHub:
   ```bash
   git clone https://github.com/ljsanchez23/franchise-microservice.git
   
2. **Construir la imagen de Docker**
   Usa el Dockerfile incluido en el proyecto para crear la imagen:
    ```bash
   docker build -t nequi-backend-challenge:latest .
   
3. **Ejecuta el contenedor de Docker**
   Una vez que el contenedor esté en ejecución, puedes probar los endpoints accediendo a:
    ```bash
   docker run -p 8080:8080 nequi-backend-challenge:latest
   
4. **Probar los endpoints**
    ```bash
   http://localhost:8080

¡Así de sencillo! La aplicación estará lista para usarse en tu entorno local. 🚀

### **Endpoints**
1. **FranchiseController** (/franchise)
   - /create
   - /update-name
   - /highest-stock
2. **BranchController** (/branch)
   - /create
   - /update-name
   - /delete-product
3. **ProductController** (/product)
   - /create
   - /update-name
   - /update-stock

## **Base de datos**
Durante el proyecto utilizamos RDS para la persistencia de los datos y SecretsManger para acceder a las credenciales mediante
el sdk.

## **Notas del desarrollador**
Al momento de carga del repositorio hay oportunidades de mejora que se dejan por fuera de la primera carga
debido al tiempo establecido para el reto.

Se ajusta el DatabaseCredentialsService para facilitar la ejecucion de la app desde cualquier equipo en local,
esto sera actualizado en caso de completar el despliegue a ECS para que se conecte mediante el Default
credentials provider.
