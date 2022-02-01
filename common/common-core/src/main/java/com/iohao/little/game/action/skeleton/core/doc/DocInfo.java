package com.iohao.little.game.action.skeleton.core.doc;

import cn.hutool.core.util.StrUtil;
import com.iohao.little.game.action.skeleton.core.ActionCommand;
import lombok.Getter;

import java.util.*;

/**
 * @author 洛朱
 * @date 2022-01-29
 */
@Getter
class DocInfo {
    String actionSimpleName;
    String classComment;
    ActionSendDocsRegion actionSendDocsRegion;

    final List<Map<String, String>> subBehaviorList = new ArrayList<>();

    public void setHead(ActionCommand subBehavior) {
        ActionCommandDoc actionCommandDoc = subBehavior.getActionCommandDoc();

        this.actionSimpleName = subBehavior.getActionControllerClazz().getSimpleName();
        this.classComment = actionCommandDoc.getClassComment();
    }


    void add(ActionCommand subBehavior) {
        Map<String, String> paramMap = new HashMap<>();
        subBehaviorList.add(paramMap);

        ActionCommandDoc actionCommandDoc = subBehavior.getActionCommandDoc();

        int cmd = subBehavior.getCmdInfo().getCmd();
        int subCmd = subBehavior.getCmdInfo().getSubCmd();

        var actionMethodReturnInfo = subBehavior.getActionMethodReturnInfo();

        paramMap.put("cmd", String.valueOf(cmd));
        paramMap.put("subCmd", String.valueOf(subCmd));
        paramMap.put("actionSimpleName", subBehavior.getActionControllerClazz().getSimpleName());
        paramMap.put("methodName", subBehavior.getActionMethodName());
        paramMap.put("methodComment", actionCommandDoc.getComment());
        paramMap.put("methodParam", "");
        paramMap.put("returnTypeClazz", actionMethodReturnInfo.isVoid() ? "" : actionMethodReturnInfo.getReturnTypeClazz().getName());
        paramMap.put("lineNumber", String.valueOf(actionCommandDoc.getLineNumber()));

        // 方法参数
        for (ActionCommand.ParamInfo paramInfo : subBehavior.getParamInfos()) {
            if (paramInfo.isExtension()) {
                continue;
            }

            Class<?> paramClazz = paramInfo.getParamClazz();
            paramMap.put("methodParam", paramClazz.getName());
        }
    }

    String render() {
        if (this.subBehaviorList.isEmpty()) {
            return "";
        }

        String separator = System.getProperty("line.separator");

        List<String> lineList = new ArrayList<>();

        String templateHead = "==================== {} {} ====================";
        lineList.add(StrUtil.format(templateHead, this.actionSimpleName, this.classComment));

        String subActionCommandTemplate =
                "路由: {cmd} - {subCmd}  --- 【{methodComment}】 --- 【{actionSimpleName}:{lineNumber}】【{methodName}】";

        for (Map<String, String> paramMap : subBehaviorList) {

            int cmd = Integer.parseInt(paramMap.get("cmd"));
            int subCmd = Integer.parseInt(paramMap.get("subCmd"));

            ActionSendDoc actionSendDoc = this.actionSendDocsRegion.getActionSendDoc(cmd, subCmd);

            String format = StrUtil.format(subActionCommandTemplate, paramMap);
            lineList.add(format);

            // 方法参数
            if (StrUtil.isNotEmpty(paramMap.get("methodParam"))) {
                format = StrUtil.format("    方法参数: {methodParam}", paramMap);
                lineList.add(format);
            }

            // 方法返回值
            if (StrUtil.isNotEmpty(paramMap.get("returnTypeClazz"))) {
                format = StrUtil.format("    方法返回值: {returnTypeClazz}", paramMap);
                lineList.add(format);
            }

            // 广播推送
            if (Objects.nonNull(actionSendDoc)) {
                Class<?> dataClass = actionSendDoc.getDataClass();
                format = StrUtil.format("    广播推送: {}", dataClass.getName());
                lineList.add(format);
            }

            lineList.add(" ");
        }

        lineList.add(separator);

        return String.join(separator, lineList);
    }
}
