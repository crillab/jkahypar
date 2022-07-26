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
 * The UnknownSizeHypergraphBuilder implements {@link HypergraphBuilder} to build
 * hypergraphs for which the size is not known in advance.
 * The size is thus updated while the hyperedges are added to the hypergraph.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 * @since 0.2.0
 */
final class UnknownSizeHypergraphBuilder extends AbstractHypergraphBuilder {

    /**
     * Creates a new UnknownSizeHypergraphBuilder.
     */
    UnknownSizeHypergraphBuilder() {
        super(0, 0);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder#withVertexWeight(int,
     * int)
     */
    @Override
    public HypergraphBuilder withVertexWeight(int vertex, int weight) {
        if (vertexWeights == null) {
            // This is the first vertex for which a weight is specified.
            vertexWeights = new int[numberOfVertices];
        }

        if (vertexWeights.length < vertex) {
            // There is not enough room to store the weight of this vertex.
            vertexWeights = Arrays.copyOf(vertexWeights, vertex << 1);
        }

        numberOfVertices = Math.max(numberOfVertices, vertex);
        vertexWeights[vertex - 1] = weight;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.AbstractHypergraphBuilder#setHyperedgeIndex(
     * int, int)
     */
    @Override
    protected void setHyperedgeIndex(int index, int vertexIndex) {
        int hyperedgeId = index + 1;

        if (hyperedgeIndices.length < hyperedgeId) {
            // There is not enough room to store the new hyperedge.
            hyperedgeIndices = Arrays.copyOf(hyperedgeIndices, hyperedgeId << 1);
        }

        numberOfHyperedges = Math.max(numberOfHyperedges, hyperedgeId);
        hyperedgeIndices[index] = vertexIndex;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.AbstractHypergraphBuilder#
     * withHyperedgeWeight(int, int)
     */
    @Override
    protected HypergraphBuilder withHyperedgeWeight(int hyperedge, int weight) {
        if (hyperedgeWeights == null) {
            // This is the first hyperedge for which a weight is specified.
            hyperedgeWeights = new int[numberOfHyperedges];
        }

        int hyperedgeId = hyperedge + 1;
        if (hyperedgeWeights.length < hyperedgeId) {
            // There is not enough room to store the weight of this hyperedge.
            hyperedgeWeights = Arrays.copyOf(hyperedgeWeights, hyperedgeId << 1);
        }

        numberOfHyperedges = Math.max(numberOfHyperedges, hyperedgeId);
        hyperedgeWeights[hyperedge] = weight;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.AbstractHypergraphBuilder#appendVertex(int)
     */
    @Override
    protected void appendVertex(int vertex) {
        super.appendVertex(vertex);
        numberOfVertices = Math.max(numberOfVertices, vertex);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.AbstractHypergraphBuilder#resizeArrays()
     */
    @Override
    protected void resizeArrays() {
        // The array storing the vertices must be resized.
        // Note that it has one more element to store the position of the last vertex.
        hyperedgeIndices = Arrays.copyOf(hyperedgeIndices, numberOfHyperedges);
        numberOfHyperedges--;

        if (vertexWeights != null) {
            // The array storing the weights of the vertices must be resized.
            vertexWeights = Arrays.copyOf(vertexWeights, numberOfVertices);
        }

        if (hyperedgeWeights != null) {
            // The array storing the weights of the hyperedges must be resized.
            hyperedgeWeights = Arrays.copyOf(hyperedgeWeights, numberOfHyperedges);
        }
    }

}
