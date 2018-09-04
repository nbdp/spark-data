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





