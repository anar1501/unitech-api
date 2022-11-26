FROM openjdk:8
VOLUME /tmp
ADD build/libs/*.jar dockerimage.jar
EXPOSE 8888
RUN bash -c 'touch /dockerimage.jar'
ENTRYPOINT ["java","-jar","dockerimage.jar"]