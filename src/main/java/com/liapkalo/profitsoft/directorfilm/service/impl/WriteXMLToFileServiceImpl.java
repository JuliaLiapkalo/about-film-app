package com.liapkalo.profitsoft.directorfilm.service.impl;

import com.liapkalo.profitsoft.directorfilm.service.WriteXMLToFileService;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class WriteXMLToFileServiceImpl implements WriteXMLToFileService {

    /**
     * Writes the XML content to a file named "statistics_by_{attribute}.xml" in the current directory.
     * If the file already exists, it will be overwritten.
     *
     * @param attribute  The attribute based on which the statistics are generated.
     * @param xmlContent The XML content to be written to the file.
     * @throws RuntimeException if an error occurs while writing the XML file.
     */
    @Override
    public void writeXmlToFile(String attribute, String xmlContent) {
        try (FileWriter fileWriter = new FileWriter(String.format("statistics_by_%s.xml", attribute))) {
            fileWriter.write(xmlContent);
            log.info("XML file with '{}' attribute created successfully.", attribute);

        } catch (IOException e) {
            log.error("Error writing XML file with '{}' attribute: {}", attribute, e.getMessage());

        }
    }

}
