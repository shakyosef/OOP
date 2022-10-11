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
		//���� ����� 
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
	protected void setmaxdelivery(int x) // ����� �������� ������� 
	{
		this.maxdelivery=x;
	}
	
	public  void run() { 
	    	while(!endday.getendday() ) // �� ��� ���� �� ���� 
			   {
	    	PizzaDelivery newDelivery= readypizza.extract(); // ������ ���� ���� ������ ������� 
	    	if (newDelivery== null)
	    	{
	    		break;
	    	}
	    	myDelivery.add(newDelivery); // ������ �� ������ ���� �������� ���
	    	income += newDelivery.priceOrder;
	    	if (pizzago.size()<=maxdelivery) // �� ����� �� ����� ���� ������� - ������ 
	    	{
	        	pizzago.add(newDelivery);
	        	DistanceDrove = DistanceDrove+ newDelivery.Distance; 
	    	}
	    	if (pizzago.size()==maxdelivery)  // �� ����� ���� ������� - ���� ������ 
	    	{
	    	deliveryProcess();
	    	this.sumtip= this.sumtip + tip;
	    	
	    	this.bigboos.back(maxdelivery); // ������ ���� ������ 
	    	}
	    	 
			   }
	    
		}
		
	
	
	private  void deliveryProcess() // ����� ������ 
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
