cordova.define("cordova/plugin/pincode", function(require, exports, module) {
    var pincode = {
        createEvent: function(title, successCallback, errorCallback) {
            cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'Pincode', // mapped to our native Java class called "Calendar"
                'enterPin', // with this action name
                [{                  // and this array of custom arguments to create our entry
                    "title": title
                }]
            );
        }
    }
    module.exports = pincode;
})
