package Enumeration;


public enum ResponseCode {

    SUCCESS(200,"请求成功"),
    REDIRECT(300,"资源重定向"),
    ERROR(500,"错误");

    private String message;
    private int code;

    ResponseCode(int code, String message) {
        this.code=code;
        this.message=message;
    }


    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
