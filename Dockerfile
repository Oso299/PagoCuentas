# Usamos Tomcat 9 con JDK 17
FROM tomcat:9.0-jdk17

# Limpiamos las apps por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiamos tu WAR al contenedor
COPY SistemaPagosClientes.war /usr/local/tomcat/webapps/ROOT.war

# Exponemos el puerto 8080
EXPOSE 8080

# Variables de entorno para la conexión a la DB
ENV DB_HOST=<TU_HOST_RENDER>
ENV DB_PORT=<PUERTO_RENDER>
ENV DB_NAME=<NOMBRE_DB>
ENV DB_USER=<USUARIO_DB>
ENV DB_PASS=<PASSWORD_DB>

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]
