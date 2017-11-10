class Common{
	static int value;
	boolean flag =true;
	
	public synchronized void produce(int i){
		if(flag == true){
			value=i;
			System.out.println("Producer produced :"+value);
			flag=false;
			notify();
		}
		try{
			wait();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public synchronized int consume(){
		if(flag==true){
			try{
				wait();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
		flag =true;
		notify();
		return(value);
		
	}
}
class Producer extends Thread{
	Common c;
	Producer(Common c){
		this.c=c;
	}
	public void run(){
		int i=0;
		
		for(int k=0;k<=10;k++){
			c.produce(i);
			i=i+1;
			try{
				Thread.sleep(1000);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}

class Consumer extends Thread{
	Common c;
	public Consumer(Common c) {
		
		this.c=c;
	}
	public void run()
	{
		for(int j=0; j<=10;j++)
		{
			int i=c.consume();
			System.out.println("Consumer consumed: "+i);
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
public class ProduceConsume {

	public static void main(String[] args) {
	
		Common c1 =new Common();
		Producer p =new Producer(c1);
		Consumer cr = new Consumer(c1);
		p.start();
		cr.start();
	}
}
