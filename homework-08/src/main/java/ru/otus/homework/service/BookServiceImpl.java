package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.BookRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

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
    public Book insert(String bookName, String authorName, String genreName) {
        Book bookForInsert = new Book();

        bookForInsert.setName(bookName);
        bookForInsert.setGenre(new Genre(genreName));
        bookForInsert.setAuthor(new Author(authorName));

        return bookRepository.save(bookForInsert);
    }

    @Override
    public Book update(String bookId, String bookName, String authorName, String genreName) {
        Book updatedBook = null;
        Optional<Book> bookForUpdateOptional = bookRepository.findById(bookId);

        if (bookForUpdateOptional.isPresent()) {
            Book bookForUpdate = bookForUpdateOptional.get();

            bookForUpdate.setGenre(new Genre(genreName));
            bookForUpdate.setAuthor(new Author(authorName));
            bookForUpdate.setName(bookName);

            updatedBook = bookRepository.save(bookForUpdate);
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
