package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Main {
    /**
     * 消费型
     * @param money
     * @param consumer
     */
    public void donate(Integer money,Csm consumer){
        consumer.accept(money);
    }

    /**
     * 生产型
     * @param n
     * @param supplier
     * @return
     */
    public List<Integer> supply(Integer n,Spl supplier){
        List<Integer> list=new ArrayList<>(n);
        for (int i=0;i<n;++i){
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * 函数式接口
     * @param s
     * @param convector
     * @return
     */
    public String convert(String s,Cvt convector){
        return convector.convert(s);
    }
    public static void main(String[] argc){
        Main o=new Main();
        //case1
        o.donate(1000,money->System.out.println("捐赠了 "+money+"元"));
        //case2
        List<Integer> list=o.supply(10,()->new Random().nextInt(100));
        for(Integer n:list){
            System.out.print(n+" ");
        }
        System.out.println();
        //case3
        String s=o.convert("hello",e->e.toUpperCase(Locale.ROOT));
        System.out.println(s);
    }
}
