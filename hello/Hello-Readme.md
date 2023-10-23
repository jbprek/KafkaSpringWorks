# Hello Producer and  Consumer

Simple String produce/consume
## Create topic
```bash
kafka-topics --bootstrap-server localhost:9092 --create --topic hello-topic
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic hello-topic
```

## Delete topic
```bash
kafka-topics --bootstrap-server localhost:9092 --delete --topic hello-topic
kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic hello-topic
```