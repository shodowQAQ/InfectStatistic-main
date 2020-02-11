import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * InfectStatistic
 * TODO
 *
 * @author xxx
 * @version xxx
 * @since xxx
 */
class InfectStatistic {
	static String[] province=new String[24];
	static int[] infected=new int[24];
	static int[] doubt=new int[24];
	static int[] death=new int[24];
	static int[] cure=new int[24];
    public static void main(String[] args) throws IOException{
    	province[0]="全国";
    	province[1]="";
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter lines of text.");
        str = br.readLine();
        System.out.println(str);
        String x="15616";
        int y=Integer.parseInt(x);
        System.out.print(y);
        /*
        File file = new File("log//2020-01-22.log.txt");
        String xx;
        System.out.println(txt2String(file));
        xx=txt2String(file);
        */
    }
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            	if(!s.substring(0, 1).equals("/"))
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}

	
