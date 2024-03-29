package com.gitee.sop.sopauth.auth;

/**
 * @author liumf
 */
public interface AppIdManager {
    /**
     * 是否是合法的appId
     *
     * @param appId
     * @return true:合法
     */
    boolean isValidAppId(String appId);
}
