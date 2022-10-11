import java.util.Vector;

public class PizzaGuy  extends Employee implements Runnable  {

	private BoundedOueue<PizzaDelivery> readypizza;
	protected  int  maxdelivery;
	private Vector<PizzaDelivery> myDelivery;
	private Vector<PizzaDelivery> pizzago;
	private double tip;
	protected boolean back;
	protected double income;
	private Manager bigboos;
	private double DistanceDrove; 	
	EndDay endday;
	private  int allthedelivery;
	private int sumDelivery;
	private  double sumtip;
	
	
	
	public PizzaGuy(BoundedOueue<PizzaDelivery> readypizza,String name,int allthedelivery,EndDay endday, Manager bigboos )
	{ 
		//בנאי לשליח 
	   this.name= name;
		this.readypizza= readypizza;
		maxdelivery = (int) ((Math.random()*(4-2+1))+2);
		 pizzago= new Vector<PizzaDelivery>();
		myDelivery= new Vector<PizzaDelivery>();
		tip=0;
		this.endday= endday;
		back= false;
		this. allthedelivery=allthedelivery;
		this.bigboos=bigboos;
		DistanceDrove=0;
		
	}
	protected void setmaxdelivery(int x) // שינוי המקסימום משלוחים 
	{
		this.maxdelivery=x;
	}
	
	public  void run() { 
	    	while(!endday.getendday() ) // כל עוד היום לא נגמר 
			   {
	    	PizzaDelivery newDelivery= readypizza.extract(); // להוציא פיצה מתור הפיצות המוכנות 
	    	if (newDelivery== null)
	    	{
	    		break;
	    	}
	    	myDelivery.add(newDelivery); // להוסיף את ההזמנה לתור המשלוחים שלי
	    	income += newDelivery.priceOrder;
	    	if (pizzago.size()<=maxdelivery) // אם עדיין לא הגעתי למקס משלוחים - להמשיך 
	    	{
	        	pizzago.add(newDelivery);
	        	DistanceDrove = DistanceDrove+ newDelivery.Distance; 
	    	}
	    	if (pizzago.size()==maxdelivery)  // אם הגעתי למקס משלוחים - לצאת למשלוח 
	    	{
	    	deliveryProcess();
	    	this.sumtip= this.sumtip + tip;
	    	
	    	this.bigboos.back(maxdelivery); // להודיע לבוס שחזרתי 
	    	}
	    	 
			   }
	    
		}
		
	
	
	private  void deliveryProcess() // תהליך המשלוח 
	{
		tip=0;
	 		
    		for (int i=0; i<pizzago.size(); i++)
    		{
    			
    			double sleeptime=pizzago.get(i).Distance;
    		
    			try {
					Thread.sleep((long) (sleeptime*1000));
					pizzago.remove(i);
	    			Thread.sleep((long) (1*1000));
	    			
				} catch (InterruptedException e) {
					
				}
    			tip= tip +  (int) (Math.random()*16);
    		
    		}
    	
       }
	
	public void setsalary() {

	 
		salary = 3*this.sumDelivery + 4*DistanceDrove+ this.sumtip;

	}
	     


}
