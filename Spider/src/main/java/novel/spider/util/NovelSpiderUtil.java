package novel.spider.util;

import novel.spider.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Classname NovelSpiderUtil
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/5 22:32
 * @Version 1.0
 **/
public final class NovelSpiderUtil {
     public static final Map<NovelSiteEnum,Map<String,String>> context_Map = new HashMap<>();
     static {
         init();
     }
     private NovelSpiderUtil(){};

     private static void init(){
         SAXReader reader = new SAXReader();
         try {
             Document doc = reader.read(new File("src\\main\\resources\\conf\\spider-Rule.xml"));
             Element root = doc.getRootElement();
             List<Element> sites = root.elements("site");
             for(Element site:sites){
                 List<Element> subs = site.elements();
                 Map<String,String> subMap = new HashMap<>();
                 for(Element sub : subs){
                     String name = sub.getName();
                     String text = sub.getTextTrim();
                     subMap.put(name,text);
                 }
                 context_Map.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")),subMap);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     public static void setConfigPath(String path){
         SAXReader reader = new SAXReader();
         try {
             Document doc = reader.read(new File(path));
             Element root = doc.getRootElement();
             List<Element> sites = root.elements("site");
             for(Element site:sites){
                 List<Element> subs = site.elements();
                 Map<String,String> subMap = new HashMap<>();
                 for(Element sub : subs){
                     String name = sub.getName();
                     String text = sub.getTextTrim();
                     subMap.put(name,text);
                 }
                 context_Map.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")),subMap);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }




    /**
     * 获得对应网站的解析规则
     * @param novelSiteEnum
     * @return
     */
     public static Map<String,String> getContext(NovelSiteEnum novelSiteEnum){
         return  context_Map.get(novelSiteEnum);

     }

    /**
     *多个文件合并为一个文件，按文件名进行合并。
     * @param path 基础目录，该目录下所有文件都会合并到megraFile
     * @param mergeToFile 被合并的文本文件，该参数可以为null，合并后保存在pat/megra.txt
     */
    public static void multiFileMerge(String path,String mergeToFile,boolean deleThisFile){
        mergeToFile = mergeToFile == null ? path + "/merge.txt":mergeToFile;
        File[] files = new File(path).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        for(File file :files){
            System.out.println(file.getName());
        }
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int o1Index =Integer.parseInt(o1.getName().split("\\-")[0]);
                int o2Index =Integer.parseInt(o2.getName().split("\\-")[0]);
                if(o1Index >= o2Index){
                    return 1;
                }else if(o1Index  == o2Index) {
                    return  0;
                }else {
                    return -1;
                }
            }
        });
        PrintWriter out = null;
        try{
            out = new PrintWriter(new File(mergeToFile),"UTF-8");
            for(File file : files){
                BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                String line = null;
                while ((line = bufr.readLine())!= null){
                    out.println(line);
                }
                bufr.close();
                if(deleThisFile){
                    file.delete();
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            out.close();
        }

     }

    /***
     *获取书籍状态
     * @param status
     * @return
     */
    public static int getNoveStatus(String status){
        if(status.contains("连载")){
            return 1;
        }else if(status.contains("完结")|| status.contains("完本")||status.contains("完成")){
            return 2;
        }else {
            throw new RuntimeException("不支持的书籍状态："+status);
        }
    }

    /**
     * 格式化字符串为日期对象
     * @param dateStr
     * @param pattern
     * @return
     * @throws Exception
     */
    public static Date getData(String dateStr, String pattern) throws Exception{
        SimpleDateFormat sdf  = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateStr);
        return date;
    }

}
