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

/**
 * The HypergraphBuilder defines an easy and natural interface for building hypergraphs.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public interface HypergraphBuilder {

    /**
     * Creates a new HypergraphBuilder for a hypergraph for which the size is unknown.
     * If you know the size of the hypergraph in advance, prefer using
     * {@link #createHypergraph(int, int)} for better efficiency.
     *
     * @return The created HypergraphBuilder.
     *
     * @see #createHypergraph(int, int)
     *
     * @since 0.2.0
     */
    public static HypergraphBuilder createHypergraph() {
        return new UnknownSizeHypergraphBuilder();
    }

    /**
     * Creates a new HypergraphBuilder for a hypergraph for which the size is known.
     * This method should always be used when the size is known, as it allows to manage
     * internal data structures more efficiently than when using
     * {@link #createHypergraph()}.
     *
     * @param nbVertices The number of vertices in the hypergraph.
     * @param nbHyperedges The number of hyperedges in the hypergraph.
     *
     * @return The created HypergraphBuilder.
     */
    public static HypergraphBuilder createHypergraph(int nbVertices, int nbHyperedges) {
        return new KnownSizeHypergraphBuilder(nbVertices, nbHyperedges);
    }

    /**
     * Gives the number of vertices in the hypergraph.
     *
     * @return The number of vertices.
     */
    int getNumberOfVertices();

    /**
     * Sets the weight of a vertex in the hypergraph.
     *
     * @param vertex The vertex to set the weight of.
     * @param weight The weight of the vertex.
     *
     * @return This builder.
     */
    HypergraphBuilder withVertexWeight(int vertex, int weight);

    /**
     * Gives the number of hyperedges in the hypergraph.
     *
     * @return The number of hyperedges.
     */
    int getNumberOfHyperedges();

    /**
     * Gives the indices at which the vertices of each hyperedge start in the array
     * returned by {@link #getHyperedgeVertices()}.
     *
     * @return The indices of the vertices for each hyperedge.
     *
     * @see #getHyperedgeVertices()
     */
    long[] getHyperedgeIndices();

    /**
     * Gives the vertices of the hyperedges in the hypergraph.
     *
     * @return The vertices of the different hyperedges.
     *
     * @see #getHyperedgeIndices()
     */
    int[] getHyperedgeVertices();

    /**
     * Adds an {@link UnweightedHyperedge} to the hypergraph.
     *
     * @param hyperedge The hyperedge to add.
     *
     * @return This builder.
     */
    HypergraphBuilder withHyperedge(UnweightedHyperedge hyperedge);

    /**
     * Adds a {@link WeightedHyperedge} to the hypergraph.
     *
     * @param hyperedge The hyperedge to add.
     *
     * @return This builder.
     */
    HypergraphBuilder withHyperedge(WeightedHyperedge hyperedge);

    /**
     * Creates the hypergraph built by this builder.
     *
     * @return The built hypergraph.
     */
    Hypergraph build();

}
