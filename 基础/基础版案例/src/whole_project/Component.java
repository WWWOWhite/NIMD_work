package whole_project;

public abstract class Component {

	private double price;
	private String name;
	
	public Component(String name , double price)
	{
		this.price = price;
		this.name = name;
	}
	
	public String description()
	{
		StringBuilder descriptionBuilder = new StringBuilder();
		descriptionBuilder.append("Name");
		descriptionBuilder.append(name);
		descriptionBuilder.append("};Price:{");
		descriptionBuilder.append(price);
		descriptionBuilder.append("};");
		return descriptionBuilder.toString();
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}
