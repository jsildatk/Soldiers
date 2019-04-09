package org.soldiers.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, unique = true)
    @NotNull
    private String title;

    @Column(name = "content", nullable = false)
    @NotNull
    private String content;

    @Column(name = "add_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    public News(User user, @NotNull String title, @NotNull String content, Date addDate) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.addDate = addDate;
    }

    public News() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "News(" + this.id + " " + this.user + " " + this.title + " " + this.content + " " + this.addDate + ")";
    }
}
