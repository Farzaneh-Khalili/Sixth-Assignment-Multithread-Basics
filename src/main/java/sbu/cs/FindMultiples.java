package sbu.cs;

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindMultiples
{

    public static class Task implements Runnable {
        int number;
        int quotient;
        ArrayList<Integer> array = new ArrayList<>();
        public Task(int number, int quotient) {
            this.number = number;
            this.quotient = quotient;
        }

        @Override
        public void run() {
            for (int i = 1; i <= number; i++) {
                if (i % quotient == 0) {
                    array.add(i);
                }
            }
        }
    }

    // TODO create the required multithreading class/classes using your preferred method.


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */


    public static int getSum(int n) {
        int sum = 0;

        Task task3 = new Task(n, 3);
        Task task5 = new Task(n, 5);
        Task task7 = new Task(n, 7);
        Thread thread3 = new Thread(task3);
        Thread thread5 = new Thread(task5);
        Thread thread7 = new Thread(task7);
        thread3.start();
        thread5.start();
        thread7.start();

        System.out.println(task3.array);

        ArrayList<Integer> allNumber = new ArrayList<>();
        allNumber.addAll(task3.array);
        allNumber.addAll(task5.array);
        allNumber.addAll(task7.array);

        Stream<Integer> stream = allNumber.stream();
        stream = stream.distinct();
        allNumber = (ArrayList<Integer>) stream.collect(Collectors.toList());


        for (int x : allNumber) {
            sum += x;
        }

        return sum;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(getSum(n));

    }
}
