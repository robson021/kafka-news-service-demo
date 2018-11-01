package consumer;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface NewsRepository extends ReactiveMongoRepository<NewsEntity, ObjectId> {
    Mono<NewsEntity> findTopByOrderByIdDesc();
}
