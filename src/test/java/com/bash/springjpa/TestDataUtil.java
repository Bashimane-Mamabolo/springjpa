package com.bash.springjpa;


import com.bash.springjpa.domain.Author;
import com.bash.springjpa.domain.Book;

public final class TestDataUtil {

    private TestDataUtil() {}

    // Authors
    public static Author createTestAuthor() {
        return Author.builder()  // DB is generating id manually
//                .id(null)
                .name("Bash")
                .age(31)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
//                .id(null)
                .name("Simon")
                .age(31)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
//                .id(null)
                .name("ByteBash")
                .age(31)
                .build();
    }


    // Books
    public static Book createTestBook(final Author author) {
        return Book.builder()
                .isbn("ISBN")
                .title("Title")
                .author(author)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("ISBN1")
                .title("Title")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("ISBN2")
                .title("Title")
                .author(author)
                .build();
    }
}
