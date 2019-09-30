# Unsplash Java SDK

A client SDK to connect with the Unsplash API -> https://unsplash.com

## Usage

### Authorization
First, we create the UserCredentials object for the V1 of this API.
- We need the access key, secret key and a valid redirect URI
- You can find this information in https://unsplash.com/oauth/applications/YOUR-APPLICATION-ID
```java
UserCredentials userCredentials = new UserV1Credentials(
    clientId,
    clientSecret,
    redirectUri
);
```
Now we initialize the client using the credentials and specifying the version
- Remember that the version needs to be supported by the SDK, otherwise an error is thrown
```java
UnSplashSdkClient client = UnSplashSdkClient.initialize(SupportedApiVersions.VERSION_1, userCredentials);
```
We need to obtain the authorization code. In order to do that, we need the authorization URL.
- This client provides you the URL, but later you need to access to that URL and get the Authorization Code
- The authorization code will be included as a URI parameter called code
- You can choose which permission scopes you want to grant for this authorization
- You can generate the URL without permission scopes as well
- **IMPORTANT** - to be able to use all the functions of the client, please add the same codes as the example on bellow
```java
List<String> scopes = new ArrayList<>();
scopes.add("read_user");
scopes.add("read_photos");
scopes.add("read_collections");
client.generateAuthorizeUrl(scopes);
```
Once you have the authorization code, we can generate the token credentials with the client.
- The client generates the token credentials which are the access token and the refresh token.
- Those credentials are saved in the client so you can start making calls straight away
```java
TokenCredentials credentials = client.generateAccessToken(authorizationCode);
credentials.getAccessToken();
credentials.getRefreshToken();
```
### Read your Unsplash profile
We can read from the user's profile once we have the access token generated using the client.
Remember to add the permission scope `read_user` when you generate the authorization URL.
```java
UserProfile userProfile = client.getUserProfile();
```
### Read photos from Unsplash
We can obtain the photos from Unsplash once we have the access token generated using the client.
Remember to add the permission scope `read_photos` when you generate the authorization URL.
```java
List<Photo> photos = client.getPhotos();
```
### Read collections from Unsplash
We can obtain the collections from Unsplash once we have the access token generated using the client.
Remember to add the permission scope `read_collections` when you generate the authorization URL.
```java
List<Collection> collections = client.getCollections();
```
### Try it yourself!
Do you have some troubles trying to connect to Unsplash? Don't worry, I made some tests ready to try all the functions for you :)

You can find it in `com.unsplash.sdk.FollowingTheReadmeInstructions`. You just need to remove the `@Disabled` flag, update the credentials and play around!
## Status
- This is the first version and has been done to show up my coding skills to an organization.
- This SDK allows you to authorize to the API, read the user profile, read photos and read collections.
- Currently there is some information missing in `UserProfile`, `Photo` and `Collection`.
- To include this SDK as a project dependency, it needs to be uploaded into a public repository, like Maven or Gradle.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)