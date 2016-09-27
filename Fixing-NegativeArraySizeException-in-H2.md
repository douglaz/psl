From Pigi Kouki:
>I run the MPE Inference with a relatively big dataset as input and I got the following error during the mpeInference step:

    Exception in thread "main" java.lang.RuntimeException: Error executing database query.
        at edu.umd.cs.psl.database.rdbms.RDBMSDatabase.executeQuery(RDBMSDatabase.java:612)
        at edu.umd.cs.psl.model.atom.PersistedAtomManager.executeQuery(PersistedAtomManager.java:108)
        at edu.umd.cs.psl.model.kernel.rule.AbstractRuleKernel.groundAll(AbstractRuleKernel.java:81)
        at edu.umd.cs.psl.application.util.Grounding.groundAll(Grounding.java:59)
        at edu.umd.cs.psl.application.util.Grounding.groundAll(Grounding.java:43)
        at edu.umd.cs.psl.application.inference.MPEInference.mpeInference(MPEInference.java:106)
        at edu.umd.cs.psl.application.inference.MPEInference$mpeInference.call(Unknown Source)
        ...  
    Caused by: org.h2.jdbc.JdbcSQLException: General error: "java.lang.NegativeArraySizeException"; SQL statement:
    SELECT DISTINCT t1.UniqueID_0 AS U1,t1.UniqueID_1 AS P1,t2.UniqueID_1 AS U2 FROM RATING_predicate t1, SIM_USERS_predicate t2 WHERE ((t1.parti$
        at org.h2.message.Message.getSQLException(Message.java:110)
        at org.h2.message.Message.convert(Message.java:287)
        at org.h2.message.Message.convert(Message.java:248)
        at org.h2.command.Command.executeQuery(Command.java:134)
        at org.h2.jdbc.JdbcStatement.executeQuery(JdbcStatement.java:76)
        at edu.umd.cs.psl.database.rdbms.RDBMSDatabase.executeQuery(RDBMSDatabase.java:575)
        ... 26 more
    Caused by: java.lang.NegativeArraySizeException
        at org.h2.util.ValueHashMap.reset(ValueHashMap.java:51)
        at org.h2.util.ValueHashMap.rehash(ValueHashMap.java:58)
        at org.h2.util.HashBase.checkSizePut(HashBase.java:79)
        at org.h2.util.ValueHashMap.put(ValueHashMap.java:78)
        at org.h2.util.ValueHashMap.rehash(ValueHashMap.java:62)
        at org.h2.util.HashBase.checkSizePut(HashBase.java:79)
        at org.h2.util.ValueHashMap.put(ValueHashMap.java:78)
        at org.h2.util.ValueHashMap.rehash(ValueHashMap.java:62)
        at org.h2.util.HashBase.checkSizePut(HashBase.java:79)
        at org.h2.util.ValueHashMap.put(ValueHashMap.java:78)
        at org.h2.result.LocalResult.addRow(LocalResult.java:262)
        at org.h2.command.dml.Select.queryFlat(Select.java:499)
        at org.h2.command.dml.Select.queryWithoutCache(Select.java:558)
        at org.h2.command.dml.Query.query(Query.java:243)
        at org.h2.command.CommandContainer.query(CommandContainer.java:81)
        at org.h2.command.Command.executeQuery(Command.java:132)
        ... 28 more

>I searched online and find the following useful link that helped me solve the problem

>https://groups.google.com/forum/#!topic/h2-database/XeFtWY_vvBQ

>So I needed to download the source code for h2-1.2.126-sources.jar (the version of h2 that PSL uses), change the line in HashBase.java file 

    maxSize = (int) (len * MAX_LOAD / 100L); 

>to 

    maxSize = (int) (((long)len) * MAX_LOAD / 100L); 

>and then create the new jar of this library and included it in the project.