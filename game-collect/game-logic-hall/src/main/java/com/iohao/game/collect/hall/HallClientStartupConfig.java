package com.iohao.game.collect.hall;

import com.iohao.game.collect.common.GameBarSkeletonConfig;
import com.iohao.game.collect.common.ModuleKeyCont;
import com.iohao.game.collect.hall.action.LoginAction;
import com.iohao.game.collect.logic.GameLogicCommonInit;
import com.iohao.little.game.action.skeleton.core.BarSkeleton;
import com.iohao.little.game.action.skeleton.core.BarSkeletonBuilder;
import com.iohao.little.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.little.game.net.client.core.ClientStartupConfig;
import com.iohao.little.game.net.client.core.RemoteAddress;
import com.iohao.little.game.net.message.common.ModuleKeyKit;
import com.iohao.little.game.net.message.common.ModuleMessage;

/**
 * @author 洛朱
 * @date 2022-02-02
 */
public class HallClientStartupConfig implements ClientStartupConfig {
    @Override
    public BarSkeleton createBarSkeleton() {
        BarSkeletonBuilderParamConfig config = new BarSkeletonBuilderParamConfig()
                // 扫描 LoginAction.class 所在包
                .addActionController(LoginAction.class);

        BarSkeletonBuilder builder = GameBarSkeletonConfig.createBuilder(config);

        return builder.build();
    }

    @Override
    public ModuleMessage createModuleMessage() {
        // 逻辑服的模块id，标记不同的逻辑服模块。
        int moduleId = ModuleKeyCont.userModuleId;
        var moduleKey = ModuleKeyKit.getModuleKey(moduleId);

        // 逻辑服的信息描述
        ModuleMessage moduleMessage = new ModuleMessage();
        moduleMessage.setModuleKey(moduleKey);
        moduleMessage.setName("游戏服 用户");
        moduleMessage.setDescription("用户业务");

        // 子模块 逻辑服信息
        return moduleMessage;
    }

    @Override
    public RemoteAddress createRemoteAddress() {
        return GameLogicCommonInit.createRemoteAddress();
    }
}
