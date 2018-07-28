package com.chb.title;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler //www.jb51.net");
        splitRedPacket(50, 20, 2, 4);
        System.out.println("*****************");
//        splitRedPacket(20000, 30, 300, 1000);

    }

    /**
     *
     * @param total
     *            总金额
     * @param splitCount
     *            个数
     * @param min
     *            最小金额
     * @param max
     *            最大金额
     */
    public static void splitRedPacket(int total, int splitCount, int min, int max) {
        System.out.println("总长度：	" + total);
        System.out.println("个数：	" + splitCount);
        System.out.println("最小长度：	" + min);
        System.out.println("最大长度：	" + max);

        ArrayList<Integer> al = new ArrayList<Integer>();
        Random random = new Random();

        if ((splitCount & 1) == 1) {// 奇数个红包，需要单独将其中一个红包先生成，以保证后续算法拆分份数为偶数。
            System.out.println("个数" + splitCount + "是奇数，单独生成一个长度");
            int num = 0;
            do {
                num = random.nextInt(max);
                // num = (total - num) % (splitCount / 2) + num; //
                // 将后面算法拆分时的余数加入到这个随机值中。
                System.out.println("单个的长度为：" + num);
            } while (num >= max || num <= min);

            total = total - num;
            al.add(num);
        }
        int couples = splitCount >> 1;
        int perCoupleSum = total / couples;

        if ((splitCount & 1) == 1) {
            System.out.println("处理后剩余的长度为：" + total);
        }
        System.out.println("将" + total + "拆分为" + couples + "对，每对：" + perCoupleSum);

        for (int i = 0; i < couples; i++) {
            Boolean finish = true;
            int num1 = 0;
            int num2 = 0;
            do {
                num1 = random.nextInt(max);
                num2 = perCoupleSum - num1;
                if (!al.contains(num1) && !al.contains(num2)) {
                    if (i == 0) {
                        num1 = (total - couples * perCoupleSum) + num1;
                    }
                }
            } while (num1 < min || num1 > max || num2 < min || num2 > max);
            al.add(num1);
            al.add(num2);
        }

        int check_num = 0;
        Integer.compare(1, 2);
        al.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        System.out.println(Arrays.toString(al.toArray()));

        for (int x : al) {
            check_num = check_num + x;
        }
        System.out.println("验证总和：" + check_num);
    }

}
