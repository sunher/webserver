# webserver
openssl genrsa -out mycerts/cakey.pem 2048
openssl req -new -out mycerts/careq.csr -key mycerts/cakey.pem
openssl x509 -req -in mycerts/careq.csr -out mycerts/cacert.pem -signkey mycerts/cakey.pem -days 3650
keytool -genkey -alias tomcat_server -sigalg "SHA256withRSA" -keyalg RSA -keysize 2048 -validity "36500" -keypass sunhe@123 -keystore mycerts/tomcatkey.jks -storepass sunhe@123
