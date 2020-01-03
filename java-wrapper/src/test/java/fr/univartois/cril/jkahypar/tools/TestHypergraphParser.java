/**
 * JKaHyPar - Java binding for the KaHyPar hypergraph partitioning framework.
 * Copyright (c) 2020 - Univ Artois & CNRS.
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

package fr.univartois.cril.jkahypar.tools;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.univartois.cril.jkahypar.hypergraph.Hypergraph;
import fr.univartois.cril.jkahypar.hypergraph.TestHypergraph;

/**
 * The TestHypergraphParser is a JUnit test case for testing the
 * {@link HypergraphParser}.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public final class TestHypergraphParser {

    /**
     * Tests that the parser successfully parses an unweighted hypergraph.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    @Test
    @DisplayName("Unweighted hypergraphs are properly read")
    public void testReadUnweightedHypergraph() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        var hypergraph = readHypergraph("unweighted.hgr");
        TestHypergraph.testUnweightedHypergraph(hypergraph);
    }

    /**
     * Tests that the parser successfully parses a hypergraph with weights on
     * hyperedges.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    @Test
    @DisplayName("Hypergraphs with weights on hyperedges are properly read")
    public void testReadHypergraphWithWeightsOnHyperedges() throws IOException {
        var hypergraph = readHypergraph("weighted-hyperedges.hgr");
        TestHypergraph.testHypergraphWithWeightsOnHyperedges(hypergraph);
    }

    /**
     * Tests that the parser successfully parses a hypergraph with weights on
     * vertices.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    @Test
    @DisplayName("Hypergraphs with weights on vertices are properly read")
    public void testReadHypergraphWithWeightsOnVertices() throws IOException {
        var hypergraph = readHypergraph("weighted-vertices.hgr");
        TestHypergraph.testHypergraphWithWeightsOnVertices(hypergraph);
    }

    /**
     * Tests that the parser successfully parses a hypergraph with weights on
     * hyperedges and vertices.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    @Test
    @DisplayName("Hypergraphs with weights on hyperedges and vertices are properly read")
    public void testReadHypergraphWithWeightsOnHyperedgesAndVertices() throws IOException {
        var hypergraph = readHypergraph("weighted-hyperedges-vertices.hgr");
        TestHypergraph.testHypergraphWithWeightsOnHyperedgesAndVertices(hypergraph);
    }

    /**
     * Reads a hypergraph from the given file.
     *
     * @param fileName The name of the file to read.
     *
     * @return The read hypergraph.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    private static Hypergraph readHypergraph(String fileName) throws IOException {
        try (var parser = new HypergraphParser(
                TestHypergraphParser.class.getResourceAsStream("/hypergraphs/" + fileName))) {
            parser.parse();
            return parser.getHypergraph();
        }
    }

}
