package com.iohao.little.game.action.skeleton.core.flow.interal;

import com.iohao.little.game.action.skeleton.core.exception.ActionErrorEnum;
import com.iohao.little.game.action.skeleton.core.exception.MsgException;
import com.iohao.little.game.action.skeleton.core.flow.ActionMethodExceptionProcess;

/**
 * default ActionMethodExceptionProcess
 *
 * @author 洛朱
 * @Date 2021-12-20
 */
public class DefaultActionMethodExceptionProcess implements ActionMethodExceptionProcess {
    @Override
    public MsgException processException(Throwable e) {

        if (e instanceof MsgException msgException) {
            return msgException;
        }

        // 到这里，一般不是用户自定义的错误，很可能是用户引入的第三方包的错误
        e.printStackTrace();

        return new MsgException(ActionErrorEnum.systemOtherErrCode);
    }
}
