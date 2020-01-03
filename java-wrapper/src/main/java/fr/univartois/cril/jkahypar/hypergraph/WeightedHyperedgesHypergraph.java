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

/**
 * The WeightedHyperedgesHypergraph decorates a {@link Hypergraph} for
 * considering weights on its hyperedges.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
final class WeightedHyperedgesHypergraph extends HypergraphDecorator {

    /**
     * The weights of the hyperedges.
     */
    private final int[] hyperedgeWeights;

    /**
     * Creates a new WeightedHyperedgesHypergraph.
     *
     * @param hypergraph       The hypergraph to decorate.
     * @param hyperedgeWeights The weights of the hyperedges.
     */
    WeightedHyperedgesHypergraph(AbstractHypergraph hypergraph, int[] hyperedgeWeights) {
        super(HmetisFormat.WEIGHT_ON_HYPEREDGES, hypergraph);
        this.hyperedgeWeights = hyperedgeWeights;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.HypergraphDecorator#getHyperedge(int)
     */
    @Override
    public Hyperedge getHyperedge(int index) {
        return new WeightedHyperedge(super.getHyperedge(index), hyperedgeWeights[index]);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hypergraph#getHyperedgeWeights()
     */
    @Override
    public int[] getHyperedgeWeights() {
        return hyperedgeWeights;
    }

}
