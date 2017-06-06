package idv.caemasar.chapterGeneration.tests.main;

import idv.caemasar.chapterGeneration.tests.wtf.Entity;
import idv.caemasar.chapterGeneration.tests.wtf.Table;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

public class ClassPoolUtils {  
    
    
    /** 
     * 动态ORM映射 
     *  
     * @param entityClassName   待映射的实体全限定类名 
     * @param tableName         待映射的表名 
     * @return                  映射后的类对象 
     */  
    public static Class<?> tableMapping(String entityClassName, String tableName){  
        Class<?> c = null;  
        try {  
            ClassPool classPool = ClassPool.getDefault();  
            classPool.appendClassPath(new ClassClassPath(ClassPoolUtils.class));  
            classPool.importPackage("javax.persistence");  
            CtClass clazz = classPool.get(entityClassName);  
            ClassFile classFile = clazz.getClassFile();  
             
            System.out.println("增强前Entity01:" + clazz.getAnnotation(Entity.class));  
            System.out.println("增强前Table02:" + clazz.getAnnotation(Table.class));  
              
            ConstPool constPool = classFile.getConstPool();  
            Annotation tableAnnotation = new Annotation("javax.persistence.Table", constPool);  
            tableAnnotation.addMemberValue("name", new StringMemberValue(tableName, constPool));  
            // 获取运行时注解属性  
            AnnotationsAttribute attribute = (AnnotationsAttribute)classFile.getAttribute(AnnotationsAttribute.visibleTag);  
            attribute.addAnnotation(tableAnnotation);  
            classFile.addAttribute(attribute);  
            classFile.setVersionToJava5();  
            //clazz.writeFile();  
              
            System.out.println("增强后Entity001:" + clazz.getAnnotation(Entity.class));  
            System.out.println("增强后Table002:" + clazz.getAnnotation(Table.class));  
            //TODO 当前ClassLoader中必须尚未加载该实体。（同一个ClassLoader加载同一个类只会加载一次）  
//            c = clazz.toClass();  
//            System.out.println("增强后toClass-Entity0001:" + c.getAnnotation(Entity.class));  
//            System.out.println("增强后toClass-Table0002:" + c.getAnnotation(Table.class));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return c;  
    }  
  
    public static void main(String[] args) {       
          ClassPoolUtils.tableMapping("com.andy.model.Order", "order1");  
    }  
}