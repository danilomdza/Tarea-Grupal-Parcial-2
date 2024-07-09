# Tarea-Grupal-Parcial-2

## Descripción del Código

Este proyecto es una aplicación de escritorio desarrollada en Java que se conecta a una base de datos PostgreSQL para mostrar información relacionada con la Fórmula 1. Utiliza `JDBC` para la conexión a la base de datos y `Swing` para la interfaz gráfica de usuario. La aplicación permite al usuario seleccionar entre tres opciones: Constructores, Conductores y una combinación de ambos, y muestra la información correspondiente en una tabla.

## Qué Hace el Código

1. **Conexión a la Base de Datos:**
   - Establece una conexión con una base de datos PostgreSQL utilizando `JDBC`.

2. **Interfaz Gráfica:**
   - Muestra una ventana con un `JComboBox` para seleccionar entre las opciones: Constructores, Conductores y Conductores y Constructores.
   - Muestra una `JTable` que se actualiza dinámicamente en función de la selección realizada en el `JComboBox`.

3. **Consulta y Visualización de Datos:**
   - Realiza consultas SQL para obtener datos de las tablas `constructors` y `drivers` de la base de datos.
   - Muestra los resultados de estas consultas en una tabla con columnas tales como "Constructor ID", "Name", "Driver ID", "Forename", "Surname", entre otras.

4. **Estilizado:**
   - El `JComboBox` y la `JTable` están estilizados para tener bordes redondeados, dando una apariencia moderna.

## Cómo Ejecutar el Código

1. **Configuración de la Base de Datos:**
   - Asegúrese de tener una base de datos PostgreSQL en funcionamiento con las tablas `constructors` y `drivers`.
   - Modifique las credenciales de conexión en el código (`dbUrl`, `dbUser`, `dbPassword`) según su configuración de base de datos.

2. **Compilación y Ejecución:**
   - Compile el código Java:
     ```bash
     javac Interface/MainM.java
    
## Requisitos del Sistema

- JDK 8 o superior
- PostgreSQL 9.6 o superior
- Librerías JDBC para PostgreSQL

## Datos Extra

- La interfaz gráfica está centrada y tiene un tamaño fijo de 900x700 píxeles.
- El `JComboBox` permite seleccionar entre Constructores, Conductores y una combinación de ambos para mostrar datos específicos de la tabla seleccionada.
- La `JTable` está diseñada para tener un tamaño de fila de 25 píxeles, con celdas centradas.

## Notas

- La consulta SQL para la opción "Conductores y Constructores" asume que existe una relación entre las tablas `drivers` y `constructors` basada en el `driver_id`. Asegúrese de ajustar la consulta según su esquema de base de datos real.
- Este proyecto está diseñado como un ejemplo educativo para demostrar cómo integrar JDBC y Swing en una aplicación Java.

##Evidencia

- Entorno
![image](https://github.com/danilomdza/Tarea-Grupal-Parcial-2/assets/162849123/d88075bf-85df-4156-981c-7426a908022d)

- Salida
![image](https://github.com/danilomdza/Tarea-Grupal-Parcial-2/assets/162849123/1f1f8ce3-abea-49d2-849b-6f4f543a7ada)
![image](https://github.com/danilomdza/Tarea-Grupal-Parcial-2/assets/162849123/bdc1afff-bfdd-4dbd-9f9e-e6fa463b1736)
![image](https://github.com/danilomdza/Tarea-Grupal-Parcial-2/assets/162849123/7875ee9c-01b3-4f92-a074-516bc34dfdcf)
![image](https://github.com/danilomdza/Tarea-Grupal-Parcial-2/assets/162849123/02775378-5d06-4215-8886-b9927b9ddad8)
![image](https://github.com/danilomdza/Tarea-Grupal-Parcial-2/assets/162849123/5603010b-58d3-45e0-be6d-41256422983c)

- Gestor base de datos
![image](https://github.com/danilomdza/Tarea-Grupal-Parcial-2/assets/162849123/3324c3a9-f776-475d-b5b9-4022986365e6)
![image](https://github.com/danilomdza/Tarea-Grupal-Parcial-2/assets/162849123/55ccdd37-5c9f-41b5-84e9-dbcd28d27685)





