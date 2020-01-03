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

package fr.univartois.cril.jkahypar.hypergraph;

/**
 * The HmetisFormat enumerates the possible hypergraph formats recognized by
 * hMetis.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */
public enum HmetisFormat {

    /**
     * The format representing a hypergraph without weights.
     */
    UNWEIGHTED(0),

    /**
     * The format representing a hypergraph with weights on its hyperedges.
     */
    WEIGHT_ON_HYPEREDGES(1),

    /**
     * The format representing a hypergraph with weights on its vertices.
     */
    WEIGHT_ON_VERTICES(10);

    /**
     * The hMetis identifier of this format.
     */
    private final int identifier;

    /**
     * Creates a new HmetisFormat.
     *
     * @param identifier The hMetis identifier of the format.
     */
    private HmetisFormat(int formatId) {
        this.identifier = formatId;
    }

    /**
     * Gives the hMetis identifier of this format.
     *
     * @return The identifier of this format.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Checks whether the given integer encodes that the associated hypergraph is of
     * this format.
     *
     * @param formatIdentifier The integer encoding the format of a hypergraph.
     *
     * @return Whether this format is encoded in the given integer.
     */
    public boolean isEncodedIn(int formatIdentifier) {
        return (formatIdentifier == identifier) || ((identifier > 0) && (formatIdentifier == 11));
    }

}
