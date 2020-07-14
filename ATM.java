import java.util.Scanner;

public class ATM {

    public static void main(String [] args) {

        ATM atm = new ATM();           //����ATM�����Ա�������ͨ��atm.xxx�ķ�ʽ����ATM�еĶ�̬����
        DataBase database = new DataBase();       //������뷨�ǣ����û����ݷ�������һ��java���е�����һ�����ݿ�
        atm.welcome();                 //����welcome����
        atm.login(DataBase.code,DataBase.password,DataBase.money,DataBase.current_index);
		//��ʱΪ������һ���ֲ��˼�룬��������DataBase�����ʽ�洢
		//Ϊ���ܹ����ٴ����ظ��ʣ���DataBase���л�ȡ�õ������ݲ�����ȫ�ֱ�������ʽ��ATM��������
		//���Ҳ��������һ��ȱ�㣬������Ĳ�ͬ�����е��õ�ͬһ�û��ĸ�����Ϣ����Ҫʱ�������ʵ���ں������湩����
        //����������1.ÿ����Ҫ�õ���Ӧ����ʱ������DataBase�࣬2.һ�ε��ã���������ʽ���ݣ���ʱ��ѡ���˵ڶ���
    }

	/**
	* ������ʾ��¼ҳ�淽��
	*/
    public void welcome() {
        System.out.println("***************************");
        System.out.println("****  ��ӭʹ��XXX����  ****");
        System.out.println("***************************");
    }
	
	/**
	* �����¼����
	*/
    public void login(String[] code,String[] password,double[] money,int current_index){//���������鴫������֮�����current_index�ٽ��е��ò���
        System.out.print("�����뿨�ţ�");               //����̨��ʾ
        Scanner sc = new Scanner(System.in);			//��������¼�����
        String code_in = sc.nextLine();					//���û�������浽code_in�ַ�����
        
        current_index = printArray(code,code_in);       //�����̻�ȡ��������ֵ�����ţ��ڴ洢���ŵ��������ҵ�������ŵ��±꣬����current_index�У����������������û���������Ϣ����������ͬһ�α�current_index�����ݣ����룬��
       
	   if(current_index == -1) { //�α�Ϊ-1����������û����Ӧ�Ŀ�����Ϣ
        	System.out.println("���Ų����ڣ����������뿨��");
        	login(code,password,money,current_index);   //���Ų�����ʱ�����µ��ø�login����
        }else { //���Ŵ���
        	if(code_in.equals(code[current_index])){ //�ж������Ƿ�������ȷ

                for(int i = 0; i < 3; i++){             //����������������Σ��ͻ��̿�
                    System.out.print("���������룺");

                    String password_in = sc.nextLine();

                    if(password_in.equals(password[current_index])){
                        loadSys(code,password,money,current_index);//��Ϊ������ȷ����������һ�����棬�����Ļ���Ϊ���������ֲ������ܹ��������õõ����������Ҿͷŵ�������������ȥ���鷳����ǣ�Ҫһֱ��һֱ��
                        break;					
                    }else { //�����������
                        if(i != 2){
                            System.out.println("����������������룡");
                        }else {
                            System.out.println("���������ѳ������λ��ᣬ"
                                + "ϵͳ�̿�������ϵ�ͷ���");
                            quit(code,password,money,current_index);
                        }
                    }
                }
            }else {
                System.out.println("���Ų����ڣ�");
                login(code,password,money,current_index);
            }
        }
    }

    public static int printArray(String[] code, String code_in) {  //����ͨ������ȷ���α꣬��������ҵ�񷽷����������о���ĳһλ�û���Ӧ�������������
		for(int i =0;i < code.length; i++) {
			if (code[i].equals(code_in)) {
				return i;
			}
		}
		return -1;
	}

	/**
	* ������ʾ���˵�����
	*/
	public void loadSys(String[] code,String[] password,double[] money,int current_index){ //��¼�ɹ�������˵�ҳ��
    	System.out.println("------------------");
    	System.out.println("1.��ѯ���");
    	System.out.println("2.�� ��");
    	System.out.println("3.ȡ ��");
    	System.out.println("4.ת ��");
    	System.out.println("5.�޸�����");
    	System.out.println("6.�� ��");
    	System.out.println("------------------");
    	
    	System.out.print("�������Ӧ����ѡ�");
    	Scanner sc = new Scanner(System.in);
    	int xx = sc.nextInt();
    	//ͨ����֧��䣬����ѡ����벻ͬ��ҵ�񷽷�
    	switch(xx) {
    		case 1:
    			chaxun(code,password,money,current_index);//���ò�ѯ����
    			break;
    		case 2:
    			cunkuan(code,password,money,current_index);//���ô���
    			break;
    		case 3:
    			qukuan(code,password,money,current_index);//����ȡ���
    			break;
    		case 4:
    			zhuanzhang1(code,password,money,current_index);//����ת�˷���
    			break;
    		case 5:
    			xiugai(code,password,money,current_index);//�����޸����뷽��
    			break;
    		case 6:
    			quit(code,password,money,current_index);//�����˿�����
    			break;
    		default:
    			System.out.println("��������"); //����Ƿ�ѡ���ʾ�������²���
    			loadSys(code,password,money,current_index);
    			break;
    	}
    	
    }

	/**
	* �����ѯ����
	*/
    public void chaxun(String[] code,String[] password,double[] money,int current_index){  
    	System.out.println(money[current_index]);
    	loadSys(code,password,money,current_index);
    }
    
	/**
	* �������
	*/
    public void cunkuan(String[] code,String[] password,double[] money,int current_index){
    	System.out.println("�����������");
    	System.out.println("�������Ӧ����ѡ�");
		System.out.print("1.�������"+"\t");
    	System.out.println("2.����");
    	Scanner sc = new Scanner(System.in);
    	int xx = sc.nextInt();
    	
    	switch(xx) {
    	case 1:
    		System.out.println("����������");
    		Scanner sc1 = new Scanner(System.in);
        	int value = sc1.nextInt();
        	if(value % 100 != 0) {
        		System.out.println("���ʧ�ܣ�����Ϊ����");
        		cunkuan(code,password,money,current_index);
        	}else {
        		System.out.println("�������Ӧ����ѡ�");
        		System.out.print("1.ȷ��"+"\t");
            	System.out.println("2.����");
            	Scanner sc2 = new Scanner(System.in);
            	int xxx = sc2.nextInt();
            	
            	switch(xxx) {
            	case 1: 
            		money[current_index] += value;
            		System.out.println("���ɹ���");
            		System.out.println("�����˻����Ϊ��" + money[current_index]);
            		loadSys(code,password,money,current_index);
            		break;
            	case 2:
            		cunkuan(code,password,money,current_index);
            		break;
            	default:
            		System.out.println("��������");
            		cunkuan(code,password,money,current_index);
        			break;
            	}
        	}
    		break;
    	case 2:
    		loadSys(code,password,money,current_index);
    		break;
    	default:
    		System.out.println("��������");
    		cunkuan(code,password,money,current_index);
			break;
    	}
    	
    }

	/**
	* ����ȡ���
	*/
    public void qukuan(String[] code,String[] password,double[] money,int current_index){
    	System.out.println("��������ȡ���");
    	System.out.println("�������Ӧ����ѡ�");
		System.out.print("1.����ȡ��"+"\t");
    	System.out.println("2.����");
    	Scanner sc = new Scanner(System.in);
    	int xx = sc.nextInt();
    	
    	switch(xx) {
    	case 1:
    		System.out.println("������ȡ����");
    		Scanner sc1 = new Scanner(System.in);
        	int value = sc1.nextInt();        	
        	if(value % 100 != 0 || value > money[current_index]) {
        		System.out.println("ȡ��ʧ�ܣ�ȡ���Ϊ���ٻ����㣡");
        		qukuan(code,password,money,current_index);
        	}else {
        		System.out.println("�������Ӧ����ѡ�");
        		System.out.print("1.ȷ��"+"\t");
            	System.out.println("2.����");
            	Scanner sc2 = new Scanner(System.in);
            	int xxx = sc2.nextInt();
            	
            	switch(xxx) {
            	case 1: 
            		money[current_index] -= value;
            		System.out.println("ȡ��ɹ���");
            		System.out.println("�����˻����Ϊ��" + money[current_index]);
            		loadSys(code,password,money,current_index);
            		break;
            	case 2:
            		qukuan(code,password,money,current_index);
            		break;
            	default:
            		System.out.println("��������");
            		qukuan(code,password,money,current_index);
        			break;
            	}
        	}
    		break;
    	case 2:
    		loadSys(code,password,money,current_index);
    		break;
    	default:
    		System.out.println("��������");
    		qukuan(code,password,money,current_index);
			break;
    	}
    }

    public void zhuanzhang1(String[] code,String[] password,double[] money,int current_index) {
    	System.out.println("��������ת�˲���");
    	System.out.println("�������Ӧ����ѡ�");
		System.out.print("1.����ת��"+"\t");
    	System.out.println("2.����");
    	Scanner sc = new Scanner(System.in);
    	int xx = sc.nextInt();
    	
    	switch(xx) {
    	case 1:
    		System.out.println("�������տ��˿���");
        	Scanner sc1 = new Scanner(System.in);
        	String code_in = sc1.nextLine();
        	if(code_in.equals(code[current_index])) {
        		System.out.println("����ת�˸��Լ�������������");
        		zhuanzhang1(code,password,money,current_index);
        	}else {
        		int target_index2 = printArray(code,code_in);
            	if(target_index2 == -1) {
            		System.out.println("���Ų����ڣ������������տ��˿���");
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
    		System.out.println("��������");
    		zhuanzhang1(code,password,money,current_index);
			break;
    	}

    }
    
    public void zhuanzhang2(String[] code,String[] password,double[] money,int current_index, int target_index2) {
    	System.out.println("������ת�˽��");
		Scanner sc = new Scanner(System.in);
    	int value = sc.nextInt();
    	if(value > money[current_index]) {
    		System.out.println("���㣡��ǰ���Ϊ��" + money[current_index]);
    		zhuanzhang2(code,password,money,current_index,target_index2);
    	}else {
    		System.out.println("���ٴκ˶ԶԷ����ţ�" + code[target_index2]);
    		System.out.println("�������Ӧ����ѡ�");
    		System.out.print("1.ȷ ��"+"\t");
        	System.out.println("2.����");
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
    	System.out.println("ת�˳ɹ�����ǰ���Ϊ��" + money[current_index]);
    	loadSys(code,password,money,current_index);
    }
    
	/**
	* �����޸����뷽��
	*/
    public void xiugai(String[] code,String[] password,double[] money,int current_index){
    	System.out.println("�����������");
    	Scanner sc = new Scanner(System.in);
    	String oldpsd = sc.nextLine();
        if(!oldpsd.equals(password[current_index])){
        	System.out.println("ԭ�����������");
        	xiugai(code,password,money,current_index);
        }else {
        	System.out.println("������������");
        	String newpsd1 = sc.nextLine();
        	System.out.println("��ȷ��������");
        	String newpsd2 = sc.nextLine();
        	if(!newpsd1.equals(newpsd2)){
            	System.out.println("�������벻һ�£�");
            	xiugai(code,password,money,current_index);
            }else {
            	password[current_index] = newpsd2;
            	System.out.println("�����޸ĳɹ���");
            	loadSys(code,password,money,current_index);
            }
        }
    }
	
	/**
	* �����˳�����
	*/
    public void quit(String[] code,String[] password,double[] money,int current_index){
    	System.out.println("���˳��û���¼����ȡ���������п�");
    	login(code,password,money,current_index);
    }
    

}