package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.*;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
     public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book insert(String bookName, String authorId, String genreName) {
        Book bookForInsert = new Book();

        bookForInsert.setName(bookName);
        bookForInsert.setGenre(new Genre(genreName));

        Book insertedBook = null;

        Optional<AuthorWithLinks> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            AuthorWithLinks author = authorOptional.get();

            bookForInsert.setAuthor(new Author(author.getId(), author.getName()));
            insertedBook = bookRepository.save(bookForInsert);

            author.getBookIds().add(insertedBook.getId());
            authorRepository.save(author);
        }

        return insertedBook;
    }

    @Override
    public Book update(String bookId, String bookName, String authorId, String genreName) {
        Book updatedBook = null;
        Optional<Book> bookForUpdateOptional = bookRepository.findById(bookId);

        if (bookForUpdateOptional.isPresent()) {
            Book bookForUpdate = bookForUpdateOptional.get();

            bookForUpdate.setName(bookName);
            bookForUpdate.setGenre(new Genre(genreName));

            Optional<AuthorWithLinks> authorOptional = authorRepository.findById(authorId);
            if (authorOptional.isPresent()) {
                AuthorWithLinks author = authorOptional.get();
                Author oldAuthor = bookForUpdate.getAuthor();

                if (!oldAuthor.getId().equals(author.getId())) {
                    String oldAuthorId = bookForUpdate.getAuthor().getId();

                    Optional<AuthorWithLinks> oldAuthorWithLinksOptional = authorRepository.findById(oldAuthorId);
                    if (oldAuthorWithLinksOptional.isPresent()) {
                        AuthorWithLinks oldAuthorWithLinks = oldAuthorWithLinksOptional.get();
                        oldAuthorWithLinks.getBookIds().remove(bookId);
                        authorRepository.save(oldAuthorWithLinks);
                    }

                    bookForUpdate.setAuthor(new Author(author.getId(), author.getName()));
                    author.getBookIds().add(bookForUpdate.getId());
                    authorRepository.save(author);
                }

                updatedBook = bookRepository.save(bookForUpdate);
            }
        }

        return updatedBook;
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book addComment(String bookId, Comment comment) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Book actualBook = null;

        if (bookOptional.isPresent()) {
            Book bookForCommentInsert = bookOptional.get();
            bookForCommentInsert.getComments().add(comment);
            actualBook = bookRepository.save(bookForCommentInsert);
        }

        return actualBook;
    }

    @Override
    public Book deleteComment(String bookId, String commentId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Book actualBook = null;

        if (bookOptional.isPresent()) {
            Book bookForCommentDelete = bookOptional.get();
            List<Comment> comments = bookForCommentDelete.getComments();
            for (Comment comment: comments) {
                if (comment.getId().equals(commentId)) {
                    comments.remove(comment);
                    break;
                }
            }
            actualBook = bookRepository.save(bookForCommentDelete);
        }

        return actualBook;
    }

    @Override
    public Book updateComment(String bookId, String commentId, String nickname, String content) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Book actualBook = null;

        if (bookOptional.isPresent()) {
            Book bookForCommentUpdate = bookOptional.get();
            List<Comment> comments = bookForCommentUpdate.getComments();
            for (Comment comment: comments) {
                if (comment.getId().equals(commentId)) {
                    comment.setNickname(nickname);
                    comment.setContent(content);
                    break;
                }
            }
            actualBook = bookRepository.save(bookForCommentUpdate);
        }

        return actualBook;
    }

}
