/**
 * @author bo.luo
 * @date 2020/11/12 14:45
 */
public class TestDemo {

    public static void main(String[] args) {
        String str = "123";
        System.out.println(str);
        changeStr(str);
        System.out.println(str);
    }

    public static void changeStr(String str){
        str = "456";
    }
}
