class NormalThreads extends Thread{
   volatile static boolean flag = false;
 
   static class NormalStartup extends Thread {
 
   public void run() {
      // Create and start 2 threads
      NormalThreads t1 = new NormalThreads();
      // run 1st thread at 4th real-time priority
      t1.setPriority(4);
      NormalThreads t2 = new NormalThreads();
      // run 1st thread at 6th real-time priority
      t2.setPriority(6);
      t1.start();           // start 1st thread to execute
      t2.start();           // start 2nd thread to execute
 
      // Sleep for 5 seconds, then call thread terminate
      try {
            Thread.sleep(5*1000);
      } catch (InterruptedException e) {
      }
      RealtimeThread.flag = true;
      }
   }
 
   //thread RealtimeThread main
   public static void main(String args[]) {
	  NormalStartup startThr = new NormalStartup();
      startThr.setPriority(8);
      startThr.start();
   }
 
   public void run() { // Created threads execute this method
      System.out.println("Created thread");
      int count = 0;
      while(!flag) {    // continue until asked to stop
         count++;
         // Thread.yield();   // yield to other thread
      }
      System.out.println("Thread terminates. Loop count is " + count);
   }
}