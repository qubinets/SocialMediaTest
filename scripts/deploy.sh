#!/usr/bin/env bash
mvn install
echo 'Copy files...'
scp -i  ~/.ssh/id_rsa  \
        target/Springboot-0.0.1-SNAPSHOT.jar\
        root@206.189.109.206:/root
echo 'Restart server...'

ssh -i  ~/.ssh/id_rsa root@206.189.109.206 << EOF
pgrep java | xargs kill -9
nohup java -jar Springboot-0.0.1-SNAPSHOT.jar
EOF

echo 'Bye'