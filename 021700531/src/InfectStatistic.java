
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
	            		judgeCase(lineTxt);
	            	}
	            }
	            bfr.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /*�ı�����*/
	    public void judgeCase(String string) {
	    	String case1 = "(\\S+) ���� ��Ⱦ���� (\\d+)��";
	    	String case2 = "(\\S+) ���� ���ƻ��� (\\d+)��";
	    	String case3 = "(\\S+) ���� (\\d+)��";
	    	String case4 = "(\\S+) ���� (\\d+)��";
	    	String case5 = "(\\S+) ��Ⱦ���� ���� (\\S+) (\\d+)��";
	    	String case6 = "(\\S+) ���ƻ��� ���� (\\S+) (\\d+)��";
	    	String case7 = "(\\S+) ���ƻ��� ȷ���Ⱦ (\\d+)��";
	    	String case8 = "(\\S+) �ų� ���ƻ��� (\\d+)��";
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
	    		//System.out.print("��Ⱦ��");
	    	}
	    	else if(isCase6==true) {
	    		//System.out.print("������");
	    	}
	    	else if(isCase7==true) {
	    		//System.out.print("����ȷ.");
	    	}
	    	else if(isCase8=true) {
	    		//System.out.print("������");
	    	}
	    }
	    
	    /*����������Ⱦ����*/
	    public void addIP(String string) {
	    	String[] str_arr = string.split(" "); 
	    	int i;
	    	int n = Integer.valueOf(str_arr[3].replace("��", ""));//�õ�����
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) { 
	    			data[0][0] += n; //ȫ����Ⱦ������������
	    			data[i][0] += n; //��ʡ�ݸ�Ⱦ������������
	    			break;
	    		}
	    	}
	    }
	    
	    /*�������Ƹ�Ⱦ����*/
	    public void addSP(String string) {
	    	String[] str_arr = string.split(" ");
	    	int i;
	    	int n = Integer.valueOf(str_arr[3].replace("��", ""));
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) { 
	    			data[0][1] += n; //ȫ�����ƻ�����������
	    			data[i][1] += n; //��ʡ�����ƻ�����������
	    			break;
	    		}
	    	}
	    }
	    
	    /*�������ߴ���*/
	    public void addCure(String string) {
	    	String[] str_arr = string.split(" "); //���ַ����Կո�ָ�Ϊ����ַ���
	    	int i;
	    	int n = Integer.valueOf(str_arr[2].replace("��", ""));
	    	for(i = 0; i < province.length; i++) { 
	    		if(str_arr[0].equals(province[i])) {
	    			data[0][2] += n; //ȫ��������������
	    			data[0][0] -= n; //ȫ����Ⱦ������������
	    			data[i][2] += n; //��ʡ��������������
	    			data[i][0] -= n; //��ʡ�ݸ�Ⱦ������������
	    			break;
	    		}
	    	}
	    }
	    
	    /*�������ߴ���*/
	    public void addDeath(String string) {
	    	String[] str_arr = string.split(" "); //���ַ����Կո�ָ�Ϊ����ַ���
	    	int i;
	    	int n = Integer.valueOf(str_arr[2].replace("��", ""));
	    	
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) {
	    			data[0][3] += n; //ȫ��������������
	    			data[0][0] -= n; //ȫ����Ⱦ������������
	    			data[i][3] += n; //��ʡ��������������
	    			data[i][0] -= n; //��ʡ�ݸ�Ⱦ������������
	    			System.out.println(data[0][3]);
	    			System.out.println(data[0][0]);
	    			System.out.println(data[i][3]);
	    			System.out.println(data[i][0]);
	    			break;
	    		}
	    	}
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


