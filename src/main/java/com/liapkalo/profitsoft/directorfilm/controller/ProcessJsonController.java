package com.liapkalo.profitsoft.directorfilm.controller;

import com.liapkalo.profitsoft.directorfilm.service.GenerateXMLStatisticService;
import com.liapkalo.profitsoft.directorfilm.service.ParseJsonService;
import com.liapkalo.profitsoft.directorfilm.service.WriteXMLToFileService;
import com.liapkalo.profitsoft.directorfilm.service.impl.GenerateXMLStatisticServiceImpl;
import com.liapkalo.profitsoft.directorfilm.service.impl.ParseJsonServiceImpl;
import com.liapkalo.profitsoft.directorfilm.service.impl.WriteXMLToFileServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProcessJsonController {

    static ParseJsonService parseJsonService = new ParseJsonServiceImpl();
    static GenerateXMLStatisticService generateXMLStatisticService = new GenerateXMLStatisticServiceImpl();
    static WriteXMLToFileService writeXMLToFileService = new WriteXMLToFileServiceImpl();

    public static void processJson(String path, String attribute) {
        Map<String, Integer> attributeCounts = new HashMap<>();

        parseJsonService.parseFiles(path, attribute, attributeCounts);
        writeXMLToFileService.writeXmlToFile(attribute, generateXMLStatisticService.generateXmlStatistics(attributeCounts));
    }
}
