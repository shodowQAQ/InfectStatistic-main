
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * InfectStatistic
 * TODO
 *
 * @author ����
 * @version 1.0
 * @since 2020-02-19
 */
class InfectStatistic {
	/*���徲̬����ʡ�ݺ�����*/
	static String[] province = {"ȫ��", "����", "����" ,"����", "����", "����","����","�㶫", "����", "����", "����", "�ӱ�", "����", "������", "����", "����", "����","����", "����", "����", "���ɹ�", "����", "�ຣ", "ɽ��", "ɽ��", "����", "�Ϻ�","�Ĵ�", "̨��", "���", "����", "���", "�½�", "����", "�㽭"};
	static int[][] data =new int[35][4];
   
    /*����txt�ļ�*/
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
            String s = null;
            while((s = br.readLine()) != null){//ʹ��readLine������һ�ζ�һ��
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

/*ָ����*/
class Instructions{
	String [] Instructions;
	void setInstructions(String[] a){//����ָ��
		Instructions=a;
	}		
	public boolean checkInstructions() {//�ж�ָ���Ƿ�����ɹ�
		if(Instructions.length==0)
		{
			System.out.println("ָ������ʧ��");
			return false;
		}
		if(!Instructions[0].equals("list")) {//�ж������ʽ�Ƿ���ȷ
			System.out.println("ָ�������list��ͷ");
			return false;
		}
		return true;
	}
}	
