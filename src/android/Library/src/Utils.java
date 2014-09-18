package com.brentsys;

/**
 * Created by henrisack on 15/09/2014.
 */
public class Utils {

    public static String repeat(char c, int i){
        String tst = "";
        for(int j = 0; j < i; j++)
        {
            tst = tst+c;
        }
        return tst;
    }
}
