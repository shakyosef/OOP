
public class PizzaDelivery {
	protected String Address;
	protected double Distance;
	protected int sumpizza;
	protected double priceOrder;
	
	public PizzaDelivery(String Address, // בנאי למשלוח פיצה
			double Distance,int sumpizza,double priceOrder )
	{
		this.Address=Address;
		this.Distance=Distance;
		this.sumpizza=sumpizza;
		this.priceOrder= priceOrder;
	}

}
