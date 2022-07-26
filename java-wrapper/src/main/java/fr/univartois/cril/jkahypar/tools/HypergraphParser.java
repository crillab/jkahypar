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

package fr.univartois.cril.jkahypar.tools;

import static fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder.createHypergraph;
import static fr.univartois.cril.jkahypar.hypergraph.UnweightedHyperedge.joining;
import static java.util.Arrays.copyOfRange;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

import fr.univartois.cril.jkahypar.hypergraph.HmetisFormat;
import fr.univartois.cril.jkahypar.hypergraph.Hypergraph;
import fr.univartois.cril.jkahypar.hypergraph.HypergraphBuilder;

/**
 * The HypergraphParser allows to parse a file in the hMetis format so as to read a
 * {@link Hypergraph} instance.
 *
 * @author Romain WALLON
 *
 * @version 0.2.0
 */
public final class HypergraphParser implements Closeable {

    /**
     * The reader used to read the hypergraph.
     */
    private final BufferedReader reader;

    /**
     * The number of hyperedges in the read hypergraph.
     */
    private int numberOfHyperedges;

    /**
     * The number of vertices in the read hypergraph.
     */
    private int numberOfVertices;

    /**
     * The hMetis format of the input hypergraph.
     */
    private int hmetisFormat;

    /**
     * The builder used to build the hypergraph.
     */
    private HypergraphBuilder builder;

    /**
     * The array to use to read the integers in a line of the input stream.
     */
    private int[] integerLine;

    /**
     * Creates a new HypergraphParser.
     *
     * @param inputFile The path of the input file to read the hypergraph from.
     *
     * @throws IOException If an I/O error occurs while opening the file.
     */
    public HypergraphParser(String inputFile) throws IOException {
        this(Files.newInputStream(Path.of(inputFile)));
    }

    /**
     * Creates a new HypergraphParser.
     *
     * @param stream The input stream to read the hypergraph from.
     */
    public HypergraphParser(InputStream stream) {
        this.reader = new BufferedReader(new InputStreamReader(stream));
        this.integerLine = new int[3];
    }

    /**
     * Parses the input stream to read a hypergraph instance.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    public void parse() throws IOException {
        // Initializing the internal data structure.
        readHypergraphSize();
        builder = createHypergraph(numberOfVertices, numberOfHyperedges);
        integerLine = new int[numberOfVertices + 1];

        // Reading the hyperedges of the hypergraph.
        readHyperedges();

        // Reading the weights of the vertices, if any.
        if (hasWeightsOnVertices()) {
            readVertexWeights();
        }
    }

    /**
     * Reads the size of the hypergraph.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    private void readHypergraphSize() throws IOException {
        // Reading the next line.
        int nb = readLine();

        // Getting the size of the hypergraph.
        numberOfHyperedges = integerLine[0];
        numberOfVertices = integerLine[1];

        // Getting the hypergraph format, if any.
        if (nb == 3) {
            hmetisFormat = integerLine[2];
        }
    }

    /**
     * Reads the hyperedges of the hypergraph.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    private void readHyperedges() throws IOException {
        for (int i = 0; i < numberOfHyperedges; i++) {
            readHyperedge();
        }
    }

    /**
     * Reads a hyperedge of the hypergraph.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    private void readHyperedge() throws IOException {
        // Reading the next line.
        int nb = readLine();

        if (hasWeightsOnHyperedges()) {
            // The hyperedge to read has a weight.
            var hyperedge = joining(copyOfRange(integerLine, 1, nb));
            builder.withHyperedge(hyperedge.withWeight(integerLine[0]));

        } else {
            // The hyperedge to read has no weight.
            var hyperedge = joining(copyOfRange(integerLine, 0, nb));
            builder.withHyperedge(hyperedge);
        }
    }

    /**
     * Reads the weights of the vertices in the hypergraph.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    private void readVertexWeights() throws IOException {
        for (int v = 1; v <= numberOfVertices; v++) {
            readLine();
            builder.withVertexWeight(v, integerLine[0]);
        }
    }

    /**
     * Checks whether the read hypergraph has weights on its hyperedges.
     *
     * @return Whether hyperedges are weighted.
     */
    private boolean hasWeightsOnHyperedges() {
        return HmetisFormat.WEIGHT_ON_HYPEREDGES.isEncodedIn(hmetisFormat);
    }

    /**
     * Checks whether the read hypergraph has weights on its vertices.
     *
     * @return Whether vertices are weighted.
     */
    private boolean hasWeightsOnVertices() {
        return HmetisFormat.WEIGHT_ON_VERTICES.isEncodedIn(hmetisFormat);
    }

    /**
     * Reads the integers of the next line, and stores them in {@link #integerLine}.
     *
     * @return The number of integers that have been read.
     *
     * @throws IOException If an I/O error occurs while reading.
     */
    private int readLine() throws IOException {
        // Reading the next line of the input stream.
        var line = reader.readLine();

        // Converting the integers of the line.
        int nb = 0;
        for (var number : line.split("\\s+")) {
            integerLine[nb++] = Integer.parseInt(number);
        }
        return nb;
    }

    /**
     * Gives the hypergraph that has been read from the associated input stream.
     *
     * @return The hypergraph that has been read.
     */
    public Hypergraph getHypergraph() {
        return builder.build();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }

}
