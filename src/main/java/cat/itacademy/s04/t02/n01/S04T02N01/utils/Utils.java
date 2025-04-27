package cat.itacademy.s04.t02.n01.S04T02N01.utils;

public class Utils {

    public static boolean isReallyEmpty(String str){
        return str==null || str.isEmpty() || str.isBlank() || str.equals("null");
    }

}
