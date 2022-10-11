import java.util.Vector;

public class BoundedOueue<T> { // ��� ���� ������ ������ 
	private Vector<T> boundedtor;
	private int maxSize;
	protected boolean enddalavery;
	protected EndDay endday;
	public BoundedOueue(EndDay endday)  // ����
	{
		boundedtor = new Vector<T>(); 
	    this.maxSize=12;
	    this.enddalavery= false;
	    this.endday=endday;
	}
	public synchronized void setendday() // ������ ����
	{
		this.endday.setendday(true);
		 this.notifyAll();
	}
	public BoundedOueue(int maxSize) 
	{
		boundedtor = new Vector<T>();
	    this.maxSize=maxSize;
	    this.enddalavery= false;
	}
	public synchronized void insert(T o)  { // ����� ���� �����
	    while(boundedtor.size()>=maxSize )// �� ��� ���� ������- ����� 
	    {
	    	 try{
	    			this.wait();
	    	 }
	    	 catch(InterruptedException e)
	    	 {
	             System.out.println("InterruptedException") ;
	    	 }
	    }

	    boundedtor.add(o);
	    notifyAll();  // �� ����� ������� ��� - ���� �� ��������!
	}

	 public synchronized T extract()    // ����� ����� ����� 
	{
	     while (boundedtor.isEmpty())
	     {
	    	 try{
	    		 if (endday.getendday())
	    			 return null;
	    		 
	    		this.wait();
	    		} 
	    		  catch(InterruptedException e)
	    		  {
	    			  return null;   
	    		  }
	     }
	     T item =boundedtor.elementAt(0);
	     boundedtor.remove(item);
	     notifyAll();
	     return item;    
	}
	 
	 
		protected T getIndex(int i)
		{
			if ( boundedtor.size()!= i)
				return  boundedtor.get(i);
			else return null;
				
		}
		protected int size()
		{
			return  boundedtor.size();
		}
		public void setenddalavery()
		{
			this.enddalavery=true;
			notifyAll();
		}
	


}
