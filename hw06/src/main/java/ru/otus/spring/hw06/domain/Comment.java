package ru.otus.spring.hw06.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    public Comment(Long id, Long bookId, String bookTitle, String text) {
        this.id = id;
        this.text = text;
        this.book = new Book(bookId, bookTitle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
