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

package fr.univartois.cril.jkahypar;

/**
 * The HypergraphPartition represents a partition of a hypergraph.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public final class HypergraphPartition {

    /**
     * The array associating to each vertex the identifier of the block to which it
     * belongs.
     */
    private final int[] blockIdentifiers;

    /**
     * The value of the objective function on this partition.
     */
    private final int objectiveValue;

    /**
     * Creates a new HypergraphPartition.
     *
     * @param blockIdentifiers The array associating to each vertex the identifier
     *                         of the block to which it belongs.
     * @param objectiveValue   The value of the objective function on the partition.
     */
    HypergraphPartition(int[] blockIdentifiers, int objectiveValue) {
        this.blockIdentifiers = blockIdentifiers;
        this.objectiveValue = objectiveValue;
    }

    /**
     * Gives the identifier of the block to which the given vertex belongs.
     *
     * @param vertex The vertex to give the block of.
     *
     * @return The identifier of the block.
     */
    public int blockOf(int vertex) {
        return blockIdentifiers[vertex - 1];
    }

    /**
     * Gives the value of the objective function on this partition.
     *
     * @return The value of the objective function.
     */
    public int objectiveValue() {
        return objectiveValue;
    }

}
