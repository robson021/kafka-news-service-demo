package consumer;

import common.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NewsStorage {

    private static final Logger log = LoggerFactory.getLogger(NewsStorage.class);

    private final NewsRepository newsRepository;

    public NewsStorage(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void addNews(News newNews) {
        newsRepository.save(new NewsEntity(newNews))
                .subscribe(x -> log.info("Saved new entity: {}", x));
    }

    public Flux<News> getAllNews() {
        return newsRepository.findAll()
                .map(NewsEntity::getNews);

    }

    public Mono<News> getMostRecent() {
        return newsRepository.findTopByOrderByIdDesc()
                .map(NewsEntity::getNews);
    }

}
