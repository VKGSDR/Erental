
# Vehicle Rental System using ReactJS and Javascript.

Application built using Gradle build tool and using tomcat web server as deployment tool.
Make sure gradle build tool is installed in your pc.

Frontend building and deploying steps:
1. In cmd open the folder where the frontend code is present.
2. run the command as " npm run build"
3. Paste the file created in webapps folder of tomcat server.
4. open bin folder of tomcat in cmd
5. run command as "catalina.bat run", the tomcat server would open  after deploymnet.

Backend building and deploying steps:
1. In cmd open the folder where the Backend code is present.
2. run the command as " gradle build"
3. A war file would be created in build/libs/.war directory.
4. paste the war file in webapps folder of tomcat server 
   or you can just add that file from the tomcat browser also.
5. open bin folder of tomcat in cmd.
6. run command as "catalina.bat run", the tomcat server would open after deploymnet. 
   you can just check the browser by changing the url as "localhost:8080/user/getall".

   Note : since browser won't support post and put method, only get method of backend code is running succcesfully.

