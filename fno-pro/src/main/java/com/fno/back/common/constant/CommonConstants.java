package com.fno.back.common.constant;

/***
 * @des
 * @author Ly
 * @date 2022/7/17
 */

public class CommonConstants {
    //单据信息
    public static final String APPLY_VARS_ORDERINFO = "orderinfo";
    //标题
    public static final String APPLY_VARS_TITLE = "title";
    //申请人用户名
    public static final String APPLY_VARS_USERID = "userId";
    //申请人昵称
    public static final String APPLY_VARS_NICKNAME = "nickname";
    //单据申请时间
    public static final String APPLY_VARS_APPLYTIME = "applytime";
    //是否是自定义表单
    public static final String APPLY_VARS_ISCUSTOMFORM = "iscustomform";
    public static final String APPLY_VARS_APPSTATUS = "appStatus";
    //业务表表名
    public static final String APPLY_VARS_BUSINESSTABLENAME = "businessTableName";

    /****
     * 流程提交状态
     */
    public static final String FLOW_STATUS_START = "0";
    public static final String FLOW_STATUS_SUBMIT = "1";
    public static final String FLOW_STATUS_FINISH = "2";
    public static final String FLOW_STATUS_REJECT = "3";

    /***
     * 流程结束类型
     */
    public static final String FLOW_FINISH_TYPE = "flow_finish_type";

    /***
     * 流程结束类型---普通结束
     */
    public static final String FLOW_FINISH_TYPE_AGREE = "agree";
    /***
     * 流程结束类型
     */
    public static final String FLOW_FINISH_TYPE_REJECT = "reject";


    /***
     * 通用是、否
     */
    public static final String YES = "Y";
    public static final String NO = "N";

    /***
     * 通用可用、不可用
     */
    public static final String ABLE = "0";

    public static final String DISABLE = "1";

    //提交任务，办理类型。处理方式。0：办理。1：转办。2：委托
    public static final String TASK_OPERATE_TYPE_COMMIT="0";
    public static final String TASK_OPERATE_TYPE_TRANS="1";
    public static final String TASK_OPERATE_TYPE_DELEGATE="2";

    public static final String TASK_CIMMIT_IS_CCED = "1";

}
