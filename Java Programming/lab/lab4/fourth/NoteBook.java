package fourth;

import third.Clock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class NoteBook {
	
	private ArrayList<String> notebook = new ArrayList<String>();
	//被删除的记录
	private String deleted = "";
	//时间
	private Clock clock = new Clock(18,50,50,2021,7,21);
	//翻译器
	private HashMap<String,String> translator = new HashMap<>();
	{//静态代码块，用于初始化翻译器
		translator.put("important","重要的");
		translator.put("first","第一");
		translator.put("second","第二");
		translator.put("third","第三");
	}
	//打印所有记录
	public void print(){
		System.out.println(notebook);
	}
	//添加记录
	public void addNote(String note) {
		//遍历翻译器中所有key
		for(String str :translator.keySet()){
			if(note.contains(str))//若note中含有某一个key
				note=note.replace(str,translator.get(str));//替换note中内容
		}
		notebook.add(clock.toString()+" "+note);
	}

	public int getSize() {
		return notebook.size();
	}
	//返回index处的记录
	public String getNote(int index) {
		return notebook.get(index);
	}
	//删除记录
	public void removeNote(int index) {
		//将被删除的记录存到deleted中
		deleted = (index+"\t"+notebook.get(index)+"\n"+deleted).trim();

		//移除index处的index
		System.out.println(notebook.remove(index)+"已删除");
	}
	public void recover(int n){
		//n为要恢复的记录的条数
		//获取被删除的每一条记录
		String[] strs = deleted.split("\n");
		int i =0;
		for(;i<strs.length && i<n; i++){
			int index =Integer.valueOf(strs[i].split("\t")[0]);
			String content = strs[i].split("\t")[1];
			System.out.println(content + "已恢复");
			//向notebook中添加
			notebook.add(index,content);
		}
		//重新给deleted赋值
		StringBuilder sb = new StringBuilder();
		for(;i<strs.length && i<n; i++){
			sb.append(strs[i]);
		}
		deleted = sb.toString();
	}
	
	public String[] list() {
		String[] s = new String[notebook.size()];
		notebook.toArray(s);
		return s;
	}
	//查询
	public void find(String[] keywords){
		for(String key :keywords){
			for(String note : notebook){
				if(note.contains(key)) System.out.println(note);
			}
		}
	}

	public static void main(String[] args) {
		
		NoteBook nb = new NoteBook();
		nb.addNote("first");
		nb.addNote("second");
		nb.addNote("third");
		
		System.out.println(nb.getSize());
		System.out.println(nb.getNote(0));
		nb.removeNote(0);
		System.out.println(nb.getNote(0));

		String[] s = nb.list();

		nb.print();

		nb.recover(1);

		nb.print();
	}

}
