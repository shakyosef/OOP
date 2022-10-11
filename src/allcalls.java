
public class allcalls { // מחלקה המייצגת את מספר השיחות הנכנסות הצפוי להגיע.
	private int allcall;
	public allcalls(int x) // מקבלת את מספר השיחות
	{
		this.allcall=x;
	}
  	public void setallcall(int x)
	{
		allcall=x;
	}
	public void setallcall() //במידה ומתקבלת שיחה, מספר השיחות שנשארו יורד ב 1 
	{
		this.allcall=this.allcall-1;
	}
	public boolean end() //כשנגמרות השיחות 
	{
		if (allcall==0)
			return true;
		else
			return false;
	}
	public int getallcall() 
	{
		return this.allcall;
	}

}
