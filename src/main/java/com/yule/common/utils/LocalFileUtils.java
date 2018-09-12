package com.yule.common.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 本地文件工具
 * @author [004221]duanzhenxing@inner.czy.com
 * @version [V1.0, 2018/2/22]
 * @Description:{功能说明}。
 * @ClassName LocalFileUtils
 * @see [相关类/方法]
 * @since [产品/模块]
 */
public class LocalFileUtils {

    private static final Logger log = LoggerFactory.getLogger(LocalFileUtils.class);

    /**
     * 根据输入流上传文件到本地
     * @param input 输入流
     * @param outputPath 文件在的全路径或者相对路径
     * @param fileName 文件名
     */
    public static void upload(InputStream input, String outputPath, String fileName) throws IOException{

        OutputStream os = null;
        try {
            String path = outputPath;
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = input.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (FileNotFoundException e) {
            log.error("上传时未找到文件：",e);
        } finally {
            // 完毕，关闭所有链接
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 判断本地是否存在该文件
     * @param filePath 文件全路径
     * @return
     */
    public static boolean isLocalFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
	 * @Description 根据文件的路径，获取到文件的InputStream
	 * @param filePath 全路径或者相对路径
	 * @return 文件的InputStream对象
	 * @author duanzhenxing
	 * @date 2017年4月27日
	 */
	public static InputStream getLocalFileInputStream(String filePath){
		try {
            File file = new File(filePath);
			InputStream is = new FileInputStream(file);
			return is;
		} catch (Exception e) {
			log.error("获取到文件的InputStream出错：",e);
		} finally {

		}
		return null;
	}

    /**
     * 下载本地文件
     * @param output
     * @param remoteFile
     * @throws IOException
     */
    public static void download(OutputStream output, String remoteFile) throws IOException {
        InputStream inputStream = null;
        try {
            File file = new File(remoteFile);
            inputStream = new FileInputStream(file);
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                output.write(bs, 0, len);
            }
        }finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(inputStream);
        }
    }
}
