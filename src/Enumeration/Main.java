package Enumeration;

import java.util.Arrays;

/**
 * Jvm会为每个枚举实例对应生成一个类对象
 * 这个类对象是用public static final修饰的
 * 在static代码块中初始化，是一个单例。
 */
public class Main {
    public static void main(String[] argc){
        ResponseCode responseCode1=ResponseCode.REDIRECT;
        ResponseCode responseCode2=ResponseCode.SUCCESS;
        System.out.println(responseCode1.getCode()+" "+ responseCode1.getMessage());
        System.out.println(responseCode2.getCode()+" "+ responseCode2.getMessage());
        //values方法 返回枚举类型的所有成员
        ResponseCode[] arr=ResponseCode.values();
        System.out.println(Arrays.toString(arr));
        //valueOf方法 通过字符串获取单个枚举对象
        ResponseCode responseCode3=ResponseCode.valueOf("ERROR");
        System.out.println(responseCode3.getCode()+" "+responseCode3.getMessage());
        //ordinal方法 获取一个成员在枚举中的索引位置
        System.out.println(responseCode3.ordinal());
    }
}
