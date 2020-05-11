package error.enums;

import enums.EnumInterface;

public enum SqlMethodError implements EnumInterface {
    MTTHOD_NOT_EXIST(-1000,"方法不存在！"),
    SQL_NOT_EXIST(-1001,"SQL不存在！");

    private int code;
    private String desc;

    SqlMethodError(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
    @Override
    public int getValue() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
