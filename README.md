# API openapi documentation json endpoint:

http://localhost:1010/v3/api-docs

# API swagger ui using openapi integration:

http://localhost:1010/swagger-ui/index.html

# Kafka Related Important Commands:

zookeeper-server-start.bat ..\..\config\zookeeper.properties

kafka-server-start.bat ..\..\config\server.properties

kafka-topics.bat --create --topic sihai --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3

kafka-console-producer.bat --broker-list localhost:9092 --topic my-topic

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic sihai --from-beginning

# Ordering of message

kafka-console-producer.bat --broker-list localhost:9092 --topic foods --property "key.separator=-" --property "parse.key=true"

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic foods --from-beginning -property "key.separator=-" --property "print.key=false"


# start zookeeper

zookeeper-server-start.bat ..\..\config\zookeeper.properties



# start kafka server

kafka-server-start.bat ..\..\config\server.properties



#list all topics 

kafka-topics.bat --bootstrap-server=localhost:9092 --list



# list all consumer groups 

kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list



# describe a topic 

.\kafka-topics.bat --describe --topic my-topic --bootstrap-server localhost:9092



# start producer with key 

kafka-console-producer.bat --broker-list localhost:9092 --topic foods --property "key.separator=-" --property "parse.key=true"



# start consumer with group name 

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic foods --group console-consumer-2682

# Kafka Cluster with 3 brokers
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties
.\bin\windows\kafka-server-start.bat .\config\server-1.properties
.\bin\windows\kafka-server-start.bat .\config\server-2.properties
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --replication-factor 3 --partitions 3 --topic your_topic_name
kafka-console-producer.bat --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic foods
kafka-console-consumer.bat --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic foods --from-beginning

