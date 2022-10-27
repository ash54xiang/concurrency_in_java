# concurrency_in_java

## Motivation for Multithreading
1. Responsiveness achieved by concurrency
2. Performance achieved by parallelism


# What a single thread contains?
- Stack - Region in memory, where local variables are stored, and passed into functions
- Instruction Pointer - Address of the next instruction to execute

# What threads are sharing?
- Files
- Heap
- Code

# Context switch
- the action of switching threads by stopping and starting one thread and another
- each thread consumes resources in the CPU and memory
- when we switch to a different thread:
    - Store data for one thread
    - Restore data for another thread
- Key takeaways of context switching:
    - too many threads - Thrashing, spending more time in management than real productive work
    - threads consume less resources than processes
    - context switching between threads from the same process is cheaper than context switching between different processes

# Thread Scheduling
- Methods: 
    - First Come First Serve
        - problem - long thread can cause starvation
        - may cause UI threads being unresponsive - Bad User Experience
    - Shortest Job First
        - problem - Longer thread can never be executed as shortest job keeps coming
    - Dynamic Priority 
        - OS divides the time into moderately sized pieces called Epochs 
        - In each epoch, OS allocates a different time slice for each thread
        - Not all the threads get to run or complete in each epoch
        - ```Dynamic Priority = Static Priority + Bonus (bonus can be -ve)```
        - **Static priority** is set by the developer programmatically
        - **Bonus** is adjusted by the OS in every epoch, for each thread
        - With this dynamic priority, the OS will give preference for Interactive threads (such as UI thread)
        - OS will give preference to threads that did not complete in the last epochs, or did not get enough time to run, preventing *starvation*

## Threads VS Processes
- *Multiple Threads* VS *Multiple Processes*

# Multithreads for:
- Tasks share a lot of data
- Threads are much faster to create and destroy
- Switching between threads of the same process is faster (shorter context switches)

# Multiprocesses for:
- Security and stability are of higher importance
- Tasks are unrelated to each other

# Thread Termination - Why and When?
- Threads consume resources
    - Memory and kernel resources
    - CPU cycles and cache memory
- If a thread finished its work, but the application is still running, we want to clean up the thread's resources
- If a thread is misbehaving, we want to stop it
- By default, the application will not stop as long as at least one thread is still running

# When can we Interrupt a Thread?
1. If the thread is executing a method that throws an InterruptedException
2. If the thread's code is handling the interrupt signal explicitly
```
thread.interrupt(); // to interrupt the thread
Thread.currentThread().isInterrupted(); // to check if current thread is interrupted
```

# Daemon Threads
- Background threads that do not prevent the application from exiting if the main thread terminates
- Scenario 1:
    - background tasks, that should not block our application from terminating
    - Ex: File saving thread in a Text Editor
- Scenario 2:
    - Code in a worker thread is not under our control, and we do not want it to block our application from terminating
    - Ex: Worker thread that uses an external library
```
thread.setDaemon(true);
```
- the above line of code is to set the thread as a Daemon Thread, so the main thread will not wait for that thread to finish to exit the program. 

# Thread Coordination - why do we need it?
- Different threads run independently
- Order of execution is out of our control
```
thread.join(); // to join thread with main thread
```
