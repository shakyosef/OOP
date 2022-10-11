import java.util.Vector;

public class Manager implements Runnable  {

	pizza branch; 
	private Queue<Call>  orderbeforManager;
	private InformationSystem informationsystem ; 
	private  int sumDelivery;
	private allcalls sumCall;
	private   int complitedDelivery;
	private int count;
	private double  income;
	private EndDay endday;
	private String name;
	public Manager (pizza branch,int sumDelivery )
	{
		this.branch=branch;
		this.orderbeforManager= branch.orderbeforManager;
		this.informationsystem=branch.informationsystem; 
		sumCall= branch.sumCall;
		complitedDelivery=0;
		income= 0 ; count = 0 ; 
		 endday=branch.endday;
		 this.sumDelivery=sumDelivery;
		 this.name = "Shir & Shaked";
	}


	
	private  synchronized  void getoder( Call newcall) //מקבל שיחה והופך אותה להזמנה
	{
		 int IDorder=  newcall.ID ;
	 int sumpizza=  newcall.sumpizza;
		 String Address=  newcall.Address;
		 long visa=  newcall.visa;
		 double priceOrder;
	 double arrivalTime=newcall.Arrive;
	 
	 if(sumpizza>20)
		  priceOrder= 15*sumpizza*0.9; //מחיר אם יש יותר מ 20 פיצות
	 else 
		 priceOrder= 15*sumpizza; // אם יש בין 10-20 פיצות
	      double distance= setdistance( newcall); //מחשב את המרחק 
	      
	      Order newoder= (new Order( IDorder,  sumpizza, Address,
	 			 visa, priceOrder, arrivalTime)); //יצירת הזמנה והכנסה שלה למערכת מידע
	      informationsystem.insert(newoder); 
	      newcall.setendcall ();
	}
	
	
	public  void back(int max) // השליח מודיע אם הוא חזר וכמה משלוחים הוא הוציא
	{
		complitedDelivery =complitedDelivery+max;
		this.sumDelivery= sumDelivery-max;
		if (sumDelivery<=10) //אם נותרו פחות מ 10 הזזמנות , משנים את המקס משלוח של השליחים
		{
			for (int i=0; i<branch.pizzaguys.size();i++)
   		 branch.pizzaguys.get(i).setmaxdelivery(1);
		
		}
	
			

	}
	
	public synchronized void run() {
		
	
		while(!(endday.getendday())) { // כל עוד לא נגמר היום 
			if (sumDelivery<=10)  //אם נותרו פחות מ 10 הזזמנות , משנים את המקס משלוח של השליחים
			{
				for (int i=0; i<branch.pizzaguys.size();i++)
	   		 branch.pizzaguys.get(i).setmaxdelivery(1);
			}
		
	    	 if(sumDelivery==0) // עדכון שהיום נגמר
	    	 {
	    		 
	    			for ( int i = 0 ; i <branch.pizzaguys.size() ; i ++  )
	    			{
	    				branch.pizzaguys.get(i).setsalary();
	    				count += branch.pizzaguys.get(i).getsalary();
	    			}
	    		 branch.orderbeforScheduler.setendday(); //מעדכן את כל העובדים שנגמר היום 
	    		 branch.informationsystem.setendday();
	    		 branch.readypizza.setendday();
	    		 calculendoftheday();
	    		 printendoftheday() ;
	    		 endday.setendday(true);
	    		break;
	    		
	    	 }
	    	 while(!sumCall.end())
	    	 {
	    	 Call newcall= orderbeforManager.extract();
	    	 if(newcall!=null)
	    	 getoder( newcall);
	    	 }
		}
	}
	
	private  void calculendoftheday() // חישובי סוף היום 
	{
		Vector <Employee > employee=	branch.employee;
	
		for(int i=0; i<employee.size();i++)
		{
			
			employee.get(i).setsalary();
			count += employee.get(i).getsalary();
			
    	}
		for ( int i = 0 ; i <branch.pizzaguys.size() ; i ++  )
		{
			
			income = + branch.pizzaguys.get(i).income;
		}
		for(int i=0; i<employee.size();i++)
		{
			employee.get(i).setendday(true);
    	}
		
	}
	private  void printendoftheday() { //הדפסות של סוף היום 
		System.out.println("The total salay is: "+ count);
		System.out.println("The total deliverys that sent: " +complitedDelivery);
		
		System.out.println("The total profit: "+ income);

	}
	
	private  double  setdistance(Call newoder)
	{
		
		double distance;
		String Address=  newoder.Address;
		distance= Distance(Address);
		return distance; 	
	}
	
	private double Distance(String Address)
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


	

}
