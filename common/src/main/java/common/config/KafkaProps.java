package common.config;

public final class KafkaProps {

    public static final String KAFKA_ADDRESS = "localhost:9092";

    public static final String DEFAULT_TOPIC = "news";

    public static final String DEFAULT_GROUP = "news_group";

    private KafkaProps() {
    }
}
