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

package fr.univartois.cril.jkahypar.hypergraph;

import static fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder.createHypergraph;
import static fr.univartois.cril.jkahypar.hypergraph.UnweightedHyperedge.joining;

import java.util.List;

/**
 * The HypergraphFactory allows to create different hypergraphs used for test
 * purposes.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public final class HypergraphFactory {

    /**
     * Disables instantiation.
     */
    private HypergraphFactory() {
        throw new AssertionError("No HypergraphFactory instances for you!");
    }

    /**
     * Creates the unweighted hypergraph used as example in hMetis manual.
     *
     * @return The created hypergraph.
     */
    public static Hypergraph createUnweightedHypergraph() {
        return createHypergraph(7, 4)
                .withHyperedge(joining(1, 2))
                .withHyperedge(joining(List.of(1, 7, 5, 6)))
                .withHyperedge(joining(5, 6, 4))
                .withHyperedge(joining(List.of(2, 3, 4)))
                .build();
    }

    /**
     * Creates the hypergraph having weights only on its hyperedges used as example
     * in hMetis manual.
     *
     * @return The created hypergraph.
     */
    public static Hypergraph createHypergraphWithWeightsOnHyperedges() {
        return createHypergraph(7, 4)
                .withHyperedge(joining(1, 2).withWeight(2))
                .withHyperedge(joining(List.of(1, 7, 5, 6)).withWeight(3))
                .withHyperedge(joining(5, 6, 4).withWeight(8))
                .withHyperedge(joining(List.of(2, 3, 4)).withWeight(7))
                .build();
    }

    /**
     * Creates the hypergraph having weights only on its vertices used as example in
     * hMetis manual.
     *
     * @return The created hypergraph.
     */
    public static Hypergraph createHypergraphWithWeightsOnVertices() {
        return createHypergraph(7, 4)
                .withHyperedge(joining(1, 2))
                .withHyperedge(joining(List.of(1, 7, 5, 6)))
                .withHyperedge(joining(5, 6, 4))
                .withHyperedge(joining(List.of(2, 3, 4)))
                .withVertexWeight(1, 5)
                .withVertexWeight(2, 1)
                .withVertexWeight(3, 8)
                .withVertexWeight(4, 7)
                .withVertexWeight(5, 3)
                .withVertexWeight(6, 9)
                .withVertexWeight(7, 3)
                .build();
    }

    /**
     * Creates the hypergraph having weights on both its hyperedges and vertices
     * used as example in hMetis manual.
     *
     * @return The created hypergraph.
     */
    public static Hypergraph createHypergraphWithWeightsOnHyperedgesAndVertices() {
        return createHypergraph(7, 4)
                .withHyperedge(joining(1, 2).withWeight(2))
                .withHyperedge(joining(List.of(1, 7, 5, 6)).withWeight(3))
                .withHyperedge(joining(5, 6, 4).withWeight(8))
                .withHyperedge(joining(List.of(2, 3, 4)).withWeight(7))
                .withVertexWeight(1, 5)
                .withVertexWeight(2, 1)
                .withVertexWeight(3, 8)
                .withVertexWeight(4, 7)
                .withVertexWeight(5, 3)
                .withVertexWeight(6, 9)
                .withVertexWeight(7, 3)
                .build();
    }

}
