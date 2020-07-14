
public class DataBase {

	 //刚开始Java学习的时候，还是个小白，不懂得通过io流将数据保存在本地文档里，更不会数据库操作，因此新建了一个DB类负责存放用户数据
    public static String[] code = {"1001","1002","1003","1004"};//卡号、密码应该是字符串，将现有的用户的卡号按找一定的排列顺序组成一个字符串数组
    public static String[] password = {"111","222","333","444"};
    public static double[] money = {1000.00,2000.00,3000.00,4000.00};//存款为了易于更改数值，应该是由每个用户的存款组成数组
    public static int current_index = -1;  
    //

}