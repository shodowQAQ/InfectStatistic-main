
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * InfectStatistic
 * TODO
 *
 * @author 温温
 * @version 1.0
 * @since 2020-02-19
 */
class InfectStatistic {
	/*定义静态数据省份和数据*/
	static String[] province = {"全国", "安徽", "澳门" ,"北京", "重庆", "福建","甘肃","广东", "广西", "贵州", "海南", "河北", "河南", "黑龙江", "湖北", "湖南", "吉林","江苏", "江西", "辽宁", "内蒙古", "宁夏", "青海", "山东", "山西", "陕西", "上海","四川", "台湾", "天津", "西藏", "香港", "新疆", "云南", "浙江"};
	static int[][] data =new int[35][4];
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	static String date;
	static String input;
	static String output;
    /*读入txt文件*/
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine()) != null){//使用readLine方法，一次读一行
            	if(!s.substring(0, 1).equals("/"))
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    
    /*指令类*/
    class Instructions{
    	String [] instructions;
    	void setInstructions(String[] a){//设置指令
    		instructions=a;
    	}		
    	public boolean checkInstructions() {//判断指令是否输入成功
    		if(instructions.length==0)
    		{
    			System.out.println("指令输入失败");
    			return false;
    		}
    		if(!instructions[0].equals("list")) {//判断命令格式是否正确
    			System.out.println("指令必须以list开头");
    			return false;
    		}
    		for (int i=0;i<instructions.length;i++)
    		{
    			 if(instructions[i].equals("-date")) { //读取到-date参数
 					i= setDate(++i);
 					if(i == -1) { //说明上述步骤中发现命令行出错
 						System.out.println("日期输入格式错误");
 						return false;
 					}
    			}
    			 else if(instructions[i].equals("-log")) {//读取-log输入路径
 					i = setInPut(++i);
 					if(i == -1) { //说明上述步骤中发现命令行出错
 						System.out.println("输入参数错误");
 						return false;
 					}
    			 }
    		}
    		return true;
    	}
    	
    	/*判断日期指令*/
		public int setDate(int i) {
			if(i < instructions.length) { //当下标未越界
				date = instructions[i] + ".log.txt";
		    }	
			else
			{
				return -1;
			}
			return i;
		}	

		/*判断输入路径指令*/
		public int setInPut(int i) {
			if(i < instructions.length) { 
				if(instructions[i].matches("^[A-z]:\\\\(.+?\\\\)*$")) //正则表达式判断路径
					input = instructions[i];
				else
					return -1;
			} else
				return -1;
			return i;
		}
		
    }
    public static void main(String[] args){
    	InfectStatistic infectstatistic=new InfectStatistic();
        Instructions instruction=infectstatistic.new Instructions();
        instruction.setInstructions(args);
        instruction.checkInstructions();

    }
    
}


