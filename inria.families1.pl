dbase(families1,[family,member]).

table(family,[fid,lastName,dadid,momid]).
family(f1,March,m1,m2).
family(f2,Sailor,m5,m6).

table(member,[mid,firstName,fid,isMale]).
member(m1,Jim,f1,true).
member(m2,Cindy,f1,false).
member(m3,Brandon,f1,true).
member(m4,Brenda,f1,false).
member(m5,Peter,f2,true).
member(m6,Jackie,f2,false).
member(m7,David,f2,true).
member(m8,Dylan,f2,true).
member(m9,Kelly,f2,false).

