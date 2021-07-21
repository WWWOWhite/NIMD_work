package whole_project;

//����һ��Ҫд���캯��
// java�г����಻һ��Ҫʵ�ֽӿ��ڵĺ���
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
