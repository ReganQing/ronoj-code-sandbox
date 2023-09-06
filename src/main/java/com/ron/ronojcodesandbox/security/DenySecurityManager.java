package com.ron.ronojcodesandbox.security;

import java.security.Permission;

/**
 * 禁用所有的安全管理器
 *
 */
public class DenySecurityManager extends SecurityManager {
    // 拦截所有的权限
    @Override
    public void checkPermission(Permission perm) {
        throw new SecurityException("权限异常" + perm.getActions());
    }
}
