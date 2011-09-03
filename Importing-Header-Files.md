At the top of a groovy file, you must import all the relevant java and groovy files that you wish to use. The list below are the standard set that should be imported.

``import edu.umd.cs.psl.groovy.*; <br>
import edu.umd.cs.psl.database.RDBMS.DatabaseDriver;
import edu.umd.cs.psl.ui.functions.textsimilarity.*;
import edu.umd.cs.psl.model.function.AttributeSimilarityFunction;
import edu.umd.cs.psl.model.predicate.Predicate;
import edu.umd.cs.psl.model.argument.type.*;
import edu.umd.cs.psl.model.predicate.type.*;
import edu.umd.cs.psl.config.*;
import edu.umd.cs.psl.groovy.experiments.ontology.*;
import java.lang.management.*;
import edu.umd.cs.psl.ui.aggregators.*;"