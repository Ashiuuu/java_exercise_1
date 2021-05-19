import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            else if (command.equals("freq"))
            {
                System.out.println("Enter file path:");
                String path = scan.next();
                Path p = Paths.get(path);
                String test = new String();
                try
                {
                    test = Files.readString(p);
                }
                catch (IOException e)
                {
                    System.out.println("Unreadable file: ");
                    e.printStackTrace();
                }
                Stream<String> s = Arrays.stream(test.replaceAll("[^a-zA-Z]", " ")
                        .toLowerCase()
                        .split(" "))
                        .filter(str -> !str.isBlank());

                List<String> counted = s.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String, Long> comparingByValue(Comparator.reverseOrder())
                                .thenComparing(Map.Entry.comparingByKey()))
                        .limit(3)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

                System.out.println(counted.get(0) + " " + counted.get(1) + " " + counted.get(2));
                scan.nextLine();
            }
            else
                System.out.println("Unknown command");
        }
    }
}
