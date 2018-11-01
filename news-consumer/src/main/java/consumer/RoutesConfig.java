package consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RoutesConfig {

    private final NewsStorage newsStorage;

    public RoutesConfig(NewsStorage newsStorage) {
        this.newsStorage = newsStorage;
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(GET("/news"), this::getAllNews)
                .andRoute(GET("/news/most-recent"), this::getMostRecent);
    }

    private Mono<ServerResponse> getAllNews(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .syncBody(newsStorage.getAllNews());
    }

    private Mono<ServerResponse> getMostRecent(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .syncBody(newsStorage.getMostRecent());
    }

}