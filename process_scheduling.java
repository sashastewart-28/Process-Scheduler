import java.util.*;
class Process
{
	String pro_id;                            //process id
	int pro_pri;                              //process priority
	double exe_time;                          //execution time
	Process(String id,int pri,double t)       //constructor
	{
		pro_id=id;
		pro_pri=pri;
		exe_time=t;
	}
	void displayProcess()                    
	{
		System.out.println("Process id: "+pro_id+"\nProcess Priority: "+pro_pri+"\nProcess Execution Time: "+exe_time);
	}                                            //end display
}                                                // end of process
class PQ
{
	// array in sorted order, from max at 0 to min at size-1
	int size;
	int[] que;
	int n;
	//-------------------------------------------------------------
	public PQ(int s)
	// constructor
	{
		size= s;
		que = new int[size];
		n = 0;
	}
	//-------------------------------------------------------------
	public void insert(int x)
	// insert item
	{
		int i;
		if(n==0)
		// if no items,
			que[n++] = x;
		// insert at 0
		else
		// if items,
		{
			for(i=n-1; i>=0; i--)
			// start at end,
			{
				if( x > que[i] )
				// if new item larger,
					que[i+1] = que[i]; // shift upward
				else
				// if smaller,
					break;
				// done shifting
			} // end for
			que[i+1] = x;
			// insert it
			n++;
		} // end else (nItems > 0)
	} // end insert()
	//-------------------------------------------------------------
	public void remove()
	// remove minimum item
	{ 
		--n; 
	}
	//-------------------------------------------------------------
	public int peekMin()
	// peek at minimum item
	{ 
		return que[n-1]; 
	}
	//-------------------------------------------------------------
	public boolean isEmpty()
	// true if queue is empty
	{ 
		return (n==0); 
	}
	//-------------------------------------------------------------
	public boolean isFull()
	// true if queue is full
	{ 
		return (n == size); 
	}
	//-------------------------------------------------------------
} // end class PriorityQ
class Execution
{
	Process p[];          //array of process
	PQ pp;                // priority queue of process priority
	int nElems;
	Execution(int n)     //constructor
	{
		p=new Process[n];
		pp=new PQ(n);
		nElems=0;
	}                    //end constructor
	void insertProcess(String id,int pri,double t)
	{
		p[nElems]=new Process(id,pri,t);
		pp.insert(pri);           //insertion into priority queue
		nElems++;
	}
	void removeEle(int x)
	{
	    for(int i=x;i<nElems-1;i++)
	    {
	        p[i]=p[i+1];
	    }
	    nElems--;
	}
	Process getMax()              //returns the maximum priority process
	{
		for(int i=0;i<nElems;i++)
		{
			if(p[i].pro_pri==pp.peekMin())
				return p[i];
		}
		return null;
	}
	void display()
	{
		while(!pp.isEmpty())
		{
			int x=pp.peekMin();
			for(int i=0;i<nElems;i++)
			{
				if(p[i].pro_pri==x)
				{
					p[i].displayProcess();
					System.out.println("-------------------------------------------------------------");
			    //System.out.println("-------------------------------------------------------------");
					removeEle(i);
				}	
			}
			pp.remove();
			
		}                       //end of while
	}                           //end of method
}                                //end execution
class Main
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		String id;
		int pri,n;
		double time;
		Execution e;
		Process max;
		System.out.println("Enter no of process");
		n=sc.nextInt();
		e=new Execution(n);
		while(n>0)
		{
			System.out.println("Enter process id:");
			id=sc.next();
			System.out.println("Enter process priority:");
			pri=sc.nextInt();
			System.out.println("Enter process execution time:");
			time=sc.nextDouble();
			e.insertProcess(id,pri,time);
			n--;
		}                     //end while
		max=e.getMax();
		System.out.println("-------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------");
		if(max!=null)
			System.out.println("The Maximum Priority Process is: ");
		max.displayProcess();
		System.out.println("-------------------------------------------------------------");
    System.out.println("-------------------------------------------------------------");
		System.out.println("The Process Scheduling is as follows:");
		e.display();
    System.out.println("-------------------------------------------------------------");
    System.out.println("-------------------------------------------------------------");
	}                //end main
}