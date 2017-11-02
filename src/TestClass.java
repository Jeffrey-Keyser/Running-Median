
public class TestClass {

	public static void main(String args[])
	
	{
		MaxPQ quene = new MaxPQ();
		
		quene.insert(45);
		quene.insert(23);
		quene.insert(46);
		quene.insert(10);
		quene.insert(44);
		
		for (int i = 0; i < quene.size(); i++)
		{
		//	System.out.println(items[i]);
		}
		
		quene.getMax();
		
		System.out.println(quene.removeMax());
		
		
		
	}
}
