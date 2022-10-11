import java.util.Vector;

public class Scheduler extends Employee implements Runnable{
	
	private String name;
	private Queue<Order> orderbeforScheduler;
	private Vector<Order> myorder;
	private  InformationSystem informationsystem ;
	private EndDay endday;
	
	
	
	public Scheduler (Queue<Order> orderbeforScheduler , String name, InformationSystem informationsystem ,EndDay endday)
	{
		// בנאי למשבץ
		this.orderbeforScheduler=orderbeforScheduler;
		this.salary=0;
		this.name=name;
	
		this.informationsystem=informationsystem;
		this.endday=endday;
		myorder = new Vector<Order>();
		
	}

	
	public synchronized void run() {
	  	
	    	while(!endday.getendday()) { 
	    	Order newoder= orderbeforScheduler.extract();// להוציא הזמנה
	    	if(newoder!=null)
	    	{
	    		myorder.add(newoder );
	    	 distance( newoder);// לחשב את מרחק ההזמנה 
	    	}
	  
	    	
	    	
	       }
	
	   
		
	    
		}
		
	public  synchronized void  distance(Order neworder)
	{
		
		double distance;
		String Address=  neworder.getadress();
		distance= Distance(Address);
		neworder. setdistance(distance);
	
		informationsystem .insert(neworder);
		neworder.Tostringnewoder(neworder.getId());
	}
	private  synchronized double Distance(String Address)
	{
		int mona=1;
		double distance= 0;
		for(int i=0; i<Address.length();i++)
		{
			if(i==0)
			{
				int x= (int)Address.charAt(i);
				if(x>=97 && x<=104 || x>=65 &&x <= 72)
					distance=0.5;
				if(x>=105&& x<=112 || x>=73 &&x <=80)
					distance=2;
				if(x>=113 && x<=122|| x>=81 &&x <= 90)
					distance=7;
				if (x>=48 && x<=57)
					distance=x;
			}
			if((int)Address.charAt(i)== 32)
				mona++;
		}
		distance= distance+mona;
		return distance;  
	}


	
	public synchronized void setsalary() {
		double sumdistance=0;
	 for( int i=0; i< myorder.size();i++)
		 sumdistance= sumdistance+myorder.get(i).getdistance();
	 salary=  sumdistance;

	}
	
	

}
