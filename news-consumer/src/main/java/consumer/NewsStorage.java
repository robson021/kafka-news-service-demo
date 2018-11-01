package consumer;

import common.model.News;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NewsStorage {

    private static final News EMPTY_NEWS;

    static {
        News news = new News();
        news.setTitle("Empty Title");
        news.setContent("No content");
        EMPTY_NEWS = news;
    }

    private final List<News> news = new CopyOnWriteArrayList<>();

    public void addNews(News newNews) {
        this.news.add(newNews);
    }

    public List<News> getAllNews() {
        return this.news;
    }

    public News getMostRecent() {
        int lastIndex = news.size() - 1;
        if (lastIndex < 0)
            return EMPTY_NEWS;
        return news.get(lastIndex);
    }

}
