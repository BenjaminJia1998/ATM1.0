import java.util.Scanner;

public class ATM {

    public static void main(String [] args) {

        ATM atm = new ATM();           //创建ATM对象，以便于下面通过atm.xxx的方式调用ATM中的动态方法
        DataBase database = new DataBase();       //最初的想法是：将用户数据放在另外一个java类中当作是一个数据库
        atm.welcome();                 //调用welcome方法
        atm.login(DataBase.code,DataBase.password,DataBase.money,DataBase.current_index);
		//当时为了满足一个分层的思想，将数据以DataBase类的形式存储
		//为了能够减少代码重复率，从DataBase类中获取得到的数据不再以全局变量的形式在ATM类中体现
		//因此也引发了另一个缺点，在下面的不同界面中调用到同一用户的各种信息，就要时刻有这个实参在函数里面供调用
        //两个方案，1.每次需要用到相应数据时，调用DataBase类，2.一次调用，以数组形式传递，当时我选择了第二种
    }

	/**
	* 定义显示登录页面方法
	*/
    public void welcome() {
        System.out.println("***************************");
        System.out.println("****  欢迎使用XXX银行  ****");
        System.out.println("***************************");
    }
	
	/**
	* 定义登录方法
	*/
    public void login(String[] code,String[] password,double[] money,int current_index){//将整个数组传进来，之后根据current_index再进行调用操作
        System.out.print("请输入卡号：");               //控制台提示
        Scanner sc = new Scanner(System.in);			//创建键盘录入对象
        String code_in = sc.nextLine();					//将用户的输入存到code_in字符串中
        
        current_index = printArray(code,code_in);       //将键盘获取到的输入值（卡号）在存储卡号的数组中找到这个卡号的下标，存在current_index中，便于下面调用这个用户的其他信息（其他数组同一游标current_index的数据：密码，金额）
       
	   if(current_index == -1) { //游标为-1，即数组中没有相应的卡号信息
        	System.out.println("卡号不存在！请重新输入卡号");
        	login(code,password,money,current_index);   //卡号不存在时，重新调用该login方法
        }else { //卡号存在
        	if(code_in.equals(code[current_index])){ //判断密码是否输入正确

                for(int i = 0; i < 3; i++){             //若输入密码错误三次，就会吞卡
                    System.out.print("请输入密码：");

                    String password_in = sc.nextLine();

                    if(password_in.equals(password[current_index])){
                        loadSys(code,password,money,current_index);//因为输入正确，会跳到下一个界面，这样的话，为了这三个局部变量能够继续调用得到，在这里我就放到函数调用里面去，麻烦点就是，要一直传一直传
                        break;					
                    }else { //密码输入错误
                        if(i != 2){
                            System.out.println("密码错误，请重新输入！");
                        }else {
                            System.out.println("密码输入已超过三次机会，"
                                + "系统吞卡，请联系客服！");
                            quit(code,password,money,current_index);
                        }
                    }
                }
            }else {
                System.out.println("卡号不存在！");
                login(code,password,money,current_index);
            }
        }
    }

    public static int printArray(String[] code, String code_in) {  //用于通过卡号确定游标，便于其他业务方法调用数组中具体某一位用户对应的密码或金额数据
		for(int i =0;i < code.length; i++) {
			if (code[i].equals(code_in)) {
				return i;
			}
		}
		return -1;
	}

	/**
	* 定义显示主菜单方法
	*/
	public void loadSys(String[] code,String[] password,double[] money,int current_index){ //登录成功，进入菜单页面
    	System.out.println("------------------");
    	System.out.println("1.查询余额");
    	System.out.println("2.存 款");
    	System.out.println("3.取 款");
    	System.out.println("4.转 账");
    	System.out.println("5.修改密码");
    	System.out.println("6.退 出");
    	System.out.println("------------------");
    	
    	System.out.print("请输入对应数字选项：");
    	Scanner sc = new Scanner(System.in);
    	int xx = sc.nextInt();
    	//通过分支语句，根据选项进入不同的业务方法
    	switch(xx) {
    		case 1:
    			chaxun(code,password,money,current_index);//调用查询余额方法
    			break;
    		case 2:
    			cunkuan(code,password,money,current_index);//调用存款方法
    			break;
    		case 3:
    			qukuan(code,password,money,current_index);//调用取款方法
    			break;
    		case 4:
    			zhuanzhang1(code,password,money,current_index);//调用转账方法
    			break;
    		case 5:
    			xiugai(code,password,money,current_index);//调用修改密码方法
    			break;
    		case 6:
    			quit(code,password,money,current_index);//调用退卡方法
    			break;
    		default:
    			System.out.println("输入有误！"); //输入非法选项，提示有误并重新操作
    			loadSys(code,password,money,current_index);
    			break;
    	}
    	
    }

	/**
	* 定义查询余额方法
	*/
    public void chaxun(String[] code,String[] password,double[] money,int current_index){  
    	System.out.println(money[current_index]);
    	loadSys(code,password,money,current_index);
    }
    
	/**
	* 定义存款方法
	*/
    public void cunkuan(String[] code,String[] password,double[] money,int current_index){
    	System.out.println("即将进入存款步骤");
    	System.out.println("请输入对应数字选项：");
		System.out.print("1.继续存款"+"\t");
    	System.out.println("2.返回");
    	Scanner sc = new Scanner(System.in);
    	int xx = sc.nextInt();
    	
    	switch(xx) {
    	case 1:
    		System.out.println("请输入存款金额");
    		Scanner sc1 = new Scanner(System.in);
        	int value = sc1.nextInt();
        	if(value % 100 != 0) {
        		System.out.println("存款失败！存款金额不为整百");
        		cunkuan(code,password,money,current_index);
        	}else {
        		System.out.println("请输入对应数字选项：");
        		System.out.print("1.确认"+"\t");
            	System.out.println("2.返回");
            	Scanner sc2 = new Scanner(System.in);
            	int xxx = sc2.nextInt();
            	
            	switch(xxx) {
            	case 1: 
            		money[current_index] += value;
            		System.out.println("存款成功！");
            		System.out.println("您的账户余额为：" + money[current_index]);
            		loadSys(code,password,money,current_index);
            		break;
            	case 2:
            		cunkuan(code,password,money,current_index);
            		break;
            	default:
            		System.out.println("输入有误！");
            		cunkuan(code,password,money,current_index);
        			break;
            	}
        	}
    		break;
    	case 2:
    		loadSys(code,password,money,current_index);
    		break;
    	default:
    		System.out.println("输入有误！");
    		cunkuan(code,password,money,current_index);
			break;
    	}
    	
    }

	/**
	* 定义取款方法
	*/
    public void qukuan(String[] code,String[] password,double[] money,int current_index){
    	System.out.println("即将进入取款步骤");
    	System.out.println("请输入对应数字选项：");
		System.out.print("1.继续取款"+"\t");
    	System.out.println("2.返回");
    	Scanner sc = new Scanner(System.in);
    	int xx = sc.nextInt();
    	
    	switch(xx) {
    	case 1:
    		System.out.println("请输入取款金额");
    		Scanner sc1 = new Scanner(System.in);
        	int value = sc1.nextInt();        	
        	if(value % 100 != 0 || value > money[current_index]) {
        		System.out.println("取款失败！取款金额不为整百或余额不足！");
        		qukuan(code,password,money,current_index);
        	}else {
        		System.out.println("请输入对应数字选项：");
        		System.out.print("1.确认"+"\t");
            	System.out.println("2.返回");
            	Scanner sc2 = new Scanner(System.in);
            	int xxx = sc2.nextInt();
            	
            	switch(xxx) {
            	case 1: 
            		money[current_index] -= value;
            		System.out.println("取款成功！");
            		System.out.println("您的账户余额为：" + money[current_index]);
            		loadSys(code,password,money,current_index);
            		break;
            	case 2:
            		qukuan(code,password,money,current_index);
            		break;
            	default:
            		System.out.println("输入有误！");
            		qukuan(code,password,money,current_index);
        			break;
            	}
        	}
    		break;
    	case 2:
    		loadSys(code,password,money,current_index);
    		break;
    	default:
    		System.out.println("输入有误！");
    		qukuan(code,password,money,current_index);
			break;
    	}
    }

    public void zhuanzhang1(String[] code,String[] password,double[] money,int current_index) {
    	System.out.println("即将进入转账步骤");
    	System.out.println("请输入对应数字选项：");
		System.out.print("1.继续转账"+"\t");
    	System.out.println("2.返回");
    	Scanner sc = new Scanner(System.in);
    	int xx = sc.nextInt();
    	
    	switch(xx) {
    	case 1:
    		System.out.println("请输入收款人卡号");
        	Scanner sc1 = new Scanner(System.in);
        	String code_in = sc1.nextLine();
        	if(code_in.equals(code[current_index])) {
        		System.out.println("不能转账给自己！请重新输入");
        		zhuanzhang1(code,password,money,current_index);
        	}else {
        		int target_index2 = printArray(code,code_in);
            	if(target_index2 == -1) {
            		System.out.println("卡号不存在！请重新输入收款人卡号");
            		zhuanzhang1(code,password,money,current_index);
            	}else {
            		zhuanzhang2(code,password,money,current_index,target_index2);
            	}
        	}
    		break;
    	case 2:
    		loadSys(code,password,money,current_index);
    		break;
    	default:
    		System.out.println("输入有误！");
    		zhuanzhang1(code,password,money,current_index);
			break;
    	}

    }
    
    public void zhuanzhang2(String[] code,String[] password,double[] money,int current_index, int target_index2) {
    	System.out.println("请输入转账金额");
		Scanner sc = new Scanner(System.in);
    	int value = sc.nextInt();
    	if(value > money[current_index]) {
    		System.out.println("余额不足！当前余额为：" + money[current_index]);
    		zhuanzhang2(code,password,money,current_index,target_index2);
    	}else {
    		System.out.println("请再次核对对方卡号：" + code[target_index2]);
    		System.out.println("请输入对应数字选项：");
    		System.out.print("1.确 认"+"\t");
        	System.out.println("2.返回");
        	Scanner sc1 = new Scanner(System.in);
        	int xx = sc1.nextInt();
        	
        	switch(xx) {
        	case 1:
        		zhuanzhang3(code,password,money,current_index,target_index2,value);
        		break;
        	case 2:
        		zhuanzhang1(code,password,money,current_index);
        	}
    	}
    }
    
    public void zhuanzhang3(String[] code,String[] password,double[] money,int current_index, int target_index2, int value) {
    	money[current_index] -= value;
    	money[target_index2] += value;
    	System.out.println("转账成功！当前余额为：" + money[current_index]);
    	loadSys(code,password,money,current_index);
    }
    
	/**
	* 定义修改密码方法
	*/
    public void xiugai(String[] code,String[] password,double[] money,int current_index){
    	System.out.println("请输入旧密码");
    	Scanner sc = new Scanner(System.in);
    	String oldpsd = sc.nextLine();
        if(!oldpsd.equals(password[current_index])){
        	System.out.println("原密码输入错误！");
        	xiugai(code,password,money,current_index);
        }else {
        	System.out.println("请输入新密码");
        	String newpsd1 = sc.nextLine();
        	System.out.println("请确认新密码");
        	String newpsd2 = sc.nextLine();
        	if(!newpsd1.equals(newpsd2)){
            	System.out.println("两次密码不一致！");
            	xiugai(code,password,money,current_index);
            }else {
            	password[current_index] = newpsd2;
            	System.out.println("密码修改成功！");
            	loadSys(code,password,money,current_index);
            }
        }
    }
	
	/**
	* 定义退出方法
	*/
    public void quit(String[] code,String[] password,double[] money,int current_index){
    	System.out.println("已退出用户登录！请取走您的银行卡");
    	login(code,password,money,current_index);
    }
    

}