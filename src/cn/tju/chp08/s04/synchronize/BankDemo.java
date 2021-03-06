package cn.tju.chp08.s04.synchronize;

class Bank{	
	private int money = 500;	
	//取钱的方法，返回取钱的数目
	public synchronized int  getMoney(int number){		
			if (number < 0) {
				return -1; //不合法的取钱数
			}else if (money < 0) {
				return -2; //余额小于0
			}else if (number > money) {
				return -3; //余额不足
			}else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				money -=number;
				System.out.println( Thread.currentThread().getName() + "取完钱的余额：" + money);
				return number;
			}		
	}
}

class BankThread extends Thread{
	private Bank bank = null;
	public BankThread(Bank bank){
		this.bank = bank;
	}
	
	@Override
	public void run() {
		System.out.println( this.getName() + "取钱：" + bank.getMoney(300));
	}
}

public class BankDemo {
	public static void main(String[] args) {
		Bank bank = new Bank();
		BankThread bt1 = new BankThread(bank);
		bt1.setName("微信");
		bt1.start();
		BankThread bt2 = new BankThread(bank);
		bt2.setName("支付宝");
		bt2.start();
	}

}
