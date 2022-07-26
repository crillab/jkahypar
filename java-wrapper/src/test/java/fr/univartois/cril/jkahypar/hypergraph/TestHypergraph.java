/**
 * JKaHyPar - Java binding for the KaHyPar hypergraph partitioning framework.
 * Copyright (c) 2020-2022 - Univ Artois & CNRS.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package fr.univartois.cril.jkahypar.hypergraph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The TestHypergraph is a JUnit test case for testing manipulations on
 * instances of {@link Hypergraph}.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public final class TestHypergraph {

    /**
     * Test method for unweighted hypergraphs.
     *
     * @see UnweightedHypergraph
     */
    @Test
    @DisplayName("Unweighted hypergraphs are properly built")
    void testUnweightedHypergraph() {
        var hypergraph = HypergraphFactory.createUnweightedHypergraph();
        testUnweightedHypergraph(hypergraph);
    }

    /**
     * Checks that the given hypergraph is the unweighted hypergraph that is expected.
     *
     * @param hypergraph The hypergraph to check
     */
    public static void testUnweightedHypergraph(Hypergraph hypergraph) {
        // Checking the class of the hypergraph.
        assertEquals(UnweightedHypergraph.class, hypergraph.getClass());

        // Checking the vertices.
        assertEquals(7, hypergraph.getNumberOfVertices());
        assertNull(hypergraph.getVertexWeights());

        // Checking the internal representation of the hyperedges.
        assertEquals(4, hypergraph.getNumberOfHyperedges());
        assertNull(hypergraph.getHyperedgeWeights());
        assertArrayEquals(new long[] { 0, 2, 6, 9, 12 }, hypergraph.getHyperedgeIndices());
        assertArrayEquals(new int[] { 0, 1, 0, 6, 4, 5, 4, 5, 3, 1, 2, 3 },
                hypergraph.getHyperedgeVertices());

        // Checking the views of the hyperedges.
        var hyperedge0 = hypergraph.getHyperedge(0);
        assertEquals(UnweightedHyperedge.class, hyperedge0.getClass());
        assertEquals(2, hyperedge0.size());
        assertArrayEquals(new int[] { 1, 2 }, hyperedge0.getVertices());

        var hyperedge1 = hypergraph.getHyperedge(1);
        assertEquals(UnweightedHyperedge.class, hyperedge1.getClass());
        assertEquals(4, hyperedge1.size());
        assertArrayEquals(new int[] { 1, 7, 5, 6 }, hyperedge1.getVertices());

        var hyperedge2 = hypergraph.getHyperedge(2);
        assertEquals(UnweightedHyperedge.class, hyperedge2.getClass());
        assertEquals(3, hyperedge2.size());
        assertArrayEquals(new int[] { 5, 6, 4 }, hyperedge2.getVertices());

        var hyperedge3 = hypergraph.getHyperedge(3);
        assertEquals(UnweightedHyperedge.class, hyperedge3.getClass());
        assertEquals(3, hyperedge3.size());
        assertArrayEquals(new int[] { 2, 3, 4 }, hyperedge3.getVertices());

        // Checking the hMetis representation.
        var string = "4 7" + System.lineSeparator()
                   + "1 2" + System.lineSeparator()
                   + "1 7 5 6" + System.lineSeparator()
                   + "5 6 4" + System.lineSeparator()
                   + "2 3 4" + System.lineSeparator();
        assertEquals(string, hypergraph.toString());
    }

    /**
     * Test method for hypergraphs with weights on hyperedges.
     *
     * @see WeightedHyperedgesHypergraph
     */
    @Test
    @DisplayName("Hypergraphs with weights on hyperedges are properly built")
    void testHypergraphWithWeightsOnHyperedges() {
        var hypergraph = HypergraphFactory.createHypergraphWithWeightsOnHyperedges();
        testHypergraphWithWeightsOnHyperedges(hypergraph);
    }

    /**
     * Checks that the given hypergraph is the hypergraph with weights on hyperedges
     * that is expected.
     *
     * @param hypergraph The hypergraph to check
     */
    public static void testHypergraphWithWeightsOnHyperedges(Hypergraph hypergraph) {
        // Checking the class of the hypergraph.
        assertEquals(WeightedHyperedgesHypergraph.class, hypergraph.getClass());

        // Checking the vertices.
        assertEquals(7, hypergraph.getNumberOfVertices());
        assertNull(hypergraph.getVertexWeights());

        // Checking the internal representation of the hyperedges.
        assertEquals(4, hypergraph.getNumberOfHyperedges());
        assertArrayEquals(new int[] { 2, 3, 8, 7 }, hypergraph.getHyperedgeWeights());
        assertArrayEquals(new long[] { 0, 2, 6, 9, 12 }, hypergraph.getHyperedgeIndices());
        assertArrayEquals(new int[] { 0, 1, 0, 6, 4, 5, 4, 5, 3, 1, 2, 3 },
                hypergraph.getHyperedgeVertices());

        // Checking the views of the hyperedges.
        var hyperedge0 = hypergraph.getHyperedge(0);
        assertEquals(WeightedHyperedge.class, hyperedge0.getClass());
        assertEquals(2, hyperedge0.size());
        assertArrayEquals(new int[] { 1, 2 }, hyperedge0.getVertices());
        assertEquals(2, ((WeightedHyperedge) hyperedge0).getWeight());

        var hyperedge1 = hypergraph.getHyperedge(1);
        assertEquals(WeightedHyperedge.class, hyperedge1.getClass());
        assertEquals(4, hyperedge1.size());
        assertArrayEquals(new int[] { 1, 7, 5, 6 }, hyperedge1.getVertices());
        assertEquals(3, ((WeightedHyperedge) hyperedge1).getWeight());

        var hyperedge2 = hypergraph.getHyperedge(2);
        assertEquals(WeightedHyperedge.class, hyperedge2.getClass());
        assertEquals(3, hyperedge2.size());
        assertArrayEquals(new int[] { 5, 6, 4 }, hyperedge2.getVertices());
        assertEquals(8, ((WeightedHyperedge) hyperedge2).getWeight());

        var hyperedge3 = hypergraph.getHyperedge(3);
        assertEquals(WeightedHyperedge.class, hyperedge3.getClass());
        assertEquals(3, hyperedge3.size());
        assertArrayEquals(new int[] { 2, 3, 4 }, hyperedge3.getVertices());
        assertEquals(7, ((WeightedHyperedge) hyperedge3).getWeight());

        // Checking the hMetis representation.
        var string = "4 7 1" + System.lineSeparator()
                   + "2 1 2" + System.lineSeparator()
                   + "3 1 7 5 6" + System.lineSeparator()
                   + "8 5 6 4" + System.lineSeparator()
                   + "7 2 3 4" + System.lineSeparator();
        assertEquals(string, hypergraph.toString());
    }

    /**
     * Test method for hypergraphs with weights on vertices.
     *
     * @see WeightedVerticesHypergraph
     */
    @Test
    @DisplayName("Hypergraphs with weights on vertices are properly built")
    void testHypergraphWithWeightsOnVertices() {
        var hypergraph = HypergraphFactory.createHypergraphWithWeightsOnVertices();
        testHypergraphWithWeightsOnVertices(hypergraph);
    }

    /**
     * Checks that the given hypergraph is the hypergraph with weights on vertices
     * that is expected.
     *
     * @param hypergraph The hypergraph to check
     */
    public static void testHypergraphWithWeightsOnVertices(Hypergraph hypergraph) {
        // Checking the class of the hypergraph.
        assertEquals(WeightedVerticesHypergraph.class, hypergraph.getClass());

        // Checking the vertices.
        assertEquals(7, hypergraph.getNumberOfVertices());
        assertArrayEquals(new int[] { 5, 1, 8, 7, 3, 9, 3 }, hypergraph.getVertexWeights());

        // Checking the internal representation of the hyperedges.
        assertEquals(4, hypergraph.getNumberOfHyperedges());
        assertNull(hypergraph.getHyperedgeWeights());
        assertArrayEquals(new long[] { 0, 2, 6, 9, 12 }, hypergraph.getHyperedgeIndices());
        assertArrayEquals(new int[] { 0, 1, 0, 6, 4, 5, 4, 5, 3, 1, 2, 3 },
                hypergraph.getHyperedgeVertices());

        // Checking the views of the hyperedges.
        var hyperedge0 = hypergraph.getHyperedge(0);
        assertEquals(UnweightedHyperedge.class, hyperedge0.getClass());
        assertEquals(2, hyperedge0.size());
        assertArrayEquals(new int[] { 1, 2 }, hyperedge0.getVertices());

        var hyperedge1 = hypergraph.getHyperedge(1);
        assertEquals(UnweightedHyperedge.class, hyperedge1.getClass());
        assertEquals(4, hyperedge1.size());
        assertArrayEquals(new int[] { 1, 7, 5, 6 }, hyperedge1.getVertices());

        var hyperedge2 = hypergraph.getHyperedge(2);
        assertEquals(UnweightedHyperedge.class, hyperedge2.getClass());
        assertEquals(3, hyperedge2.size());
        assertArrayEquals(new int[] { 5, 6, 4 }, hyperedge2.getVertices());

        var hyperedge3 = hypergraph.getHyperedge(3);
        assertEquals(UnweightedHyperedge.class, hyperedge3.getClass());
        assertEquals(3, hyperedge3.size());
        assertArrayEquals(new int[] { 2, 3, 4 }, hyperedge3.getVertices());

        // Checking the hMetis representation.
        var string = "4 7 10" + System.lineSeparator()
                   + "1 2" + System.lineSeparator()
                   + "1 7 5 6" + System.lineSeparator()
                   + "5 6 4" + System.lineSeparator()
                   + "2 3 4" + System.lineSeparator()
                   + "5" + System.lineSeparator()
                   + "1" + System.lineSeparator()
                   + "8" + System.lineSeparator()
                   + "7" + System.lineSeparator()
                   + "3" + System.lineSeparator()
                   + "9" + System.lineSeparator()
                   + "3" + System.lineSeparator();
        assertEquals(string, hypergraph.toString());
    }

    /**
     * Test method for hypergraphs with weights on hyperedges and vertices.
     *
     * @see WeightedVerticesHypergraph
     * @see WeightedHyperedgesHypergraph
     */
    @Test
    @DisplayName("Hypergraphs with weights on hyperedges and vertices are properly built")
    void testHypergraphWithWeightsOnHyperedgesAndVertices() {
        var hypergraph = HypergraphFactory.createHypergraphWithWeightsOnHyperedgesAndVertices();
        testHypergraphWithWeightsOnHyperedgesAndVertices(hypergraph);
    }

    /**
     * Checks that the given hypergraph is the hypergraph with weights on hyperedges
     * and vertices that is expected.
     *
     * @param hypergraph The hypergraph to check
     */
    public static void testHypergraphWithWeightsOnHyperedgesAndVertices(Hypergraph hypergraph) {
        // Checking the class of the hypergraph.
        assertEquals(WeightedVerticesHypergraph.class, hypergraph.getClass());

        // Checking the vertices.
        assertEquals(7, hypergraph.getNumberOfVertices());
        assertArrayEquals(new int[] { 5, 1, 8, 7, 3, 9, 3 }, hypergraph.getVertexWeights());

        // Checking the internal representation of the hyperedges.
        assertEquals(4, hypergraph.getNumberOfHyperedges());
        assertArrayEquals(new int[] { 2, 3, 8, 7 }, hypergraph.getHyperedgeWeights());
        assertArrayEquals(new long[] { 0, 2, 6, 9, 12 }, hypergraph.getHyperedgeIndices());
        assertArrayEquals(new int[] { 0, 1, 0, 6, 4, 5, 4, 5, 3, 1, 2, 3 },
                hypergraph.getHyperedgeVertices());

        // Checking the views of the hyperedges.
        var hyperedge0 = hypergraph.getHyperedge(0);
        assertEquals(WeightedHyperedge.class, hyperedge0.getClass());
        assertEquals(2, hyperedge0.size());
        assertArrayEquals(new int[] { 1, 2 }, hyperedge0.getVertices());
        assertEquals(2, ((WeightedHyperedge) hyperedge0).getWeight());

        var hyperedge1 = hypergraph.getHyperedge(1);
        assertEquals(WeightedHyperedge.class, hyperedge1.getClass());
        assertEquals(4, hyperedge1.size());
        assertArrayEquals(new int[] { 1, 7, 5, 6 }, hyperedge1.getVertices());
        assertEquals(3, ((WeightedHyperedge) hyperedge1).getWeight());

        var hyperedge2 = hypergraph.getHyperedge(2);
        assertEquals(WeightedHyperedge.class, hyperedge2.getClass());
        assertEquals(3, hyperedge2.size());
        assertArrayEquals(new int[] { 5, 6, 4 }, hyperedge2.getVertices());
        assertEquals(8, ((WeightedHyperedge) hyperedge2).getWeight());

        var hyperedge3 = hypergraph.getHyperedge(3);
        assertEquals(WeightedHyperedge.class, hyperedge3.getClass());
        assertEquals(3, hyperedge3.size());
        assertArrayEquals(new int[] { 2, 3, 4 }, hyperedge3.getVertices());
        assertEquals(7, ((WeightedHyperedge) hyperedge3).getWeight());

        // Checking the hMetis representation.
        var string = "4 7 11" + System.lineSeparator()
                   + "2 1 2" + System.lineSeparator()
                   + "3 1 7 5 6" + System.lineSeparator()
                   + "8 5 6 4" + System.lineSeparator()
                   + "7 2 3 4" + System.lineSeparator()
                   + "5" + System.lineSeparator()
                   + "1" + System.lineSeparator()
                   + "8" + System.lineSeparator()
                   + "7" + System.lineSeparator()
                   + "3" + System.lineSeparator()
                   + "9" + System.lineSeparator()
                   + "3" + System.lineSeparator();
        assertEquals(string, hypergraph.toString());
    }

}
