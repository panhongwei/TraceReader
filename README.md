# TraceReader
## 1.简述
 Android 调试工具ddms的devices栏目存在一个功能叫做start method profiling，此按钮顾名思义是启动method profiling的，而Android的Method Profiling功能，是可以在一段时间内记录所有运行过的函数，最后生成一个xxx.trace文件。xxx.trace文件比较有趣，如果分析andorid源码可以知道，xxx.trace文件按线程和时间记录了method的enter和exit事件。也就是说，如果我们能够读取xxx.trace文件，那么我们就能够获取到某段时间，某个app运行过的java函数。<br>
## 2.功能
  于是乎，便有个这个工具，起名是个大难题，暂时起名叫TraceReader v1.0，目前实现了功能如下：<br>
  1.以线程的形式展示每个线程运行的方法数量。<br>
  2.树形结构展示方法调用<br>
  3.展示所有方法列表和调用者<br>
  4.支持搜索，过滤等等。。。<br>
## 3.用法
  解析trace文件，用法如下：
```Java
  File fl=new File(path);
  byte[] bytes=BytesHelper.toByteArray(fl.getPath());
  Trace trace=new Trace(bytes);
  traceThreads=new Threads(trace);
```
  
