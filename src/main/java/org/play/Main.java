package org.play;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Email> emails = List.of(
                new Email("a@google.com"),
                new Email("b@facebook.com"),
                new Email("c@twitter.com"),
                new Email("d@facebook.com"),
                new Email("e@google.com"),
                new Email("af@facebook.com"),
                new Email("af@facebook1.com"),
                new Email("af@facebook2.com"),
                new Email("af@facebook3.com"),
                new Email("af@facebook4.com"),
                new Email("af@facebook5.com"),
                new Email("af@facebook6.com"),
                new Email("af@facebook7.com"),
                new Email("af@facebook8.com"),
                new Email("af@facebook9.com"),
                new Email("af@facebook10.com")

        );
        Emails.printTopDomains(emails, 10);
    }
}