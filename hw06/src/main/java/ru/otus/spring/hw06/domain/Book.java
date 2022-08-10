package ru.otus.spring.hw06.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "genre_id")
    @ManyToOne
    private Genre genre;

    @ToString.Exclude
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    @ToString.Exclude
    @BatchSize(size = 10)
    @OneToMany(mappedBy = "book")
    private List<Comment> comments = new ArrayList<>();

    public Book(Long id, String title, Genre genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public Book(String title, Genre genre) {
        this.title = title;
        this.genre = genre;
    }

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
