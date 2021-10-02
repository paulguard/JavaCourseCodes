package io.kimmking.rpcfx.demo.provider;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import io.kimmking.rpcfx.api.RpcfxResolver;

public class DemoResolver implements RpcfxResolver {

    private final String IMPL_PACKAGE = "io.kimmking.rpcfx.demo.provider";

    @Override
    public Class<?> resolve(String serviceClass) {

        Class<?> interfaceClass = null;
        try {
            interfaceClass = Class.forName(serviceClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Class<?>> allClasses = getAllClassByPatch(IMPL_PACKAGE);

        List<Class<?>> classList = new ArrayList<>();
        for (int i = 0; i < allClasses.size(); i++) {

            //判断是不是同一个接口
            if (interfaceClass.isAssignableFrom(allClasses.get(i))) {
                if (!interfaceClass.equals(allClasses.get(i))) {
                    classList.add(allClasses.get(i));
                }
            }
        }

        return classList.get(0);
    }

    private List<Class<?>> getAllClassByPatch(String implPackage) {
        ArrayList<Class<?>> list = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = implPackage.replace('.', '/');
        try {
            ArrayList<File> fileList = new ArrayList<>();
            Enumeration<URL> enumeration = classLoader.getResources(path);
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                fileList.add(new File(url.getFile()));
            }
            for (int i = 0; i < fileList.size(); i++) {
                list.addAll(findClass(fileList.get(i),implPackage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static ArrayList<Class<?>> findClass(File file,String packagename) {
        ArrayList<Class<?>> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        File[] files = file.listFiles();
        for (File file2 : files) {
            if (file2.isDirectory()) {
                assert !file2.getName().contains(".");//添加断言用于判断
                ArrayList<Class<?>> arrayList = findClass(file2, packagename+"."+file2.getName());
                list.addAll(arrayList);
            }else if(file2.getName().endsWith(".class")){
                try {
                    //保存的类文件不需要后缀.class
                    list.add(Class.forName(packagename + '.' + file2.getName().substring(0,
                        file2.getName().length()-6)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
