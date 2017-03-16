/*
   作者:lion
   日期：2017.3.15
   功能:判断两个数是否能整除

*/
import java.io.*;
public class Demo1
{
   public static void main(String[] args){
/*    int a=2;
    int b=10;
    if(b%a==0)
      System.out.println("能被整除");
    else
      System.out.println("不能被整除");
  */
/*    int a=3;
    int b=++(a--);
    System.out.println(b);   
*/
/*    try{
     //输入流
     InputStreamReader isr = new InputStreamReader(System.in);
     BufferedReader br = new BufferedReader(isr);

     //从控制台读取一行数据
     System.out.println("请输入第一个数");
     String a1 = br.readLine();
     System.out.println("请输入第二个数");
     String a2 = br.readLine();
     

     //把String->float
     float num1 = Float.parseFloat(a1);
     float num2 = Float.parseFloat(a2);
     
     if(num1>num2)
        System.out.println("第一个大");
     if(num1==num2)
        System.out.println("相等");
     if(num1<num2)
        System.out.println("第二个大");
        
    }catch(Exception e){

    }*/
     
    char c='a';
    switch(c){
      case 'a':
            System.out.println("今天星期一");
            break;
      case 'b':
            System.out.println("今天星期二");
            break;
      case 'c':
            System.out.println("今天星期三");
            break;
      default:
            System.out.println("今天星期四"); 
       
    }
    
  }
}
