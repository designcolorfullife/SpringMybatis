package com.zhangwei.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ���ڲ������Ե�С����,��������Ĳ�������
 * Created by Administrator on 2015/9/20.
 */
public class PresureTestTools <T>{

    private static Logger log = LoggerFactory.getLogger(PresureTestTools.class);

    private ExecutorService executorService;
    List<Callable<T>> tasks = new LinkedList<Callable<T>>();

    /**
     * ���캯��
     * @param callable ��Ҫִ�еĺ���
     * @param paralleluser ������
     * @param totaltasks �ܹ���������
     */
    public PresureTestTools(Callable<T> callable,int paralleluser, int totaltasks){
        executorService = Executors.newFixedThreadPool(paralleluser);
        for(int i=1 ;i<=totaltasks; i++){
            tasks.add(callable);
        }
    }

    /**
     * ִ��
     * @return ÿ�����񷵻�T,���ﷵ��T�ļ���
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
