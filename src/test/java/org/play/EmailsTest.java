package org.play;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmailsTest {

    @Test
    void orderDomainsByOccurrences() {
        List<Email> emails = List.of(
                new Email("a@google.com"),
                new Email("b@facebook.com"),
                new Email("c@twitter.com"),
                new Email("d@facebook.com"),
                new Email("e@google.com"),
                new Email("af@facebook.com")
        );
        Map<String, Long> expected = Map.of(
                "facebook.com", 3L,
                "google.com", 2L,
                "twitter.com", 1L
        );
        assertEquals(expected, Emails.topDomainsOf(emails));
    }
}