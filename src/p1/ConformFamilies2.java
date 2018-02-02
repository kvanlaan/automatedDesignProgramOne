package p1;

import PrologDB.*;

public class ConformFamilies2 {
    public static void main(String... args) throws Exception {
        System.out.println("\n\nPart1 ...");
        // Step 1: standard command-line processing
        MDELite.Marquee4Conform mark = new MDELite.Marquee4Conform(P1.class, ".families2.pl", args);
        String inputFileName = mark.getInputFileName();
        String AppName = mark.getAppName(inputFileName);
        System.out.format("Conforming file %s:\n\n", inputFileName);
        
        // Step 2: open database to be validated + get needed tables
        DB db = DB.readDataBase(inputFileName);
        db.print(System.out);
        Table family = db.getTableEH("family");
        Table member = db.getTableEH("member");

        ErrorReport er = new ErrorReport();

        // Step 3: now perform database checks
        Constraints.iftest(member, t -> (!t.isNull("sonOf") && !t.isNull("daughterOf")), "Error: Member is a both a son and a daughter!", er);
        Constraints.iftest(family, t -> (t.isNull("fatherid") || t.isNull("motherid")), "Error: Family does not have a father or a mother!", er);
        family.project("fatherid").duplicates().error(er,"Error: Father# %s can only belong to one family.", t->t.get("fatherid"));
        family.project("motherid").duplicates().error(er,"Error: Mother# %s can only belong to one family.", t->t.get("motherid"));
        member.project("mid").duplicates().error(er,"Error: Member# %s can only belong to one family.", t->t.get("mid"));
        
        // Step 4: finish by reporting collected errors
        er.printReportEH(System.out);
        
        if (!er.printReport(System.out)) {
            System.out.format("All constraints satisfied for %s!\n", inputFileName);
        }
    }
}
