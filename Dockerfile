FROM openjdk:11-jre
ADD ./build/libs/quotes-1.0.0-SNAPSHOT.jar /app/quotes-1.0.0-SNAPSHOT.jar
CMD ["java", "-Xdebug", "-XX:+UseContainerSupport", "-Xmx256m", "-Xss512k", "-XX:MetaspaceSize=100m", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/quotes-1.0.0-SNAPSHOT.jar"]