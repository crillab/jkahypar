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
 * The Hypergraph interface defines the contract of the data structures representing
 * hypergraphs.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public interface Hypergraph {

    /**
     * Gives the number of vertices in this hypergraph.
     *
     * @return The number of vertices.
     */
    int getNumberOfVertices();

    /**
     * Gives the weights of the vertices in this hypergraph.
     *
     * @return The weights of the vertices, or {@code null} if vertices are not weighted.
     */
    int[] getVertexWeights();

    /**
     * Gives the number of hyperedges in this hypergraph.
     *
     * @return The number of hyperedges.
     */
    int getNumberOfHyperedges();

    /**
     * Gives the {@code index}-th hyperedge in this hypergraph.
     *
     * @param index The index of the hyperedge to get.
     *
     * @return The {@code index}-th hyperedge.
     */
    Hyperedge getHyperedge(int index);

    /**
     * Gives the weights of the hyperedges in this hypergraph.
     *
     * @return The weights of the hyperedges, or {@code null} if hyperedges are not
     *         weighted.
     */
    int[] getHyperedgeWeights();

    /**
     * Gives the indices at which the vertices of each hyperedge start in the array
     * returned by {@link #getHyperedgeVertices()}.
     *
     * @return The indices of the vertices for each hyperedge.
     *
     * @see #getHyperedgeVertices()
     */
    long[] getHyperedgeIndices();

    /**
     * Gives the vertices of the hyperedges in the hypergraph.
     *
     * @return The vertices of the different hyperedges.
     *
     * @see #getHyperedgeIndices()
     */
    int[] getHyperedgeVertices();

    /**
     * Gives a String representation of this hypergraph using the hMetis format.
     *
     * @return The String representation of this hypergraph.
     */
    @Override
    String toString();

}
