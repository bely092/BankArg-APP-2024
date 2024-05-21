IEEE830 
Subo link para editar Requerimientos 

Link de DRIVE https://docs.google.com/document/d/11agF0OtVL-500H-idYffiZBbkwjvGbfP3UY93fiL3F0/edit

# Introducción

Este documento es una Especificación de Requisitos Software (ERS) para el Sistema de información para la gestión de procesos y control de inventarios. Esta especificación se ha estructurado basándose en las directrices dadas por el estándar IEEE Práctica Recomendada para Especificaciones de Requisitos Software ANSI/IEEE 830, 1998.

## Propósito

El presente documento tiene como propósito definir las especificaciones para el desarrollo de BankArg, una aplicación multiplataforma (Aplicación Web y Aplicación Móvil) destinada a brindar un acceso rápido, seguro y conveniente al usuario a sus cuentas bancarias así como brindarle herramientas de gestión para explotar al máximo el potencial de la misma.

## Ámbito del Sistema

El sistema multiplataforma denominado BankArg actuará como intermediario entre el usuario final y el sistema bancario. Su principal objetivo es proporcionar un entorno seguro y ágil que permita a los usuarios, la creación de una cuenta personal mediante la cual puedan gestionar sus cuentas bancarias. Dichas gestiones incluyen

En contraparte, los proveedores de los servicios bancarios obtendrán un reporte (con autorización de los usuarios de BankArg) que detalla los usuarios registrados y los servicios más utilizados, con el fin de que estos datos los ayuden a mejorar las prestaciones concedidas  a los usuarios finales.

## Usuario y definiciones

- **Usuario:** Persona que utilizará el sistema para acceder a su cuenta bancaria..
- **ERS:** Especificación de Requisitos de Software.
- **RF:** Requerimiento Funcional.
- **RNF:** Requerimiento No Funcional.

## Personal Involucrado

| Nombre              | Rol        | Usuario de Github | Responsabilidad | Información de contacto          |
|---------------------|------------|-------------------|-----------------|---------------------------------|
| Ezequiel Giampaoli  | Developer  | egiam             | Developer       | ezegiampaoli@gmail.com          |
| Maria Laura Peralta | Developer  | PeraltaMariaL-ISPC| Developer       | lali.635@gmail.com              |
| Valentin ALterio    | Developer  | Valealterio       | Developer       | alteriovalentin01@gmail.com     |
| Joaquin Bonvechi    | Developer  | JoacoBonvechi     | Developer       | joacobonvechi2272002@gmail.com  |
| Rodrigo Leiva       | Developer  | rodrileiv         | Developer       | rodrigoleiva1995@hotmail.com   |
| Paula Cabrera       | Developer  | paulac23          | Developer       | paucabrera95@gmail.com         |
| Brian Galarza       | Developer  | galarzabrian      | Developer       | briangalarza71@gmail.com       |
| David Spuler        | Developer  | davidspuler       | Developer       | davidspuler68@gmail.com        |
| Roberto Carlos Osores | Developer | roberto-osores   | Developer       | osoresroberto@gmail.com        |
| Belen Riquelme      | Developer  | bely092           | Developer       | koen.lion92@gmail.com          |

## Referencias

- **Título del Documento:** Standard IEEE 830 - 1998
- **Referencia:** IEEE

# Resumen

Este documento se ha estructurado en tres partes: en primer lugar, se proporciona una introducción al proyecto que ofrece una visión general de la especificación de los recursos del sistema. En segundo lugar, se intenta establecer las funciones principales que el sistema debe llevar a cabo, junto con los datos asociados, factores, restricciones, suposiciones y dependencias que afectan al desarrollo, sin entrar en excesivos detalles. Por último, se definen detalladamente los requisitos que el sistema debe cumplir.

## Descripción general

### Perspectiva del producto

El sistema de banca online “BankArg” será un producto diseñado inicialmente para funcionar en entornos web, lo que permitirá su uso rápido y efectivo, accesible desde cualquier dispositivo. Contará con secciones distintas que permitirán al usuario acceder a todos sus datos relevantes, historial de movimientos, transferencias, pagos, turnos, préstamos y aspectos relacionados con la seguridad. Además, incluirá una guía de ayuda para que los usuarios resuelvan cualquier duda respecto al uso del sistema y las herramientas disponibles. Posteriormente se iniciará el desarrollo de una aplicación móvil que complementará a la aplicación web, permitiendo a los usuarios un acceso más ágil y cotidiano desde sus dispositivos.

### Funciones del producto

En esencia, "BankArg" proporcionará las siguientes funciones:

- Acceso a cuenta
- Transferencias de dinero
- Pagos de servicios con sus respectivos comprobantes
- Alertas de vencimientos
- Solicitud de préstamos
- Validación de token para mayor seguridad
- Avisos en caso de iniciar sesión en otro dispositivo
- Visualización de saldos
- Registro de contactos frecuentes
- Turnos online

### Características de los usuarios

#### Tipo de usuario: Administrador

- **Formación:** Developers de la aplicación.
- **Actividades:** Control y manejo del sistema en general. Accesos especiales.

#### Tipo de usuario: Usuario Registrado // Usuario Final

- **Formación:** No requiere formación formal previa.
- **Actividades:** Visualización de estados de cuentas, pagos de servicios, transferencias, notificaciones,contactos y solicitud de préstamo.

#### Tipo de usuario: Usuario No Registrado

- **Formación:** No requiere formación formal previa.
- **Actividades:** Registro, login y recuperar contraseña.

### Restricciones

- Interfaz destinada a ser utilizada en internet.
- Los servidores deben ser capaces de manejar consultas concurrentes.
- El sistema se diseñará según un modelo cliente/servidor.
- El sistema debe tener un diseño e implementación sencilla, independiente de la plataforma o del lenguaje de programación.

# Requisitos Específicos

- La aplicación estará disponible tanto en una presentación Web como en Aplicación para móviles.
- La aplicación debe contemplar el registro de los usuarios a BankArg.
- Los usuarios registrados deberán contar con un identificador único dentro de la aplicación.
- Los usuarios registrados podrán crear y publicar proyectos de estudio, con el fin de reclutar nuevos integrantes para tal empresa.

## Requisitos funcionales

1. **Crear/Registrar un nuevo Usuario:** La aplicación deberá permitir mediante un formulario la creación de una cuenta de usuario, en dicha cuenta quedará asentada la información personal del usuario: Nombre y apellido, correo electrónico y contraseña. El registro permitirá a los usuarios acceder al resto de las funciones de la aplicación.
2. **Asociacion de cuenta banc

aria a la cuenta del usuario:** Un usuario registrado podrá mediante un formulario asociar una de sus cuentas bancarias a la cuenta.
3. **Consultas de información de las cuentas bancarias asociadas:** Los usuarios registrados tendrán la posibilidad de realizar consultas de información acerca de las cuentas  bancarias asociadas a su cuenta de usuario. Por ejemplo: Saldo actual, historial de transferencias, etc.
4. **Solicitudes de movimientos bancarios:** Los usuarios registrados podrán realizar solicitudes de movimientos bancarios (transferencia, pagos, solicitar préstamos, etc) desde la aplicación.

## Requisitos no funcionales

- **Guia para nuevos usuarios:** La aplicación se puede beneficiar de una pequeña guía destinada  a  los nuevos usuarios en la que se detalle de manera sencilla las funcionalidades básicas de la misma.
- **Diseño de la app:** La app contará con un diseño cohesivo y agradable a la vista. Al realizarse movimientos de dinero en la aplicación, se priorizará la claridad visual de la interfaz.

# Product Backlog

- Como miembro del equipo de desarrollo, quiero poder conectarme al repositorio de la organización en GitHub para acceder al código fuente y colaborar en el proyecto.
- Como miembro del equipo, quiero poder agregar issues como historias de usuario para definir las tareas que se deben realizar en el proyecto.
- Como equipo de desarrollo, queremos establecer hitos o milestones para cada sprint, con fechas de inicio y finalización, para organizar el trabajo de manera eficiente.
- Como equipo de desarrollo, queremos mantener un registro completo de todas las actividades realizadas en el proyecto, incluyendo commits, cambios en la wiki y carga de issues, para tener una trazabilidad clara del progreso del proyecto.
- Como equipo de desarrollo, queremos mantener una wiki actualizada con registros de las ceremonias por sprint (daily, review, retrospective) y novedades del equipo para tener un registro centralizado de la información importante del proyecto.
- Como equipo de desarrollo, queremos establecer una nomenclatura coherente para las issues del proyecto para facilitar la identificación y seguimiento de las tareas.
- Como equipo de desarrollo, queremos asignar y distribuir las issues a cada miembro del equipo de manera equitativa para optimizar la productividad y responsabilidad individual.
- Como equipo de desarrollo, queremos documentar el proyecto según los estándares del IEEE 830 para proporcionar al cliente una visión clara de los requerimientos del sistema.
- Como equipo de desarrollo, queremos utilizar un tablero Kanban para gestionar el backlog de productos, las tareas pendientes, las tareas en proceso y las tareas completadas para visualizar el flujo de trabajo del proyecto.
- Como equipo de desarrollo, queremos crear un mapa de sitio para el proyecto web para visualizar la estructura y navegación del sitio.
***
- Como desarrollador, quiero poder realizar commits en GitHub para mantener un registro de los cambios realizados en el código del proyecto.
- Como miembro del equipo, quiero mantener actualizado el tablero Kanban del proyecto para visualizar el flujo de trabajo y el progreso de las tareas.
- Como desarrollador, quiero crear una aplicación de página única (SPA) para proporcionar una experiencia de usuario fluida y rápida en el navegador.
- Como desarrollador frontend, quiero crear componentes reutilizables para las distintas vistas de la aplicación para mejorar la modularidad y la mantenibilidad del código.
- Como desarrollador, quiero implementar un sistema de ruteo en Angular para permitir la navegación entre las distintas vistas de la aplicación de manera dinámica.
- Como desarrollador frontend, quiero crear formularios reactivos en Angular para gestionar el registro de usuarios, el inicio de sesión y la carga de productos y servicios según la especificación del frontend.
- Como miembro del equipo, quiero actualizar los diagramas solicitados para reflejar los cambios y mejoras realizados en el proyecto.
- Como desarrollador backend, quiero crear un sistema de registro y login de usuarios que utilice los datos almacenados en la base de datos para autenticar a los usuarios.
- Como desarrollador, quiero implementar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para productos y/o servicios en la aplicación para gestionar eficientemente la información relacionada con ellos.
***

# Sprints

## N° de sprint: 0

**Sprint Backlog:**
- **Responsabilidades:**
> Una vez conformados los grupos y definido la organización y repositorio en github, deberán:

> 1- Definir los roles de los miembros del equipo Scrum (Scrum master y Developer team). Documentar en la Wiki del Proyecto.

> 2- Evaluar el contenido y distribución previa de la tienda para que se adecue al nuevo desarrollo de e-commerce / carrito de compras, analizando los requerimientos necesarios. Lo mismo ocurrirá con la App.

> 3- Plantear Historias de Usuarios en base a los requerimientos del proyecto teniendo en cuenta una redacción y nomenclatura adecuada, ej “#US01 Como usuario quiero registrarme en el sitio web para comprar uno o varios productos” y su respectivos criterios de aceptación. Pueden utilizar la siguiente plantilla: [Plantilla Historias de Usuario](https://docs.google.com/spreadsheets/d/13qx2aip_ppAD6lnJ7LfJ_foLKpADqb72/edit?usp=sharing&ouid=117977189208786225858&rtpof=true&sd=true)

> 4- Definir la documentación del proyecto mediante el documento[ IEEE830](https://docs.google.com/document/d/1iEXh7x6SQDkE3eHRmyorb-XhyrTbhQ1DIRcx3zFQnuM/edit?usp=sharing). 

> 5- Crear un proyecto estilo kanban en Github. 

> 6- Definir la estructura de páginas en la Wiki del repositorio en github a fin de poder documentar: Nombre y apellido de los integrantes del equipo como así también los roles de cada quién, registro de ceremonias de scrum: planning, review y retrospective (para esta última es importante publicar además el plan de mejora a ejecutar en la siguiente iteración), documento [IEEE830](https://docs.google.com/document/d/1iEXh7x6SQDkE3eHRmyorb-XhyrTbhQ1DIRcx3zFQnuM/edit?usp=sharing) del proyecto. 

- **Calendario:** Fecha Inicio = 17/04/2024 - Fecha de Fin = 26/04/2024
- **Inconvenientes:** -

***

## N° de sprint: 1
**Sprint Backlog
- **Responsabilidades:**
> Deben referirse a lo realizado en el Módulo Programador Web mencionado mas arriba, y enfatizar en mejoras de desarrollo del mismo, como por ejemplo revisar que tenga la aplicación Web lo siguiente:

> ## FrontEnd

> - Producción de una aplicación web SPA a partir de la maqueta definida en el Módulo FullStack. Debe incluir como mínimo los componentes de: landing page, dashboard, galería de productos con posibilidad de acceder a la descripción de los productos y su correspondiente compra, quienes somos y contacto.
> - Sistema de rutas para permitir la navegación
> - Formularios login y registro con sus respectivas validaciones y mensajes personalizados al usuario. Ej. validar por tipos de inputs (text, number, email, date, select, según lo que se requiera), agregar maxlenght y minlenght en los campos nombres, apellido, si solicitan DNI, usar MIN=1000000 MAX=99999999, por ejemplo. 
> - Implementación del enlace de datos (data binding) entre los componentes (*.component.ts) y los templates (*.component.html).
> - Implementación de servicios que provean de datos a los respectivos componentes.
> - Formularios reactivos. Tablero de control (Gestión) o Dashboard con la implementación del carrito. Conexión con backend para recuperar los datos de los productos/servicios y detalles de producto/servicios. Conexión con el backend para el registro e inicio de sesión. Agregar rutas protegidas a la navegación en base al usuario autenticado/autorizado. 
> - Definición de la Api Rest y los endpoints necesarios para las funcionalidades. 

> ## Backend

> - DER, Modelo Relacional, Script de la base de datos todo actualizado, 
> - Modelo de Caso de Uso y Diagrama de Clases .(1 mínimo. Ej. CRUD de Producto o Servicio).
> - Creación de Apis Rest para: manipular los productos (alta, baja, modificación, lectura, listar), para el carrito de compras y finalmente para lograr la autenticación (login y registro de usuario).

- **Calendario:** Fecha Inicio = 26/04/2024 - Fecha de Fin = 15/04/2024
- **Inconvenientes:** -

***

## N° de sprint: 2
**Sprint Backlog
- **Responsabilidades:** -

- **Calendario:** Fecha Inicio = 15/05/2024 - Fecha de Fin = --/--/2024
- **Inconvenientes:** -

***

# Anexo

Los archivos incluidos en el anexo corresponden a los periodos de desarrollo anteriores y podrían en algunos casos contener información desactualizada. Para la información más relevante, por favor visitar el repositorio de la organización.

## Repositorios Anteriores

- **Repositorio Original de BankArg Aplicación Móvil:** [BankArg App](https://github.com/egiam/BankArg-APP)
- **Repositorio Original de BankArg Web:** [BankArg Web](https://github.com/egiam/BankArg-Web)

## Figma

[Figma - BankArg Diseño y prototipado](https://www.figma.com/file/AhjScpyVarUiVG0H8JI1IR/BankArg-Dise%C3%B1o-y-prototipado?type=design&node-id=0%3A1&mode=design&t=0lbUY3KUcLKTkotq-1)

## Diagramas

- [Diagrama Relacional](https://dbdiagram.io/d/BankArg-6503c0ec02bd1c4a5e9c89a1)
- [Diagrama Entidad Relación](https://github.com/egiam/BankArg-APP/blob/main/Documentacion/DER.drawio.png)
- [Modelo Relacional](https://github.com/egiam/BankArg-APP/blob/main/DataBase/Modelo%20Relacional%20(MySQLWorkbench).png)
- [Diagrama de Clases](https://github.com/egiam/BankArg-APP/blob/main/Documentacion/BankArg%20Diagrama%20de%20Clases.png)
- [Casos de Uso](https://github.com/egiam/BankArg-APP/blob/main/Documentacion/caso%20de%20uso%20.png)

## Planificación

- [Planificación de casos de prueba](https://docs.google.com/spreadsheets/d/11NFxOZDACnIeen5MOgz6tVU_wM_sal1cWBXQvdNFUrE/edit?usp=sharing)
- [Video Demo Navegación](https://www.youtube.com/watch?v=gYrCypQRQHs&t=18s)
- [Casos de Prueba automatizados con Selenium](https://drive.google.com/file/d/1Q9ZL32cUeXCvk-MjsvOEhLSU_vXFrAHX/view?usp=sharing)
- [Video Accesibilidad](https://drive.google.com/file/d/14V0KGYr_Ks8LxnSK9O2djWtU8z715ri_/view?usp=sharing)
- [Video Final](https://youtu.be/75mPLrZDANo?si=8oz2mLlpolV8Gb6l)

*** 

### 3.2 Requerimientos Funcionales y No Funcionales

#### **Requerimientos Funcionales:**

1. **Inicio de Sesión y Registro de Usuario:**
    - Los usuarios deben poder registrarse en la aplicación proporcionando información personal y de contacto.
    - Debe existir un proceso de autenticación seguro para el inicio de sesión, que podría incluir autenticación de dos factores (2FA) opcional para una mayor seguridad.
    - Los usuarios deben poder recuperar sus contraseñas en caso de olvido mediante un procedimiento seguro y confiable.

2. **Consulta de Saldo y Movimientos:**
    - Los clientes deben poder ver el saldo de sus cuentas y los movimientos recientes de manera clara y organizada.
    - Deben poder filtrar y buscar transacciones específicas según fechas, tipos de transacciones o beneficiarios.

3. **Transferencias y Pagos:**
    - Los usuarios deben poder realizar transferencias entre cuentas propias o a cuentas de terceros de forma intuitiva y segura.
    - Debe ser posible pagar facturas y servicios, así como configurar pagos programados para una gestión financiera conveniente.

4. **Alertas y Notificaciones:**
    - Los clientes deben poder configurar alertas personalizadas para transacciones y saldos que les permitan un seguimiento constante de sus cuentas.
    - Deben recibir notificaciones instantáneas de transacciones y actividades en sus cuentas para una gestión proactiva de sus finanzas.

5. **Gestión de Tarjetas:**
    - Los usuarios deben poder bloquear o desbloquear tarjetas de débito o crédito de manera rápida en caso de pérdida o robo.
    - Deben poder solicitar nuevas tarjetas y gestionar los límites de crédito de acuerdo a sus necesidades financieras.

6. **Depósitos y Retiros:**
    - Los usuarios deben poder realizar depósitos en sus cuentas desde otras fuentes, como escaneando cheques o transfiriendo fondos de manera sencilla.
    - Deben poder solicitar retiros de fondos a cuentas externas para una gestión flexible de sus recursos.

7. **Historial de Transacciones:**
    - Los clientes deben tener acceso a un historial completo de sus transacciones y estados de cuenta anteriores para un seguimiento detallado de su actividad financiera.

8. **Atención al Cliente:**
    - Debe proporcionarse un chat en línea o soporte de mensajes para que los usuarios puedan comunicarse con el servicio de atención al cliente de manera eficiente.
    - Deben poder programar citas con asesores financieros si es relevante para recibir orientación personalizada.

9. **Seguridad y Autenticación Biométrica:**
    - Los usuarios deben poder habilitar la autenticación biométrica, como la huella digital o el reconocimiento facial, para una capa adicional de seguridad en el acceso a la aplicación.

***

#### **Requerimientos No Funcionales:**

1. **Seguridad y Privacidad:**
    - La aplicación debe cumplir con los más altos estándares de seguridad para proteger la información sensible del cliente.
    - Debe cumplir con las regulaciones de privacidad de datos, como el RGPD en Europa, garantizando la confidencialidad y privacidad de los datos.

2. **Rendimiento:**
    - La aplicación debe tener un rendimiento rápido y fluido, con tiempos de carga mínimos, asegurando una experiencia ágil para el usuario.
    - Debe ser capaz de manejar múltiples conexiones simultáneas de manera eficiente sin afectar la velocidad.

3. **Disponibilidad y Tolerancia a Fallos:**
    - La aplicación debe estar disponible en todo momento, con un tiempo de inactividad mínimo, garantizando continuidad en el servicio.
    - Debe tener mecanismos de respaldo y recuperación en caso de fallos del sistema para una operatividad ininterrumpida.

4. **Compatibilidad y Usabilidad:**
    - La aplicación debe ser compatible con una amplia gama de dispositivos Android y versiones de sistema operativo para llegar a una mayor audiencia.
    - Debe contar con una interfaz de usuario intuitiva y accesible para usuarios de todas las edades y niveles de habilidad, facilitando su uso.

5. **Escalabilidad:**
    - La infraestructura subyacente debe ser escalable para manejar un crecimiento sostenido de usuarios y transacciones sin comprometer el rendimiento.
    
6. **Mantenimiento y Actualizaciones:**
    - Debe ser posible realizar actualizaciones y mantenimiento de la aplicación sin afectar la disponibilidad del servicio, asegurando una mejora continua.

7. **Documentación y Capacitación:**
    - Debe proporcionarse documentación clara para los usuarios sobre cómo utilizar la aplicación, brindando una experiencia de usuario óptima.
    - Los empleados y administradores deben recibir capacitación adecuada para brindar soporte a los clientes de manera efectiva.

8. **Cumplimiento Normativo:**
    - La aplicación debe cumplir con todas las regulaciones bancarias y financieras aplicables en la jurisdicción en la que opera, asegurando el cumplimiento legal y ético.
