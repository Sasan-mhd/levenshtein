import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Main {


    public static void main(String[] args) {

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("sorted-wordlist-10000.txt"));
                Stream<String> lines = Files.lines(Path.of("wordlist-10000.txt"))
        ) {
            System.out.print("Enter your string:");
            String word = reader.readLine();

            lines
                    .map(eachWord -> new SimpleEntry<Integer, String>(LevenshteinMatrix.distance(word.toLowerCase(), eachWord.toLowerCase()), eachWord))
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(e -> {
                        try {
                            bufferedWriter.write(e.getKey().toString() + " " + e.getValue());
                            bufferedWriter.newLine();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}