
public class allcalls { // ����� ������� �� ���� ������ ������� ����� �����.
	private int allcall;
	public allcalls(int x) // ����� �� ���� ������
	{
		this.allcall=x;
	}
  	public void setallcall(int x)
	{
		allcall=x;
	}
	public void setallcall() //����� ������� ����, ���� ������ ������ ���� � 1 
	{
		this.allcall=this.allcall-1;
	}
	public boolean end() //�������� ������ 
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
