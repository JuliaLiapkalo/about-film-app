package com.liapkalo.profitsoft.directorfilm.service;

public interface WriteXMLToFileService {

    /**
     * Writes the XML content to a file named "statistics_by_{attribute}.xml" in the current directory.
     * If the file already exists, it will be overwritten.
     *
     * @param attribute  The attribute based on which the statistics are generated.
     * @param xmlContent The XML content to be written to the file.
     */
    void writeXmlToFile(String attribute, String xmlContent);
}
