import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface Command
{
    String name();
    boolean run(Scanner scan);
}

class Quit implements Command
{
    @Override
    public String name()
    {
        return "quit";
    }

    @Override
    public boolean run(Scanner scan)
    {
        return false;
    }
}

class Fibo implements Command
{
    public int fib(int n)
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

    @Override
    public String name()
    {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scan)
    {
        int n = scan.nextInt();
        System.out.println(fib(n));
        scan.nextLine();
        return true;
    }
}

class Freq implements Command
{
    @Override
    public String name()
    {
        return "freq";
    }

    @Override
    public boolean run(Scanner scan)
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
            return false;
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
        return true;
    }
}

public class Launcher
{
    public static void main(String[] args)
    {
        System.out.println("Hello!");
        Scanner scan =  new Scanner(System.in);
        final List<Command> com = new ArrayList<>();
        com.add(new Quit());
        com.add(new Fibo());
        com.add(new Freq());

        boolean cont = true;

        while (cont)
        {
            String s = scan.next();
            boolean found = false;
            for (Command c : com)
            {
                if (c.name().equals(s))
                {
                    cont = c.run(scan);
                    found = true;
                }
            }
            if (!found)
                System.out.println("Unknown command");
        }
    }
}
