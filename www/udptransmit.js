var exec = require('cordova/exec');
var platform = require('cordova/platform');

module.exports = {
	alert: function(message, completeCallback, title, buttonLabel) {
		exec(completeCallback, null, "Notification", "alert", [message, title, buttonLabel]);
	}
};