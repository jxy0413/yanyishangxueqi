package cn.edu.bjfu.igarden.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;
import java.util.Objects;

public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 根据图片地址转换为base64编码字符串
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        return new String(Objects.requireNonNull(Base64.encodeBase64(data)));
    }

    /**
     * 获取图片宽度
     *
     * @param file 图片文件
     * @return 宽度
     */
    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = {0, 0};
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽
            result[1] = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
     *
     * @param imgPath    源图片路径
     * @param widthdist  压缩后图片宽度
     * @param heightdist 压缩后图片高度
     */
    public static void reduceImg(String imgPath, String imgName, int widthdist,
                                 int heightdist) {
        try {
            File srcfile = new File(imgPath + imgName);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            // 如果rate不为空说明是按比例压缩
            int[] results = getImgWidth(srcfile);
            if (results[0] > results[1]) {
                heightdist = widthdist * results[1] / results[0];
            } else {
                widthdist = heightdist * results[0] / results[1];
            }
            // 开始读取文件并进行压缩
            Image src = javax.imageio.ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage(widthdist,
                    heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            ByteArrayOutputStream out = null;
            byte[] b = null;
            String dstName = imgPath + "thumb" + imgName;
            String formatName = (imgPath + "thumb" + imgName).substring((imgPath + "thumb" + imgName).lastIndexOf(".") + 1);
            ImageIO.write(tag, formatName, new File(dstName));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file);
            out.write(data);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && parent.exists() == false) {
                if (parent.mkdirs() == false) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file);
    }
    /**
     * 上传文件
     * @param file  文件
     * @param path  文件路径
     * @param fileName  文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName){

        //文件路径
        String realPath = path + File.separator + fileName;
        System.out.println("realPath = " + realPath);
        File dest = new File(realPath);
        //判断文件目录是否存在
        if( !dest.getParentFile().exists()){
            dest.getParentFile().mkdir();   //不存在就创建
        }
        try{
            //保存文件
            file.transferTo(dest);
            ImageIcon image = new ImageIcon(dest.getAbsolutePath());
            String thumbnailImg = path + File.separator + "thumbnail" + File.separator + fileName;
            File thumbNail = new File(thumbnailImg);
            if(!thumbNail.getParentFile().exists()){
                thumbNail.getParentFile().mkdir();
            }
            Thumbnails.of(realPath)
                    .size(200,200)
                    .toFile(thumbnailImg);
            return true;
        }catch (IllegalStateException  e){
            e.printStackTrace();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static void getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
