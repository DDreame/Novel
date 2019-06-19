package novel.spider.entity;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 章节详细内容实体
 * @Classname ChapterDetail
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/6 23:22
 * @Version 1.0
 **/
public class ChapterDetail implements Serializable {
    private static final long serialVersionUID = 3816410657418883979L;
    private String title;
    private String content;
    private String prev;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterDetail that = (ChapterDetail) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(prev, that.prev) &&
                Objects.equals(next, that.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, prev, next);
    }

    @Override
    public String toString() {
        return "ChapterDetail{" +
                "title='" + title + '\'' +
                ", content='" + StringUtils.abbreviate(content,30) + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
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

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    private String next;
}
