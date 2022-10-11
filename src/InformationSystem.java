import java.util.Vector;

public class InformationSystem { // מערכת המידע - מקבלת את כל ההזמנות וממיינת אותם

	private Vector<Order> lessthan3;
	private Vector<Order> between38;
	private Vector<Order> morethan8;
	private Vector<Order> allorder;
	private pizza  branch;
	protected  EndDay endday;

	public InformationSystem( EndDay endday)
	{
		 lessthan3= new Vector<Order>();
		 between38 = new Vector<Order>();
		 morethan8= new Vector<Order>();
		 allorder= new Vector<Order>();
		 this.endday= endday;
	}
	 
	public synchronized void setendday()  //אם נגמרות ההזמנות - נגמר היום!
	{
		this.endday.setendday(true);
		this.insert(null);
		 this.notifyAll();
	}
	
	private void sort(Vector<Order> l) // פונקציה שממיינת לפי זמן ההגעה של השיחה 
	{
		Vector<Order> newl = new Vector<Order>();
		while(!(l.isEmpty()))
		{
		Order minorder= getoder( l);
		newl.add(minorder);
	
		}
		for (int i=0; i<newl.size();i++)
		{
			l.add(newl.get(i));
		}
	}
	
	public  synchronized Order  extract() // מוציאה הזמנה 
	{
		Order order=null;
		 if (endday.getendday())
		 {
			 return null;
		 }
		
		 while (lessthan3.isEmpty() && between38.isEmpty()&&morethan8.isEmpty() ) // אם אין יותר הזמנות 
		 {  
			 if (endday.getendday())
			 {
				 return null; // נחזיר נאל ונמתין 
			 }
			  try{
			  	this.wait();
			  } 
			    catch(InterruptedException e)
			    {
			  	 
			    }
		 }
		
		 
		if (!(lessthan3.isEmpty())) // בודקים קודם אם יש הזמנה בטווח הכי קרוב 
		{
			order=	getoder(lessthan3);
			lessthan3.remove(order);
			return order;
			
		}
		if (!(between38.isEmpty()))
		{
			order=	getoder(between38);
			between38.remove(order);
			return order;
		}
		if (!( morethan8.isEmpty()))
		{
			order=	getoder( morethan8);
		 morethan8.remove(order);
		 return order;
			
		}
		  return order;
	}
	
	

	public  synchronized void insert(Order neworder) // מכניסה הזמנה וישר ממיינת לפי מרחק
	{
		if (neworder==null)
			this.notifyAll();
		if (neworder!=null)
		{
	
		double distance=neworder.getdistance();
		if (distance<=3)
		{
			lessthan3.add(neworder);
			allorder.add(neworder);
			 this.notifyAll();
		}
		if (3<distance && distance<=8 )
		{
			between38.add(neworder);
			allorder.add(neworder);
			 this.notifyAll();
		}
				
		if (distance>8)
		{
			morethan8.add(neworder);
		allorder.add(neworder);
		 this.notifyAll();
		}
		}

		 this.notifyAll();
	}
	
	
	private synchronized Order getoder(Vector<Order> o)
	{
		Order firstOrder= o.get(0); 
		for ( int i=1; i<o.size(); i++)	
		{
			if (firstOrder.compareTo(o.get(i))==-1) // ממיין לפי ההשוואה הטבעית - זמן ההגעה
				firstOrder=o.get(i);
				
		}
		return firstOrder;
		
	}
	
	
	public void tostring() // מדפיס את ת"ז ההזמנה
	{
		for (int i=0; i<this.lessthan3.size(); i++)
			System.out.println(lessthan3.get(i).getId());
		for (int i=0; i<this.between38.size(); i++)
			System.out.println(this.between38.get(i).getId());
		for (int i=0; i<this.morethan8.size(); i++)
			System.out.println(this.morethan8.get(i).getId());
	}


		

	
		
	}
	
	

