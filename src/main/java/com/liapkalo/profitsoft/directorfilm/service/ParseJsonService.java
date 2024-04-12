package com.liapkalo.profitsoft.directorfilm.service;

import java.io.File;
import java.util.Map;

public interface ParseJsonService {

    /**
     * Parses all files in the specified directory and updates the attribute counts based on the given attribute.
     *
     * @param path            The path to the directory containing the files to be parsed.
     * @param attribute       The attribute to be extracted from the files.
     * @param attributeCounts A map containing attribute values as keys and their respective counts as values.
     */
    void parseFiles(String path, String attribute, Map<String, Integer> attributeCounts);

    /**
     * Parses a single file and updates the attribute counts based on the given attribute.
     *
     * @param file            The file to be parsed.
     * @param attribute       The attribute to be extracted from the file.
     * @param attributeCounts A map containing attribute values as keys and their respective counts as values.
     */
    void parseFile(File file, String attribute, Map<String, Integer> attributeCounts);

}
