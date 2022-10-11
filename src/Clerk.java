import java.util.Vector;

public class Clerk  extends Employee implements Runnable {
	
	
	private  Vector <Call> myOrder;// ����� ������ ���� ������ ����� �����
	private Queue <Call>  Queueforcall;// ����� �� ������ ������ �����
	private Queue<Order> orderbeforScheduler; //����� �� ������� ������� ����� �������  
	private   Queue <Call> orderbeforManager;//��� ����� �� ������ ������ �����
	private allcalls sumCall; // ������ ����� ��� ����� ������ �����, ����� ���� �������, ����� ������ ������.
	
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
		
	    	 while (!(sumCall.end())) //�� ��� �� ����� ������!
	 		{
	    		 Call newcall= Queueforcall.extract(); //����� ���� ���� ������
	    		 if(newcall!=null) // �� ����� ����� 
	    		 {
	    			 sumCall.setallcall(); // ��� ����� �� ������� ������� ������ ����, ����� �� ���� ��� ����
	    		 myOrder.add( newcall); // ��� ����� �� ����� ������� ������ ��� ������� ����� �����
		    	 getoder( newcall); // �������� ��� ������ �����
	    		 }			
	 		}
    		 orderbeforManager.insert(null); // ����� ����� ���� ���� ��� ����� ������ ������
    		 orderbeforScheduler.insert(null); // ���� �� ����� ������ �������
		}
	
	
	private  synchronized  void getoder( Call newcall) // �������� ��� ������ �����
	{
		 int IDorder=  newcall.ID ;
	 int sumpizza=  newcall.sumpizza;
		 String Address=  newcall.Address;
		 long visa=  newcall.visa;
		 double priceOrder= 25*sumpizza;
	 double arrivalTime=newcall.Arrive;
	 
	 if(sumpizza<10) // �� �� ���� � 10 ����� , ��� ���� �����!
	 {
		 try {
			Thread.sleep((long) (newcall.time*1000)); // ��� ����� ������ 
		} catch (InterruptedException e) {
			
		}
		orderbeforScheduler.insert(new Order( IDorder,  sumpizza, Address, // ����� �� ������ ���� �����
			 visa, priceOrder, arrivalTime));
		 newcall.setendcall ();
	 } 
	 else  // ���� � 10 ����� - ����� �� ����� �����
	 {
		 try {
			Thread.sleep((long) (0.5*1000));
		} catch (InterruptedException e) {
		
		}
		 orderbeforManager.insert(newcall);
	 }
	}

	
	public synchronized void setsalary() { // ����� ������ 
		this.salary=myOrder.size()*2;
	
	}

}
