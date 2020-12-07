# KITE-AV1-AppRTCMobile-Test
This test is written with __KITE-Framework__, which follows the Page Object Model architecture
for automated test.
## Test Architecture

### Pages
 Contain all of the UI element object and the function to interact with them. All automation APIs (Selenium) are
 handled in these objects.

### Steps
In these steps, you call specific functions from the pages, to perform specific task to your app, 
and move you test script along. (Status: passed/broken)

### Checks
These are the same as steps, but the purpose of them is to perform verify (assert) certain values/stats
 to determine if your test is passing or failing. (Status : passed/failed)
 

A test passes when all of the Steps are not BROKEN, and all of the Checks don't FAIL.
 
### Test Script

The test itself is a ensemble of __steps__ and __checks__, assigned to each participants depending on
their role. These steps are synchronized between participants either by another object or by a
transitioning steps.

Step | Client 1 | Client 2 |
------------- |------------- | ------------- 
1 | Connect client and join room with __ConnectClientStep__ | Connect client and join room with __ConnectClientStep__
2 | Verify own media with __LocalVideoDisplayCheck__ | Verify own media with __LocalVideoDisplayCheck__
3 | Wait until everyone left with __ParticipantLeftCheck__ | Quit application with __QuitApplicationStep__
4 | Quit application with __QuitApplicationStep__ |


## Understand the test config file
 
 To run this test, you will need to have a config file that the KITE Engine will understands.
 A sample config file is provided at  `configs/dev.config.json`  

### Important parameters 

#### Grids
Set the address of your Selenium Hub:  
  ```
  "grids": [
     {
       "type": "local",
       "url": "http://localhost:4444/wd/hub"
     }
   ],
  ```  
Refer to the _Compilation and Run_ section to see how to set up a Selenium grid.

#### Clients
Set your app directory according to where your binary is. 

```
"clients": [
   {
     "browserName": "app",
     "platform": "MAC",
     "deviceName": "MAC",
     "app": {
       "appName": "/Users/cosmouser/Desktop/appium/AppRTCMobile_av1_m85_release.app",
       "appWorkingDir": "/Users/cosmouser/Desktop/appium"
     }
   }
  ]
```
#### Tests
This is the part which let the Engine know about the test. Most of the parameters are self-explanatory.

```
"tests": [
  {
    "name": "AppRTCMobile AV1 Test %ts",
    "description": "AppRTCMobile AV1 WebRTC Test",
    "tupleSize": 2,
    "testImpl": "io.cosmosoftware.kite.apprtcmobile.tests.AppRTCMobileTest",
    "payload": {
     ...
    }
  }
]
```

#### Payload

This is the object that contains additional data that we can use during the test. This object will
be process by the function `payloadHandling()` of the test. Here we will pass some parameters:

__coordinates__: The top left hand corner and bottom right hand corner points of the log box and video rectangle. The values are optimized for the resolution 1920x1080 and should be changed for any other. 
```
"coordinates": {
          "log": [621, 577, 199, 430], 
          "video": [921, 70, 490, 595] 
        }
```

## Compiling and Running

### Compiling
You'll of course need KITE to run this test.  

__Clone KITE__
```
git clone https://github.com/webrtc/KITE.git
```

To setup KITE, please follow these [instructions](https://github.com/webrtc/KITE/blob/master/README.md).   
The test script will only compile and run after KITE has been compiled using Maven, which will install all the required Maven
dependencies in your local m2 repo and the kite-jar-with-dependencies.jar file has been compiled.

__Set up your own grid__

Provided that you have install java, maven as in KITE's instructions.

####MAC

- Step 1: Download and install Node.js
- Step 2: Install appium 
```
npm install -g appium
```
- Step 3: Download and install Appium For Mac from [here](https://github.com/appium/appium-for-mac/releases) 
```
Mac OS X does not allow an application to use the Accessibility API without permission, so you have to enable it manually.

10.7
Open System Preferences > Universal Access.
Check the "Enable access for assistive devices" checkbox.
10.8
Open System Preferences > Accessibility.
Check the "Enable access for assistive devices" checkbox.
10.9 and later
Open System Preferences > Security & Privacy.
Click the Privacy tab.
Click Accessibility in the left hand table.
If needed, click the lock to make changes.
If you do not see AppiumForMac.app in the list of apps, then drag it to the list from Finder.
Check the checkmark next to AppiumForMac.app.
```
- Step 5: Install Xcode and Xcode Command Line Tools

- Step 6: Download the jar file for Selenium [here](https://selenium-release.storage.googleapis.com/3.141/selenium-server-standalone-3.141.59.jar)
- Step 7: Start the hub on localhost, port 4444:
```
java -jar PATH/TO/SELENIUM.JAR -role hub
```
- Step 6: Start the nodes (2 are required for this test) and connect it to the hub.
```
appium --nodeconfig mac.appium.json --port 4728```
```
Here is an example of an appium config file.
```
{
  "capabilities": [
    {
      "browserName": "app",
      "app": "/Users/cosmouser/Desktop/appium/AppRTCMobile_av1_m85_release.app",
      "appWorkingDir": "/Users/cosmouser/Desktop/appium/",
      "deviceName": "MAC",
      "maxInstances": 1,
      "platform": "MAC"
    }
  ],
  "configuration": {
    "cleanUpCycle": 2000,
    "timeout": 60000,
    "proxy": "org.openqa.grid.selenium.proxy.DefaultRemoteProxy",
    "url": "http://192.168.1.133:4444/wd/hub", -> SELENIUM HUB URL
    "maxSession": 1,
    "port": "4728",
    "host": "192.168.1.209", -> NODE IP
    "register": true,
    "registerCycle": 5000,
    "hubPort": "4444",
    "hubHost": "192.168.1.133" -> SELENIUM HUB IP
  }
}
```

### Compile

You need to finish the KITE setup and compilation before this step (grid is not needed here)

Go to the cloned folder `KITE-AV1-AppRTCMobile-Test`:

```
c
```
or
```
mvn clean install
```

### Run

```
cd KITE-AV1-AppRTCMobile-Test
r configs\dev.config.json
```

The `r` is a script you have while setting up KITE, this replaces a long java command to run the test.

During the test, you will see the logs showing the step and the status of those steps.

## Test output

Each will generate allure report found in `kite-allure-report/` folder.  
After running the test, you can open the Allure dashboard with the command `a`.

```
cd KITE-AV1-AppRTCMobile-Test
a
```
Alternatively, the full command to launch the Allure dashboard is:  
```
allure serve kite-allure-reports
```

You will also have access to the csv report in `kite-allure-reports/csv-report` and the logs in the 
`logs` folder.