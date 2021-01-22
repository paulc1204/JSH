package uk.ac.ucl.jsh.applications;

public class ApplicationFactory {
    
    public static Application getApp(String appName){
        
        if(appName.equals("find")){
            return new Find();
        }else if(appName.equals("sort")){
            return new Sort();
        }else if(appName.equals("cd")){
            return new Cd();
        }else if(appName.equals("cat")){
            return new Cat();
        }else if(appName.equals("pwd")){
            return new Pwd();
        }else if(appName.equals("ls")){
            return new Ls();
        }else if(appName.equals("echo")){
            return new Echo();
        }else if(appName.equals("head")){
            return new Head();
        }else if(appName.equals("tail")){
            return new Tail();
        }else if(appName.equals("grep")){
            return new Grep();
        }else if(appName.equals("uniq")){
            return new Uniq();
        }else if(appName.equals("cut")){
            return new Cut();
        }
        else{
            throw new RuntimeException(appName + ": command not found");
        }
        
    }
}
