# Ticketing - Partnerektől beérkező bejelentéseket és megrendeléseket nyilvántartó alkalmazás

## Vízió

Egy valós igény alapján az Excel tábla kiváltására.


### Funkcionális követelmények

A következőket kell karbantartani:

* Partnereket (`Partner`)
* Csoportokat (`TicketGroup`)
* Bejelentéseket (`Ticket`)
* A bejelentésekhez a felhasznált anyagokat is lehessen rögzíteni.

A különböző adatokat kell nyilvántartani:

#### Partner

* Név
* Könyvelési azonosító
* Cím

Lehet listázni, lekérdezni, létrehozni, törölni, attribútumokat módosítani.
#### Csoport

* Név

Lehet listázni, lekérdezni, létrehozni, törölni, attribútumot módosítani.

#### Bejelentéseket
* Kezdés dátuma (tetszőleges)
* +Befejezés dátuma (tetszőleges, később legyen, mint a kezdés)
* Partner azonosító
* +Leírás
* Csoport azonosító
* +A megoldás leírása
* +Ráfordított munkaórák száma
* +Munkalap azonosító
* Felhasznált anyagok listája

Lehet listázni, lekérdezni, létrehozni, törölni, a + jelű attribútumokat módosítani és anyagokat hozzáadni.

