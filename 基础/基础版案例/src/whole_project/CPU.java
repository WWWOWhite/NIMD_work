package whole_project;

//子类一定要写构造函数
// java中抽象类不一定要实现接口内的函数
public abstract class CPU extends Component implements Workable{
	
	protected int coreNum;
	
	public CPU(String name, int coreNum,double price)
	{
		super(name,price);
		this.coreNum = coreNum;
	}
	
	public String description()
	{
		StringBuilder cpuDescBuilder = new StringBuilder();
		cpuDescBuilder.append(super.description());
		cpuDescBuilder.append("Core Num : {");
		cpuDescBuilder.append(coreNum);
		cpuDescBuilder.append("}");
		return cpuDescBuilder.toString();
	}




}
