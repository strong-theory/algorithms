/* (C)2023 */
package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraSample01Test {

    Dijkstra dijkstra;

    @BeforeEach
    void setup() throws IOException {
        final Graph graph = new Graph();
        graph.load(Path.of("src/main/resources/v1.txt"));
        dijkstra = new Dijkstra(graph);
    }

    @Test
    void testCost0To1() throws IOException {
        final var response = dijkstra.bestWay("INICIO", "A");
        assertNotNull(response);
        assertEquals(5d, response.cost());
        final var expected = new String[] {"INICIO", "B", "A"};

        assertArrayEquals(expected, response.bestWay().toArray(new String[0]));
    }

    @Test
    void testCost0To3() throws IOException {
        final var response = dijkstra.bestWay("INICIO", "FIM");
        assertNotNull(response);
        assertEquals(6d, response.cost());
        final var expected = new String[] {"INICIO", "B", "A", "FIM"};
        assertArrayEquals(expected, response.bestWay().toArray(new String[0]));
    }
}
