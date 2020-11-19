import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class IOTest {

    @Test
    public void toZipTest(){
        IO.IOUtils.compressToZip("D:\\IdeaProjects\\JavaGo\\src\\main\\resources","D:\\IdeaProjects");
    }
    @Test
    public void decompressTest(){
        IO.IOUtils.decompressZip("D:\\IdeaProjects\\JavaGo\\src\\main\\resources.zip","D:\\IdeaProjects");
    }
    @Test
    public void copyTest(){
        IO.IOUtils.copy("D:\\IdeaProjects\\JavaGo\\src\\main\\resources","D:\\IdeaProjects");
    }
    @Test
    public void test() throws IOException {
        File f = new File("D:\\IdeaProjects\\JavaGo\\src\\main\\resources\\test.txt");
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getCanonicalPath());
        System.out.println(f.getPath());
        System.out.println(f.getParent());
    }
}
