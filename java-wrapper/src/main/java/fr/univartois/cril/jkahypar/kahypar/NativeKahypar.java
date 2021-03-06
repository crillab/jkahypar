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

package fr.univartois.cril.jkahypar.kahypar;

import com.sun.jna.Native;

import fr.univartois.cril.jkahypar.hypergraph.Hypergraph;

/**
 * The NativeKahypar class provides a user-friendly and Java-ish way of
 * interacting with the KaHyPar native library.
 *
 * Basically, this class is designed to hide the names of the functions defined
 * in this library, since they do not follow Java's naming conventions.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public final class NativeKahypar {

    /**
     * The interface with the native library.
     */
    private static final INativeKahypar NATIVE_LIBRARY =
            Native.load("kahypar", INativeKahypar.class);

    /**
     * Disables instantiation.
     */
    private NativeKahypar() {
        throw new AssertionError("No NativeKahypar instances for you!");
    }

    /**
     * Creates a new NativeKahyparContext.
     *
     * @return The created native context.
     */
    public static NativeKahyparContext kahyparContextNew() {
        var context = NATIVE_LIBRARY.kahypar_context_new();
        return new NativeKahyparContext(context);
    }

    /**
     * Reads the configuration to apply for a KaHyPar context.
     *
     * @param context  The context to configure.
     * @param fileName The name of the INI file to read the configuration from.
     */
    static void kahyparConfigureContextFromFile(NativeKahyparContext context, String fileName) {
        NATIVE_LIBRARY.kahypar_configure_context_from_file(context.getNativeContext(), fileName);
    }

    /**
     * Sets the weights that must have the blocks computed by the partitioning
     * algorithm.
     *
     * @param context      The context in which the partition is computed.
     * @param blockWeights The weights that the blocks must have.
     */
    static void kahyparSetCustomTargetBlockWeights(NativeKahyparContext context, int[] blockWeights) {
        NATIVE_LIBRARY.kahypar_set_custom_target_block_weights(blockWeights.length,
                blockWeights,context. getNativeContext());
    }

    /**
     * Computes a partition of a hypergraph using KaHyPar native implementation.
     *
     * @param context    The context in which the partition is computed.
     * @param hypergraph The hypergraph to compute a partition of.
     * @param partition  The array in which to store the improved partition.
     *
     * @return The value of the objective function with the computed partition.
     */
    static int kahyparPartition(NativeKahyparContext context, Hypergraph hypergraph, int[] partition) {
        var objective = new int[1];
        NATIVE_LIBRARY.kahypar_partition(hypergraph.getNumberOfVertices(),
                hypergraph.getNumberOfHyperedges(), context.getImbalance(),
                context.getNumberOfBlocks(), hypergraph.getVertexWeights(),
                hypergraph.getHyperedgeWeights(), hypergraph.getHyperedgeIndices(),
                hypergraph.getHyperedgeVertices(), objective, context.getNativeContext(),
                partition);
        return objective[0];
    }

    /**
     * Improves a partition generated by KaHyPar.
     *
     * @param context           The context in which the partition is computed.
     * @param hypergraph        The hypergraph to compute a partition of.
     * @param initialPartition  The partition to improve.
     * @param nbIterations      The number of iterations to perform for improving
     *                          the partition.
     * @param improvedPartition The array in which to store the improved partition.
     *
     * @return The value of the objective function with the improved partition.
     */
    static int kahyparImprovePartition(NativeKahyparContext context, Hypergraph hypergraph,
            int[] initialPartition, long nbIterations, int[] improvedPartition) {
        var objective = new int[1];
        NATIVE_LIBRARY.kahypar_improve_partition(hypergraph.getNumberOfVertices(),
                hypergraph.getNumberOfHyperedges(), context.getImbalance(),
                context.getNumberOfBlocks(), hypergraph.getVertexWeights(),
                hypergraph.getHyperedgeWeights(), hypergraph.getHyperedgeIndices(),
                hypergraph.getHyperedgeVertices(), initialPartition, nbIterations, objective,
                context.getNativeContext(), improvedPartition);
        return objective[0];
    }

    /**
     * Frees the memory used for a native context.
     *
     * @param context The context to free.
     */
    static void kahyparContextFree(NativeKahyparContext context) {
        NATIVE_LIBRARY.kahypar_context_free(context.getNativeContext());
    }

}
