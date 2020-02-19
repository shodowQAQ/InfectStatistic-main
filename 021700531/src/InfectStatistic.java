
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    
    public static void main(String[] args){
        Instructions instruction=new Instructions();
        instruction.setInstructions(args);
        instruction.checkInstructions();
      //  System.out.println("Enter lines of text.");
      //  str = br.readLine();
      //  System.out.println(str);
        
        /*
        File file = new File("log//2020-01-22.log.txt");
        String xx;
        System.out.println(txt2String(file));
        xx=txt2String(file);
        */
    }
}

/*指令类*/
class Instructions{
	String [] Instructions;
	void setInstructions(String[] a){//设置指令
		Instructions=a;
	}		
	public boolean checkInstructions() {//判断指令是否输入成功
		if(Instructions.length==0)
		{
			System.out.println("指令输入失败");
			return false;
		}
		if(!Instructions[0].equals("list")) {//判断命令格式是否正确
			System.out.println("指令必须以list开头");
			return false;
		}
		return true;
	}
}	
