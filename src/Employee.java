
abstract class Employee { // מחלקת כל העובדים :)
	protected String name;
	protected  double salary;
	protected boolean endday;

	 abstract public void setsalary();
	 
	public  void setendday(boolean flog)
	{
		endday=flog;
	}
	public  double getsalary()
	{
		return salary;
	}
	
	

}
