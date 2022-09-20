# Springboot-security-JWTAuthentication

To secure REST API end points, JWT authentication method has implemented in this project, Instead of passing username and password for every request, Spring security provides a feature to generate JWT token, once token verified successfully it will pass through Rest endpoints till token gets expired. To know more about read Readme file..

Of course, It requires a lot of configuration files but final result will be more secure application.

There are five rest end points, In which two api endpoints are permitted to all since application requires to store new user data **("/jwt/add-new-user")** and authenticating exisiting user data **("/jwt/authentication")**.

To access rest of the three endpoints ( "/jwt/secure-api/one, /jwt/secure-api/two, /jwt/secure-api/three" ) user must their userId and password, this userID and password will go to provider manager->authentication provider->jwt authentication provider ( Here token will be generated based on given userID and password using HSA256 algorithm and it verifies ) -> Once verification is done, Security context holder will set this authentication as valid token further it allows to access all the secured end points.

JWT token is valid upto 10 hours, so the user can access all rest api endpoints by passing token info to header. Once token gets expired user will have to authenticate again by passing username and password to get new JWT token.

**Rest endpoint to add new user to MongoDB**

![Screenshot from 2022-09-20 17-37-03](https://user-images.githubusercontent.com/112934529/191253446-72c66abb-35e9-4e4a-87d2-90a83db6f960.png)

**API call to authenticate existing user account to get JWT token**

![Screenshot from 2022-09-20 17-37-38](https://user-images.githubusercontent.com/112934529/191253581-6f41083f-b949-4aa0-995f-8d682cdd997d.png)

**Calling all secured end points by passing JWT token through header**

![Screenshot from 2022-09-20 17-37-43](https://user-images.githubusercontent.com/112934529/191253730-e08ac95f-4a54-4d33-b93d-10c57487189d.png)
