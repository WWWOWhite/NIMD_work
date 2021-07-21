package whole_project;


public class Computer extends Component implements Workable{
	
	private CPU cpu;
	private Disk disk;
	
	public Computer(String name,CPU cpu , Disk disk)
	{
		super(name,-1);
		this.cpu = cpu;
		this.disk = disk;
		super.setPrice(cpu.getPrice()+disk.getPrice());
	}
	
	public void work()
	{
		cpu.work();
		disk.work();
	}
	
	public String description()
	{
		StringBuilder computerDescBuilder = new StringBuilder();
		computerDescBuilder.append(super.description());
		computerDescBuilder.append("CPU description:{");
		computerDescBuilder.append(cpu.description());
		computerDescBuilder.append("}");
		computerDescBuilder.append("; Disk descripton: {");
		computerDescBuilder.append(disk.description());
		computerDescBuilder.append("}");
		
		return computerDescBuilder.toString();
	}
}
