package sbu.cs;

import java.util.*;


/*
    For this exercise, you must simulate a CPU with a single core.
    You will receive an arraylist of tasks as input. Each task has a processing
    time which is the time it needs to run in order to fully execute.

    The CPU must choose the task with the shortest processing time and create
    a new thread for it. The main thread should wait for the task to fully
    execute and then join with it, before starting the next task.

    Once a task is fully executed, add its ID to the executed tasks arraylist.
    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class CPU_Simulator
{
    public static class Task implements Runnable {
        long processingTime;
        String ID;
        public Task(String ID, long processingTime) {
            this.ID = ID;
            this.processingTime = processingTime;
        }

        public long getProcessingTime() {
            return this.processingTime;
        }
        public String getID() {
            return this.ID;
        }
    /*
        Simulate running a task by utilizing the sleep method for the duration of
        the task's processingTime. The processing time is given in milliseconds.
    */
        @Override
        public void run() {

            try {
                Thread.sleep(this.processingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    public static ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task a1, Task a2) {
                return (int) (a1.processingTime - a2.processingTime);
            }
        });

        for (int i = 0; i < tasks.size(); i++) {
            executedTasks.add(tasks.get(i).getID());
        }

        return executedTasks;
    }


    public static Task newTask() {
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        int time = scanner.nextInt();
        Task newTask = new Task(id, time);
        return newTask;
    }

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (scanner.hasNext()) {
            tasks.add(newTask());
        }

        ArrayList<String> sorted = new ArrayList<>();
        sorted = startSimulation(tasks);

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < sorted.size(); i++) {
            Task task = new Task(tasks.get(i).getID(), tasks.get(i).getProcessingTime());
            Thread thread = new Thread(task);
            threadList.add(thread);

        }

        for (Thread th : threadList) {
            th.start();
        }

        for (Thread th : threadList) {
            try {
                th.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
