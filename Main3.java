public class Main3 {



    public static void main(String[] args) {
        


                   long t= System.currentTimeMillis();
                    long end = t+5000;

                  while(System.currentTimeMillis() < end) {
 
                          RandString36 n = new RandString36();
                          System.out.println("Stroka poshla :"+n.usingUUID());
        
                    try {
                           Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
}
    }
    
}
