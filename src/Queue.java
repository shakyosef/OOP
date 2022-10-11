import java.util.Vector;

 public class Queue<T>   {

	
	 private Vector <T> tor ;
	 protected  EndDay endday;

	
	public Queue(EndDay endday)
	{
		tor =new Vector<T>();
	
		this.endday= endday;
		
	}
	
	
	
	
	public T getIndex(int i)
	{
		if (tor.size()!= i)
			return tor.get(i);
		else return null;
			
	}
	public int size()
	{
		return tor.size();
	}
	
	
	
	public  synchronized void insert(T o) // הכנסת אובייקט לתור 
	{
			 tor.add(o);
		  this.notifyAll();

	}
	public synchronized void setendday()
	{
		this.endday.setendday(true);
		 this.notifyAll();
	}
	public  synchronized T extract()  { // הוצאת אובייקט מהתור
  while (tor.isEmpty()) // אם התור ריק והיום לא נגמר , תמתין 
  {
try{
	if ( endday.getendday())
		return null;
	
		
			
	this.wait();
	
} 
  catch(InterruptedException e)
  {
	  return null;   
  }
	} 
  T t = tor.elementAt(0);
  tor.remove(t);
  return t;
  
}

	

	

}
