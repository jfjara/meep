# Meep Test

Se incluye la prueba del test técnico para Meep.

Se ha realizado la funcionalidad solicitada añadiendo lo siguiente:
- Controladores para la solicitud de datos del servidor, el acceso de vehículos disponibles, con modificaciones en sus valores y no disponibles respecto a la última consulta al servidor.
- Se ha perfilado la aplicación para poder configurar según el perfil solicitado en el fichero de propiedades del proyecto.
- Se añade librería OpenApi 3 para la documentación de los controllers.
- Se incluye Java Actuators.
- Incluye Dockerfile para desplegar la aplicación en un contenedor.
     docker build -t meep:1.0
     docker run -it --rm -p 8080:8080 meep:1.0


# Funcionamiento de la aplicación:

Se configura un scheduler con un Cron Expression que será el encargado de consultar al servidor de Meep por los vehículos disponibles según los parámetros descritos en el enunciado. Dicho Cron es configurable en el application.yml, así como los parámetros de consulta en la llamada al servidor, pudiéndose perfilar para otras consultas diferentes en esta aplicación.
Dicha tarea periódica obtiene un listado de objetos que representan vehículos disponibles, los cuales se deserializan y se procesan para obtener los vehículos disponibles, los no disponibles y los que han sufrido alguna modificación en sus datos. El programa ofrece 3 endpoints para poder consultar dicha información:
  - /avail : Obtiene un listado de jsons con la información de los vehículos
  - /modified: Obtiene listado de jsons con los vehículos que han sufrido modificación con respecto a la llamada anterior al servidor de Meep.
  - /notavail: Obtiene listado de jsons con aquellos vehículos que alguna vez estuvieron disponibles pero ya no lo están.
  - /get: Realiza petición al servidor externo, muestra por pantalla el json que este devuelve y actualiza la caché.
  
 Al recibir los datos mediante una llamada con RestTemplate al servidor de Meep, se inyectan dichos valores obtenidos a una caché propia que se encarga de computar los vehículos y mediante mapas privados se clasifican en las 3 categorías anteriormente mencionadas (disponibles, no disponibles y modificados).
 
 La aplicación, aparte de los endpoints mencionados anteriormente, expone un Swagger y los Java Actuators:
 - /swagger-ui.html
 - /v3/api-docs
 - /actuator/health
 - /actuator/info
 
 Se incluyen test realizados con Junit 4 y Mockito donde se abarca toda la funcionalidad del sistema (exceptuando POJOS del modelo).
 
 
 # Cuestiones formuladas en el enunciado
 
 P: ¿Cómo de escalable es tu solución propuesta? ¿Qué problemas a futuro podría presentar? Si has detectado alguno, ¿Qué alternativa/s propones para solventar dichos problemas?
 R: Para un sistema real esta solución no es válida. Toda la funcionalidad queda encapsulada en una misma instancia, lo cual no es correcto. No es una aplicacion escalable debido a que cada instancia contiene su propia cache, pudiendo haber diferencias en los datos de las caches de cada instancia dependiendo de cuando se realizan las consultas al servidor de Meep. Tampoco es aconsejable tener en la misma aplicación que hace todo (cache, exposición de endpoints, lógica de negocio...) una tarea programable que se ejecuta cada cierto tiempo ya que consume recursos de forma innecesaria. Lo aconsejable es dividir en más microservicios esta arquitectura, por ejemplo, se podría crear una arquitectura basada en microservicios con un Spring Config Cloud para tener las configuraciones de proyectos, un EurekaServer encargado de registrar todos los microservicios del sistema, un proxy como Zuul para enrutar llamadas a los microservicios y los siguientes microservicios:
  - Microservicio exposición de endpoints. Se comunica con el microservicio de lógica de negocio para que este consulte a la cache de información de vehículos y obtenga la información solicitada. Se podría abrir también un endpoint donde solicita al servidor externo vehículos de otras localizaciones o compañías. Para ello se puede habilitar en el microservicio de lógica de negocio el módulo de comunicación asíncrona y multihilo mediante rest con el servidor externos, indicando los valores necesarios de compañía y marco georeferenciado (estos valores llegarán desde una UI externa, móvil, web, etc).
  - Microservicio de lógica de negocio para tratar los datos que obtenemos del servidor de Meep. Después de la catalogación de datos se comunica con el microservicio de cache para almacenar dicha información. Puede tener varias instancias.
  - Microservicio de acceso a cache: Se puede crear un microservicio dedicado a cache exclusivamente, y tener una cache con Redis o EHCache por ejemplo. Puede tener varias instancias.
  - Microservicio con la tarea programable de acceso al servidor de Meep: Dejamos un microservicio para esta tarea de consultar al servidor externo. Los datos obtenidos serán consumidos por el microservicio de lógica de negocio, con comunicacion con arquitectura Event-Drive con colas (RabbitMQ o Kafka). Con una instancia sería suficiente.
  
  Aparte de los microservicios y la arquitectura propuesta, debemos modularizar en librerías las comunicaciones y el modelo de datos de la aplicación. Aparte como ya hemos comentado, se propone tener un sistema RabbitMQ o Kafka para la comunicación entre el microservicio que contiene la tarea programable y la lógica de negocio (de este modo nos aseguramos que esa información va a ser siempre procesada aún habiendo parada del sistema) y una cache de almacenamiento de datos.
  
 

