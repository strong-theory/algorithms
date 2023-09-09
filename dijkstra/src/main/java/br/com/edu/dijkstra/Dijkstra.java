/* (C)2023 */
package br.com.edu.dijkstra;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    private final Map<String, Double> costs = new HashMap<>();

    private final Map<String, String> parents = new HashMap<>();

    private final Graph graph;

    private Set<String> processed = new HashSet<>();

    public Dijkstra(final Graph graph) {
        super();
        this.graph = graph;
    }

    public Response bestWay(final String start, final String end) {

        final var nodes = graph.nodes();

        // start with all costs infinite
        nodes.keySet().forEach(key -> {
            if (!key.equals(start)) {
                costs.put(key, Double.POSITIVE_INFINITY);
            }
        });

        nodes.keySet().forEach(key -> {
            if (!key.equals(start)) {
                parents.put(key, start);
            }
        });

        costs.putAll(nodes.get(start));

        var node = neighborLowestCost();

        while (node != null) {
            for (final var neighbor : nodes.get(node).keySet()) {
                final var neighborCost = costs.get(neighbor);

                final var nodeCost = costs.getOrDefault(node, Double.POSITIVE_INFINITY);
                double totalValue;
                if (nodeCost == Double.POSITIVE_INFINITY) {
                    totalValue = nodes.get(node).get(neighbor);
                } else {
                    totalValue = nodes.get(node).get(neighbor) + nodeCost;
                }

                if (totalValue < neighborCost) {
                    costs.put(neighbor, totalValue);
                    parents.put(neighbor, node);
                }
            }
            processed.add(node);
            node = neighborLowestCost();
        }

        final List<String> bestWay = new LinkedList<>();

        node = end;
        bestWay.add(node);
        while (node != start) {
            final var parent = parents.get(node);
            node = parent;
            bestWay.add(node);
        }

        Collections.reverse(bestWay);
        return new Response(costs.get(end), bestWay);
    }

    private String neighborLowestCost() {
        Double lowestCost = Double.POSITIVE_INFINITY;
        String lowestCostNode = null;
        for (final var node : costs.entrySet()) {
            final var cost = node.getValue();
            if (cost < lowestCost && !processed.contains(node.getKey())) {
                lowestCost = cost;
                lowestCostNode = node.getKey();
            }
        }

        return lowestCostNode;
    }
}
