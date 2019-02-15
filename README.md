# cordova-app-package-info
get all install app package info ,android only


＃use

declare var AppPackageInfo:any;


    AppPackageInfo.getAllAppInfo(
      data => { 
           // the data is array
           
           data[0].name; //app name
           
           data[0].versionName;
           
           data[0].versionCode;
           
           data[0].packageName;
           
        });
        
        

    AppPackageInfo.getAppInfo("com.sample.app",data => {
          
          data.name; //app name
           
           data.versionName;
           
           data.versionCode;
           
           data.packageName;
        
        });
