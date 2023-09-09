/* (C)2023 */
package br.com.edu.dijkstra;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class Graph {

    private Map<String, Map<String, Double>> nodes;

    public Graph() {
        super();
    }

    public Graph(final Map<String, Map<String, Double>> nodes) {
        super();
        this.nodes = nodes;
    }

    public void save(final Path path) throws IOException {

        final var gson = new GsonBuilder().setPrettyPrinting().create();
        final var json = gson.toJson(this);

        Files.write(path, json.getBytes(), StandardOpenOption.CREATE);
    }

    public void load(final Path path) throws IOException {

        final var json = Files.readString(path);

        final var gson = new GsonBuilder().setPrettyPrinting().create();
        final var returned = gson.fromJson(json, Graph.class);
        this.nodes = returned.nodes;
    }

    public Map<String, Map<String, Double>> nodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "Grafo [nodes=" + nodes + "]";
    }
}
