package com.jh.util;


import com.jh.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestUtil {
    @Autowired
    private RedisUtil util;

    @Test
    public void testString() throws InterruptedException {
        System.out.println(util.set("hello11", "world11"));
        System.out.println(util.get("hello11"));

        System.out.println(util.set("1", "1", 5));
        System.out.println(util.get("1"));
        Thread.sleep(5000);
        System.out.println(util.get("1"));
    }

    @Test
    public void test1(){

    }
}
