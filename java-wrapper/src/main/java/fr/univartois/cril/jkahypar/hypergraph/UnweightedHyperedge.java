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

import java.util.List;

/**
 * The UnweightedHyperedge represents a {@link Hyperedge} for which no weight is
 * considered.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public final class UnweightedHyperedge implements Hyperedge {

    /**
     * The vertices joined by this hyperedge.
     */
    private final int[] vertices;

    /**
     * Creates a new UnweightedHyperedge.
     *
     * @param vertices The vertices joined by the hyperedge.
     */
    private UnweightedHyperedge(int[] vertices) {
        this.vertices = vertices;
    }

    /**
     * Creates a new UnweightedHyperedge.
     *
     * @param vertices The vertices joined by the hyperedge.
     *
     * @return The created hyperedge.
     */
    public static UnweightedHyperedge joining(int... vertices) {
        return new UnweightedHyperedge(vertices);
    }

    /**
     * Creates a new UnweightedHyperedge.
     *
     * @param vertices The vertices joined by the hyperedge.
     *
     * @return The created hyperedge.
     */
    public static UnweightedHyperedge joining(List<Integer> vertices) {
        int[] vertexArray = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            vertexArray[i] = vertices.get(i);
        }
        return new UnweightedHyperedge(vertexArray);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hyperedge#size()
     */
    @Override
    public int size() {
        return vertices.length;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.cril.jkahypar.hypergraph.Hyperedge#getVertices()
     */
    @Override
    public int[] getVertices() {
        return vertices;
    }

    /**
     * Creates a hyperedge joining the same vertices as this one, and having the given
     * weight.
     *
     * @param weight The weight of the hyperedge to create.
     *
     * @return The created hyperedge.
     */
    public WeightedHyperedge withWeight(int weight) {
        return new WeightedHyperedge(this, weight);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();

        for (int v = 0, size = size(); v < size; v++) {
            builder.append(vertices[v]);
            if (v != (size - 1)) {
                builder.append(' ');
            }
        }

        return builder.toString();
    }

}
