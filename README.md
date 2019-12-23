# Shared-Preferences  

<p float="left">
  <img src="http://bitjini.github.io/blogs/assets/shared_p.png" height="300" width="430" />
  <img src="https://miro.medium.com/max/4808/1*rO0nXiTXCJcQSABMMDWC7Q.png"  height="300" width="430" /> 
</p>

Android provides many ways of storing data of an application. One of this way is called Shared Preferences. Shared Preferences allow you to save and retrieve data in the form of key,value pair.  
If you have a relatively small collection of key-values that you'd like to save, you should use the SharedPreferences APIs.  


In order to use shared preferences, you have to call a method `getSharedPreferences()` that returns a SharedPreference instance pointing to the file that contains the values of preferences.  

``` java
SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);		
```

The first parameter is the key and the second parameter is the MODE.  
There are other modes available that are listed below.  

| Mode   | Description |
| ------------- | ------------- |
|`MODE_APPEND`| This will append the new preferences with the already existing preferences |
|`MODE_ENABLE_WRITE_AHEAD_LOGGING`|Database open flag. When it is set , it would enable write ahead logging by default|
|`MODE_MULTI_PROCESS`|This method will check for modification of preferences even if the sharedpreference instance has already been loaded|
|`MODE_PRIVATE`|By setting this mode, the file can only be accessed using calling application|
|`MODE_WORLD_READABLE`|This mode allow other application to read the preferences|
|`MODE_WORLD_WRITEABLE`|This mode allow other application to write the preferences|  


You can save something in the sharedpreferences by using `SharedPreferences.Editor` class. You will call the edit method of SharedPreference instance and will receive it in an editor object. Its syntax is  
```java
Editor editor = sharedpreferences.edit();
editor.putString("key", "value");
editor.commit();
```
