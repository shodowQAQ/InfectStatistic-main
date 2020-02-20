
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

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
    			 else if(instructions[i].equals("-out")) {//读取-out输出路径
 					i= setOutPut(++i);
 					if(i==-1) {
 						System.out.println("输出参数错误");
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
		
		/*判断输出路径*/
		public int setOutPut(int i) {
			if(i < instructions.length) { //当下标未越界
				if(instructions[i].matches("^[A-z]:\\\\(\\S+)+(\\.txt)$")) //判断字符串是不是txt文件路径
					output = instructions[i];
				else
					return -1;
			} else
				return -1;
			return i;
		}
    }
    
    /*文件处理类*/
    class ManageFile{
    	/*读取目录下的文件*/
		public void getFileList() {
			File file = new File(input);
			File[] fileList = file.listFiles();
			String fileName;
			for (int i = 0; i < fileList.length; i++) {
				fileName = fileList[i].getName();
					readTxt(input + fileName);
			}
		}
		
	    /*读入txt文件*/
	    public void readTxt(String filePath){
	        try {
	        	int casenumber;
	            BufferedReader bfr = new BufferedReader(new InputStreamReader(
	            		new FileInputStream(new File(filePath)), "UTF-8"));
	            String lineTxt = null;
	            while ((lineTxt = bfr.readLine()) != null) { 
	            	if(!lineTxt.startsWith("//"))
	            	{
	            		judgeCase(lineTxt);
	            	}
	            }
	            bfr.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /*文本处理*/
	    public void judgeCase(String string) {
	    	String case1 = "(\\S+) 新增 感染患者 (\\d+)人";
	    	String case2 = "(\\S+) 新增 疑似患者 (\\d+)人";
	    	String case3 = "(\\S+) 治愈 (\\d+)人";
	    	String case4 = "(\\S+) 死亡 (\\d+)人";
	    	String case5 = "(\\S+) 感染患者 流入 (\\S+) (\\d+)人";
	    	String case6 = "(\\S+) 疑似患者 流入 (\\S+) (\\d+)人";
	    	String case7 = "(\\S+) 疑似患者 确诊感染 (\\d+)人";
	    	String case8 = "(\\S+) 排除 疑似患者 (\\d+)人";
	    	boolean isCase1 = Pattern.matches(case1, string);
	    	boolean isCase2 = Pattern.matches(case2, string);
	    	boolean isCase3 = Pattern.matches(case3, string);
	    	boolean isCase4 = Pattern.matches(case4, string);
	    	boolean isCase5 = Pattern.matches(case5, string);
	    	boolean isCase6 = Pattern.matches(case6, string);
	    	boolean isCase7 = Pattern.matches(case7, string);
	    	boolean isCase8 = Pattern.matches(case8, string);
	    	if(isCase1==true) {
	    		addIP(string);
	    	}
	    	else if(isCase2==true) {
	    		addSP(string);
	    	}
	    	else if(isCase3==true) {
	    		addCure(string);
	    	}
	    	else if(isCase4==true) {
	    		addDeath(string);
	    	}
	    	else if(isCase5==true) {
	    		//System.out.print("感染流");
	    	}
	    	else if(isCase6==true) {
	    		//System.out.print("疑似流");
	    	}
	    	else if(isCase7==true) {
	    		//System.out.print("疑似确.");
	    	}
	    	else if(isCase8=true) {
	    		//System.out.print("疑似排");
	    	}
	    }
	    
	    /*处理新增感染患者*/
	    public void addIP(String string) {
	    	String[] str_arr = string.split(" "); 
	    	int i;
	    	int n = Integer.valueOf(str_arr[3].replace("人", ""));//得到数据
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) { 
	    			data[0][0] += n; //全国感染患者人数增加
	    			data[i][0] += n; //该省份感染患者人数增加
	    			break;
	    		}
	    	}
	    }
	    
	    /*处理疑似感染患者*/
	    public void addSP(String string) {
	    	String[] str_arr = string.split(" ");
	    	int i;
	    	int n = Integer.valueOf(str_arr[3].replace("人", ""));
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) { 
	    			data[0][1] += n; //全国疑似患者人数增加
	    			data[i][1] += n; //该省份疑似患者人数增加
	    			break;
	    		}
	    	}
	    }
	    
	    /*治愈患者处理*/
	    public void addCure(String string) {
	    	String[] str_arr = string.split(" "); //将字符串以空格分割为多个字符串
	    	int i;
	    	int n = Integer.valueOf(str_arr[2].replace("人", ""));
	    	for(i = 0; i < province.length; i++) { 
	    		if(str_arr[0].equals(province[i])) {
	    			data[0][2] += n; //全国治愈人数增加
	    			data[0][0] -= n; //全国感染患者人数减少
	    			data[i][2] += n; //该省份治愈人数增加
	    			data[i][0] -= n; //该省份感染患者人数减少
	    			break;
	    		}
	    	}
	    }
	    
	    /*死亡患者处理*/
	    public void addDeath(String string) {
	    	String[] str_arr = string.split(" "); //将字符串以空格分割为多个字符串
	    	int i;
	    	int n = Integer.valueOf(str_arr[2].replace("人", ""));
	    	
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) {
	    			data[0][3] += n; //全国死亡人数增加
	    			data[0][0] -= n; //全国感染患者人数减少
	    			data[i][3] += n; //该省份死亡人数增加
	    			data[i][0] -= n; //该省份感染患者人数减少
	    			System.out.println(data[0][3]);
	    			System.out.println(data[0][0]);
	    			System.out.println(data[i][3]);
	    			System.out.println(data[i][0]);
	    			break;
	    		}
	    	}
	    }
	    
    }
    
    /*主函数*/
    public static void main(String[] args){
    	InfectStatistic infectstatistic=new InfectStatistic();
        Instructions instruction=infectstatistic.new Instructions();
        instruction.setInstructions(args);
        instruction.checkInstructions();
        ManageFile mangefile=infectstatistic.new ManageFile();
        mangefile.readTxt(input);
    }
    
}


