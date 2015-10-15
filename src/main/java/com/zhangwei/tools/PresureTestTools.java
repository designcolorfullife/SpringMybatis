package com.zhangwei.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 用于并发测试的小工具,用来代码的并发测试
 * Created by Administrator on 2015/9/20.
 */
public class PresureTestTools <T>{

    private static Logger log = LoggerFactory.getLogger(PresureTestTools.class);

    private ExecutorService executorService;
    List<Callable<T>> tasks = new LinkedList<Callable<T>>();

    /**
     * 构造函数
     * @param callable 需要执行的函数
     * @param paralleluser 并发数
     * @param totaltasks 总共的任务数
     */
    public PresureTestTools(Callable<T> callable,int paralleluser, int totaltasks){
        executorService = Executors.newFixedThreadPool(paralleluser);
        for(int i=1 ;i<=totaltasks; i++){
            tasks.add(callable);
        }
    }

    /**
     * 执行
     * @return 每个任务返回T,这里返回T的集合
     */
    public List<T> run(){

        List<T> results = new LinkedList<T>();
        List<Future<T>> temp = null;
        long start = System.currentTimeMillis();
        try {
            temp = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Future<T> t : temp){
            try {
                results.add(t.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        log.info("Task finished,cost time:"+(end-start)+"ms");
        executorService.shutdown();
        return results;
    }
}
