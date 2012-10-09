Connecting to a database in PSL is done through its underlying Groovy interface. In turn, Groovy can take advantage of any JDBC driver available for the database of your choice. Example usage of a few different databases is shown below.

_Note: it will be assumed that any JDBC driver appropriate for the database has been included on the classpath._
#MySql#

First be sure to import the driver into your project:
```include_sql
import groovy.sql.Sql

```

Next set up a new connection. This follows the format:
sql = Sql.newInstance( 'jdbc:mysql://serverName/dbName-CLASS;domain=domainName', 'username',
                       'password', 'net.sourceforge.jtds.jdbc.Driver' )

so for a local database called _groovydb_ listening on port _2020_ with user _usr_ and password _pass_ this would be:
```sql_con
def sql = Sql.newInstance("jdbc:mysql://localhost:2020/groovydb", 'usr','pass', "com.mysql.jdbc.Driver")

```

If database tables shard a 1:1 mapping with PSL tuples, the following would pull each row entry from the table _Persons_
```sql_run
def persons = []
sql.eachRow('Select * from Person') {
    persons << it.toRowResult()
}
```

### Translating from a Database to ground PSL predicates
_The following makes reference to [PSL Database creation](https://github.com/linqs/psl/wiki/Database-creation)_
