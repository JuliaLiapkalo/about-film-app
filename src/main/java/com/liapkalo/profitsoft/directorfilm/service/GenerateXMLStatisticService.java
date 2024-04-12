package com.liapkalo.profitsoft.directorfilm.service;

import java.util.Map;

public interface GenerateXMLStatisticService {

    /**
     * Generates an XML representation of the statistics based on the provided map with attribute counts.
     *
     * @param attributeCounts A map containing attribute values as keys and their respective counts as values.
     * @return A string representing the XML statistics.
     */
    String generateXmlStatistics(Map<String, Integer> attributeCounts);
}
