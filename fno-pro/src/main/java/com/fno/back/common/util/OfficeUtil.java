package com.fno.back.common.util;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Optional;

/***
 * @des
 * @author Ly
 * @date 2023/5/13
 */

public class OfficeUtil {

    /**
     * 通过html生成PDF
     *
     * @param htmlContent html格式内容
     * @param readPath        输出文件file
     */
    public static void createdPdfByItextHtml(String htmlContent, String readPath) {
        File file = new File(readPath);
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        PdfWriter writer = null;
        try {
            // 1. 获取生成pdf的html内容
            inputStream = new ByteArrayInputStream(htmlContent.getBytes("utf-8"));
            outputStream = new FileOutputStream(file);
            Document document = new Document();
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            // 2. 添加字体
            //            XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
            //            fontImp.register(getFontPath());
            // 3. 设置编码
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, inputStream, Charset.forName("UTF-8"), new CustomXMLWorkerFontProvider());
            // 4. 关闭,(不关闭则会生成无效pdf)
            document.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 通过HTML生成Word
     *
     * @param htmlbody
     * @param fileName
     * @return
     * @throws Exception
     */
    public static File createWordByHtml(String htmlbody, String fileName) throws Exception {
        File file = new File(fileName);
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(htmlbody.getBytes());
        outputStream.flush();
        outputStream.close();
        return file;
    }

    public static void createDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String generateHtmlBody(String detailContent) {
        detailContent = StringUtils.isEmpty(detailContent) ? "<h1 style=\"margin-left:28px\"><strong><span style=\";font-family:宋体;font-size:29px\">当前无展示内容</span></strong></h1>" : detailContent;
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                detailContent +
                "</body>\n" +
                "</html>";
        return html;
    }


    //public static void main(String args[]) throws Exception {
    //        addSignSeal("C:/Users/pansoft/Desktop/协议1.pdf",
    //                "C:/Users/pansoft/Desktop/output.pdf",
    //                "C:/Users/pansoft/Desktop/1.png","北京科技有限公司");
    //}


    /**
     * @param sourceFilePath
     * @param targetFilePath
     * @param imagePath
     * 给生成好的PDF文件,在文件结尾，公司名称日期位置，添加印章
     */
    public static void addSignSeal(String sourceFilePath,
                                   String targetFilePath,String imagePath,String company, String bgType)throws Exception{
        Image image = Image.getInstance(imagePath);
        image.scaleToFit(150,150);
        PdfReader pdfReader = new PdfReader(sourceFilePath);
        int pageNumber = pdfReader.getNumberOfPages();
        PdfReaderContentParser parser = new PdfReaderContentParser(pdfReader);
        parser.processContent(pageNumber, new RenderListener() {
            @Override
            public void beginTextBlock() {

            }

            @Override
            public void renderText(TextRenderInfo textRenderInfo) {
                String text = textRenderInfo.getText();
                if(text != null && text.contains(company)){
                    Rectangle2D.Float textFloat = textRenderInfo.getBaseline().getBoundingRectange();
                    float x = textFloat.x;
                    float y = textFloat.y;
                    image.setAbsolutePosition(x+30f,y-75f);
                }
            }

            @Override
            public void endTextBlock() {

            }

            @Override
            public void renderImage(ImageRenderInfo imageRenderInfo) {

            }
        });
        PdfStamper stamper = new PdfStamper(pdfReader,new FileOutputStream(targetFilePath));

        //transparent 是否透明.0：白色背景。1：透明背景
        if("0".equals(bgType)){
            //白底的印章图片,效果看起来是字在章上面。
            PdfContentByte cvas = stamper.getUnderContent(pageNumber);
            cvas.addImage(image);
        }else{
            //透明的图片就需要用。效果看起来是章在字上面。
            PdfContentByte cvas = stamper.getOverContent(pageNumber);
            cvas.addImage(image);
        }
        stamper.close();
        Optional.of(pdfReader).ifPresent(PdfReader::close);
    }


}
