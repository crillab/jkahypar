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

import static fr.univartois.cril.jkahypar.hypergraph.UnweightedHyperedge.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The TestHyperedge is a JUnit test case for testing implementations of
 * {@link Hyperedge}.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
final class TestHyperedge {

    /**
     * Test method for unweighted hyperedges.
     *
     * @see UnweightedHyperedge
     */
    @Test
    @DisplayName("Unweighted hyperedges have the expected behavior")
    void testUnweightedHyperedge() {
        var hyperedge = joining(2, 7, 9, 12, 15);

        assertEquals(5, hyperedge.size());

        var vertices = hyperedge.getVertices();
        assertEquals(5, vertices.length);
        assertEquals(2, vertices[0]);
        assertEquals(7, vertices[1]);
        assertEquals(9, vertices[2]);
        assertEquals(12, vertices[3]);
        assertEquals(15, vertices[4]);

        assertEquals("2 7 9 12 15", hyperedge.toString());
    }

    /**
     * Test method for weighted hyperedges.
     *
     * @see WeightedHyperedge
     */
    @Test
    @DisplayName("Weighted hyperedges have the expected behavior")
    void testWeightedHyperedge() {
        var hyperedge = joining(1, 3, 5, 8).withWeight(6);

        assertEquals(4, hyperedge.size());

        var vertices = hyperedge.getVertices();
        assertEquals(4, vertices.length);
        assertEquals(1, vertices[0]);
        assertEquals(3, vertices[1]);
        assertEquals(5, vertices[2]);
        assertEquals(8, vertices[3]);

        assertEquals(6, hyperedge.getWeight());

        assertEquals("6 1 3 5 8", hyperedge.toString());
    }

}
