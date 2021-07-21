package whole_project;

public class Seagate extends Disk{

	public Seagate(String name, double price , int volume)
	{
		super(name,price,volume);
	}
	
	public void work()
	{
		System.out.println("This is seagate disk working");
	}
	
}
