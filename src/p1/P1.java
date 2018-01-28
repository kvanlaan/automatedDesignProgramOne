package p1;

import MDELite.RunningBear;
import LectureExamples.HelloWorld;
import MDELite.Marquee1Argument;

public class P1 {
    public static void main(String[] args) throws Exception {  //<-- program #1
        
        //play around with MDELite
//        new Marquee1Argument(HelloWorld.class,"",args);
        RunningBear.l("hello world!");
        RunningBear.l();
        RunningBear.l("this is don calling!");
        
        
        /*
        try {
            // bad.families2.pl will fail to conform.
            ConformFamilies2.main("bad.families2.pl");         //<-- program #2
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ConformFamilies2.main("inria.families2.pl");           //<-- program #2 (called again)
        M2M.main("inria.families2.pl");                        //<-- program #3
        M2T.main("inria.families1.pl");                        //<-- program #4 (called again)
        */
    }
}
