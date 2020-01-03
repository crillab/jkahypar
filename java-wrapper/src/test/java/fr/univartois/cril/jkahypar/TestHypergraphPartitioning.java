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

import static fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder.createHypergraph;
import static fr.univartois.cril.jkahypar.hypergraph.UnweightedHyperedge.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The TestHypergraphParser is a JUnit test case for testing the
 * partitioning of a hypergraph.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public final class TestHypergraphPartitioning {

    /**
     * Test method for the computation of a partition of a hypergraph.
     */
    @Test
    @DisplayName("A correct partition is computed")
    public void testComputePartition() {
        try (var context = new KahyparContext()) {
            // Configuring the context.
            context.configureFrom("src/test/resources/config/km1_direct_kway_sea18.ini");
            context.setImbalance(0.03);
            context.setNumberOfBlocks(2);

            // Compute the partition of the hypergraph.
            var hypergraph = createHypergraph(7, 4)
                    .withHyperedge(joining(1, 3).withWeight(1))
                    .withHyperedge(joining(1, 2, 4, 5).withWeight(1000))
                    .withHyperedge(joining(4, 5, 7).withWeight(1))
                    .withHyperedge(joining(3, 6, 7).withWeight(1000))
                    .build();
            var partitioner = context.createPartitionerFor(hypergraph);
            var partition = partitioner.computePartition();

            // Checking whether the first correct solution have been found.
            boolean firstSolution = (partition.blockOf(1) == 0)
                    && (partition.blockOf(2) == 0)
                    && (partition.blockOf(3) == 1)
                    && (partition.blockOf(4) == 0)
                    && (partition.blockOf(5) == 0)
                    && (partition.blockOf(6) == 1)
                    && (partition.blockOf(7) == 1);

            // Checking whether the second correct solution have been found.
            boolean secondSolution = (partition.blockOf(1) == 1)
                    && (partition.blockOf(2) == 1)
                    && (partition.blockOf(3) == 0)
                    && (partition.blockOf(4) == 1)
                    && (partition.blockOf(5) == 1)
                    && (partition.blockOf(6) == 0)
                    && (partition.blockOf(7) == 0);

            // Checking that a correct solution has been found.
            assertTrue(firstSolution || secondSolution, "Incorrect partitioning!");
            assertEquals(2, partition.objectiveValue());
        }
    }

}
