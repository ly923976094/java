/*
   作者：sun
   功能：打出空心金字塔
*/


public class Demo222{
  
    public static void main(String[] args){
      
     //死去活来->大四层
       int lay=9;
     //上半部分
      for(int i=1; i<=lay; i++){
        //找出空格规律
       //1->3,2->2,3->1,4->0  

         for(int k=1; k<=lay-i; k++)
             System.out.print(" ");

        //打印*号
          
          for(int j=1; j<=(i-1)*2+1; j++){
                   if(j==1 || j==(i-1)*2+1){
                    System.out.print("*");
                   }else{
                    System.out.print(" ");
                   }
       }
          System.out.println();
     }
    //下半部分
      for(int i=1; i<=lay-1; i++){
          
           for(int k=1; k<=i;k++){
              System.out.print(" ");
         }

          for(int j=1; j<=(lay-i-1)*2+1; j++){
           
                  if(j==1 || j==2*(lay-i-1)+1){
                       System.out.print("*");
                  } else{
                       System.out.print(" ");
             }

      }
         System.out.println();
    }
     
 }
}
