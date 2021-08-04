# Ticketing - Partnerektől beérkező bejelentéseket és megrendeléseket nyilvántartó alkalmazás

## Vízió

Egy valós igény alapján az Excel tábla kiváltására.


### Funkcionális követelmények

A következőket kell karbantartani:

* Partnereket (`Partner`)
* Csoportokat (`TicketGroup`)
* Bejelentéseket (`Ticket`)
* A bejelentésekhez a felhasznált anyagokat is lehessen rögzíteni.

A alábbi adatokat kell nyilvántartani:

#### Partner

* Név
* Könyvelési azonosító
* Cím

Lehet listázni, lekérdezni, létrehozni, törölni, attribútumokat módosítani.

A következő végpontokon érjük el az entitást

| Http metódus | Végpont              | Leírás                           |
| ------------ | -------------------- | -------------------------------- |
| GET          | `"api/partners"`     | lekérdezi az összes partnert     |
| GET          | `"api/partners/{id}"`| lekérdez egy partnert id alapján |
| POST         | `"api/partners"`     | létrehoz egy partnert            |
| PUT          | `"api/partners/{id}"`| módosít egy partnert id alapján  |
| DELETE       | `"api/partners/{id}"`| töröl egy partnert id alapján    |



#### Csoport

* Név

Lehet listázni, lekérdezni, létrehozni, törölni, attribútumot módosítani.

A következő végpontokon érjük el az entitást

| Http metódus | Végpont              | Leírás                            |
| ------------ | -------------------- | --------------------------------- |
| GET          | `"api/groups"`       | lekérdezi az összes csoportot     |
| GET          | `"api/groups/{id}"`  | lekérdez egy csoportot id alapján |
| POST         | `"api/groups"`       | létrehoz egy csoportot            |
| PUT          | `"api/groups/{id}"`  | módosít egy csoportot id alapján  |
| DELETE       | `"api/groups/{id}"`  | töröl egy csoportot id alapján    |


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

| Http metódus | Végpont              | Leírás                            |
| ------------ | -------------------- | --------------------------------- |
| GET          | `"api/tickets"`       | lekérdezi az összes bejelentést     |
| GET          | `"api/tickets/{id}"`                 | lekérdez egy bejelentést id alapján |
| POST         | `"api/tickets"`                      | létrehoz egy bejelentést            |
| PUT          | `"api/tickets/{id}"`                 | módosít egy bejelentést id alapján  |
| DELETE       | `"api/tickets/{id}"`                 | töröl egy bejelentést id alapján    |
| POST         | `"api/tickets/{id}/materials"`       | létrehoz egy anyagot az adott id-jű bejelentéshez            |

