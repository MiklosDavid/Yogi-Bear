***3. Beadandó***  

***Programozási Technológia I.  1.Feladat***  

Feladat leírása:  

A meséből jól ismert Maci Laci bőrébe bújva a Yellowstone Nemzeti Park megmászhatatlan hegyei és fái között szeretnénk begyűjteni az összes rendelkezésre álló piknik kosarat. Az átjárhatatlan akadályok mellett Yogi élelem szerzését vadőrök nehezítik, akik vízszintesen vagy függőlegesen járőröznek a parkban. Amennyiben Yogi egy egység távolságon belül a vadőr látószögébe kerül, úgy elveszít egy élet pontot. (Az egység meghatározása rád van bízva, de legalább a Yogi sprite-od szélessége legyen.) Ha a 3 élet pontja még nem fogyott el, úgy a park bejáratához kerül, ahonnan indult.   

A kalandozás során, számon tartjuk, hogy hány piknik kosarat sikerült összegyűjtenie Lacinak. Amennyiben egy pályán sikerül összegyűjteni az összes kosarat, úgy töltsünk be, vagy generáljunk egy új játékteret. Abban az esetben, ha elveszítjük a 3 élet pontunkat, úgy jelenjen meg egy felugró ablak, melyben a nevüket megadva el tudják menteni az aktuális eredményüket az adatbázisba. Legyen egy menüpont, ahol a 10 legjobb eredménnyel rendelkező játékost lehet megtekinteni, az elért pontszámukkal, továbbá lehessen bármikor új játékot indítani egy másik menüből.  

*UML osztálydiagram:*

![image-000](https://user-images.githubusercontent.com/48122593/182321052-ace799cd-217b-4ddc-8708-fa9c4120e1a9.png)

Grafikai megjelenítés:  

1. Játék inditásakor.  

![image-001](https://user-images.githubusercontent.com/48122593/182321060-0ee07e2d-2d98-4e10-ba3e-4cd73b5abeae.png)

Kiválasztjuk a neházséget (könnyű, közepes, nehéz), majd elindítjuk a játékot.  

2. Elindul a játék, a megfelelő pályával.  

![image-002](https://user-images.githubusercontent.com/48122593/182321063-3c3a74ea-4549-4671-a4cf-e80be42d4507.png)

3. Győzelem esetén megjelenik a „You Won!” szöveg. A név megadása után az

![image-003](https://user-images.githubusercontent.com/48122593/182321065-87bdfa06-eda6-4493-b200-d3d88902c1a8.png)

4. Vereség esetén megjelenik a „You Lost!” szöveg. A név megadása után az eredmény bekerül a dicsőség táblaba.  

![image-004](https://user-images.githubusercontent.com/48122593/182321070-fa07e67e-5f83-4ab9-8f31-5fb8bba066d4.png)

5. Dicsőségtábla:  

![image-005](https://user-images.githubusercontent.com/48122593/182321072-3515cdba-6070-465f-bd7f-e23e8138feba.png)

6. Ha a felhasználó, nem ad meg nevet, akkor az alkalmazás hibaüzenettel tér vissza.   

![image-006](https://user-images.githubusercontent.com/48122593/182321076-a1b3baca-6cec-4046-9c58-83d902b6ebbb.png)
