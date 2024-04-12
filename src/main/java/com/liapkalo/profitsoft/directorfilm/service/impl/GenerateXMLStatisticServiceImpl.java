package com.liapkalo.profitsoft.directorfilm.service.impl;

import com.liapkalo.profitsoft.directorfilm.service.GenerateXMLStatisticService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class GenerateXMLStatisticServiceImpl implements GenerateXMLStatisticService {

    /**
     * Generates an XML representation of the statistics based on the provided map with attribute counts.
     *
     * @param attributeCounts A map containing attribute values as keys and their respective counts as values.
     * @return A string representing the XML statistics.
     */
    @Override
    public String generateXmlStatistics(Map<String, Integer> attributeCounts) {
        log.info("Generating XML statistics with attribute counts size: {}", attributeCounts.size());

        StringBuilder filmsStatisticsXML = new StringBuilder("<statistics> \n");
        getSortedEntries(attributeCounts).forEach(entry -> filmsStatisticsXML.append(generateXmlItem(entry.getKey(),
                String.valueOf(entry.getValue()))));

        log.info("XML statistics generated successfully");
        return filmsStatisticsXML.append("</statistics>").toString();
    }

    /**
     * Sorts the entries of the given attribute counts map by the integer values in descending order.
     *
     * @param attributeCounts The map containing attribute counts.
     * @return A list of map entries sorted by their integer values in descending order.
     */
    private static List<Map.Entry<String, Integer>> getSortedEntries(Map<String, Integer> attributeCounts) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(attributeCounts.entrySet());

        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        return sortedEntries;
    }

    /**
     * Generates an XML representation of a single item in the statistics.
     *
     * @param value The value of the attribute.
     * @param count The count associated with the attribute value.
     * @return A string representing the XML item.
     */
    private String generateXmlItem(String value, String count) {
        return String.format("""
                 <item>
                    <value>%s</value>
                    <count>%s</count>
                 </item>
                """, value, count);
    }

}
