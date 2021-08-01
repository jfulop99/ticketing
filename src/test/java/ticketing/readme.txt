docker run -d -e MYSQL_DATABASE=ticketing -e MYSQL_USER=ticketing -e MYSQL_PASSWORD=ticketing -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3310:3306 --name ticketing-mariadb mariadb
