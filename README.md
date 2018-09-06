# spark-study
spark-study



# spark sql 调优
使用缓存
sqlContext.cacheTable("tableName")  
dataFrame.cache()  
取消缓存
sqlContext.uncacheTable("tableName")  


# Transformation操作

| Transformation操作 | 描述 |
| -------------------| ------ | 
| map(func) | 对源RDD中的每个元素调用func,生成新的元素，这些新元素构成新的RDD返回 | 
| flatMap(func)| 与Map类似，但每个输入的RDD成员可以产生0或多个输出成员(所以func的返回值是Seq类型) |
| filter(func)| 对RDD进行过滤，对源RDD中的每个元素调用func，如果返回true，则保留该元素，由这些元素构成新的RDD并返回 |
| mapPartitions(func)| 与map类似，但map中的func作用的是RDD中的每个元素，而mapPartitions中的func作用的对象是RDD的一整个分区。所以func的类型是Iterator&lt;T&gt; => Iterator&lt;U&gt;,其中T是输入RDD元素的类型|
| mapPartitionWithIndex(func)|与mapPartitions类似，但输入会多提供一个整数表示分号的编号，所以func的类型是（Int,Iterator&lt;T&gt;） =>Iterator&lt;U&gt;,多了一个Int|
|union(otherDataset)|合并两个RDD，不去重，要求两个RDD中的元素类型一致|
|distinct([numTasks])|对原RDD进行去重操作，返回的RDD中没有重复的成员|
|groupByKey([numTasks])|对&lt;key,value&gt;结构的RDD进行类似RMDB的group by聚合操作，具有相同key的RDD成员的value会被聚合在一起，返回的RDD结构是（key,Iterator<value>）。注意:groupByKey除了聚合，不对value进行任何操作，除非你调用完groupByKey之后没有进一步的操作，直接调用Action操作，否则建议使用下面的reduceByKey或aggregateByKey以获取更高的性能。注意：groupByKey涉及shuffle操作，默认输出的并发数量取决于数据输入RDD的分区数量，也可以通过可选参数[numTasks]手工指定|
|||


