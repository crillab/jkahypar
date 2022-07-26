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
 * The HypergraphDecorator delegates the invocations of all methods defined in
 * {@link Hypergraph} to an instance of a class implementing this interface to make easier
 * the implementation of decorators.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
abstract class HypergraphDecorator extends AbstractHypergraph {

    /**
     * The decorated hypergraph.
     */
    private final AbstractHypergraph decorated;

    /**
     * Creates a new HypergraphDecorator.
     *
     * @param hmetisFormat The hMetis format of the hypergraph.
     * @param decorated The hypergraph to decorate.
     */
    HypergraphDecorator(HmetisFormat hmetisFormat, AbstractHypergraph decorated) {
        super(hmetisFormat);
        this.decorated = decorated;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getNumberOfVertices()
     */
    @Override
    public int getNumberOfVertices() {
        return decorated.getNumberOfVertices();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getVertexWeights()
     */
    @Override
    public int[] getVertexWeights() {
        return decorated.getVertexWeights();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getNumberOfHyperedges()
     */
    @Override
    public int getNumberOfHyperedges() {
        return decorated.getNumberOfHyperedges();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedge(int)
     */
    @Override
    public Hyperedge getHyperedge(int index) {
        return decorated.getHyperedge(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedgeWeights()
     */
    @Override
    public int[] getHyperedgeWeights() {
        return decorated.getHyperedgeWeights();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedgeIndices()
     */
    @Override
    public long[] getHyperedgeIndices() {
        return decorated.getHyperedgeIndices();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedgeVertices()
     */
    @Override
    public int[] getHyperedgeVertices() {
        return decorated.getHyperedgeVertices();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#hmetisFormat()
     */
    @Override
    public final int hmetisFormat() {
        return super.hmetisFormat() + decorated.hmetisFormat();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#hmetisHeader()
     */
    @Override
    public final String hmetisHeader() {
        return getNumberOfHyperedges() + " " + getNumberOfVertices() + " " + hmetisFormat();
    }

}
