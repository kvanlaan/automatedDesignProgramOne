package p1;

import PrologDB.*;

public class ConformFamilies2 {
    public static void main(String... args) throws Exception {
        // Step 1: standard command-line processing
        MDELite.Marquee4Conform mark = new MDELite.Marquee4Conform(P1.class, ".families2.pl", args);
        String inputFileName = mark.getInputFileName();
        String AppName = mark.getAppName(inputFileName);

        // Step 2: open database to be validated + get needed tables
        DB db = DB.readDataBase(inputFileName);
        Table family = db.getTableEH("family");
        Table member = db.getTableEH("member");
//        family.print(System.out);
//        member.print(System.out);

        ErrorReport er = new ErrorReport();

        // Step 3: now perform database checks
        Constraints.iftest(member, t -> (!t.isNull("sonOf") && !t.isNull("daughterOf")), "Member is a both a son and a daughter!", er);
        Constraints.iftest(family, t -> (t.isNull("fatherid") || t.isNull("motherid")), "Family does not have a father or a mother!", er);
        
        // Step 4: finish by reporting collected errors
        er.printReportEH(System.out);
    }
}
