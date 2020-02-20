
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
    			 else if(instructions[i].equals("-out")) {//��ȡ-out���·��
 					i= setOutPut(++i);
 					if(i==-1) {
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
		
		/*�ж����·��*/
		public int setOutPut(int i) {
			if(i < instructions.length) { //���±�δԽ��
				if(instructions[i].matches("^[A-z]:\\\\(\\S+)+(\\.txt)$")) //�ж��ַ����ǲ���txt�ļ�·��
					output = instructions[i];
				else
					return -1;
			} else
				return -1;
			return i;
		}
    }
    
    /*�ļ�������*/
    class ManageFile{
    	/*��ȡĿ¼�µ��ļ�*/
		public void getFileList() {
			File file = new File(input);
			File[] fileList = file.listFiles();
			String fileName;
			for (int i = 0; i < fileList.length; i++) {
				fileName = fileList[i].getName();
					readTxt(input + fileName);
			}
		}
		
	    /*����txt�ļ�*/
	    public void readTxt(String filePath){
	        try {
	        	int casenumber;
	            BufferedReader bfr = new BufferedReader(new InputStreamReader(
	            		new FileInputStream(new File(filePath)), "UTF-8"));
	            String lineTxt = null;
	            while ((lineTxt = bfr.readLine()) != null) { 
	            	if(!lineTxt.startsWith("//"))
	            	{
	            		int judgenumber;
	            		judgenumber=judgeCase(lineTxt);
	            	//	switch (jumdgenumber)
	            	}
	            }
	            bfr.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /*�ı�����*/
	    public int judgeCase(String string) {
	    	String case1 = "(\\S+) ���� ��Ⱦ���� (\\d+)��";
	    	boolean isCase = Pattern.matches(case1, string);
	    	if(isCase==true) {
	    		System.out.print("��⵽��Ⱦ����");
	    		return 1;
	    	}
	    	return -1;
	    }
	    
	    
    }
    
    /*������*/
    public static void main(String[] args){
    	InfectStatistic infectstatistic=new InfectStatistic();
        Instructions instruction=infectstatistic.new Instructions();
        instruction.setInstructions(args);
        instruction.checkInstructions();
        ManageFile mangefile=infectstatistic.new ManageFile();
        mangefile.readTxt(input);
    }
    
}


