public class Main {

    public static void main(String[] args)
    {
        int a = 123;
        int b = 321;

        System.out.println("Normal multiplying: " + a + "*" + b + "=" + (a*b));

        System.out.println("Normal Markov algorithm multiplying: " + a + "*" + b + "=" + multiply(a,b));
    }
    static int multiply (int x, int y)
    {
        return  y==0?0:((y&1) ==1?x:0)+multiply(x<<1,y>>1);
    }
}
