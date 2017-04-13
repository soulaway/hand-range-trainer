# ng2spring-boot-seed

Stack: *NodeJs, Angular2, TypeScript, Spring-boot*

Integration: *Maven, Docker, AWS ec2* 

For being able to build and run application you have to install nodejs ^v7.0.0, Apache Maven ^3, Java 8

please use *N module* from npm to update your Node to relevant version <https://www.npmjs.com/package/n>

more constructive details to update/revert nodeJs : <http://askubuntu.com/questions/426750/how-can-i-update-my-nodejs-to-the-latest-version>

make sure you do that bind for your node daemon

> sudo ln -sf /usr/local/n/versions/node/#VERSION#/bin/node /usr/bin/node 

# assemple/build/test

> cd ../ng2spring-boot-seed

> mvn clean install

# run local

> cd ../backend

> mvn spring-boot:run

# run remote

> TBD