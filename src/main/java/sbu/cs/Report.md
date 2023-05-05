# sixth assignment >> Multithread Basics

## CPU Simulator :
- for the first exercise I first add getter method for Task so I would be able to have ID and processing time of any task.
- in the run method I wrote sleep method with processing time as the program wanted us to do.
- in the startSimulation I used Collection.sort to sort the task arraylist based on their processing time.
- then i used the executed arraylist in the main and made a thread for each of them, start them and used join method to make the main thread wait for the task to completely executed and then join with it.


## Find Multiples :
- for this exercise I used Runnable Interface(object Task which implements Runnable)
- I put 3 attributes for Task : 1.number(which is the number of at the end of the range) 
- 2.quotient(we have 3 thread with quotient 3, 5 and 7) 
- 3.an arraylist for integers that are divisible by the quotient.
- I wrote 3 task. the first one find all integers that are divisible by 3 in range [1, number] and the other 2 tasks do the same thing for 5 and 7.
- In the "getSum" function I made 3 threads of our task and also another arraylist wich combine the arraylists of our 3 task.
- and I used Stream method to remove duplicates elements from our final arraylist (for example 21 which is divisible by both 3 and 7 and will be count 2 times)
- then I add all the numbers to the sum and return it.
- at the end I call getSum function in the main and print its result.


## Use Interrupts :
- for the last exercise I used **System.currentTimeMillis()** to the get the current time of the system at the start and end of the run method.
- and I used **if** to check if it's more than 3000 milliS and if it was, then it will terminate itself.
- in the main method i will check if the thread is terminated or not and if it was terminated, it will print "the thread is interrupted"