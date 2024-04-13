package com.liapkalo.profitsoft.directorfilm.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liapkalo.profitsoft.directorfilm.entity.Film;
import com.liapkalo.profitsoft.directorfilm.service.ParseJsonService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static com.liapkalo.profitsoft.directorfilm.utils.JsonParseUtils.validateJson;

@Slf4j
@Setter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ParseJsonServiceImpl implements ParseJsonService {

    static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parses all files in the specified directory and updates the attribute counts based on the given attribute.
     *
     * @param path            The path to the directory containing the files to be parsed.
     * @param attribute       The attribute whose occurrences are to be counted.
     * @param attributeCounts A map containing attribute values as keys and their respective counts as values.
     * @throws RuntimeException if an error occurs while parsing the files.
     */
    public void parseFiles(String path, String attribute, Map<String, Integer> attributeCounts) {
        log.info("Parsing files in directory: {}", path);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            directoryStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    parseFile(filePath.toFile(), attribute, attributeCounts);
                }
            });
            log.info("Files parsed successfully.");

        } catch (IOException e) {
            log.error("Error parsing files in directory: {}", path, e);
            System.exit(0);
        }
    }

    /**
     * Parses a single file and updates the attribute counts based on the given attribute.
     *
     * @param file            The file to be parsed.
     * @param attribute       The attribute to be extracted from the file.
     * @param attributeCounts A map containing attribute values as keys and their respective counts as values.
     */
    public void parseFile(File file, String attribute, Map<String, Integer> attributeCounts) {
        log.info("Parsing file: {}", file.getPath());

        try (JsonParser parser = objectMapper.getFactory().createParser(file)) {
            validateJson(parser);

            while (parser.nextToken() != JsonToken.END_ARRAY) {
                processFilmAttribute(parser.readValueAs(Film.class), attribute, attributeCounts);
            }
            log.info("File parsed successfully: {}", file.getPath());

        } catch (IOException e) {
            log.error("Error parsing file: {}", file.getPath(), e);
            System.exit(0);
        }
    }

    /**
     * Processes the attribute of a film and updates the attribute counts accordingly.
     *
     * @param film            The film to process.
     * @param attribute       The attribute to be extracted from the film.
     * @param attributeCounts A map containing attribute values as keys and their respective counts as values.
     */
    private void processFilmAttribute(Film film, String attribute, Map<String, Integer> attributeCounts) {
        if (film.getAttribute(attribute) instanceof List<?>) {
            processAttributeList((List<?>) film.getAttribute(attribute), attributeCounts);
        } else {
            String attributeValue = String.valueOf(film.getAttribute(attribute));
            attributeCounts.put(attributeValue, attributeCounts.getOrDefault(attributeValue, 0) + 1);
        }
    }

    /**
     * Processes a list of attributes and updates the attribute counts accordingly.
     *
     * @param attributeList   The list of attributes to process.
     * @param attributeCounts A map containing attribute values as keys and their respective counts as values.
     */
    private void processAttributeList(List<?> attributeList, Map<String, Integer> attributeCounts) {
        attributeList.stream()
                .map(Object::toString)
                .forEach(attributeValue -> attributeCounts.put(attributeValue, attributeCounts.getOrDefault(attributeValue, 0) + 1));
    }

}
