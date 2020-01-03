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
 * The WeightedVerticesHypergraph decorates a {@link Hypergraph} for considering
 * weights on its vertices.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
final class WeightedVerticesHypergraph extends HypergraphDecorator {

    /**
     * The weights of the vertices.
     */
    private final int[] vertexWeights;

    /**
     * Creates a new WeightedVerticesHypergraph.
     *
     * @param hypergraph    The hypergraph to decorate.
     * @param vertexWeights The weights of the vertices.
     */
    WeightedVerticesHypergraph(AbstractHypergraph hypergraph, int[] vertexWeights) {
        super(HmetisFormat.WEIGHT_ON_VERTICES, hypergraph);
        this.vertexWeights = vertexWeights;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.cril.jkahypar.hypergraph.HypergraphDecorator#getVertexWeights()
     */
    @Override
    public int[] getVertexWeights() {
        return vertexWeights;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.AbstractHypergraph#toString()
     */
    @Override
    public String toString() {
        var builder = new StringBuilder(super.toString());

        // Adding the weight of each vertex.
        for (int weight : vertexWeights) {
            builder.append(weight).append(System.lineSeparator());
        }

        return builder.toString();
    }

}
