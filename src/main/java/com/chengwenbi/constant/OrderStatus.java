package com.chengwenbi.constant;

public class OrderStatus {

    public static final Integer BEFORE_CREATE = 0;//空单据未添加数据

    public static final Integer DAIAPPROVAL = 1;//待审批

    public static final Integer APPROVALING = 2;//审批中

    public static final Integer DISAGREE = 3;//不同意

    public static final Integer HIRE = 4;//租用中

    public static final Integer REPAY = 5;//归还

    public static final Integer DESTROY = 6;//报废

    public static String getStateName(Integer id){
        String name = "";
        switch (id) {
            case 1:
                name = "待审批";
                break;
            case 2:
                name = "审批中";
                break;
            case 3:
                name = "否决";
                break;
            case 4:
                name = "租用中";
                break;
            case 5:
                name = "归还";
                break;
            case 6:
                name = "报废";
                break;

        }
        return name;
    }
}
