/*
   作者：sun
   功能：打出金字塔
*/


public class Demo2{
  
    public static void main(String[] args){
      
     //死去活来->大四层
       int lay=4;
       for(int i=0; i<=lay; i++){
        //找出空格规律
       //1->3,2->2,3->1,4->0  

      for(int k=1; k<=lay-i; k++)
           System.out.print(" ");


        //打印*号
          
          for(int j=1; j<=(i-1)*2+1; j++)
               System.out.print("*");
       
          System.out.println();
        }
    }
     
}
