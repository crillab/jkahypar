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

import static fr.univartois.cril.jkahypar.kahypar.NativeKahypar.kahyparContextNew;

import fr.univartois.cril.jkahypar.hypergraph.Hypergraph;
import fr.univartois.cril.jkahypar.kahypar.NativeKahyparContext;

/**
 * The KahyparContext provides a context for computing hypergraph partitions.
 * In particular, this context allows to configure the partitioning algorithm.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public final class KahyparContext implements AutoCloseable {

    /**
     * The native context which is used internally.
     */
    private final NativeKahyparContext nativeContext;

    /**
     * Creates a new KahyparContext.
     */
    public KahyparContext() {
        this.nativeContext = kahyparContextNew();
    }

    /**
     * Loads this context configuration from an INI file.
     *
     * @param iniFile The file to load the configuration from.
     */
    public void configureFrom(String iniFile) {
        nativeContext.configureFrom(iniFile);
    }

    /**
     * Sets the imbalance parameter for the partitioning algorithm.
     *
     * @param imbalance The imbalance parameter.
     */
    public void setImbalance(double imbalance) {
        nativeContext.setImbalance(imbalance);
    }

    /**
     * Sets the number of blocks in the partitions to compute.
     *
     * @param numberOfBlocks The number of blocks.
     */
    public void setNumberOfBlocks(int numberOfBlocks) {
        nativeContext.setNumberOfBlocks(numberOfBlocks);
    }

    /**
     * Creates a partitioner for the given hypergraph.
     *
     * @param hypergraph The hypergraph to compute a partition of.
     *
     * @return The created partitioner.
     */
    public KahyparPartitioner createPartitionerFor(Hypergraph hypergraph) {
        var nativePartitioner = nativeContext.createPartitionerFor(hypergraph);
        return new KahyparPartitioner(nativePartitioner);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() {
        nativeContext.dispose();
    }

}
