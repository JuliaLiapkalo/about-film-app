package com.liapkalo.profitsoft.directorfilm.service;
import com.liapkalo.profitsoft.directorfilm.service.impl.GenerateXMLStatisticServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class GenerateXMLStatisticServiceTest {

    @Mock
    private GenerateXMLStatisticService service;

    @BeforeEach
    public void setUp() {
        service = new GenerateXMLStatisticServiceImpl();
    }


    @Test
    public void testGenerateXmlStatistics() {
        Map<String, Integer> attributeCounts = new LinkedHashMap<>();
        attributeCounts.put("action", 3);
        attributeCounts.put("comedy", 5);
        attributeCounts.put("drama", 2);

        String xmlStatistics = service.generateXmlStatistics(attributeCounts);

        assertEquals("<statistics> \n" +
                " <item>\n" +
                "    <value>action</value>\n" +
                "    <count>3</count>\n" +
                " </item>\n" +
                " <item>\n" +
                "    <value>comedy</value>\n" +
                "    <count>5</count>\n" +
                " </item>\n" +
                " <item>\n" +
                "    <value>drama</value>\n" +
                "    <count>2</count>\n" +
                " </item>\n" +
                "</statistics>", xmlStatistics);
    }

    @Test
    public void testGenerateXmlStatistics_EmptyAttributeCounts() {
        String xmlStatistics = service.generateXmlStatistics(new HashMap<>());

        assertEquals("<statistics> \n</statistics>", xmlStatistics);
    }


}
