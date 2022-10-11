
public class Order implements Comparable<Order>{
	private int IDorder;
	private int sumpizza;
	private String Address;
	private long visa;
	private double priceOrder;
	private double arrivalTime;
	private double distance;
	private  String nameMyKitchenWorker;
	private double salaryMyKitchenWorker;

	public Order(int IDorder, int sumpizza,String Address,
		long visa,double priceOrder,double arrivalTime) 
	{
		this.IDorder=IDorder;
		this.sumpizza=sumpizza;
		this.Address=Address;
		this.visa= visa;
		this.priceOrder=priceOrder;
		this.arrivalTime=arrivalTime;
		distance=0;
		nameMyKitchenWorker="";
		salaryMyKitchenWorker=0;
	
	}
	public int getId()
	{
		return this.IDorder;
	}
		public double getdistance()
	{
		return distance;
	}
		public double getpriceOrder()
		{
			return this.priceOrder;
		}
		public static synchronized void Tostringnewoder(int id)
		{
			System.out.println("New Order Arrived: ");
			System.out.println(" The Id order: "+ id);
			
		}
		public  synchronized void Tostringnewoder()
		{
			
			System.out.println("New Order Arrived: ");
			System.out.println(" The Id order: "+ this.IDorder);
			
		}
		public  synchronized void setmy(double salaryMyKitchenWorker,String nameMyKitchenWorker)
		{
			
			this.nameMyKitchenWorker=nameMyKitchenWorker;
			this.salaryMyKitchenWorker=salaryMyKitchenWorker;
			this.notifyAll();
			
		}
		public synchronized boolean my() {
			while(salaryMyKitchenWorker==0)
				try {
					this.wait();
				} catch (InterruptedException e) {
				
				
				}
			return true;
		}
		public static synchronized void tha(Order newoder,double salaryMyKitchenWorker,String nameMyKitchenWorker  )
		{
		
			System.out.println("The Kitchen Worker name : " +nameMyKitchenWorker);
		    
	    	System.out.println("The salary: "+ salaryMyKitchenWorker);
	    	newoder.ToString();
	    	
		}
	
		public   synchronized void myKitchenWorker(  ) //הדפסה של מי שהכין את ההזמנה
		{
			
			System.out.println("The Kitchen Worker name : " +this.nameMyKitchenWorker);
	    
	    	System.out.println("The salary: "+ this.salaryMyKitchenWorker);
	    	this.ToString();
	    	
		}
	public   synchronized void ToString() { // הדפסת כל המידע של ההזמנה
		
		
		System.out.println("Order information: ");
		System.out.println("Number of pizza:" + sumpizza);
		System.out.println("Price of order:" + priceOrder);
		System.out.println("Distance:" + distance);
		System.out.println("Address:" + Address);
		System.out.println("Visa number:" + visa);


	}
	public void setdistance( double distance)
	{
		this.distance=distance;
	}
	
	public String getadress()
	{
		return this.Address;
	}
	
	public int getsumpizza()
	{
		return this.sumpizza;
	}
	public int compareTo( Order other) {
	if (this.arrivalTime> other.arrivalTime)
		return  -1;
	if (this.arrivalTime< other.arrivalTime)
	return 1;
	
	return 0; 
	}
	



	
	

}
