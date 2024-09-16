## Fibonacci API
### Funcionalidades

* **Obtener el numero de Fibonacci:** Permite obtener el número de Fibonacci de un número dado.
  * **Obtener el numero Fibonacci mas consultado:** Permite obtener la información del numero de Fibonacci que ha sido consultado más veces.
* 
### Requisitos

* Java 17
* Maven
* Spring Boot
* PostgreSql

### Instalación y Ejecución

**Clona el repositorio:**

   ```bash
   git clone https://github.com/yaiselpm/fibonacci-api.git
   ```
1. **Navegar al directorio del proyecto:**
 
```bash
cd fibonacci-api
```

2**Construir el proyecto:**

```bash
mvn clean install
```

3**Ejecutar la aplicación:**

```bash
mvn spring-boot:run
```


### Endpoints de la API
* **GET /fibonacci/{numero}:** Obtiene el número de Fibonacci de un número dado.
  *https://fibonacci-api-mn0e.onrender.com/fibonacci/5
* **GET /fibonacci/most-consumed:** Obtiene el numero mas consultado.
* https://fibonacci-api-mn0e.onrender.com/fibonacci/most-consumed
```json
{
  "id": 5,
  "number": "5",
  "count": 1
}
```
**Explicación:**
* **`id`:** ID del numero de fibonacci.
* **`number`:** Numero de fibonacci generado.
* **`count`:** Cantidad de veces que se ha consultado el numero de fibonacci.

**Configuracion de la base datos:**
**Cambiar la configuracion en application.properties:**
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/tu-base-de-datos
spring.datasource.username=tu-usuario
spring.datasource.password=tu-contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
