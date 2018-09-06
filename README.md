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
|groupByKey([numTasks])||