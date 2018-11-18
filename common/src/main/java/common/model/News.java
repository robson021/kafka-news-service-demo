package common.model;

public class News {

    private NewsCategory newsCategory;

    private String title;

    private String content;

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsCategory=" + newsCategory +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
