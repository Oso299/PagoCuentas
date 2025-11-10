# Usamos Tomcat 9.0 con Java 17
FROM tomcat:9.0-jdk17

# Limpiamos apps por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiamos tu WAR al contenedor y lo renombramos a ROOT.war
COPY SistemaPagosClientes.war /usr/local/tomcat/webapps/ROOT.war

# Abrimos el puerto 8080
EXPOSE 8080

# Comando que arranca Tomcat
CMD ["catalina.sh", "run"]
