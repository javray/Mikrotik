var exec = require('cordova/exec');

function Mikrotik() {
}

Mikrotik.prototype.connect = function(arg0, arg1, success, error) {
    exec(success, error, "Mikrotik", "connect", [arg0, arg1]);
};

Mikrotik.prototype.disconnect = function(success, error) {
    exec(success, error, "Mikrotik", "disconnect", []);
};

Mikrotik.prototype.login = function(arg0, arg1, success, error) {
    exec(success, error, "Mikrotik", "login", [arg0, arg1]);
}

Mikrotik.prototype.command = function(arg0, success, error) {
    exec(success, error, "Mikrotik", "command", [arg0]);
}

module.exports = new Mikrotik();
