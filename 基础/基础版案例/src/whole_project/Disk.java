package whole_project;

public abstract class Disk extends Component implements Workable {
	
	protected int volume;
	
	public Disk(String name,double price , int volume)
	{
		super(name,price);
		this.volume = volume;
	}
	
	public String description() {
		StringBuilder cpuDescBuilder = new StringBuilder();
		cpuDescBuilder.append(super.description());
		cpuDescBuilder.append("size(MB):{");
		cpuDescBuilder.append(volume);
		cpuDescBuilder.append("MB}");
		return cpuDescBuilder.toString();
	}

}
