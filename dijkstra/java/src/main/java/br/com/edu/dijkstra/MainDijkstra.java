/* (C)2023 */
package br.com.edu.dijkstra;

import java.io.IOException;
import java.nio.file.Path;

public class MainDijkstra {

    public static void main(String[] args) throws IOException {
        test1();
        test2();
    }

    private static void test1() throws IOException {
        Graph graph = new Graph();
        graph.load(Path.of("src/main/resources/v1.txt"));

        final var v2 = new Dijkstra(graph);
        final var response = v2.bestWay("INICIO", "A");

        System.out.println(response);
    }

    private static void test2() throws IOException {
        Graph graph = new Graph();
        graph.load(Path.of("src/main/resources/v1.txt"));

        final var v2 = new Dijkstra(graph);
        final var response = v2.bestWay("INICIO", "FIM");

        System.out.println(response);
    }
}
