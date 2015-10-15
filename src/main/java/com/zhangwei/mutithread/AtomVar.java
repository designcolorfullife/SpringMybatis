package com.zhangwei.mutithread;

import com.zhangwei.tools.PresureTestTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2015/9/29.
 */
public class AtomVar {

    private static final Logger logger = LoggerFactory.getLogger(AtomVar.class);

    public static void main(String[] args){
        final AtomicLong atomicLong = new AtomicLong(0);
        PresureTestTools presureTestTools = new PresureTestTools(new Callable() {
            @Override
            public Object call() throws Exception {
                long a = atomicLong.incrementAndGet();
                return a;
            }
        },500, 10000);
        List<Long> result = presureTestTools.run();
        Map<Long, Boolean> test = new HashMap<Long, Boolean>();
        for(Long r : result){
            Boolean b = test.get(r);
            if(b!=null){
                logger.info("∑¢œ÷÷ÿ∏¥"+r);
            }else {
                test.put(r,Boolean.TRUE);
            }
        }
        Collections.sort(result);
        print(result.toString());
    }

    public static void print(Object a){
        System.out.println(a.toString());
    }
}
