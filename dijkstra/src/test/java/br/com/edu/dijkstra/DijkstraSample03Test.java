/* (C)2023 */
package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraSample03Test {

    Dijkstra dijkstra;

    @BeforeEach
    void setup() throws IOException {
        final var graph = new Graph();
        graph.load(Path.of("src/main/resources/v3.txt"));
        dijkstra = new Dijkstra(graph);
    }

    @Test
    void testCostInicioToFim() throws IOException {
        final var response = dijkstra.bestWay("INICIO", "FIM");
        System.out.println(response);
        assertNotNull(response);
        assertEquals(60d, response.cost());

        final var expected = new String[] {"INICIO", "A", "B", "FIM"};
        assertArrayEquals(expected, response.bestWay().toArray(new String[0]));
    }
}
