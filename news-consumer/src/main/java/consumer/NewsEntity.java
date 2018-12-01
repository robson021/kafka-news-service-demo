package consumer;

import common.model.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class NewsEntity {

    @Id
    private ObjectId id;

    @lombok.NonNull
    @com.mongodb.lang.NonNull
    private News news;
}
