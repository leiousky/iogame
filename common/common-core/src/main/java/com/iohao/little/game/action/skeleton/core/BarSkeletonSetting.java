package com.iohao.little.game.action.skeleton.core;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务框架 Setting
 *
 * @author 洛朱
 * @Date 2021-12-20
 */
@Getter
@Setter
public class BarSkeletonSetting {
    /**
     * <pre>
     *     true: action 对象是 single.
     *     false: 每次都创建新的 action 对象.
     * </pre>
     */
    boolean createSingleActionCommandController = true;

    /** action 的默认长度 (一级 cmd) */
    int actionMaxLen = 127;
    /** 子 action 的默认长度 (二级 subCmd) */
    int subActionMaxLen = 127;

    /** 是否保留 action Map */
    boolean keepActionMap = true;

    /** true action 日志打印 */
    boolean printAction = true;
    /** false action 日志打印短名称(类、参数名、返回值) */
    boolean printActionShort = true;
    /** true inout 日志打印 */
    boolean printInout = true;
    /** true handler 日志打印 */
    boolean printHandler = true;

    /** inOut 的 in 。 true 开启 */
    boolean openIn = true;
    /** inOut 的 out 。 true 开启 */
    boolean openOut = true;
    /** 解析类型 */
    ParseType parseType = ParseType.JAVA;

    /**
     * true : 业务参数开启 JSR303、JSR 349、JSR 380 验证规范
     *
     * <p>
     * 需要在你的项目 maven 中引入
     * <pre>
     *         &lt;!-- EL实现。在Java SE环境中，您必须将实现作为依赖项添加到POM文件中-->
     *         &lt;dependency>
     *             &lt;groupId>org.glassfish&lt;/groupId>
     *             &lt;artifactId>jakarta.el&lt;/artifactId>
     *             &lt;version>4.0.1&lt;/version>
     *         &lt;/dependency>
     *
     *         &lt;!-- 验证器Maven依赖项 -->
     *         &lt;dependency>
     *             &lt;groupId>jakarta.validation&lt;/groupId>
     *             &lt;artifactId>jakarta.validation-api&lt;/artifactId>
     *             &lt;version>3.0.0&lt;/version>
     *         &lt;/dependency>
     * </pre>
     */
    boolean validator = false;
}