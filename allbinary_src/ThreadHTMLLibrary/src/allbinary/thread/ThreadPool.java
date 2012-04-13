/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.thread;

import org.allbinary.util.BasicArrayList;

public class ThreadPool
{
    private boolean isAlive;
    private BasicArrayList taskQueue = new BasicArrayList();
    //private int threadID;
    private int numThreads;
    //private static int threadPoolID;

    private boolean runningTask;
    
    public ThreadPool(int numThreads)
    {
        this.numThreads = numThreads;
    }

    //TWB - PlayN Single Thread Fix
    public void runATask()
            throws Exception
    {
            Runnable runnable = this.getTask();

            if (runnable != null)
            {
                runnable.run();
            }
    }
    
    public void init()
    {
        if (!this.isAlive)
        {
            isAlive = true;

            taskQueue = new BasicArrayList();
            //for (int i = 0; i < numThreads; i++)
            //{
                //new PooledThread().start();
            //}
        }
    }

    public synchronized void runTask(Runnable task)
    {
        if (!isAlive)
        {
            this.init();
            //throw new IllegalStateException();
        }
        if (task != null)
        {
            //LogUtil.put(LogFactory.getInstance("Add: " + task, this, "runTask"));
            //PreLogUtil.put("Add: " + task, this, "runTask");

            taskQueue.add(task);
            //TWB - Playn Testing
            //notify();
        }
    }

    protected synchronized Runnable getTask()
            throws InterruptedException
    {
        if(taskQueue.isEmpty())
        {
            return null;
        }
        /*
        while (taskQueue.size() == 0)
        {
            if (!isAlive)
            {
                return null;
            }
            //TWB - Playn Testing
            //this.wait();
        }
        */
        return (Runnable) taskQueue.remove(0);
    }

    public synchronized void close()
    {
        if (isAlive)
        {
            isAlive = false;
            taskQueue.clear();
            //interrupt();
        }
    }

    public void join()
    {

        synchronized (this)
        {
            isAlive = false;
            //TWB - Playn Testing
            //notifyAll();
        }

        //Thread[] threads = new Thread[MAX];
        //int count = threads.length;
        //for (int i = 0; i < count; i++) {
        //try {
        //threads[i].join();
        //} catch (InterruptedException ex) {
        //}
        //}
    }

    public boolean isBusy()
    {
        if (this.taskQueue.size() > 0)
        {
            return true;
        }

        if (this.runningTask)
        {
            return true;
        }
        return false;
    }

    protected void threadStarted()
    {
    }

    protected void threadStopped()
    {
        if (this.numThreads == 1)
        {
            this.isAlive = false;
        }
    }

    protected void startTask(Runnable task)
    {
    }

    protected void completedTask(Runnable task)
    {
    }
}
