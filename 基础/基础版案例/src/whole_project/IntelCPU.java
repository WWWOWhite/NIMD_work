package whole_project;

//子类必须写一遍构造函数

public class IntelCPU extends CPU{
	public IntelCPU(String name,int coreNum,double price) {
		super(name,coreNum,price);
	}
	
	public void work() {
		System.out.println("This is Inrel's CPU working");
	}
}
