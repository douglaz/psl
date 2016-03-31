At the top of a groovy file, you must import all the relevant java and groovy files that you wish to use. The list below is a standard set that should be imported.

```
import edu.umd.cs.psl.config.*
import edu.umd.cs.psl.groovy.*;
import edu.umd.cs.psl.database.DataStore;
import edu.umd.cs.psl.database.rdbms.RDBMSDataStore;
import edu.umd.cs.psl.database.rdbms.driver.H2DatabaseDriver;
import edu.umd.cs.psl.database.rdbms.driver.H2DatabaseDriver.Type;
import edu.umd.cs.psl.model.atom.GroundAtom;
import edu.umd.cs.psl.model.atom.QueryAtom;
import edu.umd.cs.psl.model.predicate.Predicate;
import edu.umd.cs.psl.model.term.*;

```
