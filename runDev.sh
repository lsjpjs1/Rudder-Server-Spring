sudo fuser -k -n tcp 8080
nohup /opt/jdk-17/bin/java -jar -Dspring.profiles.active=development /home/ubuntu/rudder/RESTApiMVC-0.0.1-SNAPSHOT.jar > log.txt 2>&1 &