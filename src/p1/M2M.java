package p1;

import MDELite.Marquee1Argument;
import PrologDB.*;
import MDELite.Marquee2Arguments;
import PrologDB.Column;
import PrologDB.ColumnCorrespondence;
import PrologDB.DB;
import PrologDB.DBSchema;
import PrologDB.ErrorReport;
import PrologDB.SubTableSchema;
import PrologDB.Table;
import PrologDB.toTable;
import PrologDB.TableSchema;
import PrologDB.Tuple;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class M2M {

    public static void main(String... args) throws Exception {
        System.out.println("\n\nPart2 ...");
        Marquee2Arguments mark = new Marquee2Arguments(P1.class, ".families2.pl", ".families1.pl", args);
        String inputFileName = mark.getInputFileName();
        String outputFileName = mark.getOutputFileName();
        String appName = mark.getAppName(outputFileName);
        DB db = DB.readDataBase("test/inria.families2.pl");
        Table family = db.getTableEH("family");
        Table member = db.getTableEH("member");

        DBSchema dbs = DBSchema.readSchema("test/families1.schema.pl");
        DB newfamilydb = new DB(appName, dbs);
        Table newfamily = newfamilydb.getTable("family");
        ColumnCorrespondence familyColumnCorr = new ColumnCorrespondence()
                .add("fid", "id")
                .add("dadid", "fatherid")
                .add("momid", "motherid")
                .add("lastName", "lastName");
        newfamily.addTuples(family, familyColumnCorr);
        Table newmember = newfamilydb.getTable("member");
        ColumnCorrespondence memberColumnCorr = new ColumnCorrespondence()
                .add("mid", "mid")
                .add("firstName", "firstName")
                .add("fid", b -> getFid(b))
                .add("isMale", b -> isMale(b, family));
        newmember.addTuples(member, memberColumnCorr);
        newfamilydb.print(System.out);
        newfamilydb.print(outputFileName);
    }

    public static String getFid(Tuple t) {
        if (!t.isNull("daughterOf")) {
            return t.get("daughterOf");
        }
        if (!t.isNull("sonOf")) {
            return t.get("sonOf");
        }
        return "null";
    }
// Originally I wanted to check the original family database to see if members missing fids, were in the family table
// However I decided to remove this code becasue it did not match your expected inria.families1.pl linked on the assignment page
// I have linked a snapshot of the alternate outcome in the homework pdf
//    public static String getFid(Tuple t, Table family) {
//        if (!t.isNull("daughterOf")) {
//            return t.get("daughterOf");
//        }
//        if (!t.isNull("sonOf")) {
//            return t.get("sonOf");
//        }
//        for (Tuple familytuple : family.tuples()) {
//            if (familytuple.get("fatherid").equals(t.get("mid"))) {
//                return familytuple.get("id");
//            }
//              if (familytuple.get("motherid").equals(t.get("mid"))) {
//                return familytuple.get("id");
//            }
//        }
//        return "null";
//    }

        public static String isMale(Tuple membertuple, Table family) {
        if (!membertuple.isNull("sonOf")) {
            return "true";
        } else {
            for (Tuple familytuple : family.tuples()) {
                if (familytuple.get("fatherid").equals(membertuple.get("mid"))) {
                    return "true";
                }
            }
        }
        return "false";
    }
}
