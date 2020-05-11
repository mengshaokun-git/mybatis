package mapperscan;

import java.io.File;

public class DealWithXML {

    private static void scanXML(File f){
        if(f.isDirectory()){
            // 如果是文件夹则进入下一层路径扫描
            File[] files = f.listFiles();
            if(files != null && files.length > 0){
                for(File file : files){
                    scanXML(file);
                }
            }
        }else if(f.isFile()){
            // 如果是文件则判断是否带有特定注解，从而注入到IOC容器中
//            String className = f.getPath().replace(classPath,"")
//                    .replaceAll("/",".")
//                    .replace(".class","");
//            Class c = Class.forName(className);
//            //加载普通对象
//            IocHandle.loadIoc(c);
            //处理xml
        }
    }



}
