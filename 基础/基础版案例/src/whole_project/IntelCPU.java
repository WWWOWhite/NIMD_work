package whole_project;

//�������дһ�鹹�캯��

public class IntelCPU extends CPU{
	public IntelCPU(String name,int coreNum,double price) {
		super(name,coreNum,price);
	}
	
	public void work() {
		System.out.println("This is Inrel's CPU working");
	}
}
