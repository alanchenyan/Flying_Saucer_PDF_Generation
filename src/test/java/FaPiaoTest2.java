import entity.User;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static com.itextpdf.text.pdf.BaseFont.NOT_EMBEDDED;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

/**
 * This is a JUnit test which will generate a PDF using Flying Saucer
 * and Thymeleaf templates. The PDF will display a letter styled with
 * CSS. The letter has two pages and will contain text and images.
 * <p>
 * Simply run this test to generate the PDF. The file is called:
 * <p>
 * /test.pdf
 */
public class FaPiaoTest2 {

    private static final String OUTPUT_FILE = "fapiao.pdf";
    private static final String UTF_8 = "UTF-8";

    @Test
    public void generatePdf() throws Exception {

        // We set-up a Thymeleaf rendering engine. All Thymeleaf templates
        // are HTML-based files located under "src/test/resources". Beside
        // of the main HTML file, we also have partials like a footer or
        // a header. We can re-use those partials in different documents.
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(UTF_8);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // The data in our Thymeleaf templates is not hard-coded. Instead,
        // we use placeholders in our templates. We fill these placeholders
        // with actual data by passing in an object. In this example, we will
        // write a letter to "John Doe".
        //
        // Note that we could also read this data from a JSON file, a database
        // a web service or whatever.
        List<List<String>> datas = exampleDataForJohnDoe();

        Context context = new Context();

        List<String> columns = new ArrayList<>();
        columns.add("姓名");
        columns.add("年龄");
        columns.add("性别");

        context.setVariable("title", "发票");
        context.setVariable("columns",columns);
        context.setVariable("datas", datas);

        // Flying Saucer needs XHTML - not just normal HTML. To make our life
        // easy, we use JTidy to convert the rendered Thymeleaf template to
        // XHTML. Note that this might not work for very complicated HTML. But
        // it's good enough for a simple letter.
        String renderedHtmlContent = templateEngine.process("fapiao2", context);
        String xHtml = convertToXhtml(renderedHtmlContent);

        ITextRenderer renderer = new ITextRenderer();

        // 宋体（支持中文）
        renderer.getFontResolver().addFont("SIMSUN.TTC", IDENTITY_H, NOT_EMBEDDED);

        // FlyingSaucer has a working directory. If you run this test, the working directory
        // will be the root folder of your project. However, all files (HTML, CSS, etc.) are
        // located under "/src/test/resources". So we want to use this folder as the working
        // directory.
        String baseUrl = FileSystems
                                .getDefault()
                                .getPath("src", "test", "resources")
                                .toUri()
                                .toURL()
                                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        // And finally, we create the PDF:
        OutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        renderer.createPDF(outputStream);
        outputStream.close();
    }

    private List<List<String>> exampleDataForJohnDoe() {
        List<List<String>> datas = new ArrayList<>();

        List<String> bean = new ArrayList<>();
        bean.add("Tom");
        bean.add("19");
        bean.add("1");

        datas.add(bean);

        List<String> bean2 = new ArrayList<>();
        bean2.add("Amy");
        bean2.add("28");
        bean2.add("0");
        datas.add(bean2);

//        User tom = new User("Tom2", 19, 1);
//        User amy = new User("Amy", 28, 0);
//        User leo = new User("Leo", 23, 1);

//        userList.add(tom);
//        userList.add(amy);
//        userList.add(leo);

        return datas;
    }

    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }

}
