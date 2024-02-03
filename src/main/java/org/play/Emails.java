package org.play;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Collections.reverseOrder;

public final class Emails {

    /**
     * @param topDomainsLimit the maximum number of domains in the result (the result may have fewer domains though)
     */
    public static void printTopDomains(Iterable<Email> emails, int topDomainsLimit) {
        int domainsListed = 0;
        for (Map.Entry<String, Long> domainOccurrences : topDomainsOf(emails).entrySet()){
            if (domainsListed >= topDomainsLimit) {
                break;
            }
            System.out.println(domainOccurrences.getKey() + " " + domainOccurrences.getValue());
            domainsListed++;
        }
    }

    /**
     * Algorithm complexity is O(n log n):
     *   1. Count the number of domains for every email – O(n).
     *      1.1 Iterate over all emails and use a Hash Map for look up – O(n).
     *   2. Sort domains by the number of occurrences. Typical sorting algorithm is O(n log n).
     *   3. O(n) + O(n log n) = O(n log n)
     *
     * @param emails the emails to count domains for
     * @return the number of occurrences for each domain
     * (the first element is the most popular domain, the last one is the least popular domain)
     */
    public static SequencedMap<String, Long> topDomainsOf(Iterable<Email> emails) {
        Map<String, Long> domainsCount = StreamSupport.stream(emails.spliterator(), false).
                collect(Collectors.groupingBy(Email::domain, Collectors.counting()));
        return domainsCount.entrySet().stream()
                .sorted(reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {throw new IllegalStateException("Expected to have unique domains at this moment");},
                        LinkedHashMap::new)
                );
    }
}
