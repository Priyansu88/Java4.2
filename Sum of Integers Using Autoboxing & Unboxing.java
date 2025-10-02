

public class SumAutoboxing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers separated by space or newline. Type 'done' to finish:");

        while (true) {
            String token = sc.next();
            if (token.equalsIgnoreCase("done")) break;
            try {
                // Parsing string to primitive int, then autoboxed to Integer when added
                int value = Integer.parseInt(token.trim());
                numbers.add(value); // autoboxing from int -> Integer
            } catch (NumberFormatException e) {
                System.out.println("'" + token + "' is not a valid integer. Try again or type 'done'.");
            }
        }

        // Unboxing happens automatically when we assign Integer -> int or use in arithmetic
        int sum = 0;
        for (Integer n : numbers) {
            sum += n; // unboxing Integer -> int then summed
        }

        System.out.println("Numbers: " + numbers);
        System.out.println("Sum: " + sum);

        sc.close();
    }
}