package datatypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadMap {
    public static void Generate() throws IOException {
        generateIndices();
        generateConnections();
    }

    private static void generateConnections() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Data.landPath));
        String row;
        while ((row = br.readLine()) != null) {
            String[] data = row.split(",");
            Data.connections.put(Data.name_to_ix.get(data[0]), toIXList(removeFirst(data)));  //this part is messy but watever
        }
    }

    private static List<Integer> toIXList(String[] data) {
        List<Integer> connections = new ArrayList<>();
        for (String s : data) {
            connections.add(Data.name_to_ix.get(s));
        }
        return connections;
    }

    private static String[] removeFirst(String[] data) {
        return Arrays.copyOfRange(data, 1, data.length);
    }

    private static void generateIndices() throws IOException {
        String[] names = Files.readString(Paths.get(Data.landPath), StandardCharsets.US_ASCII).replace("\r\n", ",").split(",");
        int i = 0;
        for (String country : names) {
            if (!Data.countries.contains(country)) {
                Data.countries.add(country);         // adds and assigns every unique country to a index (for faster search algorithms)
                Data.name_to_ix.put(country, i);
                Data.ix_to_name.put(i, country);
                i++;
            }
        }
    }
}
