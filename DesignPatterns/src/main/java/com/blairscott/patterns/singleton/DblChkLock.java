package com.blairscott.patterns.singleton;

/**
 * 双重检查锁
 */
public class DblChkLock {

    private volatile static DblChkLock dblChkLock = null;

    private DblChkLock() {}

    public static DblChkLock getInstance() {
        if (dblChkLock == null) {
            synchronized (DblChkLock.class) {
                if (dblChkLock == null) {
                    dblChkLock = new DblChkLock();
                }
            }
        }
        return dblChkLock;
    }

}
