Kafka Running

Zookeeper starting
D:\Softwares\kafka_2.11-2.4.1\bin\windows>zookeeper-server-start.bat "D:\Softwares\kafka_2.11-2.4.1\config\zookeeper.properties"

kafka starting
D:\Softwares\kafka_2.11-2.4.1\bin\windows>kafka-server-start.bat "D:\Softwares\kafka_2.11-2.4.1\config\server.properties"

kafka producer starting
D:\Softwares\kafka_2.11-2.4.1\bin\windows>kafka-console-producer.bat --broker-list localhost:9092 --topic test

kafka consumer starting
D:\Softwares\kafka_2.11-2.4.1\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning

groupname=test
topicname=test

topic creation
D:\Softwares\kafka_2.11-2.4.1\bin\windows>kafka-topics.bat "D:\Softwares\kafka_2.11-2.4.1\config\server.properties"

application url
http://localhost:8080/sos/swagger-ui.html
