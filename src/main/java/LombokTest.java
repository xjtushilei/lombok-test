import lombok.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author scriptshi
 * 2018/4/12
 */
public class LombokTest {
    public static void main(String[] args) throws IOException {
        //val
        val example = new ArrayList<String>();
        example.add("HelloWorld");
        val foo = example.get(0);
        System.out.println(foo.toLowerCase());
        //val
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }

        //var   与val的区别在于是否是final的
        val example_var = new ArrayList<String>();
        example_var.add("HelloWorld");
        val foo_var = example_var.get(0);
        System.out.println(foo_var.toLowerCase());
        //var
        val map_var = new HashMap<Integer, String>();
        map_var.put(0, "zero");
        map_var.put(5, "five");
        for (val entry : map_var.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }

        //@NonNull
        class TestNull {
            public void println(@NonNull String str) {
                System.out.println(str);
            }
        }
        new TestNull().println("test pass");
//        new TestNull().println(null); 打开该注释测试null

        // @Cleanup
        @Cleanup OutputStream out = new FileOutputStream(new File("test.txt"));
        out.write("HelloWorld!".getBytes());


        //@Getter @Setter
        class TestGetterSetter {
            @Getter
            @Setter
            private int age = 10;
        }
        val t = new TestGetterSetter();
        t.setAge(25);
        System.out.println(t.getAge());


        //@ToString
        @ToString(exclude = "id", includeFieldNames = false)
        class ToStringExample {
            private static final int STATIC_VAR = 10;
            private String name = "名字";
            private String[] tags = {"1", "2", "3"};
            private int id = 1;
        }
        System.out.println(new ToStringExample());


        //EqualsAndHashCode
        @EqualsAndHashCode(exclude = {"id"})
        class EqualsAndHashCodeExample {
            private String name;
            private int count;
            private int id;

            public EqualsAndHashCodeExample(String name, int count, int id) {
                this.name = name;
                this.count = count;
                this.id = id;
            }
        }
        val o1 = new EqualsAndHashCodeExample("name1", 12, 15);
        val o2 = new EqualsAndHashCodeExample("name2", 12, 12);
        val o3 = new EqualsAndHashCodeExample("name1", 12, 16);
        System.out.println(o1.equals(o2));
        System.out.println(o2.equals(o3));
        System.out.println(o1.equals(o3));



    }
}
