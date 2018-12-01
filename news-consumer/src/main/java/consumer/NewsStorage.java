package consumer;

import common.model.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsStorage {

    private final NewsRepository newsRepository;

    public void addNews(News news) {
        newsRepository.save(new NewsEntity(news)).subscribe(e -> log.info("Saved new entity: {}", e));
    }

    public Flux<News> getAllNews() {
        return newsRepository.findAll().map(NewsEntity::getNews);

    }

    public Mono<News> getMostRecent() {
        return newsRepository.findTopByOrderByIdDesc().map(NewsEntity::getNews);
    }

}
