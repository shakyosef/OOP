
public class Call extends Thread { 
	
	protected int sumpizza;
	protected String Address;
	protected long visa;
	protected  double time;
	protected double Arrive;
	protected int ID;
	protected boolean endcall;
	

	
	
	public  Call (int sumpizza,String Address,
			long visa,double time,double Arrive, int ID ) // ���� ����� 
	{
		this.sumpizza=sumpizza;
		this.Address=Address;
		this.visa=visa;
		this.time=time;
		this.Arrive=Arrive;
		endcall= false;
		this.ID=ID; 
		
		
		
	}
	public double getTime()
	{
		return time;
	}
	
	
	
	public synchronized  void run() {
     try{
	Thread.sleep((long) (time*1000)); // ���� ������ ����� ���� ��� ����� ������
	  
	
       }
	catch(InterruptedException e){
	System.out.println("Error");
     }
   
     endCall( );
     
	}
	private synchronized void endCall(	 ) 
	{
		while(endcall==false) //�� ��� ����� �� ������� 
		{
			 try{
	    			this.wait(); //�����
	    	 }
	    	 catch(InterruptedException e)
	    	 {
	             System.out.println("InterruptedException") ;
	    	 }
			
	    }
		
		
		 
		
		
	}
	public  synchronized void setendcall ()
	{
		this.endcall= true;
		 this.notify();
	}
	
     public int getsumpizza()
     {
    	 return sumpizza;
     }
     public  String   getAddress()
     {
    	 return Address;
     }
     public   long getvisa ()
     {
    	 return visa ;
     }
     public  void setid (int i)
     {
    	ID=i;
     }
     public int getid ()
     {
    	return ID;
     }
     public double getArrive ()
     {
    	return Arrive;
     }
 
	
}

	
	


