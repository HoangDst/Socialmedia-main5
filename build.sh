#build
mvn -pl noron-api clean install -am

#run
java -jar noron-api/target/noron-api-0.0.1-SNAPSHOT.jar