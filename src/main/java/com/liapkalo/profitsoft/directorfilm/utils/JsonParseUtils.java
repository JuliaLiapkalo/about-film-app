package com.liapkalo.profitsoft.directorfilm.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonParseUtils {

    /**
     * Validates whether the provided JSON parser starts with an array.
     *
     * @param parser The JSON parser to be validated.
     * @throws IOException If an I/O error occurs during JSON parsing.
     */
    public static void validateJson(JsonParser parser) throws IOException {
        if (parser.nextToken() != JsonToken.START_ARRAY) {
            log.error("Input JSON is not an array.");
        }
    }
}
