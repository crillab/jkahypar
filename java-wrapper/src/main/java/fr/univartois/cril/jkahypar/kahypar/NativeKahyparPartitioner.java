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

import static fr.univartois.cril.jkahypar.kahypar.NativeKahypar.kahyparImprovePartition;
import static fr.univartois.cril.jkahypar.kahypar.NativeKahypar.kahyparPartition;

import fr.univartois.cril.jkahypar.hypergraph.Hypergraph;

/**
 * The NativeKahyparPartitioner provides an object-oriented wrapper for the
 * partitioning algorithm implemented in the KaHyPar native library.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public final class NativeKahyparPartitioner {

    /**
     * The context in which the partitioning algorithm is executed.
     */
    private final NativeKahyparContext context;

    /**
     * The hypergraph to compute a partition of.
     */
    private final Hypergraph hypergraph;

    /**
     * The last partition that has been computed by this partitioner.
     */
    private int[] lastPartition;

    /**
     * The value of the objective function on the last computed partition.
     */
    private int lastObjectiveValue = -1;

    /**
     * Creates a new NativeKahyparPartitioner.
     *
     * @param context    The context in which the partitioning algorithm is
     *                   executed.
     * @param hypergraph The hypergraph to compute a partition of.
     */
    NativeKahyparPartitioner(NativeKahyparContext context, Hypergraph hypergraph) {
        this.context = context;
        this.hypergraph = hypergraph;
    }

    /**
     * Computes a partition of the associated hypergraph.
     * The partition is computed only once.
     * If you want to improve the current partition, use {@link #improvePartition(int)}
     * instead.
     *
     * @see #improvePartition(int)
     */
    public void computePartition() {
        if (lastPartition == null) {
            lastPartition = new int[hypergraph.getNumberOfVertices()];
            lastObjectiveValue = kahyparPartition(context, hypergraph, lastPartition);
        }
    }

    /**
     * Improves the last partition that has been computed by this partitioner.
     * The method {@link #computePartition()} must have been invoked before invoking
     * this method.
     *
     * @param nbIterations The number of iterations to perform for improving the
     *                     partition.
     *
     * @see #computePartition()
     */
    public void improvePartition(int nbIterations) {
        var newPartition = new int[hypergraph.getNumberOfVertices()];
        lastObjectiveValue = kahyparImprovePartition(context, hypergraph, lastPartition,
                nbIterations, newPartition);
        lastPartition = newPartition;
    }

    /**
     * Gives the last partition that has been computed by this partitioner.
     *
     * @return The last computed partition.
     */
    public int[] getLastPartition() {
        return lastPartition;
    }

    /**
     * Gives the value of the objective function on the last computed partition.
     *
     * @return The last value of the objective function.
     */
    public int getLastObjectiveValue() {
        return lastObjectiveValue;
    }

}
