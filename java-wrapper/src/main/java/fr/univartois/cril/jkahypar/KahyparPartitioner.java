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

import fr.univartois.cril.jkahypar.kahypar.NativeKahyparPartitioner;

/**
 * The KahyparPartitioner allows to compute a partition of a given hypergraph using a
 * predefined configuration inherited from the context in which it is executed.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public final class KahyparPartitioner {

    /**
     * The native partitioner which actually computes the partitions.
     */
    private final NativeKahyparPartitioner nativePartitioner;

    /**
     * The number of blocks in the partitions to compute.
     */
    private final int numberOfBlocks;

    /**
     * Creates a new KahyparPartitioner.
     *
     * @param nativePartitioner The native partitioner which actually computes the partitions.
     * @param numberOfBlocks The number of blocks in the partitions to compute.
     */
    KahyparPartitioner(NativeKahyparPartitioner nativePartitioner, int numberOfBlocks) {
        this.nativePartitioner = nativePartitioner;
        this.numberOfBlocks = numberOfBlocks;
    }

    /**
     * Computes a partition of the given hypergraph.
     *
     * @return The computed partition.
     */
    public HypergraphPartition computePartition() {
        nativePartitioner.computePartition();
        return lastPartition();
    }

    /**
     * Improves the last partition computed by this partitioner.
     * Only one iteration is performed.
     *
     * @return The improved partition.
     *
     * @see #improvePartition(int)
     */
    public HypergraphPartition improvePartition() {
        return improvePartition(1);
    }

    /**
     * Improves the last partition computed by this partitioner.
     *
     * @param nbIterations The number of iterations to perform to improve the partition.
     *
     * @return The improved partition.
     */
    public HypergraphPartition improvePartition(int nbIterations) {
        nativePartitioner.improvePartition(nbIterations);
        return lastPartition();
    }

    /**
     * Creates a HypergraphPartition representing the last computed partition.
     *
     * @return The created partition.
     */
    private HypergraphPartition lastPartition() {
        return new HypergraphPartition(
                numberOfBlocks,
                nativePartitioner.getLastPartition(),
                nativePartitioner.getLastObjectiveValue());
    }

}
