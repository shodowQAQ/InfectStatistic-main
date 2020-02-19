
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
 * @author ����
 * @version 1.0
 * @since 2020-02-19
 */
class InfectStatistic {
	/*���徲̬����ʡ�ݺ�����*/
	static String[] province = {"ȫ��", "����", "����" ,"����", "����", "����","����","�㶫", "����", "����", "����", "�ӱ�", "����", "������", "����", "����", "����","����", "����", "����", "���ɹ�", "����", "�ຣ", "ɽ��", "ɽ��", "����", "�Ϻ�","�Ĵ�", "̨��", "���", "����", "���", "�½�", "����", "�㽭"};
	static int[][] data =new int[35][4];
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	static String date;
	static String input;
	static String output;
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
    
    /*ָ����*/
    class Instructions{
    	String [] instructions;
    	void setInstructions(String[] a){//����ָ��
    		instructions=a;
    	}		
    	public boolean checkInstructions() {//�ж�ָ���Ƿ�����ɹ�
    		if(instructions.length==0)
    		{
    			System.out.println("ָ������ʧ��");
    			return false;
    		}
    		if(!instructions[0].equals("list")) {//�ж������ʽ�Ƿ���ȷ
    			System.out.println("ָ�������list��ͷ");
    			return false;
    		}
    		for (int i=0;i<instructions.length;i++)
    		{
    			 if(instructions[i].equals("-date")) { //��ȡ��-date����
 					i= setDate(++i);
 					if(i == -1) { //˵�����������з��������г���
 						System.out.println("���������ʽ����");
 						return false;
 					}
    			}
    			 else if(instructions[i].equals("-log")) {//��ȡ-log����·��
 					i = setInPut(++i);
 					if(i == -1) { //˵�����������з��������г���
 						System.out.println("�����������");
 						return false;
 					}
    			 }
    		}
    		return true;
    	}
    	
    	/*�ж�����ָ��*/
		public int setDate(int i) {
			if(i < instructions.length) { //���±�δԽ��
				date = instructions[i] + ".log.txt";
		    }	
			else
			{
				return -1;
			}
			return i;
		}	

		/*�ж�����·��ָ��*/
		public int setInPut(int i) {
			if(i < instructions.length) { 
				if(instructions[i].matches("^[A-z]:\\\\(.+?\\\\)*$")) //������ʽ�ж�·��
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


