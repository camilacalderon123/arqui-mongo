<h1 align="center"> 👨‍🎓🍃 MONGO DB STUDENT  🍃👨‍🎓 </h1>

<p align="center">
<img src="https://github.com/camilacalderon123/arqui-mongo/assets/80492180/dffbe5a8-e719-4f7f-92a7-de52d71620aa" height="500"  />
</p>

<!-- VERSION (estática)-->
![VERSION-1.0](https://img.shields.io/badge/VERSION-1.0.0-blueviolet)

## Tabla de Contenidos

1. [Descripción General]
2. [Replicación en Local]
3. [Requerimientos]
4. [Arquitectura]
5. [Acerca de nosotros]

## Descripción del Proyecto

El proyecto consiste en desarrollar una aplicación web basada en MongoDB que ofrecerá servicios a estudiantes, brindando funcionalidades similares a las conocidas herramientas de gestión de bases de datos PhpMyAdmin y PgAdmin. Para garantizar la portabilidad y la eficiencia en el manejo de datos, se utilizarán contenedores Docker para alojar MongoDB, con la configuración adecuada para el uso de volúmenes que permitirán la persistencia de los datos. Además, se implementará otro contenedor Docker que utilizará el contenedor de MongoDB para actuar como el servidor principal de la aplicación.

## ¿ Cómo clonar el proyecto? 

# Requerimientos del Proyecto

## Requerimientos Funcionales

- Registro y login de usuarios: Los usuarios deben poder registrarse en la aplicación y acceder a sus cuentas mediante un proceso de login seguro.
- Creación de bases de datos: Los usuarios deben tener la capacidad de crear nuevas bases de datos en MongoDB a través de la aplicación.
- Comunicación entre dockers: La aplicación debe permitir la comunicación entre dos dockers, uno que actúa como administrador de la base de datos y otro que contiene el grupo de colecciones de MongoDB.
- Envío de comandos a la consola de comandos de MongoDB: Los usuarios deben poder enviar comandos a ejecutar en la consola de comandos de MongoDB a través de la aplicación. Esto les permitirá realizar operaciones como consultas, actualizaciones y eliminaciones en la base de datos.

## Requerimientos No Funcionales

- Usabilidad: La interfaz de usuario de la aplicación debe ser intuitiva y fácil de usar. Debe proporcionar una experiencia de usuario agradable y eficiente para realizar las operaciones de gestión de bases de datos.
- Experiencia de usuario fluida: La aplicación debe ofrecer una experiencia de usuario fluida y sin interrupciones. Los tiempos de carga deben ser rápidos y las transiciones entre páginas o secciones deben ser suaves y sin retrasos perceptibles.

## Justificación de la elección de la Arquitectura 

La elección de implementar una arquitectura cliente-servidor, donde una aplicación de Spring Boot actúa como el cliente y un contenedor de Docker con MongoDB actúa como el servidor, se respaldada en varias ventajas significativas que impactarán positivamente en el rendimiento, la escalabilidad y la eficiencia del sistema.

- Primero, se tiene la separación de responsabilidades entre el cliente y el servidor, al asignar la lógica de presentación y la interacción con el usuario a la aplicación de Spring Boot, y la gestión de datos y operaciones de base de datos al contenedor de MongoDB, se obtiene un diseño modular y bien estructurado. 

- La arquitectura cliente-servidor también nos brinda la posibilidad de escalar y mejorar el rendimiento del sistema. Teniendo en cuenta que los usuarios principales son los estudiantes que requieran una base de datos con MongoDB al utilizar contenedores de Docker, se puede escalar horizontalmente el servidor (contenedor de MongoDB) según la demanda.

- Otra ventaja es la facilidad de mantenimiento y actualización. Al separar claramente el cliente y el servidor, se pueden realizar cambios y mejoras en la aplicación de Spring Boot sin afectar la infraestructura del servidor. 

# Arquitectura

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**
