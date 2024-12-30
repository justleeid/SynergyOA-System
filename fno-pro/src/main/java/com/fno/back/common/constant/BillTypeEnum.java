package com.fno.back.common.constant;
/**
 * @author Ly
 * @Description 单据类型枚举类
 * @Classname BillTypeEnum
 * @Date 2023/06/02
 */
public enum BillTypeEnum {

    /**
     * 单据类型枚举
     */
    //仓库，采购，销售----ERP
    MATERIAL("material","物料","pl_material","");
    /**
     * 单据类型
     */
    private String billType;

    /**
     * 单据名称
     */
    private String billName;

    /**
     * 单据对应表名
     */
    private String tableName;

    /****
     * 启动流程
     */
    private String defKey;

    /**
     * 构造方法
     * @param billType  单据类型
     * @param billName  单据名称
     * @param tableName 单据状态更新类路径
     */
    private BillTypeEnum(String billType, String billName, String tableName,String defKey){
       this.billType = billType;
       this.billName = billName;
       this.tableName= tableName;
       this.defKey = defKey;
    }

    public String getBillType(){
       return billType;
    }

    public String getBillName(){
       return billName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getDefKey() {
        return defKey;
    }

    /**
     *  根据键(billType),获取枚举的值(billName)
     *
     * @param billType 单据编码
     * @return
     */
    public static String getBillName(String billType) {
        BillTypeEnum[] BillTypeEnums = values();
        for (BillTypeEnum BillTypeEnum : BillTypeEnums) {
            if (BillTypeEnum.getBillType().equals(billType)) {
                return BillTypeEnum.getBillName();
            }
        }
        return null;
    }


    /**
     *  根据键(billType),获取枚举的值(className)
     *
     * @param billType 单据编码
     * @return
     */
    public static String getTableName(String billType) {
        BillTypeEnum[] classNameEnums = values();
        for (BillTypeEnum classNameEnum : classNameEnums) {
            if (classNameEnum.getBillType().equals(billType)) {
                return classNameEnum.getTableName();
            }
        }
        return null;
    }

    /***
     * 获取启动流程编号
     * @param billType
     * @return
     */
    public static String getDefKey(String billType) {
        BillTypeEnum[] classNameEnums = values();
        for (BillTypeEnum classNameEnum : classNameEnums) {
            if (classNameEnum.getBillType().equals(billType)) {
                return classNameEnum.getDefKey();
            }
        }
        return null;
    }
}
