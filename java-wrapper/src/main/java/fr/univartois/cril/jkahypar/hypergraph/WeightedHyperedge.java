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
 * The WeightedHyperedge decorates a {@link Hyperedge} so as to consider its
 * weight.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public final class WeightedHyperedge implements Hyperedge {

    /**
     * The decorated hyperedge.
     */
    private final Hyperedge decorated;

    /**
     * The weight of this hyperedge.
     */
    private final int weight;

    /**
     * Creates a new WeightedHyperedge.
     *
     * @param decorated The hyperedge to decorate.
     * @param weight    The weight of the hyperedge.
     */
    WeightedHyperedge(Hyperedge decorated, int weight) {
        this.decorated = decorated;
        this.weight = weight;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hyperedge#size()
     */
    @Override
    public int size() {
        return decorated.size();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hyperedge#getVertices()
     */
    @Override
    public int[] getVertices() {
        return decorated.getVertices();
    }

    /**
     * Gives the weight of this hyperedge.
     *
     * @return The weight of this hyperedge.
     */
    int getWeight() {
        return weight;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return weight + " " + decorated;
    }

}
