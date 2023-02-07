# IDATA2305 Assignment - 1. Threads

## Report

### Task

In this assignment we were meant to create 2 server applications were one was single threaded and other multi threaded. We had to our disposal the teachers [provided code template](https://gist.github.com/Pipe-Runner/b56d3315f5c9436992d57643c94052e5#file-directory-structure).

### Procedure

We started copying over the template into out code folder to allow more easier access to them. We then went trough tasks, but were unsure about the workflow the teacher wanted us to use. We decided to focus on the overall task and worked with one pc, to allow for discussions and easier teamwork. We ended up with some problems like the host of the server not being able to send a package to it and finding a alternative to postman because it did not support batch of packages sent simultaneously.

### Results And Observations

We were able to create both of the mentioned servers and found that the multithreaded could finish a batch of requests faster then the original single threaded. When using a single thread we are limited to finish up one and one request while using multi threads we can designate tasks to different threads to allow more multiple computations to run simultaneously.

## Running the code

To run the code use the main function found within [Code Folder](code).

To switch between multi and single threads you will need to edit the [Main.java](code\Main.java) to the appropriate Class and run the server from there.

In our test we used first postman, but found it lacking because of lack of ability to send packets simultaneously. We decided to switch over to Jmeter that allowed us to test the server a bit better with simultaneous packets from one client. The tool can be found [here](apache-jmeter-5.5) with the [Test File](apache-jmeter-5.5\Test.jmx) or on their [Website](https://jmeter.apache.org/). Refer to the [Getting Started](https://jmeter.apache.org/usermanual/get-started.html) section on their website or use the following method to copy our test method:

- Find the right startup script within the [bin folder](apache-jmeter-5.5\bin)
- Open the [Test File](apache-jmeter-5.5\Test.jmx)
- Change the preferences within the Thread group and HTTP Request
- Press the run button and go to View Result Tree to see the results