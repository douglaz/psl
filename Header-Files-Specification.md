At the top of a Groovy file, you must import all the relevant Java and Groovy packages that you wish to use. The list below is a standard set that may be helpful to import in your program

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
