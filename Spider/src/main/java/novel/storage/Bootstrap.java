package novel.storage;

import novel.storage.impl.BxwxNovelStorage;

/**
 * @Classname Bootstrap
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/18 23:58
 * @Version 1.0
 **/
public class Bootstrap {
    public static void main(String[] args) throws Exception {
        Processor processor = new BxwxNovelStorage();
        processor.process();
    }
}
