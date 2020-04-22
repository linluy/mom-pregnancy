package com.wyyuand.utils;


import java.util.Random;
import java.util.UUID;

public class StringUtil {

    public  static  String pid()
    {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public  static  String createRandomNum(int length){
      /*  String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;*/

        String val = "";
        Random random = new Random();
        for ( int i = 0; i <length; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            { // 产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) ( nextInt + random.nextInt( 26 ) );
            }
            else if ( "num".equalsIgnoreCase( str ) )
            { // 产生数字
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }
        return val;
    }



    public static void main(String[] args) {
    /*    String ui =   UUID.randomUUID().toString().replaceAll("-","");*/
        String ui  = createRandomNum(10);
        System.out.println( ui);
    }
}
