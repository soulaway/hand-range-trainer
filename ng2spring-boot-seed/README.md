# ng2spring-boot-seed

Stack: *Maven, NodeJs, Angular2, TypeScript, Spring-boot*

For being able to build and run application you have to install nodejs ^v7.0.0, Apache Maven ^3, Java 8

please usen N module from npm to update youre Node to relevant version <https://www.npmjs.com/package/n>

more constructive details to update/revert nodejs : <http://askubuntu.com/questions/426750/how-can-i-update-my-nodejs-to-the-latest-version>

make sure you do that bind for youre node daemon

> sudo ln -sf /usr/local/n/versions/node/#VERSION#/bin/node /usr/bin/node 

# assemple/build/test

> cd ../ng2spring-boot-seed

> mvn clean install

# run

> cd ../backend

> mvn spring-boot:run
