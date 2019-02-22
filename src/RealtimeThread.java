import javax.realtime.*;

class RealtimeThread extends javax.realtime.RealtimeThread {
   volatile static boolean flag = false;
 
   static class myRealtimeStartup extends javax.realtime.RealtimeThread {
 
   public void run() {
      // Create and start 2 threads
      RealtimeThread t1 = new RealtimeThread();
      // run 1st thread at 4th real-time priority
      thread1.setPriority(PriorityScheduler.getMinPriority(null)+ 4);
      RealtimeThread t2 = new RealtimeThread();
      // run 1st thread at 6th real-time priority
      t2.setPriority(PriorityScheduler.getMinPriority(null)+ 6);
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
      RealtimeThread startThr = new RealtimeThread();
      startThr.setPriority(PriorityScheduler.getMinPriority(null)+ 8);
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