import java.util.Scanner;

public class Launcher
{
    public static void main(String[] args)
    {
        System.out.println("Hello!");
        Scanner scan =  new Scanner(System.in);
        while (true)
        {
            String command = scan.next();
            if (command.equals("quit"))
                break;
            System.out.println("Unknown command");
        }
    }
}
