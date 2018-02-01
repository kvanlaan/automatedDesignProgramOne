dbase(families2,[family,member]).

table(family,[id,lastName,fatherid,motherid]).
family(f1, Pence, m1, m2).
family(f2, Trump, null, m3).
family(f3, Obama, m4, null).
family(f4, Clinton, m1, m5).

table(member,[mid,firstName,sonOf,daughterOf]).
member(m1, Mike, null, null).
member(m2, Karen, null, null).
member(m2, Donald, null, null).
member(m3, Melania, null, null).
member(m4, Barack, null, null).
member(m5, Michelle, null, null).
member(m1, Bill, null, null).
member(m5, Hillary, null, null).
member(m6, Audrey, f1, f1).
member(m7, Ivanka, null, null).
member(m8, barron, f2, f3).
