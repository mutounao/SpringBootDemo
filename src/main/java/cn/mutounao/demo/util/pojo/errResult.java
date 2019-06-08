package cn.mutounao.demo.util.pojo;

public enum errResult {
    SUCCESS(20000, "成功"),
    NO_PERMISSION(25000, "权限不足"),
    AUTH_ERROR(25500, "认证失败"),
    SERVER_ERROR(30000, "服务器异常"),
    PARAMS_ERROR(10000, "参数错误"),
    JSON_PARSE_ERROR(15500, "Json解析错误"),
    ILLEAGAL_STRING(16500, "非法字符串"),
    UNKNOW_ERROR(90000, "未知错误");


    private int code;
    private String msg;

    errResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

