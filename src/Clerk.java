import java.util.Vector;

public class Clerk  extends Employee implements Runnable {
	
	
	private  Vector <Call> myOrder;// וקטור השיחות שאני הצלחתי לענות עליהם
	private Queue <Call>  Queueforcall;// וקטור כל השיחות שהגיעו אלינו
	private Queue<Order> orderbeforScheduler; //וקטור כל ההזמנות שצריכות לעבור למשבצים  
	private   Queue <Call> orderbeforManager;//תור שיחות של הזמנות גדולות למנהל
	private allcalls sumCall; // אובקיט שיודע כמה שירות אמורות להגיע, מוריד שיחה שהתקבלה, מעדכן שנגמרו השיחות.
	
	public Clerk (String name,Queue <Call>  Queueforcall, Queue<Order> orderbeforScheduler, Queue <Call> orderbeforManager,allcalls sumCall )
	{
		this. Queueforcall= Queueforcall;
		this.name=name;
		this.orderbeforScheduler=orderbeforScheduler;
		this.orderbeforManager= orderbeforManager;
		this.salary=0;
		this.sumCall=sumCall;
		myOrder= new Vector <Call>();
	}
	
	
	public synchronized void run() {
		
	    	 while (!(sumCall.end())) //כל עוד לא נגמרו השיחות!
	 		{
	    		 Call newcall= Queueforcall.extract(); //מוציא שיחה מתור השיחות
	    		 if(newcall!=null) // אם השיחה קיימת 
	    		 {
	    			 sumCall.setallcall(); // אני מעדכן את האוביקט שהצלחתי להוציא שיחה, וכרגע יש שיחה אחת פחות
	    		 myOrder.add( newcall); // אני מוסיף את השיחה לווקטור השיחות שלי שהצלחתי לענות עליהם
		    	 getoder( newcall); // פונקציית עזר ליצירת הזמנה
	    		 }			
	 		}
    		 orderbeforManager.insert(null); // מכניס למנהל שיחה ריקה כדי שיידע שנגמרו השיחות
    		 orderbeforScheduler.insert(null); // מעיר את המשבץ שממתין להזמנות
		}
	
	
	private  synchronized  void getoder( Call newcall) // פונקציית עזר ליצירת הזמנה
	{
		 int IDorder=  newcall.ID ;
	 int sumpizza=  newcall.sumpizza;
		 String Address=  newcall.Address;
		 long visa=  newcall.visa;
		 double priceOrder= 25*sumpizza;
	 double arrivalTime=newcall.Arrive;
	 
	 if(sumpizza<10) // אם יש פחות מ 10 פיצות , אני יוצר הזמנה!
	 {
		 try {
			Thread.sleep((long) (newcall.time*1000)); // זמן יצירת ההזמנה 
		} catch (InterruptedException e) {
			
		}
		orderbeforScheduler.insert(new Order( IDorder,  sumpizza, Address, // מעביר את ההזמנה לתור למשבץ
			 visa, priceOrder, arrivalTime));
		 newcall.setendcall ();
	 } 
	 else  // יותר מ 10 פיצות - מעביר את השיחה למנהל
	 {
		 try {
			Thread.sleep((long) (0.5*1000));
		} catch (InterruptedException e) {
		
		}
		 orderbeforManager.insert(newcall);
	 }
	}

	
	public synchronized void setsalary() { // חישוב משכורת 
		this.salary=myOrder.size()*2;
	
	}

}
