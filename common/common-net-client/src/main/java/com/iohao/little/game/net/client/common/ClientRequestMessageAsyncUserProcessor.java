package com.iohao.little.game.net.client.common;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.AsyncUserProcessor;
import com.iohao.little.game.action.skeleton.core.BarSkeleton;
import com.iohao.little.game.action.skeleton.core.DefaultParamContext;
import com.iohao.little.game.action.skeleton.protocol.RequestMessage;
import com.iohao.little.game.net.client.BoltClientServerSetting;
import lombok.Setter;

/**
 * 客户端请求处理器
 * <pre>
 *     通过业务框架把请求派发给指定的业务类来处理
 * </pre>
 *
 * @author 洛朱
 * @Date 2021-12-20
 */
public class ClientRequestMessageAsyncUserProcessor extends AsyncUserProcessor<RequestMessage> {

    @Setter
    BoltClientServerSetting boltClientServerSetting;

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, RequestMessage request) {
        
        // TODO: 2021-12-14 这里可以使用对象池技术
        DefaultParamContext paramContext = new DefaultParamContext();
        paramContext.setBizCtx(bizCtx);
        paramContext.setAsyncCtx(asyncCtx);
        paramContext.setServerContext(boltClientServerSetting.getBoltClientProxy());

        // TODO: 2021-12-14 这里可以使用领域事件
        BarSkeleton barSkeleton = boltClientServerSetting.getBarSkeleton();
        // 通过业务框架把请求派发给指定的业务类来处理
        barSkeleton.handle(paramContext, request);
    }

    /**
     * 指定感兴趣的请求数据类型，该 UserProcessor 只对感兴趣的请求类型的数据进行处理；
     * 假设 除了需要处理 MyRequest 类型的数据，还要处理 java.lang.String 类型，有两种方式：
     * 1、再提供一个 UserProcessor 实现类，其 interest() 返回 java.lang.String.class.getName()
     * 2、使用 MultiInterestUserProcessor 实现类，可以为一个 UserProcessor 指定 List<String> multiInterest()
     */
    @Override
    public String interest() {
        return RequestMessage.class.getName();
    }
}