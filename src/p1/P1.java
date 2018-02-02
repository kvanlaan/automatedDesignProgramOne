package p1;

public class P1 {
    public static void main(String[] args) throws Exception {  //<-- program #1        
        try {
            // bad.families2.pl will fail to conform.
            ConformFamilies2.main("test/test_bad.families2.pl");         //<-- program #2
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        M2M.main("inria.families2.pl");                        //<-- program #3
        M2T.main("inria.families1.pl");                        //<-- program #4 (called again)
    }
}
