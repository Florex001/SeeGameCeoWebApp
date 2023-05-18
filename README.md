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

* http://localhost:9000/api/user/v4/createjob/{műhelyid} :x:

### Műhelyhez tartozó munkák lekérdezése:

* http://localhost:9000/api/user/v4/getworkbyworkshop/{műhelyid} :x:

### Műhelyben dolgozók lekérdezése:

* http://localhost:9000/api/user/v4/getworkerbyworkshop/{muhelyid} :x:

### Folyamat hozzáadása a munkához:

* http://localhost:9000/api/user/v4/createprocess/{jobid} :x:

### Folyamat lekérdezése az adott munkához:

* http://localhost:9000/api/user/v4/getprocess/{jobid} :x:

### Folyamat törlése:

* http://localhost:9000/api/user/v4/deleteprocess/{folyamatid} :x:
