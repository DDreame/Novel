package novel.spider.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 小说的章节实体
 * @Classname Chapter
 * @Description TO
 * @Author XinChen
 * @Date 2019/5/5 19:18
 * @Version 1.0
 **/

public class Chapter implements Serializable{
    private static final long serialVersionUID = 1952081502449669633L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chapter chapter = (Chapter) o;
        return title.equals(chapter.title) &&
                url.equals(chapter.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }

    private String title;
    private String url;

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
