import java.util.Vector;

public class KitchenWorker  extends Employee implements Runnable {
	private  InformationSystem informationsystem ; 
	private BoundedOueue<PizzaDelivery> readypizza; //תור חסום של הפיצות המוכנות 
	private Vector<PizzaDelivery> mypizza;//וקטור הסוכם את כל הפיצות שאני הכנתי 
	private double worktime;  
	private EndDay endday;


	
	public KitchenWorker(String name, InformationSystem informationsystem ,BoundedOueue<PizzaDelivery> readypizza,double worktime, EndDay endday  )
	{
		this.salary= 0;
		this.name= name;
		this.informationsystem=informationsystem;
		this.readypizza= readypizza;
		this.endday=endday;
		this.worktime= worktime;
		mypizza= new  Vector<PizzaDelivery> ();
	}

	
	private synchronized void calculsalary(Order firstOrder) // פונקציה המחשבת משכורת 
	{
		 int monapizza= firstOrder.getsumpizza();
		 salary=salary +( monapizza *2);
		 firstOrder.tha(firstOrder,salary ,name  );
	}



	public  void run() {
				   while(!endday.getendday()) //כל עוד היום לא נגמר 
				   {
					   Order firstOrder;
		        		firstOrder =  informationsystem.extract();//מוציאה הזמנה ראשונה  כבר ממויינת לפי מרחק וזמן הגעה
		        		if (firstOrder!= null)
			        	calculsalary( firstOrder); // מחשב את המשכורת שלי 
		        		else
		        			continue;
		        		if (firstOrder!= null)
		        		{
		    	PizzaDelivery ready=new PizzaDelivery(firstOrder.getadress() ,firstOrder.getdistance(),firstOrder.getsumpizza(),firstOrder.getpriceOrder());
		    	mypizza.add(ready); // מוסיף את ההזמנה לוקטור הפיצות המוכנות שלי  
		    	 try{
		    		 Thread.sleep((long) (firstOrder.getsumpizza()*1*1000));
		 
					   }
		    	 catch(InterruptedException e){
		 			System.out.println("Error");
		 		     }
		    	 readypizza.insert( ready);
		        		}
          
		   }
			}

	public void setsalary() {
		
	}

		

}
