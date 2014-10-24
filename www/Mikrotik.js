var exec = require('cordova/exec');

exports.connect = function(arg0, arg1, success, error) {
    exec(success, error, "Mikrotik", "connect", [arg0, arg1]);
};
