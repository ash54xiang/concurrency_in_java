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
