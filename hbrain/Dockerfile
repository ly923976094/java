#FROM 10.10.150.149:5000/airdock/oracle-jdk:1.8
FROM 10.10.150.149:5000/java:8-jre

ADD target/hbrain-spring-boot-1.0.jar /hbrain-spring-boot-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xms1800m","-Xmx1800m","-Xmn1400m","-jar","/hbrain-spring-boot-1.0.jar"]