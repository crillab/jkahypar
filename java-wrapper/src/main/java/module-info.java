/**
 * The {@code fr.univartois.cril.jkahypar} module provides a partitioning tool
 * for hypergraphs based on the native implementation proposed in
 * <a href="https://github.com/SebastianSchlag/kahypar">KaHyPar</a>.
 *
 * @author Romain WALLON
 * @version 0.1.0
 */

module fr.univartois.cril.jkahypar {

    // Exported packages.

    exports fr.univartois.cril.jkahypar;

    exports fr.univartois.cril.jkahypar.hypergraph;

    exports fr.univartois.cril.jkahypar.tools;

    // Required external modules for accessing native implementation.

    requires com.sun.jna;

}
