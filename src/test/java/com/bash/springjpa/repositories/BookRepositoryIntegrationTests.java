package com.bash.springjpa.repositories;

import com.bash.springjpa.TestDataUtil;
import com.bash.springjpa.domain.Author;
import com.bash.springjpa.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

    private BookRepository bookRepository;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRetrieved(){
        // Arrange
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);
        // Act
        // Usage of save() returns the "managed" entity with generated IDs
        book = bookRepository.save(book);
        Optional<Book> result = bookRepository.findById(book.getIsbn());
        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
}
