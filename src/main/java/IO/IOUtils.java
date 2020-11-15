package IO;

import com.sun.istack.internal.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class IOUtils {
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    /* @description: This method can compress a directory to a zip file under the designated path.
                        Notice that if designated path is null, the directory will be compressed to the same path as itself.
     * @author: Sherwin Liang
     * @timestamp: 2020/11/15 12:54
     * @param: {directory} The directory need to be compressed; {destPath} The given path where places the zip file.
    */
    public static void compressToZip(String directory, String destPath){
        File sourceFile = new File(directory);
        if(!sourceFile.isDirectory()){
            logger.error("Input path is not a directory.");
            return;
        }
        if(destPath!=null){
            File destFile = new File(destPath);
            if(!destFile.exists()){
                destFile.mkdirs();
            }
            if(!destPath.endsWith(File.separator)){
                destPath+=File.separator;
            }
        }else{
            destPath = sourceFile.getParentFile().getPath()+File.separator;
        }
        ZipOutputStream zos = null;
        try{
            zos = new ZipOutputStream(new FileOutputStream(destPath+sourceFile.getName()+".zip"));
            compress(zos,sourceFile,sourceFile.getName());
        }catch(Exception e){
            logger.error(e.getMessage());
        }finally {
            closeStream(zos);
        }
    }
    /* @description: Decompress a zip file to the given path. If path is not given, the zip file path will be applied.
     * @author: Sherwin Liang
     * @timestamp: 2020/11/15 13:02
     * @param: {abstFileName} The file name with its absolute path; {destPath} The given path where places the zip file.
    */
    public static void decompressZip(String abstFileName, String destPath){
        File sourceFile = new File(abstFileName);
        if(!sourceFile.exists() || !sourceFile.isFile()){
            logger.error("Input file name is not valid.");
            return;
        }
        if(destPath==null){
            destPath = sourceFile.getParentFile().getPath()+File.separator;
        }else{
            File file = new File(destPath);
            if(!file.exists()){
                file.mkdirs();
            }
            if(!destPath.endsWith(File.separator)){
                destPath+=File.separator;
            }
        }
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new FileInputStream(sourceFile));
            ZipEntry zipEntry = null;
            File tempFile = null;
            byte[] buf = new byte[64];
            int len = 0;
            while((zipEntry=zis.getNextEntry())!=null){
                if(zipEntry.isDirectory()){
                    tempFile = new File(destPath+zipEntry.getName());
                    if(!tempFile.exists()){
                        tempFile.mkdirs();
                    }
                }else{
                    tempFile = new File(destPath+zipEntry.getName());
                    if(!tempFile.getParentFile().exists()){
                        tempFile.getParentFile().mkdirs();
                    }
                    try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile))){
                        while((len=zis.read(buf))!=-1){
                            bos.write(buf,0,len);
                        }
                    }catch (Exception e){
                        logger.error(e.getMessage());
                    }
                    zis.closeEntry();
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally{
            closeStream(zis);
        }
    }
    /* @description: Copy a file or a directory to the designated path.
     * @author: Sherwin Liang
     * @timestamp: 2020/11/15 17:25
     * @param: {source} The source file or directory; {destPath} The given path where places the copied file or directory.
    */
    public static void copy(String source, @NotNull String destPath){
        File sourceFile = new File(source);
        if(!sourceFile.exists()){
            logger.error("Input file or directory is not valid.");
            return;
        }
        File destFile = new File(destPath);
        if(!destFile.exists())
            destFile.mkdirs();
        if(!destPath.endsWith(File.separator)){
            destPath+=File.separator;
        }
        copySource(sourceFile, destPath+sourceFile.getName());

    }

    private static void copySource(File sourceFile, String basePath) {
        File[] files = sourceFile.listFiles();
        File tempFile = new File(basePath);
        byte[] buf = new byte[64];
        int len = 0;
        if(files==null||files.length==0){
            if(sourceFile.isFile()){
                if(!tempFile.getParentFile().exists())
                    tempFile.getParentFile().mkdirs();
                try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile))){
                    while((len=bis.read(buf))!=-1){
                        bos.write(buf,0,len);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }else{
                if(!tempFile.exists())
                    tempFile.mkdirs();
            }
        }else{
            for(File file:files){
                copySource(file, basePath+File.separator+file.getName());
            }
        }
    }

    private static void compress(ZipOutputStream zos, File file, String basePath){
        File[] files = file.listFiles();
        if(files!=null && files.length>0){
            for(File tempFile : files){
                compress(zos, tempFile, basePath+File.separator+tempFile.getName());
            }
        }else{
            BufferedInputStream bis = null;
            try {
                zos.putNextEntry(new ZipEntry(basePath));
                if (file.isFile()) {
                    bis = new BufferedInputStream(new FileInputStream(file));
                    byte[] buf = new byte[64];
                    int len = 0;
                    while((len=bis.read(buf))!=-1){
                        zos.write(buf,0,len);
                    }
                    zos.closeEntry();
                }
            }catch(Exception e){
                logger.error(e.getMessage());
            }finally {
                closeStream(bis);
            }
        }
    }

    private static void closeStream(Closeable stream) {
        if(stream != null){
            try{
                stream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
