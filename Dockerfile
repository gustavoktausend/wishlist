FROM azul/zulu-openjdk-alpine:17
MAINTAINER Gustavo Kring Tausendfreund <gustavoktausend@gmail.com>
ADD target/wishlist.jar wishlist.jar

ENTRYPOINT ["java", "-jar", "wishlist.jar"]

# setting timezone
RUN apk add tzdata
RUN cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
RUN echo "America/Sao_Paulo" >  /etc/timezone