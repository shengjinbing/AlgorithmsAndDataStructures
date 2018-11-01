package cn.modesty.suanfa.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {
    /**
     * @param input 需要压缩的字符串
     * @return 压缩后的字符串
     * @throws IOException IO
     */
    public static String compress(String input) throws IOException {
        if (input == null || input.length() == 0) {
            return input;
        }
        ByteArrayOutputStream out    = new ByteArrayOutputStream();
        GZIPOutputStream      gzipOs = new GZIPOutputStream(out);
        gzipOs.write(input.getBytes());
        gzipOs.flush();
        gzipOs.close();
        return out.toString("ISO-8859-1");
    }
    /**
     * @param zippedStr 压缩后的字符串
     * @return 解压缩后的
     * @throws IOException IO
     */
    public static String uncompress(String zippedStr) throws IOException {
        if (zippedStr == null || zippedStr.length() == 0) {
            return zippedStr;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(zippedStr
                .getBytes("ISO-8859-1"));
        GZIPInputStream gzipIs = new GZIPInputStream(in);
        byte[]          buffer = new byte[256];
        int             n;
        while ((n = gzipIs.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
        return out.toString();
    }

    /**

     * 使用gzip进行压缩
     */

    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip=null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(gzip!=null){
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     *
     * <p>Description:使用gzip进行解压缩</p>
     * @param compressedStr
     * @return
     */
    public static String gunzip(String compressedStr){
        if(compressedStr==null){
            return null;
        }

        ByteArrayOutputStream out= new ByteArrayOutputStream();
        ByteArrayInputStream in=null;
        GZIPInputStream ginzip=null;
        byte[] compressed=null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in=new ByteArrayInputStream(compressed);
            ginzip=new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed=out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }

        return decompressed;
    }


}
