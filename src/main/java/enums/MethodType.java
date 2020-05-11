package enums;

public enum  MethodType implements EnumInterface{


    INSERT(1001,"方法不存在！"),
    DELETE(1002,"SQL不存在！"),
    UPDATE(1003,"方法不存在！"),
    SELECT(1004,"SQL不存在！");


    private int code;
    private String desc;

    MethodType(int code,String desc){
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
