var exec = require('cordova/exec');

;

exports.getAllAppInfo = function (success, error) {
    exec(success, error, 'AppPackageInfo', 'getAllAppInfo', []);
};


exports.getAppInfo = function (packageName, success, error) {
    exec(success, error, 'AppPackageInfo', 'getAppInfo', [packageName]);
};