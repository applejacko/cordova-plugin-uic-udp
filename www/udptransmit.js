var exec = require('cordova/exec');
var platform = require('cordova/platform');

module.exports = {
	
    /**
     * Open a native alert dialog, with a customizable title and button text.
     *
     * @param {String} message              Message to print in the body of the alert
     * @param {Function} completeCallback   The callback that is called when user clicks on a button.
     * @param {String} title                Title of the alert dialog (default: Alert)
     * @param {String} buttonLabel          Label of the close button (default: OK)
     */
alert: function(message, completeCallback, title, buttonLabel) {
	exec(completeCallback, null, "Notification", "alert", [message, title, buttonLabel]);
	}
};