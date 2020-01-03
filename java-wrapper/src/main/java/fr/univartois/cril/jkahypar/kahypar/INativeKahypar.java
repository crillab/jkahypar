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

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 * The INativeKahypar interface provides an access to the functions defined in
 * the KaHyPar native library.
 *
 * Note that this interface does not follow Java naming conventions, as its
 * methods must have exactly the same name as in the native implementation.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
interface INativeKahypar extends Library {

    /**
     * Creates a new context from KaHyPar native implementation.
     *
     * @return A reference to the native context.
     */
    Pointer kahypar_context_new();

    /**
     * Reads the configuration to apply for a KaHyPar context.
     *
     * @param context  The context to configure.
     * @param fileName The name of the INI file to read the configuration from.
     */
    void kahypar_configure_context_from_file(Pointer context, String fileName);

    /**
     * Sets the weights that must have the blocks computed by the partitioning
     * algorithm.
     *
     * @param nbBlocks     The number of blocks in the partition.
     * @param blockWeights The weights that the blocks must have.
     * @param context      The context in which the partition is computed.
     */
    void kahypar_set_custom_target_block_weights(int nbBlocks, int[] blockWeights,
            Pointer context);

    /**
     * Computes a partition of a hypergraph using KaHyPar native implementation.
     *
     * @param nbVertices       The number of vertices in the hypergraph.
     * @param nbHyperedges     The number of hyperedges in the hypergraph.
     * @param imbalance        The imbalance parameter for the partitioning
     *                         algorithm.
     * @param nbBlocks         The number of blocks in the partition.
     * @param vertexWeights    The weights of the vertices in the hypergraph.
     * @param hyperedgeWeights The weights of the hyperedges in the hypergraph.
     * @param hyperedgeIndices The indices at which the vertices of each hyperedge
     *                         start in {@code hyperedges}.
     * @param hyperedges       The vertices of the hyperedges in the hypergraph.
     * @param objective        The array for storing the value of the objective
     *                         function.
     * @param context          The context in which the partition is computed.
     * @param partition        The array in which to store the improved partition.
     */
    void kahypar_partition(int nbVertices, int nbHyperedges, double imbalance, int nbBlocks,
            int[] vertexWeights, int[] hyperedgeWeights, long[] hyperedgeIndices,
            int[] hyperedges, int[] objective, Pointer context, int[] partition);

    /**
     * Improves a partition computed by KaHyPar.
     *
     * @param nbVertices        The number of vertices in the hypergraph.
     * @param nbHyperedges      The number of hyperedges in the hypergraph.
     * @param imbalance         The imbalance parameter for the partitioning
     *                          algorithm.
     * @param nbBlocks          The number of blocks in the partition.
     * @param vertexWeights     The weights of the vertices in the hypergraph.
     * @param hyperedgeWeights  The weights of the hyperedges in the hypergraph.
     * @param hyperedgeIndices  The indices at which the vertices of each hyperedge
     *                          start in {@code hyperedges}.
     * @param hyperedges        The vertices of the hyperedges in the hypergraph.
     * @param initialPartition  The partition to improve.
     * @param nbIterations      The number of iterations to perform for improving
     *                          the partition.
     * @param objective         The array for storing the value of the objective
     *                          function.
     * @param context           The context in which the partition is computed.
     * @param improvedPartition The array in which to store the improved partition.
     */
    void kahypar_improve_partition(int nbVertices, int nbHyperedges, double imbalance,
            int nbBlocks, int[] vertexWeights, int[] hyperedgeWeights, long[] hyperedgeIndices,
            int[] hyperedges, int[] initialPartition, long nbIterations, int[] objective,
            Pointer context, int[] improvedPartition);

    /**
     * Frees the memory used for a context from KaHyPar.
     *
     * @param context The context to free.
     */
    void kahypar_context_free(Pointer context);

}
