这个压缩包中有三个目录，也就是三个项目，全都基于java8。
首先logGen为生成hadoop测试用的log文件的，这是一个纯Java项目其主要运行的类位于/logGen/src/Main.java
Test类用于查看其中的额前两白条内容。（如果要运行测试的话，需要修改其中的文件路径，两个文件里的都要改）
然后hadoopDemo，这是用来分析log文件的mapreduce程序的，这是一个maven项目，所以需要有maven才可以运行。其中用来启动分析程序的主类为\hadoopDemo\src\main\java\org\example\logAnalyse\AnalyseDriver.java。运行这个程序的的话需要在启动命令加上输入，输出路径，或者修改文件中路径，将对运行时参数的使用改成需要的路径。
另外在\hadoopDemo\src\main\java\org\example\wordcount，还有一个wordcouont的测试程序，这个程序是用来测试当前环境是否完善，可以运行，所以没有删除。
最后是activemqDemo，这个是用来完成actiemq实验的程序，是一个纯java的程序，但是需要导入解压activemq后其中的jar包，才可以运行。其中主要的测试类为\activemqDemo\src\com\example\demo\Test.java。
这所有的项目的实现效果都在实验报告中进行展示。
