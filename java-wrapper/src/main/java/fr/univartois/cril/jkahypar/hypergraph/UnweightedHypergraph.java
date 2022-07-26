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
 * The UnweightedHypergraph is a {@link Hypergraph} for which neither the vertices nor the
 * hyperedges have a weight.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
final class UnweightedHypergraph extends AbstractHypergraph {

    /**
     * The number of vertices in this hypergraph.
     */
    private final int numberOfVertices;

    /**
     * The number of hyperedges in this hypergraph.
     */
    private final int numberOfHyperedges;

    /**
     * The indices at which the vertices of each hyperedge start in
     * {@link #hyperedgeVertices}.
     */
    private final long[] hyperedgeIndices;

    /**
     * The vertices of the different hyperedges.
     */
    private final int[] hyperedgeVertices;

    /**
     * Creates a new UnweightedHypergraph.
     *
     * @param builder The builder to create the hypergraph from.
     */
    UnweightedHypergraph(HypergraphBuilder builder) {
        super(HmetisFormat.UNWEIGHTED);
        this.numberOfVertices = builder.getNumberOfVertices();
        this.numberOfHyperedges = builder.getNumberOfHyperedges();
        this.hyperedgeIndices = builder.getHyperedgeIndices();
        this.hyperedgeVertices = builder.getHyperedgeVertices();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getNumberOfVertices()
     */
    @Override
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getVertexWeights()
     */
    @Override
    public int[] getVertexWeights() {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getNumberOfHyperedges()
     */
    @Override
    public int getNumberOfHyperedges() {
        return numberOfHyperedges;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedge(int)
     */
    @Override
    public Hyperedge getHyperedge(int index) {
        int begin = (int) hyperedgeIndices[index];
        int end = (int) hyperedgeIndices[index + 1];
        int nbVertices = end - begin;
        int[] vertices = new int[nbVertices];

        // Copying the vertices, considering that vertices are currently shifted.
        for (int i = 0; i < nbVertices; i++) {
            vertices[i] = hyperedgeVertices[begin + i] + 1;
        }

        return UnweightedHyperedge.joining(vertices);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedgeWeights()
     */
    @Override
    public int[] getHyperedgeWeights() {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedgeIndices()
     */
    @Override
    public long[] getHyperedgeIndices() {
        return hyperedgeIndices;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedgeVertices()
     */
    @Override
    public int[] getHyperedgeVertices() {
        return hyperedgeVertices;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#hmetisHeader()
     */
    @Override
    public String hmetisHeader() {
        return numberOfHyperedges + " " + numberOfVertices;
    }

}
