package com.liapkalo.profitsoft.directorfilm.perfomence;

import com.liapkalo.profitsoft.directorfilm.service.ParseJsonService;
import com.liapkalo.profitsoft.directorfilm.service.impl.ParseJsonServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class JsonParseMultiThreadTest {

    private final ParseJsonService parseJsonService = new ParseJsonServiceImpl();

    @Test
    public void testPerformance() {
        String path = "./src/test/resources/films";
        String attribute = "genre";

        int[] threadPoolSizes = {1, 2, 4, 8};

        for (int poolSize : threadPoolSizes) {
            long startTime = System.nanoTime();
            testWithThreadPool(path, attribute, poolSize);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // convert to milliseconds
            System.out.println("Pool size " + poolSize + ": " + duration + " milliseconds");
        }
    }

    private void testWithThreadPool(String path, String attribute, int poolSize) {

        Map<String, Integer> attributeCounts = new HashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            directoryStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    parseJsonService.parseFile(filePath.toFile(), attribute, attributeCounts);
                }
            });
            executor.shutdown();

        } catch (IOException e) {
            throw new RuntimeException("Error parsing files in directory: " + path, e);
        }

        assertFalse(attributeCounts.isEmpty());
    }
}
