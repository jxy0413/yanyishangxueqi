package cn.edu.bjfu.igarden.util;

import java.util.UUID;

/**
 * Created by Cookie on 2019/3/6.
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
