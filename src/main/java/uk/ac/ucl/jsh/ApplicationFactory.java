package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.applications.*;

public class ApplicationFactory {
    
    public static Application getApp(String appName){
        
        if(appName.equals("find")){
            return new Find();
        }else if(appName.equals("sort")){
            return new Sort();
        }
        else{
            throw new RuntimeException(appName + ": command not found");
        }
        

    }
}
