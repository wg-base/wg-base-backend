FROM java:8

MAINTAINER <springioc@yeah.net>

ADD ./target/backend-0.0.1-SNAPSHOT.jar /wg-base-backend.jar

EXPOSE 9000

CMD ["java","-jar","wg-base-backend.jar"]