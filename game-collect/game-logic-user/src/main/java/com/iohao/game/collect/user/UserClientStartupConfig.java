package com.iohao.game.collect.user;

import com.iohao.game.collect.common.GameBarSkeletonConfig;
import com.iohao.game.collect.common.ModuleKeyCont;
import com.iohao.game.collect.logic.GameLogicCommonInit;
import com.iohao.game.collect.user.action.LoginAction;
import com.iohao.little.game.action.skeleton.core.BarSkeleton;
import com.iohao.little.game.net.client.core.ClientStartupConfig;
import com.iohao.little.game.net.client.core.RemoteAddress;
import com.iohao.little.game.net.message.common.ModuleKeyKit;
import com.iohao.little.game.net.message.common.ModuleMessage;

/**
 * @author 洛朱
 * @date 2022-01-12
 */
public class UserClientStartupConfig implements ClientStartupConfig {
    @Override
    public BarSkeleton createBarSkeleton() {
        // 扫描 AppleAction.class 所在包
        BarSkeleton barSkeleton = GameBarSkeletonConfig.newBarSkeleton(LoginAction.class);
        return barSkeleton;
    }

    @Override
    public ModuleMessage createModuleMessage() {

        int moduleId = ModuleKeyCont.userModuleId;
        var moduleKey = ModuleKeyKit.getModuleKey(moduleId);

        ModuleMessage moduleMessage = new ModuleMessage();
        moduleMessage.setModuleKey(moduleKey);
        moduleMessage.setName("游戏服 用户");
        moduleMessage.setDescription("用户业务");

        return moduleMessage;
    }

    @Override
    public RemoteAddress createRemoteAddress() {
        return GameLogicCommonInit.createRemoteAddress();
    }
}
