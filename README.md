API REST desarrollada en Java con Spring Boot para la gestión de productos.
El proyecto fue creado de forma individual con un enfoque en buenas prácticas backend y estructura utilizada en entornos empresariales.


Tecnologías
* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Jakarta Validation
* Lombok
* Maven

Funcionalidades

* Alta de productos
* Consulta de productos (filtro opcional por nombre)
* Actualización parcial (PATCH)
* Eliminación de productos
* Validaciones de entrada
* Reglas de negocio para control de stock

POST    /products
GET     /products
GET     /products?name={name}
PATCH   /products/{id}
DELETE  /products/{id}
