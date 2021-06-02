package numbers;

import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static numbers.AmazingNumber.*;

class AmazingNumber {
    LinkedHashMap<String, Boolean> properties = new LinkedHashMap<>();

    public AmazingNumber(long n) {
        boolean buzz = checkBuzz(n);
        boolean duck = checkDuck(n);
        boolean palindromic = checkPalindromic(n);
        boolean gapful = checkGapful(n);
        boolean spy = checkSpy(n);
        boolean square = checkSquare(n);
        boolean sunny = checkSunny(n);
        boolean jumping = checkJumping(n);
        boolean happy = checkHappy(n);
        boolean even = checkEven(n);
        properties.put("buzz", buzz);
        properties.put("duck", duck);
        properties.put("palindromic", palindromic);
        properties.put("gapful", gapful);
        properties.put("spy", spy);
        properties.put("square", square);
        properties.put("sunny", sunny);
        properties.put("jumping", jumping);
        properties.put("happy", happy);
        properties.put("sad", !happy);
        properties.put("even", even);
        properties.put("odd", !even);

        properties.put("-buzz", !buzz);
        properties.put("-duck", !duck);
        properties.put("-palindromic", !palindromic);
        properties.put("-gapful", !gapful);
        properties.put("-spy", !spy);
        properties.put("-square", !square);
        properties.put("-sunny", !sunny);
        properties.put("-jumping", !jumping);
        properties.put("-happy", !happy);
        properties.put("-sad", happy);
        properties.put("-even", !even);
        properties.put("-odd", even);
    }

    public static boolean checkHappy(long n) {
        ArrayList<Long> list = new ArrayList<>();
        while (true) {
            list.add(n);
            String[] strings = String.valueOf(list.get(list.size() - 1)).split("");
            n = 0;
            for (String digit : strings) {
                n += Long.parseLong(digit) * Long.parseLong(digit);
            }
            if (n == 1) {
                break;
            }
            if (list.contains(n)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkJumping(long n) {

        while (n != 0) {
            long digit1 = n % 10;
            n /= 10;
            if (n != 0) {
                long digit2 = n % 10;
                if (abs(digit1 - digit2) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkSunny(long n) {
        return checkSquare(n + 1);
    }

    public static boolean checkSquare(long n) {
        long sqrt = (int) sqrt(n);
        return n == sqrt * sqrt;
    }

    public static boolean checkSpy(long n) {
        String[] numbers = String.valueOf(n).split("");
        int sum = 0;
        int mul = 1;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
            mul *= Integer.parseInt(number);
        }
        return sum == mul;
    }

    public static boolean checkGapful(long n) {
        if (n < 100) {
            return false;
        } else {
            String number = String.valueOf(n);
            int firstEnd = Integer.parseInt(number.charAt(0) + number.substring(number.length() - 1));
            return n % firstEnd == 0;
        }
    }

    public static boolean checkPalindromic(long n) {
        String number = String.valueOf(n);
        for (int i = 0; i < number.length() / 2; i++) {
            if (number.charAt(i) != number.charAt(number.length() - 1 - i))
                return false;
        }
        return true;
    }

    public static boolean checkEven(long n) {
        return n % 2 == 0;
    }

    public static boolean checkDuck(long n) {
        if (n >= 10 && n % 10 == 0)
            return true;
        if (n < 10)
            return false;
        return checkDuck(n / 10);
    }

    public static boolean checkBuzz(long n) {
        long unit = n % 10;
        long rest = n / 10;
        return (2 * unit - rest) % 7 == 0 || n % 10 == 7;
    }

    public String propertiesTrue() {
        List<String> propertiesTrue = new ArrayList<>();
        for (String i : properties.keySet()) {
            if (i.charAt(0) != '-') {
                if (properties.get(i))
                    propertiesTrue.add(i);
            }
        }
        return propertiesTrue.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
        Scanner scanner = new Scanner(System.in);
        long n = -1;
        int n2;
        do {
            System.out.println("\nEnter a request:");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");
            try {
                n = Long.parseLong(inputArray[0]);
            } catch (NumberFormatException e) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            if (n < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            if (n == 0) {
                System.out.println("Goodbye!");
                break;
            }
            switch1:
            switch (inputArray.length) {
                case 1:
                    System.out.println("Properties of " + n +
                            "\n        buzz: " + checkBuzz(n) +
                            "\n        duck: " + checkDuck(n) +
                            "\n palindromic: " + checkPalindromic(n) +
                            "\n      gapful: " + checkGapful(n) +
                            "\n         spy: " + checkSpy(n) +
                            "\n      square: " + checkSquare(n) +
                            "\n       sunny: " + checkSunny(n) +
                            "\n     jumping: " + checkJumping(n) +
                            "\n       happy: " + checkHappy(n) +
                            "\n         sad: " + !checkHappy(n) +
                            "\n        even: " + checkEven(n) +
                            "\n         odd: " + !checkEven(n));
                    break;
                case 2:
                    try {
                        n2 = Integer.parseInt(inputArray[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("The second parameter should be a natural number.");
                        break;
                    }
                    if (n2 <= 0)
                        System.out.println("The second parameter should be a natural number.");
                    else
                        for (int i = 0; i < n2; i++) {
                            AmazingNumber amazingNumber = new AmazingNumber(n);
                            System.out.println(n + " is " + amazingNumber.propertiesTrue());
                            n++;
                        }
                    break;
                default:
                    try {
                        n2 = Integer.parseInt(inputArray[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("The second parameter should be a natural number.");
                        break;
                    }
                    List<String> inputProperties = new ArrayList<>();
                    for (int i = 2; i < inputArray.length; i++) {
                        inputProperties.add(inputArray[i].toLowerCase());
                    }
                    AmazingNumber amazingNumber = new AmazingNumber(n);
                    List<String> notProperties = new ArrayList<>();
                    for (String prop : inputProperties) {
                        String negative = "";
                        if (prop.charAt(0) == '-')
                            negative = prop.substring(1);
                        if (!amazingNumber.properties.containsKey(prop) && !amazingNumber.properties.containsKey(negative)) {
                            notProperties.add(prop);
                        }
                    }
                    if (notProperties.size() == 1) {
                        System.out.println("The property " + notProperties.toString().toUpperCase() + " is wrong.\n" +
                                "Available properties: " + amazingNumber.properties.keySet().toString().toUpperCase());
                        break;
                    }
                    if (notProperties.size() > 1) {
                        System.out.println("The properties " + notProperties.toString().toUpperCase() + " are wrong.\n" +
                                "Available properties: " + amazingNumber.properties.keySet().toString().toUpperCase());
                        break;
                    }

                    List<List> impossibleList = new ArrayList<>();

                    List<String> evenAndOdd = new ArrayList<>();
                    evenAndOdd.add("even");
                    evenAndOdd.add("odd");
                    impossibleList.add(evenAndOdd);

                    List<String> duckAndSpy = new ArrayList<>();
                    duckAndSpy.add("duck");
                    duckAndSpy.add("spy");
                    impossibleList.add(duckAndSpy);

                    List<String> sunnyAndSquare = new ArrayList<>();
                    sunnyAndSquare.add("sunny");
                    sunnyAndSquare.add("square");
                    impossibleList.add(sunnyAndSquare);

                    List<String> sadAndHappy = new ArrayList<>();
                    sadAndHappy.add("sad");
                    sadAndHappy.add("happy");
                    impossibleList.add(sadAndHappy);

                    List<String> evenAndOdd1 = new ArrayList<>();
                    evenAndOdd1.add("-even");
                    evenAndOdd1.add("-odd");
                    impossibleList.add(evenAndOdd1);

                    List<String> duckAndSpy1 = new ArrayList<>();
                    duckAndSpy1.add("-duck");
                    duckAndSpy1.add("-spy");
                    impossibleList.add(duckAndSpy1);

                    List<String> sunnyAndSquare1 = new ArrayList<>();
                    sunnyAndSquare1.add("-sunny");
                    sunnyAndSquare1.add("-square");
                    impossibleList.add(sunnyAndSquare1);

                    List<String> sadAndHappy1 = new ArrayList<>();
                    sadAndHappy1.add("-sad");
                    sadAndHappy1.add("-happy");
                    impossibleList.add(sadAndHappy1);

                    for (String p1 : inputProperties) {
                        for (String p2 : inputProperties) {
                            if (("-" + p1).equals(p2)) {
                                System.out.println("The request contains mutually exclusive properties:" +
                                        " [" + p1.toUpperCase() + ", " + p2.toUpperCase() + "]" +
                                        "\nThere are no input with these properties.");
                                break switch1;
                            }
                        }
                    }

                    for (List impossible : impossibleList) {
                        if (inputProperties.containsAll(impossible)) {
                            System.out.println("The request contains mutually exclusive properties: " + impossible.toString().toUpperCase() +
                                    "\nThere are no input with these properties.");
                            break switch1;
                        }
                    }
                    if (amazingNumber.properties.keySet().containsAll(inputProperties)) {
                        int count = 0;
                        while (count < n2) {
                            if (checkProperty(n, inputProperties)) {
                                System.out.println(n + " is " + amazingNumber.propertiesTrue());
                                count++;
                            }

                            n++;
                            amazingNumber = new AmazingNumber(n);
                        }
                    }
            }
        } while (n != 0);
    }

    private static boolean checkProperty(long n, List<String> inputProperties) {
        AmazingNumber amazingNumber = new AmazingNumber(n);
        for (String property : inputProperties) {
            if (!amazingNumber.properties.get(property))
                return false;
        }
        return true;
    }
}
