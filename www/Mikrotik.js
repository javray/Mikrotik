var exec = require('cordova/exec');

function Mikrotik() {
}

Mikrotik.prototype.connect = function(arg0, arg1, success, error) {
    exec(success, error, "Mikrotik", "connect", [arg0, arg1]);
};

Mikrotik.prototype.login = function(arg0, arg1, success, error) {
    exec(success, error, "Mikrotik", "login", [arg0, arg1]);
}

module.exports = new Mikrotik();

/*
exports.connect = function(arg0, arg1, success, error) {
    exec(success, error, "Mikrotik", "connect", [arg0, arg1]);
};
*/
