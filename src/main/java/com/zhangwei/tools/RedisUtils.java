package com.zhangwei.tools;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * redisС���� ����������������
 * Created by Administrator on 2015/9/20.
 */
public class RedisUtils {

    //��־
    private static Logger log = LoggerFactory.getLogger(RedisUtils.class);

//    private static Jedis jedis;//����Ƭ��ͻ�������
    private static JedisPool jedisPool;//����Ƭ���ӳ�
    private static HessianProxyFactory hessianProxyFactory;
    private static ReentrantReadWriteLock lock;
    //    private ShardedJedis shardedJedis;//��Ƭ��ͻ�������
//    private ShardedJedisPool shardedJedisPool;//��Ƭ���ӳ�
    static {
        // �ػ�������
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config,"127.0.0.1",6379,200);
//        jedis = jedisPool.getResource();
        //hessian��ʼ��
        hessianProxyFactory = new HessianProxyFactory();
        //��������
        lock = new ReentrantReadWriteLock();
    }

    /**
     * ��redis�л�ȡ����
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public  static <T> T get(String key,Class<T> type){

//        ByteArrayInputStream in = new ByteArrayInputStream(a);
//        Hessian2Input hessian2Input = new Hessian2Input();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String a = jedis.get(key);
            if(a == null) {
                return null;
            }
            T result = JSON.parseObject(a, type);
            log.info("get [key:" + key.toString() + "|value:" + result.toString() + "] success");
            return result;

        }catch (Exception e){
            log.info("��ȡ��Դʧ��,��ȡkey:"+key+"ʧ��");
            return null;
        }finally {
            jedisPool.returnResourceObject(jedis);
        }
    }

    public static boolean put(String key, Object value) throws IOException {
        return put(key,value,true);
    }

    /**
     * ��KV�ŵ�redis��
     * @param key key
     * @param value value
     * @param cover �Ƿ񸲸�,true:ɾ��redis�Ѿ��е�KV,���Ҵ洢,false:����Ѿ�����key�����κβ���
     * @return �洢�Ƿ�ɹ�
     * @throws IOException
     */
    public static boolean put(String key, Object value, boolean cover) throws IOException {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Transaction tx = jedis.multi();
            Response<Boolean> exist = tx.exists(key);
            if (cover && exist.get()) {
                tx.del(key);
            }
            if(value!=null && key!=null){
                String result = JSON.toJSONString(value);
                tx.append(key, result);
                log.info("put [key:" + value.toString() + "|value:" + value.toString() + "] success");
            }
            tx.exec();
            return true;
        }catch (Exception e){
            log.info("put [key:" + value.toString() + "|value:" + value.toString() + "] failed");
            return false;
        }finally {
            jedisPool.returnResourceObject(jedis);
        }

    }

//    public static void putWithoutLock(String key, Object value, boolean cover){
//
//        boolean exist = jedis.exists(key);
//        if (cover && exist) {
//            jedis.del(key);
//        }
//        if(value!=null && key!=null){
//            String result = JSON.toJSONString(value);
//            jedis.append(key,result);
//            log.info("put [key:" + value.toString() + "|value:" + value.toString() + "] success");
//        }
//    }

    /**
     * ɾ��ָ��Ԫ��
     * @param key
     */
    public synchronized static void del(String key){
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        log.info("del [Key:" + key + "] success!");
    }

    public static void delAll(){
        Jedis jedis = jedisPool.getResource();
        String key = jedis.randomKey();
        while (!"".equals(key) && key!=null){
            jedis.del(key);
            log.info("del [Key:" + key + "] success!");
            key = jedis.randomKey();
        }
    }

    public static boolean isExist(String key){

        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisPool.returnResourceObject(jedis);
        }
        return false;
    }
}
