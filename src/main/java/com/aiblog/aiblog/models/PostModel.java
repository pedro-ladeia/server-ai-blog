package com.aiblog.aiblog.models;


import jakarta.persistence.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_POST")
public class PostModel implements Serializable {
    private static final long serialVersionUID = 1L; // Version control number to serializable of the class

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPost;

    @Column
    private String name;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDate updatedAt;

    @Column
    private String title ;

    @Column
    private String category;

    @Column
    private String content;

    @Column
    private String author;

    @Column
    private String image;

    @Column
    private String snippet;


    public UUID getIdPost() {
        return idPost;
    }

    public void setIdPost(UUID idPost) {
        this.idPost = idPost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
}
