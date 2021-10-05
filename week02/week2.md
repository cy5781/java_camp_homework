# week02 homework
4.（必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。


串行GC 单线程垃圾回收 

java -Xmx1g -Xms1g -XX:+UseAdaptiveSizePolicy -XX:-UseSerialGC -jar 


并行GC 并行垃圾回收 

java -Xmx1g -Xms1g -XX:+UseAdaptiveSizePolicy -XX:-UseParallelGC -jar 

CMS GC 以获取最短回收停顿时间为目标，采用“标记-清除”算法，分 4 大步进行垃圾收集

java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:-UseConcMarkSweepGC -jar

G1 GC G1是一个有整理内存过程的垃圾收集器，不会产生很多内存碎片 感觉后面会优化的越来越好从而取代CMS

堆内存
演练gateway*.jar时发现 串行 并行GC堆内存结构类似
CMS收集器仅作用于老年代的收集，是基于标记-清除算法的。
G1重新定义了堆空间，打破了原有的分代模型，将堆划分为一个个区域。

根据wrk压力测试的结果看 CMS和G1 性能提升较好 
