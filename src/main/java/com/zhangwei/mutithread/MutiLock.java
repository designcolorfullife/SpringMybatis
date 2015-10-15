package com.zhangwei.mutithread;

/**
 * Created by Administrator on 2015/9/29.
 */
public class MutiLock extends Parent{

    public static void main(String[] args) {
        MutiLock m = new MutiLock();
        m.pint();
    }

    @Override
    public synchronized void pint() {
        System.out.print("zilei de suo");
        super.pint();
    }
}
class Parent{
    public synchronized void pint(){
        System.out.print("fuleidesuo");
    }
}