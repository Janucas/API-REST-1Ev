# Gestión de Proyectos - Proyecto final 1er trimestre DAW (API REST)
**Ciclo Formativo: Desarrollo de Aplicaciones Web (DAW)**  
**Alumno: Juan Antonio Núñez Castaño**

---

## Índice
1. [Introducción](#introducción)
2. [Funcionalidades del proyecto y tecnologías usadas](#funcionalidades-del-proyecto-y-tecnologías-usadas)
3. [Guía de instalación](#guía-de-instalación)
4. [Guía de uso](#guía-de-uso)
5. [Conclusión](#conclusión)
6. [Contribuciones, agradecimientos, referencias](#contribuciones-agradecimientos-referencias)
7. [Licencias](#licencias)
8. [Contacto](#contacto)

---

## Introducción
La **API REST de Gestión de Proyectos** es una herramienta diseñada para administrar y gestionar información sobre proyectos, desarrolladores y tecnologías utilizadas en cada proyecto.  
Esta API ha sido desarrollada siguiendo las mejores prácticas en diseño de software, empleando el modelo Modelo-Vista-Controlador (MVC) o MSV como lo hemos trabajado nosotros ya que la "S" serian los services y asegurando la correcta separación de responsabilidades.  

### Justificación
El desarrollo de esta API responde a la necesidad de una solución eficiente para gestionar proyectos de software, permitiendo organizar y consultar información de manera estructurada y escalable.

### Objetivos
- Facilitar la gestión de proyectos, tecnologías y desarrolladores mediante peticiones HTTP.
- Implementar una estructura de proyecto robusta y escalable.
- Cumplir con los requerimientos del ciclo formativo DAW.

### Motivación
Este proyecto representa un desafío práctico que consolida el conocimiento adquirido en el desarrollo de las clases de todo el primer trimestre y siendo fundamental tambien para aprobar el trimestre asi como la practica con algunas de las tecnologias actuales en un proytecto.

---

## Funcionalidades del proyecto y tecnologías usadas

### Funcionalidades
1. **Gestión de Proyectos**  
   - Consultar proyectos (paginados o por palabra clave).
   - Crear, editar y eliminar proyectos.

2. **Gestión de Desarrolladores**  
   - Insertar y eliminar desarrolladores.

3. **Gestión de Tecnologías**  
   - Insertar y eliminar tecnologías.

### Tecnologías utilizadas
- **Java**: Lenguaje principal del desarrollo.
- **Spring Boot**: Framework para la creación de aplicaciones backend.
- **Maven**: Gestión de dependencias.
- **MySql**: Base de datos con la que hemos trabajado.
- **GIT**: Sistema de control de versiones.
- **ThunderClient**: Herramienta para probar endpoints.

---

## Guía de instalación

### Requisitos previos
- Tener instalado **Java 17** o superior.
- Tener instalado **Maven**.
- Un editor de código, en este caso **VS Code**.

### Pasos de instalación
1. Descargo la base de datos que nos proporciona el profesor como base del proyecto
2. La creo en **Workbench** y ejecuto el diagrama eer.
3. Configuro el nuevo proyecto en **VS Code**, en mi caso lo configuro desde el propio editor
   Create java project --> Spring Boot --> Maven Project --> 3.4.0 
   --> Java -->com.vedrina --> demo--> Jar--> 17 --> Selcciono las dependencias a usar -->
   Ruta donde crear el proyecto --------------> Abro el proyecto en vs code
4. Miro dependencias esten correctas
5. Comprueblo configuracion y conexion a BD
6. Inicializo e instalo Maven
7. Comienzo a trabajar a partir de mi modelo vista controlador.

8. Instao dependencia para lanzar swagger
9. Guardo enlace swagger
10. Comando por si acaso mvn dependency:tree

---

## Guía de uso

### Endpoints disponibles
- **Proyectos**
  - `GET /api/v1/projects/show` - Obtener todos los proyectos (paginados).
  - `GET /api/v1/projects/{word}` - Obtener proyectos que contengan la palabra clave.
  - `POST /api/v1/projects/insert` - Crear un nuevo proyecto.
  - `PUT /api/v1/projects/update/{id}` - Editar un proyecto existente.
  - `DELETE /api/v1/projects/{id}` - Eliminar un proyecto.

- **Desarrolladores**
  - `POST /api/v1/developers/insert` - Insertar un nuevo desarrollador.
  - `DELETE /api/v1/developers/{id}` - Eliminar un desarrollador.

- **Tecnologías**
  - `POST /api/v1/technologies/insert` - Insertar una nueva tecnología.
  - `DELETE /api/v1/technologies/{id}` - Eliminar una tecnología.

### Uso con ThunderClient
1. Importar la colección de Postman incluida en el proyecto y sus respectivas peticiones.
2. Configurar las peticiones con los métodos y rutas descritos anteriormente.
3. Enviar las solicitudes y observar las respuestas.

---

## Conclusión
El desarrollo de esta API ha permitido consolidar los conocimientos adquiridos durante el 1er trimestre, además de explorar el potencial de tecnologías como Spring Boot para crear soluciones robustas y escalables y poder practicar con ellos de 
cara a nuestra salida al mundo laboral.

---

## Contribuciones, agradecimientos, referencias
- Agradecimiento al tutor y compañeros del ciclo DAW por el apoyo y feedback durante el desarroll, en especial 
a mi compañero Miguel Ángel Reyes, el cual me ha explicado y ayudado a sacar bastantes cosas donde me he quedado pillado.
- Referencias a la [documentación oficial de Spring Boot](https://spring.io/projects/spring-boot).
- Uso de consulta basicas en google para buscar algo concreto
- Uso de videos de yt
- Uso de codigo de otros git
- Uso de apuntes habilitados en classroom
- Uso del ejemplo de alumno-asignatura proporciionado por el profesor
- Uso de chat gpt

---

## Licencias
Este proyecto está licenciado bajo la licencia MIT. Consulte el archivo `LICENSE` para más detalles.

---

## Contacto
- **Nombre:** Juan Antonio Núñez Castaño
- **Email:** juanantonio.nunez@a.vedrunasevillasj.es