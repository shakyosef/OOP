import java.util.Vector;

public class KitchenWorker  extends Employee implements Runnable {
	private  InformationSystem informationsystem ; 
	private BoundedOueue<PizzaDelivery> readypizza; //��� ���� �� ������ ������� 
	private Vector<PizzaDelivery> mypizza;//����� ����� �� �� ������ ���� ����� 
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

	
	private synchronized void calculsalary(Order firstOrder) // ������� ������ ������ 
	{
		 int monapizza= firstOrder.getsumpizza();
		 salary=salary +( monapizza *2);
		 firstOrder.tha(firstOrder,salary ,name  );
	}



	public  void run() {
				   while(!endday.getendday()) //�� ��� ���� �� ���� 
				   {
					   Order firstOrder;
		        		firstOrder =  informationsystem.extract();//������ ����� ������  ��� ������� ��� ���� ���� ����
		        		if (firstOrder!= null)
			        	calculsalary( firstOrder); // ���� �� ������� ��� 
		        		else
		        			continue;
		        		if (firstOrder!= null)
		        		{
		    	PizzaDelivery ready=new PizzaDelivery(firstOrder.getadress() ,firstOrder.getdistance(),firstOrder.getsumpizza(),firstOrder.getpriceOrder());
		    	mypizza.add(ready); // ����� �� ������ ������ ������ ������� ���  
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
