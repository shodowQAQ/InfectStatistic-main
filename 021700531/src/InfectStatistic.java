

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
	static String date;
	static String input;
	static String output;
	static int[] type = {0,0,0,0};
	static String[] typename = {"��Ⱦ����", "���ƻ���", "����", "����"};
    
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
 					i = setDate(++i);
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
 					i = setOutPut(++i);
 					if(i==-1) {
 						System.out.println("�����������");
 						return false;
 					}
    			 }
    			 else if(instructions[i].equals("-type")) {
    				 i = setType(i++);
    				 if(i==-1) {
    					 System.out.println("type���ʹ���");
    					 return false;
    				 }
    			 }
    		}
    		return true;
    	}
    	
    	/*ָ�������������*/
    	public int setType(int i) {
    		for(int j=0;j<4;j++)//���������Ͷ���Ϊ�����
    		{
    			type[j]=1;
    		}
    		if(i < instructions.length) {
    			if(instructions.equals("ip"))
    			{
    				type[0]=0;
    				i++;
    			}
    			if(instructions.equals("sp")) {
    				type[1]=0;
    				i++;
    			}
    			if(instructions.equals("cure")) {
    				type[2]=0;
    				i++;
    			}
    			if(instructions.equals("dead")) {
    				type[3]=0;
    				i++;
    			}
    			if (type[0]==1&&type[1]==1&&type[2]==1&&type[3]==1) {
    				return -1;
    			}
    		}
    		else
    			return -1;
    		return i;
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
		public void manageTxt() {
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
	            		handleCase(lineTxt);
	            	}
	            }
	            bfr.close();
	            writeTxt();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /*�ı�����*/
	    public void handleCase(String string) {
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
	    		moveIP(string);
	    	}
	    	else if(isCase6==true) {
	    		moveSP(string);
	    	}
	    	else if(isCase7==true) {
	    		diagnoseSP(string);
	    	}
	    	else if(isCase8=true) {
	    		ignoreSP(string);
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
	    	String[] str_arr = string.split(" "); 
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
	    	String[] str_arr = string.split(" "); 
	    	int i;
	    	int n = Integer.valueOf(str_arr[2].replace("��", ""));
	    	
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) {
	    			data[0][3] += n; //ȫ��������������
	    			data[0][0] -= n; //ȫ����Ⱦ������������
	    			data[i][3] += n; //��ʡ��������������
	    			data[i][0] -= n; //��ʡ�ݸ�Ⱦ������������
	    			break;
	    		}
	    	}
	    }
	    
	    /*��Ⱦ�����ƶ�*/
	    public void moveIP(String string) {
	    	String[] str_arr = string.split(" "); 
	    	int i;
	    	int n = Integer.valueOf(str_arr[4].replace("��", ""));//�õ�����
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) {
	    			data[i][0] -= n; //��ʡ�ݸ�Ⱦ������������
	    		}
	    		if(str_arr[3].equals(province[i])) { 
	    			data[i][0] += n; //��ʡ�ݸ�Ⱦ������������
	    		}
	    	}
	    }
	    
	    /*���ƻ����ƶ�*/
	    public void moveSP(String string) {
	    	String[] str_arr = string.split(" "); 
	    	int i;
	    	int n = Integer.valueOf(str_arr[4].replace("��", ""));
	    	
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) { 
	    			data[i][1] -= n; //��ʡ�����ƻ��߼���
	    		}
	    		if(str_arr[3].equals(province[i])) { //���ĸ��ַ���Ϊ����ʡ��
	    			data[i][1] += n; //��ʡ�����ƻ�������
	    		}
	    	}
	    }
	    
	    /*���ƻ���ȷ��s*/
	    public void diagnoseSP(String string) {
	    	String[] str_arr = string.split(" "); 
	    	int i;
	    	int n = Integer.valueOf(str_arr[3].replace("��", ""));
	    	
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) { 
	    			data[0][1] -= n; //ȫ�����ƻ�����������
	    			data[0][0] += n; //ȫ����Ⱦ������������
	    			data[i][1] -= n; //��ʡ�����ƻ�����������
	    			data[i][0] += n; //��ʡ�ݸ�Ⱦ������������
	    			break;
	    		}
	    	}
	    }
	    
	    
	    /*���ƻ����ų�*/
	    public void ignoreSP(String string) {
	    	String[] str_arr = string.split(" "); 
	    	int i;
	    	int n = Integer.valueOf(str_arr[3].replace("��", ""));
	    	
	    	for(i = 0; i < province.length; i++) {
	    		if(str_arr[0].equals(province[i])) { //��һ���ַ���Ϊʡ��
	    			data[i][1] -= n; //��ʡ�����ƻ�����������
	    			data[0][1] -= n; //ȫ�����ƻ�����������
	    			System.out.println(data[i][1]);
	    			System.out.println(data[0][1]);
	    			break;
	    		}
	    	}
	    }
	    
	    public void writeTxt() {
	    	FileWriter fwriter = null;
	    	int i, j, k;
	        try {
	        	fwriter = new FileWriter(output);
	        	for(i = 0; i < province.length; i++) { 
	        			fwriter.write(province[i] + " ");
	        				for(k = 0; k < type.length; k++) {
	        						if(type[k]==0)
	        							fwriter.write(typename[k] + data[i][k] + "�� ");
	        						break;
	        				}
	        			fwriter.write("\n");
	        	}
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                fwriter.flush();
	                fwriter.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
    }
    
    /*������*/
    public static void main(String[] args){
    	InfectStatistic infectstatistic=new InfectStatistic();
        Instructions instruction=infectstatistic.new Instructions();
        instruction.setInstructions(args);
        ManageFile mangefile=infectstatistic.new ManageFile();
        mangefile.manageTxt();
    }
    
}


