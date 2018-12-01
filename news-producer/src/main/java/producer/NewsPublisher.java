package producer;

import common.model.News;
import common.model.NewsCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("all")
public class NewsPublisher {

    private static final String[] newsContent = {
            "Some content.",
            "Antother content.",
            "Lorem ipsum dolor sit amet, graeci periculis ocurreret has ad, qui veri oratio suscipit ad, at debet partiendo quo.",
            "Idque sanctus indoctum ea per. Nam id dico putent nostro.",
            "Natum harum nostrum mea et, eum an magna virtute suavitate.",
            "Consul integre contentiones ius eu."
    };

    private final KafkaTemplate template;

    @Scheduled(fixedDelay = 3_500)
    public void publishNews() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        NewsCategory[] categories = NewsCategory.values();

        News news = new News();
        news.setNewsCategory(categories[random.nextInt(categories.length)]);
        news.setTitle("Title " + random.nextInt(999999999));
        news.setContent(newsContent[random.nextInt(newsContent.length)]);

        template.send(new GenericMessage<>(news))
                .addCallback(new ListenableFutureCallback() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        log.error("Failed to publish news", throwable);
                    }

                    @Override
                    public void onSuccess(Object o) {
                        log.info("Succesfully publised news: {}", o);
                    }
                });
    }
}