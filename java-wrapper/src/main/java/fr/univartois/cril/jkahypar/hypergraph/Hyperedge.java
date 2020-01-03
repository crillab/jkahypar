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
 * The Hyperedge interface defines the contract of the data structures
 * representing the hyperedges of a hypergraph.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public interface Hyperedge {

    /**
     * Gives the size of this hyperedge, measured in number of vertices it joins.
     *
     * @return The size of this hyperedge.
     */
    int size();

    /**
     * Gives the vertices that are joined by this hyperedge in the hypergraph.
     *
     * @return The vertices joined by this hyperedge.
     */
    int[] getVertices();

    /**
     * Gives the String representation of this hyperedge in the hMetis format.
     *
     * @return The hMetis representation of this hyperedge.
     */
    @Override
    String toString();

}
