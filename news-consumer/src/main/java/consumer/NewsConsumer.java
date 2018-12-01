package consumer;

import common.config.KafkaProps;
import common.model.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NewsConsumer {

    private final NewsStorage newsStorage;

    @KafkaListener(topics = KafkaProps.DEFAULT_TOPIC)
    public void newsListener(News news) {
        log.info("Consumed new message: {}", news);
        newsStorage.addNews(news);
    }

}
