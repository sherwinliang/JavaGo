package common;

import java.util.concurrent.atomic.AtomicLong;

public class CommonUtils {

    private static final AtomicLong capitalChangeIdGenerator = new AtomicLong();

    public static long getCapitalChangeID(){
        return capitalChangeIdGenerator.getAndIncrement();
    }
}
