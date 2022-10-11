import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class pizza implements Runnable { //
	protected static Queue<Call>   allcalls; // ��� �� �� ������
	protected Queue<Order> orderbeforScheduler; // ��� �� ������ ����� �����
	protected  Queue<Call>  orderbeforManager; // ��� �� ������ ������ ������
	protected Clerk clerk1;
	protected Clerk clerk2;
	protected Clerk clerk3;
	protected  EndDay endday;
	protected   InformationSystem informationsystem ;
	protected Scheduler  scheduler1;
	protected Scheduler  scheduler2;
	protected  KitchenWorker  kitchenworker1;
	protected  KitchenWorker  kitchenworker2;
	protected  KitchenWorker  kitchenworker3;
	protected BoundedOueue<PizzaDelivery> readypizza;
	protected  Vector<PizzaGuy>  pizzaguys;
	protected Vector <Employee > employee; 
	protected Manager bigboos;
	protected Vector<Call> thenew;
	protected allcalls sumCall ;
	protected  int allthedelivery;
	public pizza (String calls, int numofpizzaguy,double worktime) // ���� ����� - ����� �� �������
	{
		 thenew = new Vector<Call>(); 
		allthedelivery=0;
		endday = new EndDay();
		employee= new  Vector <Employee > (); 
		informationsystem= new  InformationSystem(endday);
		readCalls( calls);
		 int mona=this.thenew.size();
		 sumCall.setallcall(mona); 
		 this.sumCall= new allcalls(mona);
		 allcalls= new Queue<Call> (endday);
		 for(int i=0; i<this.thenew.size();i++)
			 allcalls.insert(thenew.get(i));
		 int sumDelivery= mona; // ����� �����
		 orderbeforScheduler= new Queue<Order>(endday);
		 orderbeforManager = new  Queue<Call> (endday);
		 newclerk1();
		 newscheduler();
		 readypizza= new BoundedOueue<PizzaDelivery>( endday);
		 newkitchenworker( worktime);
		 pizzaguys= new Vector <PizzaGuy>();
		 bigboos = new Manager(this, mona);
		 newpizzaguys( numofpizzaguy); 
	}


	
	private void newpizzaguys(int numofpizzaguy ) // ����� ������ 
	{
		 while (numofpizzaguy!=0)
		 {
			 String name = ""+ numofpizzaguy;
			 pizzaguys.add(new PizzaGuy( readypizza,name,allthedelivery,endday,this.bigboos));
			 employee.add(new PizzaGuy( readypizza,name,allthedelivery,endday,this.bigboos) );
			 numofpizzaguy--;
			 
		 }
	}
	private void newkitchenworker(double worktime) //����� ����� ����
	{
		 kitchenworker1= new KitchenWorker( "ayala",  informationsystem , readypizza, worktime,endday);
		 employee.add(	kitchenworker1 );
		 kitchenworker2= new KitchenWorker( "lior",  informationsystem , readypizza,worktime,endday);
		 employee.add(	kitchenworker2 );
		 kitchenworker3= new KitchenWorker( "yuval",  informationsystem , readypizza,worktime,endday);
		 employee.add(	kitchenworker3 );
	}
	private void newscheduler() //����� ������
	{
		 scheduler1 = new Scheduler( orderbeforScheduler, "shlomi", informationsystem ,endday);
		 employee.add(	 scheduler1);
		 scheduler2 = new Scheduler( orderbeforScheduler,  "shahar", informationsystem,endday );
		 employee.add(	 scheduler2);
	}
	
	private void newclerk1() // ����� ������� 
	{
		 clerk1 = new Clerk("dor", allcalls, orderbeforScheduler,orderbeforManager ,this.sumCall);
		 employee.add(clerk1);
		 clerk2 = new Clerk("erez", allcalls, orderbeforScheduler,orderbeforManager, this.sumCall );
		 employee.add(clerk2);
		 clerk3 = new Clerk("yevgeny", allcalls, orderbeforScheduler,orderbeforManager , this.sumCall);
		 employee.add(clerk3);
		
	}
	private static Vector<String> readFile(String file) { // ����� ����
		Vector<String> lines = new Vector<String>(); // ���� �� ������
		BufferedReader inFile = null;
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			inFile = new BufferedReader(fr);
			String line = inFile.readLine(); // ����� ���� �����
			while ((line = inFile.readLine()) != null) {
				lines.add(line); // ����� �� ���� ��� �����
			}
			return new Vector<String>(lines); // ����� �����
		} catch (IOException e) {
			System.out.println("The file " + file + " was not found.");
			return null;
		} finally {
			try {
				inFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void readCalls(String calls)  { // ����� ���� �����
		Vector<String> lines = readFile(calls);
		long visa ;
		int sumpizza;
		 double Arrive;
		 double time;
		 String Address;
		
		int length = lines.size();
		for (int i = 0; i < length; i++) {
			String[] Calls = lines.get(i).split("\t"); // ���� ���� �������� ��� �������� - �� ��� ���� ��� ����
			visa = Integer.parseInt(Calls[0]); 
			sumpizza = Integer.parseInt(Calls[1]);
			Arrive = Double.parseDouble(Calls[2]);
			time = Double.parseDouble(Calls[3]);
			 Address= Calls[4];
			 Call c=	new Call(  sumpizza,Address,
			visa, time,Arrive,i+1);
			 this.thenew.add(c);
			 
		
		}
	}
	
	
	
	
	
	public void run() { //����� ����� 
		
	for(int i=0;i<	this.allcalls.size();i++)
	{
		this.allcalls.getIndex(i).start();// ����� �� ������ 
	}
		 
	 Thread t0 = new Thread( bigboos);
	  t0.start();
		Thread t1 = new Thread(clerk1);
		Thread t2 = new Thread(clerk2);
		Thread t3= new Thread(clerk3);
		t1.start();
		t2.start();
		t3.start();
		
		Thread t4 = new Thread(scheduler1);
		Thread t5 = new Thread(scheduler2);
		t4.start();
		t5.start();
		Thread t6 = new Thread(kitchenworker1);
		Thread t7 = new Thread(kitchenworker2);
		Thread t8 = new Thread(kitchenworker3);
		t6.start();
		t7.start();
		t8.start();
	
		
		Vector <Thread> s = new Vector <Thread>();
		for (int i=0; i<pizzaguys.size();i++)
		{
			s.add(new  Thread(pizzaguys.get(i)) );
			s.get(i).start();
			
		}
		
		

		
		
	}
	
	
	

}
