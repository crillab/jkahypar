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

package fr.univartois.cril.jkahypar.hypergraph;

/**
 * The AbstractHypergraph is the parent class of all implementations of {@link Hypergraph}.
 * It implements their String representation in the hMetis format, as a template method.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
abstract class AbstractHypergraph implements Hypergraph {

    /**
     * The format of this hypergraph.
     */
    private final HmetisFormat hmetisFormat;

    /**
     * Creates a new AbstractHypergraph.
     *
     * @param hmetisFormat The format of this hypergraph.
     */
    protected AbstractHypergraph(HmetisFormat hmetisFormat) {
        this.hmetisFormat = hmetisFormat;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();

        // Adding the header.
        builder.append(hmetisHeader());
        builder.append(System.lineSeparator());

        // Adding the hyperedges.
        for (int h = 0; h < getNumberOfHyperedges(); h++) {
            builder.append(getHyperedge(h));
            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }

    /**
     * Gives the String containing the characteristics of this hypergraph and used as
     * header in the hMetis representation of this hypergraph
     *
     * @return The header String in the hMetis representation of this hypergraph.
     */
    protected abstract String hmetisHeader();

    /**
     * Gives the hMetis format of this hypergraph.
     *
     * @return An integer representing the hMetis format of the hypergraph.
     *
     * @implSpec The default implementation returns the identifier of the
     *           {@link HmetisFormat} specified when creating this instance.
     */
    protected int hmetisFormat() {
        return hmetisFormat.getIdentifier();
    }

}
