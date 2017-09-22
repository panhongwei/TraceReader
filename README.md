# TraceReader
## 1.简述
 Android 调试工具ddms的devices栏目存在一个功能叫做start method profiling，此按钮顾名思义是启动method profiling的，而Android的Method Profiling功能，是可以在一段时间内记录所有运行过的函数，最后生成一个xxx.trace文件。xxx.trace文件比较有趣，如果分析andorid源码可以知道，xxx.trace文件按线程和时间记录了method的enter和exit事件。也就是说，如果我们能够读取xxx.trace文件，那么我们就能够获取到某段时间，某个app运行过的java函数。<br>
## 2.功能
  于是乎，便有个这个工具，目前更新到TraceReader v1.1，实现了如下功能：<br>
  1）支持拖拽解析xx.trace。<br>
 ![image](http://123.207.98.15:8080/image/9.png)<br>
  2）支持显示线程。<br>
![image](http://123.207.98.15:8080/image/10.png)<br>
  3）支持树形显示方法调用<br>
![image](http://123.207.98.15:8080/image/13.png)<br>
  4）支持按列显示运行方法<br>
![image](http://123.207.98.15:8080/image/22.png)<br>
  5）支持搜索<br>
![image](http://123.207.98.15:8080/image/10.png)<br>
  6）支持复制、重命名<br>
![image](http://123.207.98.15:8080/image/16.png)<br>
![image](http://123.207.98.15:8080/image/17.png)<br>
  7）支持显示调用时间<br>
![image](http://123.207.98.15:8080/image/18.png)<br>
![image](http://123.207.98.15:8080/image/19.png)<br>
## 3.用法
  解析trace文件，用法如下：
```Java
  byte[] bytes=BytesHelper.toByteArray(fl.getPath());
  Trace trace=new Trace(bytes);		 
  trace.getThreadList();
```
## 3.原理
TODO….
  
