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

package fr.univartois.cril.jkahypar.kahypar;

import static fr.univartois.cril.jkahypar.kahypar.NativeKahypar.kahyparConfigureContextFromFile;
import static fr.univartois.cril.jkahypar.kahypar.NativeKahypar.kahyparContextFree;
import static fr.univartois.cril.jkahypar.kahypar.NativeKahypar.kahyparSetCustomTargetBlockWeights;

import com.sun.jna.Pointer;

import fr.univartois.cril.jkahypar.hypergraph.Hypergraph;

/**
 * The NativeKahyparContext provides an object-oriented wrapper for a native
 * KaHyPar {@code Context} object.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public final class NativeKahyparContext {

    /**
     * The reference to the native context.
     */
    private final Pointer nativeContext;

    /**
     * The imbalance parameter for the partitioning algorithm.
     */
    private double imbalance;

    /**
     * The number of blocks in the partitions to compute.
     */
    private int numberOfBlocks;

    /**
     * Creates a new NativeKahyparContext.
     *
     * @param nativeContext The reference to the native context.
     */
    NativeKahyparContext(Pointer nativeContext) {
        this.nativeContext = nativeContext;
    }

    /**
     * Reads the configuration of this context from an INI configuration file.
     *
     * @param iniFile The name of the file to read the configuration from.
     */
    public void configureFrom(String iniFile) {
        kahyparConfigureContextFromFile(this, iniFile);
    }

    /**
     * Gives the reference to the native context wrapped in this NativeKahyparContext.
     *
     * @return The reference to the native context.
     */
    Pointer getNativeContext() {
        return nativeContext;
    }

    /**
     * Sets the imbalance parameter to use in the partitioning algorithm.
     *
     * @param imbalance The imbalance parameter to use.
     */
    public void setImbalance(double imbalance) {
        this.imbalance = imbalance;
    }

    /**
     * Gives the imbalance parameter to use in the partitioning algorithm.
     *
     * @return The imbalance parameter to use.
     */
    public double getImbalance() {
        return imbalance;
    }

    /**
     * Sets the number of blocks in the partitions to compute.
     *
     * @param numberOfBlocks The number of blocks.
     */
    public void setNumberOfBlocks(int numberOfBlocks) {
        this.numberOfBlocks = numberOfBlocks;
    }

    /**
     * Gives the number of blocks in the partitions to compute.
     *
     * @return The number of blocks.
     */
    public int getNumberOfBlocks() {
        return numberOfBlocks;
    }

    /**
     * Sets the weights that must have the blocks computed by the partitioning algorithm.
     *
     * @param blockWeights The weights of the blocks.
     */
    public void setBlockWeights(int[] blockWeights) {
        setNumberOfBlocks(blockWeights.length);
        kahyparSetCustomTargetBlockWeights(this, blockWeights);
    }

    /**
     * Creates a partitioner for the given hypergraph.
     *
     * @param hypergraph The hypergraph to partition.
     *
     * @return The created partitioner.
     */
    public NativeKahyparPartitioner createPartitionerFor(Hypergraph hypergraph) {
        return new NativeKahyparPartitioner(this, hypergraph);
    }

    /**
     * Frees the memory used by the underlying native context.
     */
    public void dispose() {
        kahyparContextFree(this);
    }

}
