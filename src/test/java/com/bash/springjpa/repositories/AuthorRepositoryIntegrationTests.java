package com.bash.springjpa.repositories;

import com.bash.springjpa.TestDataUtil;
import com.bash.springjpa.domain.Author;
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
}
