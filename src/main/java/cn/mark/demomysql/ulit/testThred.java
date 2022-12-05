package cn.mark.demomysql.ulit;

//lambad
public class testThred {

    //静态类不类
    static  class B2 implements IA{

        @Override
        public void lambad() {
            System.out.println("今天是个好日子2");
        }
    }

    public static void main(String[] args) {

        IA a=new B();
        a.lambad();

        a =new B2();
        a.lambad();

        //局部类不类
        class B3 implements IA{
            @Override
            public void lambad() {
                System.out.println("今天是个好日子3");
            }
        }
        a =new B3();
        a.lambad();

        //匿名类不类,没有类的名称,必须借助接口后者父类
        a =new IA() {
            @Override
            public void lambad() {
                System.out.println("今天是个好日子4");
            }
        };
        a.lambad();

        //lambad表达式简化

        a=()->{
            System.out.println("今天是个好日子5");
        };
        a.lambad();

    }


}
interface IA{
    void lambad();
}

class B implements IA{

    @Override
    public void lambad() {
        System.out.println("今天是个好日子");
    }
}
