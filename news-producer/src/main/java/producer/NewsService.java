package producer;

import common.model.News;
import common.model.NewsCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ThreadLocalRandom;

@Component
@SuppressWarnings("all")
public class NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsService.class);

    private static final String[] newsContent = {
            "Some content.",
            "Antother content.",
            "Lorem ipsum dolor sit amet, graeci periculis ocurreret has ad, qui veri oratio suscipit ad, at debet partiendo quo.",
            "Idque sanctus indoctum ea per. Nam id dico putent nostro.",
            "Natum harum nostrum mea et, eum an magna virtute suavitate.",
            "Consul integre contentiones ius eu."
    };

    private static final ListenableFutureCallback publishCallback = new ListenableFutureCallback() {
        @Override
        public void onFailure(Throwable throwable) {
            log.error("Failed to publish news", throwable);
        }

        @Override
        public void onSuccess(Object o) {
            log.info("Succesfully publised news: {}", o);
        }
    };

    private final KafkaTemplate template;

    public NewsService(KafkaTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedDelay = 10_000)
    public void publishNews() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        NewsCategory[] categories = NewsCategory.values();

        News news = new News();
        news.setNewsCategory(categories[random.nextInt(categories.length)]);
        news.setTitle("Title " + random.nextInt(999999999));
        news.setContent(newsContent[random.nextInt(newsContent.length)]);

        template.send(new GenericMessage<>(news)).addCallback(publishCallback);
    }
}
