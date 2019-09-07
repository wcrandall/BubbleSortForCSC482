import java.lang.management.ManagementFactory;

import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Random;

public class test {
    static Random random = new Random();
    static int MINSIZE = 1000;
    static int MAXSIZE = 10 * MINSIZE;
    static int INCSIZE = MINSIZE;
    static int NUMTESTS = 50;

    static public void main(String[] args)
    {

        int[] test = createRandomIntegerList(10);
        System.out.println(Arrays.toString(test));
        bubbleSort(test);
        //sout tab
        System.out.println(Arrays.toString(test));
        for(int inputSize = MINSIZE; inputSize<=MAXSIZE;inputSize+=INCSIZE)
        {
            long totalTime = 0;
            for (int trialNum = 0; trialNum < NUMTESTS; trialNum++)
            {
                test = createRandomIntegerList(inputSize);
                long timeStampBefore=System.nanoTime();
                bubbleSort(test);
                long timeStampAfter = System.nanoTime();
                long trialTime = timeStampAfter-timeStampBefore;
                totalTime+=trialTime;
            }
            double averageTime = (double)totalTime/(double)NUMTESTS;
            System.out.println(inputSize + "    " + averageTime + "     " + totalTime);
        }
    }

    static public void bubbleSort(int[] list)
    {
        for (int i = 0; i < list.length -1; i++)
        {
            for(int j =0; j< list.length - i - 1; j++)
            {
                if(list[j] > list[j+1])
                {
                    int temp = list[j + 1];
                    list[j+1] = list[j];
                    list[j]=temp;
                }
            }
        }
    }

    static public long getCpuTime( ) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

        return bean.isCurrentThreadCpuTimeSupported( ) ?

                bean.getCurrentThreadCpuTime( ) : 0L;

    }

    static public int[] createRandomIntegerList(int size)
    {
        int[] newList = new int[size];

        for(int i = 0; i < size; i++)
        {
            newList[i] = random.nextInt();
        }

        return newList;
    }
}
