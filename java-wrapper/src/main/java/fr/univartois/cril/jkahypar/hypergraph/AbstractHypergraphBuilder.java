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
 * The AbstractHypergraphBuilder provides a base implementation for
 * {@link HypergraphBuilder}.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 * @since 0.2.0
 */
public abstract class AbstractHypergraphBuilder implements HypergraphBuilder {

    /**
     * The number of vertices in the hypergraph.
     */
    protected int numberOfVertices;

    /**
     * The weights of the vertices.
     * If vertices are not weighted, this array remains {@code null}.
     */
    protected int[] vertexWeights;

    /**
     * The number of hyperedges in the hypergraph.
     */
    protected int numberOfHyperedges;

    /**
     * The weights of the hyperedges.
     * If hyperedges are not weighted, this array remains {@code null}.
     */
    protected int[] hyperedgeWeights;

    /**
     * The indices at which the vertices of each hyperedge start in
     * {@link #hyperedgeVertices}.
     */
    protected long[] hyperedgeIndices;

    /**
     * The vertices of the different hyperedges.
     */
    protected int[] hyperedgeVertices;

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
     * Creates a new AbstractHypergraphBuilder.
     *
     * @param nbVertices The number of vertices in the hypergraph.
     * @param nbHyperedges The number of hyperedges in the hypergraph.
     */
    protected AbstractHypergraphBuilder(int nbVertices, int nbHyperedges) {
        this.numberOfVertices = nbVertices;
        this.numberOfHyperedges = nbHyperedges;
        this.hyperedgeIndices = new long[nbHyperedges + 1];
        this.hyperedgeVertices = new int[(nbHyperedges << 1) + 1];
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder#getNumberOfVertices()
     */
    @Override
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder#getNumberOfHyperedges()
     */
    @Override
    public int getNumberOfHyperedges() {
        return numberOfHyperedges;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder#getHyperedgeIndices()
     */
    @Override
    public long[] getHyperedgeIndices() {
        return hyperedgeIndices;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder#getHyperedgeVertices()
     */
    @Override
    public int[] getHyperedgeVertices() {
        return hyperedgeVertices;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder#withHyperedge(fr.
     * univartois.cril.jkahypar.hypergraph.UnweightedHyperedge)
     */
    @Override
    public HypergraphBuilder withHyperedge(UnweightedHyperedge hyperedge) {
        appendVertices(hyperedge);
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder#withHyperedge(fr.
     * univartois.cril.jkahypar.hypergraph.WeightedHyperedge)
     */
    @Override
    public HypergraphBuilder withHyperedge(WeightedHyperedge hyperedge) {
        withHyperedgeWeight(hyperedgeIndex, hyperedge.getWeight());
        appendVertices(hyperedge);
        return this;
    }

    /**
     * Sets the index at which the vertices of a hyperedge start in
     * {@link #hyperedgeIndices}.
     *
     * @param index The index of the hyperedge.
     * @param vertexIndex The index at which the vertices of the hyperedge start.
     */
    protected abstract void setHyperedgeIndex(int index, int vertexIndex);

    /**
     * Sets the weight of a hyperedge in the hypergraph.
     *
     * @param hyperedge The hyperedge to set the weight of.
     * @param weight The weight of the hyperedge.
     *
     * @return This builder.
     */
    protected abstract HypergraphBuilder withHyperedgeWeight(int hyperedge, int weight);

    /**
     * Appends the vertices of the given hyperedge to {@link #hyperedgeVertices}.
     *
     * @param hyperedge The hyperedge to append the vertices of.
     */
    protected void appendVertices(Hyperedge hyperedge) {
        setHyperedgeIndex(hyperedgeIndex, hyperedgeVerticesIndex);
        for (int vertex : hyperedge.getVertices()) {
            appendVertex(vertex);
        }
        hyperedgeIndex++;
    }

    /**
     * Appends a vertex to {@link #hyperedgeVertices}.
     * The size of the array is doubled if there is not enough room to add the vertex.
     *
     * @param vertex The vertex to append.
     */
    protected void appendVertex(int vertex) {
        if (hyperedgeVertices.length == hyperedgeVerticesIndex) {
            // There is not enough room for adding a vertex.
            hyperedgeVertices = Arrays.copyOf(hyperedgeVertices, hyperedgeVerticesIndex << 1);
        }

        hyperedgeVertices[hyperedgeVerticesIndex++] = vertex - 1;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder#build()
     */
    @Override
    public Hypergraph build() {
        // Terminating the vertices, and making sure all arrays have the correct size.
        setHyperedgeIndex(hyperedgeIndex, hyperedgeVerticesIndex);
        hyperedgeVertices = Arrays.copyOf(hyperedgeVertices, hyperedgeVerticesIndex);
        resizeArrays();

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

    /**
     * Resizes the internal arrays to make sure their lengths correspond exactly to their
     * number of elements.
     */
    protected abstract void resizeArrays();

}
