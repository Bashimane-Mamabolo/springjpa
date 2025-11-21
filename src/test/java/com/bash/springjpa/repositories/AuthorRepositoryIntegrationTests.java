package com.bash.springjpa.repositories;

import com.bash.springjpa.TestDataUtil;
import com.bash.springjpa.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRetrieved(){
        // Arrange
        Author author = TestDataUtil.createTestAuthor();
        // Act
        author = authorRepository.save(author);
        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());
        // Assert
        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRetrieved(){
        // Arrange
        Author author1 = TestDataUtil.createTestAuthor();
        Author author2 = TestDataUtil.createTestAuthorA();
        Author author3 = TestDataUtil.createTestAuthorB();
        // Act
        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);
        author3 = authorRepository.save(author3);

        Iterable<Author> result = authorRepository.findAll();
        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(author1, author2, author3);
    }

    @Test
    public void testThatAuthorCanBeUpdatedAndRetrieved(){
        // Arrange
        Author author = TestDataUtil.createTestAuthor();
        // Act
        author = authorRepository.save(author);
        author.setAge(32);
        author = authorRepository.save(author);
        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());
        // Assert
        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get()).isEqualTo(author);
        assertThat(optionalAuthor.get().getAge()).isEqualTo(32);
    }

    @Test
    public void testThatAuthorCanBeDeletedAndRetrieved(){
        // Arrange
        Author author = TestDataUtil.createTestAuthor();
        // Act
        author = authorRepository.save(author);
        authorRepository.deleteById(author.getId());
        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());
        // Assert
        assertThat(optionalAuthor).isEmpty();
    }

    // A more complex test, we're going to leverage SPRING JPA muscle
    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        // Arrange
        Author author = TestDataUtil.createTestAuthor();
        Author author2 = TestDataUtil.createTestAuthorA();
        Author author3 = TestDataUtil.createTestAuthorB();
        // Act
        author = authorRepository.save(author);
        author2 = authorRepository.save(author2);
        author3 = authorRepository.save(author3);
        Iterable<Author> result = authorRepository.findAllByAgeLessThan(30);
        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(author2, author3);

    }
}
