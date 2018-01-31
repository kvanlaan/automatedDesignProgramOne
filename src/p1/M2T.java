package p1;

import MDELite.Marquee1Argument;
import PrologDB.*;;
import MDELite.RunningBear;

public class M2T {
    static void helper(Table table, Table fam, Table mem, Tuple t) {
        String pid = t.get("mid");
        
        if (!t.isNull("fid")) {
            String fullName = t.get("firstName") + " " + fam.getFirst(d -> d.get("fid").equals(t.get("fid"))).get("lastName");
            
            String dadid = fam.getFirst(d -> d.get("fid").equals(t.get("fid"))).get("dadid");
            String dadName = mem.getFirst(d -> d.get("mid").equals(dadid)).get("firstName");

            String momid = fam.getFirst(d -> d.get("fid").equals(t.get("fid"))).get("momid");
            String momName = mem.getFirst(d -> d.get("mid").equals(momid)).get("firstName");

            table.addTuple(pid, fullName, dadName, momName);
        } else {
            String fullName = "";
            if (t.get("isMale").equals("true")) {
                fullName = t.get("firstName") + " " + fam.getFirst(d -> d.get("dadid").equals(t.get("mid"))).get("lastName");
            } else {
                fullName = t.get("firstName") + " " + fam.getFirst(d -> d.get("momid").equals(t.get("mid"))).get("lastName");
            }
            table.addTuple(pid, fullName, "null", "null");
        }
    }
    
    public static void main(String... args) throws Exception {
        // Step 1: standard marquee processing
        Marquee1Argument mark = new Marquee1Argument(P1.class, ".families1.pl", args);
        String inputFileName = mark.getInputFileName();
        String outputSchema = "D:\\UTAustin\\Term4_2018Spring\\CS392F(51775)\\Programmings\\1\\P1\\test\\print.schema.pl";
        
        // Step 2: read the families database and their tables
        DB in = DB.readDataBase(inputFileName);
        Table fam = in.getTableEH("family");
        Table mem = in.getTableEH("member");
        
        // Step 3: create an empty families1 database with empty tables
        DBSchema outSchema = DBSchema.readSchema(outputSchema);
        DB out = new DB(in.getName(), outSchema);
        Table people = out.getTableEH("person");
        
        // Step 4: construct the table
        mem.forEach(t -> helper(people, fam, mem, t));
//        people.print(System.out);
//        RunningBear.l(3);
        
        // Printing
        for (Tuple t: people.filter(d -> !d.isNull("dadName")).tuples()) {
            RunningBear.l("%s has parents %s and %s", t.get("fullName"), t.get("dadName"), t.get("momName"));
        }
        
        for (Tuple t: people.filter(d -> d.isNull("dadName")).tuples()) {
            RunningBear.l("%s has <unknown> parents", t.get("fullName"));
        }
    }
}
