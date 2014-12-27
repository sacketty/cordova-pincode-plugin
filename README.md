Cordova Pincode Keyboard plugin
======

The `cordova.plugins.pincode` object provides functions to make interacting with the keyboard easier, and fires events to indicate that the keyboard will hide/show.

    cordova plugin add com.brentsys.cordova.pincode

Methods
-------

- cordova.plugins.pincode.createEvent


createEvent
=================

Shows the PinCode entry keyboard with a title.

    cordova.plugins.plugin.createEvent(title, success, error)
    

Supported Platforms
-------------------

- Android


Tutorial
========
## Steps
####1. install cordova

	npm install -g cordova
	
if you already have cordova installes, make sure you upgrade to the latest version

	npm update -g cordova


####2. create a cordova project
Navigate (cd) to a directory where you store projects on your file system.

	cd <my projects directory>
	
Using the Cordova CLI, create a Cordova project named PinExample in a directory named pinexample:

	cordova create pinexample com.yourname.pinexample PinExample
	
####3. setup your example project
Navigate to the project directory:

	cd pinexample
	
Add support for the Android platform. But before, make sure:
1. the android sdk is available on your system
2. your are in the 'pinexample' directory
then

	cordova platforms add android
	
####4. add the pincode plugin

	cordova plugin add com.brentsys.cordova.pincode
	
#####5. modify the index.html file
1. add a button to test the plugin
2. add jquery.

your index.html file should somewhatt look loke this..


>
        .
        .
        .
        <div class="app">
            <h1>Apache Cordova</h1>
            <div id="deviceready" class="blink">
                <p class="event listening">Connecting to Device</p>
                <p class="event received">Device is Ready</p>
            </div>
        </div>
        <button id="btn" type="button">Test plugin!</button>
        <script type="text/javascript" src="cordova.js"></script>
        <script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
        <script type="text/javascript" src="js/index.js"></script>

>

####6. modify then index.js file

#####1. initialize the picode variable
>
	.
	.
	.
	var pincode; //<--- initialized variable
	var app = {
    // Application Constructor
    .
    .
    .
    
#####2. set variable and listener after device ready
>
	.
	.
	.
	    onDeviceReady: function() {
        app.receivedEvent('deviceready');
        pincode = window.cordova.require('cordova/plugin/pincode'); // <-- pincode variable set after device ready
        $('#btn').on('click', showPincode); // <-- click listener added to button
    },
    // Update DOM on a Received Event
    .
    .

#####3. finally manage button and pincode
>
	.
	.
	.
	//<--- begin added code --->
	function success(obj){
    	if(obj.cancelled === true){
        	window.alert('operation cancelled');
    	} else {
        	window.alert('pinCode entered: '+ obj.pinCode);
    	}
	}
	function error(message) { window.alert('Oopsie! ' + message); };
	function showPincode(){
    	pincode.createEvent('Enter pinCode:', success, error);
	}
	//<--- end added code ---->
	app.initialize();

####4 That's it
You should see a standard pin-entry keyboard.

Feel free to report any bug or omission.









