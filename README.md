# Elérési útvonalak

## Felhasználóhoz kapcsolódó utvonalak:

### Regisztráció:

* http://localhost:9000/api/user/registration :white_check_mark:

### Bejelentkezés:

* http://localhost:9000/api/user/login :white_check_mark:

### Auth:

* http://localhost:9000/api/user/auth :white_check_mark:

### Kijelentkezés:

* http://localhost:9000/api/user/logout :white_check_mark:


## See V4 elérések:

### Szerver választás:

* http://localhost:9000/api/user/v4/chooseserver :white_check_mark:

### Karakter hozzáadás:

* http://localhost:9000/api/user/v4/createcharacter :white_check_mark:

### Ic nevek:

* http://localhost:9000/api/user/v4/getallicname :white_check_mark:

### Műhely hozzáadása:

* http://localhost:9000/api/user/v4/createworkshop :white_check_mark:

### Felhasználó saját műhelye:

* http://localhost:9000/api/user/v4/getmyworkshop :white_check_mark:

### Felhasználó műhelyei amiben dolgozik:

* http://localhost:9000/api/user/v4/workshopiworkin :white_check_mark:

### Műhelyhez munka hozzáadás

* http://localhost:9000/api/user/v4/createjob/{műhelyid} :white_check_mark:

### Műhelyhez tartozó munkák lekérdezése:

* http://localhost:9000/api/user/v4/getworkbyworkshop/{műhelyid} :white_check_mark:

### Műhelyben dolgozók lekérdezése:

* http://localhost:9000/api/user/v4/getworkerbyworkshop/{muhelyid} :white_check_mark:

### Folyamat hozzáadása a munkához:

* http://localhost:9000/api/user/v4/createprocess/{jobid} :white_check_mark:

### Folyamat lekérdezése az adott munkához:

* http://localhost:9000/api/user/v4/getprocess/{jobid} :white_check_mark:

### Folyamat törlése:

* http://localhost:9000/api/user/v4/deleteprocess/{folyamatid} :white_check_mark:

### Fizetést számolja ki:

* http://localhost:9000/api/user/v4/pay/{munkaid} :white_check_mark:

### Elvégzett munkákat listázza ki:

* http://localhost:9000/api/user/v4/getcompletedworkbyworkshop/{muhelyid} :x:

### Elvégzett munkákhoz tartozó fizetések:

* http://localhost:9000/api/user/v4/pay/{munkaid} :x:

### Ha elvégzett munkához tartozó fizetést meg kapta a munkás akkor a fizetve true érték lesz:

* http://localhost:9000/api/user/v4/ispay/{fizetesid} :x:

### Műhely hosszabítása:

* http://localhost:9000/api/user/v4/workshopextension/{muhelyid} :x:
