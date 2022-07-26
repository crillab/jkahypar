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

import java.util.Arrays;

/**
 * The HypergraphBuilder provides an easy and natural interface for building hypergraphs.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public final class HypergraphBuilder {

    /**
     * The number of vertices in the hypergraph.
     */
    private final int numberOfVertices;

    /**
     * The weights of the vertices.
     * If vertices are not weighted, this array remains {@code null}.
     */
    private int[] vertexWeights;

    /**
     * The number of hyperedges in the hypergraph.
     */
    private final int numberOfHyperedges;

    /**
     * The weights of the hyperedges.
     * If hyperedges are not weighted, this array remains {@code null}.
     */
    private int[] hyperedgeWeights;

    /**
     * The indices at which the vertices of each hyperedge start in
     * {@link #hyperedgeVertices}.
     */
    private final long[] hyperedgeIndices;

    /**
     * The vertices of the different hyperedges.
     */
    private int[] hyperedgeVertices;

    /**
     * The index of the current hyperedge.
     */
    private int hyperedgeIndex;

    /**
     * The index at which the vertices of the current hyperedge start in
     * {@link #hyperedgeVertices}.
     */
    private int hyperedgeVerticesIndex;

    /**
     * Creates a new HypergraphBuilder.
     *
     * @param nbVertices The number of vertices in the hypergraph.
     * @param nbHyperedges The number of hyperedges in the hypergraph.
     */
    private HypergraphBuilder(int nbVertices, int nbHyperedges) {
        this.numberOfVertices = nbVertices;
        this.numberOfHyperedges = nbHyperedges;
        this.hyperedgeIndices = new long[nbHyperedges + 1];
        this.hyperedgeVertices = new int[nbHyperedges << 1];
    }

    /**
     * Creates a new HypergraphBuilder.
     *
     * @param nbVertices The number of vertices in the hypergraph.
     * @param nbHyperedges The number of hyperedges in the hypergraph.
     *
     * @return The created HypergraphBuilder.
     */
    public static HypergraphBuilder createHypergraph(int nbVertices, int nbHyperedges) {
        return new HypergraphBuilder(nbVertices, nbHyperedges);
    }

    /**
     * Gives the number of vertices in the hypergraph.
     *
     * @return The number of vertices.
     */
    int getNumberOfVertices() {
        return numberOfVertices;
    }

    /**
     * Sets the weight of a vertex in the hypergraph.
     *
     * @param vertex The vertex to set the weight of.
     * @param weight The weight of the vertex.
     *
     * @return This builder.
     */
    public HypergraphBuilder withVertexWeight(int vertex, int weight) {
        if (vertexWeights == null) {
            // This is the first vertex for which a weight is specified.
            vertexWeights = new int[numberOfVertices];
        }

        vertexWeights[vertex - 1] = weight;
        return this;
    }

    /**
     * Gives the number of hyperedges in the hypergraph.
     *
     * @return The number of hyperedges.
     */
    int getNumberOfHyperedges() {
        return numberOfHyperedges;
    }

    /**
     * Gives the indices at which the vertices of each hyperedge start in the array
     * returned by {@link #getHyperedgeVertices()}.
     *
     * @return The indices of the vertices for each hyperedge.
     *
     * @see #getHyperedgeVertices()
     */
    long[] getHyperedgeIndices() {
        return hyperedgeIndices;
    }

    /**
     * Gives the vertices of the hyperedges in the hypergraph.
     *
     * @return The vertices of the different hyperedges.
     *
     * @see #getHyperedgeIndices()
     */
    int[] getHyperedgeVertices() {
        return hyperedgeVertices;
    }

    /**
     * Adds an {@link UnweightedHyperedge} to the hypergraph.
     *
     * @param hyperedge The hyperedge to add.
     *
     * @return This builder.
     */
    public HypergraphBuilder withHyperedge(UnweightedHyperedge hyperedge) {
        appendVertices(hyperedge);
        return this;
    }

    /**
     * Adds a {@link WeightedHyperedge} to the hypergraph.
     *
     * @param hyperedge The hyperedge to add.
     *
     * @return This builder.
     */
    public HypergraphBuilder withHyperedge(WeightedHyperedge hyperedge) {
        if (hyperedgeWeights == null) {
            // This is the first hyperedge for which a weight is specified.
            hyperedgeWeights = new int[numberOfHyperedges];
        }

        hyperedgeWeights[hyperedgeIndex] = hyperedge.getWeight();
        appendVertices(hyperedge);
        return this;
    }

    /**
     * Appends the vertices of the given hyperedge to {@link #hyperedgeVertices}.
     *
     * @param hyperedge The hyperedge to append the vertices of.
     */
    private void appendVertices(Hyperedge hyperedge) {
        hyperedgeIndices[hyperedgeIndex++] = hyperedgeVerticesIndex;
        for (int vertex : hyperedge.getVertices()) {
            appendVertex(vertex);
        }
    }

    /**
     * Appends a vertex to {@link #hyperedgeVertices}.
     * The size of the array is doubled if there is not enough room to add the vertex.
     *
     * @param vertex The vertex to append.
     */
    private void appendVertex(int vertex) {
        if (hyperedgeVertices.length == hyperedgeVerticesIndex) {
            // There is not enough room for adding a vertex.
            hyperedgeVertices = Arrays.copyOf(hyperedgeVertices, hyperedgeVerticesIndex << 1);
        }

        hyperedgeVertices[hyperedgeVerticesIndex++] = vertex - 1;
    }

    /**
     * Creates the hypergraph built by this builder.
     *
     * @return The built hypergraph.
     */
    public Hypergraph build() {
        // Terminating the vertices.
        hyperedgeIndices[hyperedgeIndex] = hyperedgeVerticesIndex;
        hyperedgeVertices = Arrays.copyOf(hyperedgeVertices, hyperedgeVerticesIndex);

        // Creating the hypergraph.
        AbstractHypergraph hypergraph = new UnweightedHypergraph(this);

        if (hyperedgeWeights != null) {
            // The hyperedges of the hypergraph are weighted.
            hypergraph = new WeightedHyperedgesHypergraph(hypergraph, hyperedgeWeights);
        }

        if (vertexWeights != null) {
            // The vertices of the hypergraph are weighted.
            hypergraph = new WeightedVerticesHypergraph(hypergraph, vertexWeights);
        }

        return hypergraph;
    }

}
