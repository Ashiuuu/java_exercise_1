import java.util.Scanner;

public class Launcher
{
    public static int fibo(int n)
    {
        if (n <= 1)
            return n;

        int a = 0;
        int b = 1;

        for (int i = 0; i < n - 1; i++)
        {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static void main(String[] args)
    {
        System.out.println("Hello!");
        Scanner scan =  new Scanner(System.in);
        while (true)
        {
            String command = scan.next();
            if (command.equals("quit"))
                break;
            else if (command.equals("fibo"))
            {
                int n = scan.nextInt();
                System.out.println(fibo(n));
                scan.nextLine();
            }
            else
                System.out.println("Unknown command");
        }
    }
}
