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
 * The KnownSizeHypergraphBuilder implements {@link HypergraphBuilder} to build
 * hypergraphs for which the size is known in advance, allowing a more efficient
 * management of internal data structures.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 * @since 0.2.0
 */
final class KnownSizeHypergraphBuilder extends AbstractHypergraphBuilder {

    /**
     * Creates a new KnownSizeHypergraphBuilder.
     *
     * @param nbVertices The number of vertices in the hypergraph.
     * @param nbHyperedges The number of hyperedges in the hypergraph.
     */
    KnownSizeHypergraphBuilder(int nbVertices, int nbHyperedges) {
        super(nbVertices, nbHyperedges);
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

        hyperedgeWeights[hyperedge] = weight;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.AbstractHypergraphBuilder#resizeArrays()
     */
    @Override
    protected void resizeArrays() {
        // All arrays already have the correct size.
    }

}
