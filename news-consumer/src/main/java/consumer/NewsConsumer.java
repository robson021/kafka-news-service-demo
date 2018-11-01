package consumer;

import common.config.KafkaProps;
import common.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewsConsumer {

    private static final Logger log = LoggerFactory.getLogger(NewsConsumer.class);

    private final NewsStorage newsStorage;

    public NewsConsumer(NewsStorage newsStorage) {
        this.newsStorage = newsStorage;
    }

    @KafkaListener(topics = KafkaProps.DEFAULT_TOPIC)
    public void newsListener(News news) {
        log.info("Consumed new message: {}", news);
        newsStorage.addNews(news);
    }

}
