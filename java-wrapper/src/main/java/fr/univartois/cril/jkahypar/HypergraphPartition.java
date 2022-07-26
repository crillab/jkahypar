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

package fr.univartois.cril.jkahypar;

import java.util.ArrayList;
import java.util.List;

/**
 * The HypergraphPartition represents a partition of a hypergraph.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public final class HypergraphPartition {

    /**
     * The number of blocks in the partition.
     */
    private final int numberOfBlocks;

    /**
     * The array associating to each vertex the identifier of the block to which it
     * belongs.
     */
    private final int[] blockIdentifiers;

    /**
     * The blocks of which this partition is made of.
     * These blocks are lazily computed.
     */
    private List<List<Integer>> blocks;

    /**
     * The value of the objective function on this partition.
     */
    private final int objectiveValue;

    /**
     * Creates a new HypergraphPartition.
     *
     * @param numberOfBlocks The number of blocks in the partition.
     * @param blockIdentifiers The array associating to each vertex the identifier of the
     *        block to which it belongs.
     * @param objectiveValue The value of the objective function on the partition.
     */
    HypergraphPartition(int numberOfBlocks, int[] blockIdentifiers, int objectiveValue) {
        this.numberOfBlocks = numberOfBlocks;
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

    /**
     * Gives the blocks of which this partition is made of.
     *
     * @return The blocks of this partition.
     */
    public List<List<Integer>> getBlocks() {
        if (blocks == null) {
            // The blocks have not been computed yet.
            blocks = computeBlocks();
        }

        return blocks;
    }

    /**
     * Computes the blocks of which this partition is made of.
     *
     * @return The blocks of this partition.
     */
    private List<List<Integer>> computeBlocks() {
        // Initializing the blocks as empty lists.
        var result = new ArrayList<List<Integer>>(numberOfBlocks);
        for (int i = 0; i < numberOfBlocks; i++) {
            result.add(new ArrayList<>());
        }

        // Filling the blocks of the partition with their corresponding vertices.
        for (int v = 0; v < blockIdentifiers.length; v++) {
            result.get(blockIdentifiers[v]).add(v + 1);
        }

        return result;
    }

}
