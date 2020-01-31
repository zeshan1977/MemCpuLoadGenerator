package cpumem;
import java.util.Vector;

public class cpuiomembusy
{
/** JavaDebugExample. stuck threads..etc etc**/

    public static void main(String[] args)
    {
      int numCore = 2;
      int numThreadsPerCore = 1;
      double load = 0.8; //Percentage ~80%
      final long duration = 100000;
      //final long duration = 10;
      for (int thread = 0; thread < numCore * numThreadsPerCore; thread++) {
          new BusyThread("Thread: " + thread, load, duration).start();
      }
  
    }  

    /**
     * Thread that actually generates the given load
     * @author Sriram
     */
    private static class BusyThread extends Thread {
        private double load;
        private long duration;

        /**
         * Constructor which creates the thread
         * @param name Name of this thread
         * @param load Load % that this thread should generate
         * @param duration Duration that this thread should generate the load for
         */
        public BusyThread(String name, double load, long duration) {
            super(name);
            this.load = load;
            this.duration = duration;
        }

        /**
         * Generates the load when run
         */
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            Vector<byte[]> vecbytearr=new Vector<byte[]>();
            //byte[] newbyte= new byte[1048576];
            try {
                // Loop for the given duration
                while (System.currentTimeMillis() - startTime < duration) {
                    // Every 100ms, sleep for the percentage of unladen time
                    
                    //byte[] newbyte= new byte[1048576];
                    byte[] newbyte= new byte[256];
                    vecbytearr.add(newbyte);
                    Runtime rt = Runtime.getRuntime();
                    System.out.println( "free memory: " + rt.freeMemory()/1000+"M");
                    if (System.currentTimeMillis() % 100 == 0) {
                        Thread.sleep((long) Math.floor((1 - load) * 100));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
/*
  private class StuckThread()
   {
      MyThread t1 = new MyThread(0, 3, 300);
      MyThread t2 = new MyThread(1, 3, 300);
      MyThread t3 = new MyThread(2, 3, 300);

      t1.start();
      t2.start();
      t3.start();
   }
 */
  private class MyThread extends Thread
  {
   private int startIdx, nThreads, maxIdx;

   public MyThread(int s, int n, int m)
   {
      this.startIdx = s;
      this.nThreads = n;
      this.maxIdx = m;
   }

   @Override
   public void run()
   {
      for(int i = this.startIdx; i < this.maxIdx; i += this.nThreads)
      {
         System.out.println("Thread [ID " + this.getId() + "] " + i);
      }
   }
}

}


