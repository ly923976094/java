import java.util.*;

/*
Set:���򣬲������ظ�Ԫ�ء�
	|--HashSet�����ݽṹ�ǹ�ϣ�����߳��Ƿ�ͬ���ġ�
				��֤Ԫ��Ψһ�Ե�ԭ�����ж�Ԫ�ص�hashCodeֵ�Ƿ���ͬ��
				�����ͬ����������ж�Ԫ�ص�equals�������Ƿ�Ϊtrue��

	|--TreeSet�����Զ�Set�����е�Ԫ�ؽ�������
				�ײ����ݽṹ�Ƕ�������
				��֤Ԫ��Ψһ�Ե����ݣ�
				compareTo����return 0.

				TreeSet����ĵ�һ�ַ�ʽ����Ԫ�������߱��Ƚ��ԡ�
				Ԫ����Ҫʵ��Comparable�ӿڣ�����compareTo������
				Ҳ�ַ�ʽҲ��ΪԪ�ص���Ȼ˳�򣬻��߽���Ĭ��˳��

				TreeSet�ĵڶ�������ʽ��
				��Ԫ���������߱��Ƚ���ʱ�����߾߱��ıȽ��Բ�������Ҫ�ġ�
				��ʱ����Ҫ�ü��������߱��Ƚ��ԡ�
				�ڼ��ϳ�ʼ��ʱ�������˱ȽϷ�ʽ��








����
��TreeSet�����д洢�Զ������ѧ����
�밴��ѧ���������������


��ס������ʱ������Ҫ������ͬʱ��һ���ж�һ�´�Ҫ������


*/

class TreeSetDemo 
{
	public static void main(String[] args) 
	{
		TreeSet ts = new TreeSet();

		ts.add(new Student("lisi02",22));
		ts.add(new Student("lisi007",20));
		ts.add(new Student("lisi09",19));
		ts.add(new Student("lisi08",19));
		//ts.add(new Student("lisi007",20));
		//ts.add(new Student("lisi01",40));

		Iterator it = ts.iterator();
		while(it.hasNext())
		{
			Student stu = (Student)it.next();
			System.out.println(stu.getName()+"..."+stu.getAge());
		}
	}
}


class Student implements Comparable//�ýӿ�ǿ����ѧ���߱��Ƚ��ԡ�
{
	private String name;
	private int age;

	Student(String name,int age)
	{
		this.name = name;
		this.age = age;
	}

	public int compareTo(Object obj)
	{

		//return 0;
		
		if(!(obj instanceof Student))
			throw new RuntimeException("����ѧ������");
		Student s = (Student)obj;

		System.out.println(this.name+"....compareto....."+s.name);
		if(this.age>s.age)
			return 1;
		if(this.age==s.age)
		{
			return this.name.compareTo(s.name);
		}
		return -1;
		/**/
	}

	public String getName()
	{
		return name;

	}
	public int getAge()
	{
		return age;
	}
}