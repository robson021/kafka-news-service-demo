package consumer;

import com.mongodb.lang.NonNull;
import common.model.News;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class NewsEntity {

    @Id
    private ObjectId id;

    @NonNull
    private News news;

    @PersistenceConstructor
    public NewsEntity(ObjectId id, News news) {
        this.id = id;
        this.news = news;
    }

    public NewsEntity(News news) {
        this.news = news;
    }

    public ObjectId getId() {
        return id;
    }

    public News getNews() {
        return news;
    }

    @Override
    public boolean equals(Object o) {
        if (this.id == null) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "id=" + id +
                ", news=" + news +
                '}';
    }
}
