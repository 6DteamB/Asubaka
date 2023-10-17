package test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.ImageServlet;

public class ImageServletTest {

    @Test
    public void testImageServlet() throws Exception {
        // テストに必要なモックオブジェクトを作成
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        ServletOutputStream outputStream = Mockito.mock(ServletOutputStream.class);

        // モックオブジェクトの動作を設定
        Mockito.when(request.getServletContext()).thenReturn(servletContext);
        Mockito.when(servletContext.getMimeType(Mockito.anyString())).thenReturn("image/png");
        Mockito.when(servletContext.getResourceAsStream(Mockito.anyString())).thenReturn(getClass().getResourceAsStream("/path/to/image.png"));
        Mockito.when(response.getOutputStream()).thenReturn(outputStream);

        // ImageServletを初期化
        ImageServlet imageServlet = new ImageServlet();
        imageServlet.init(Mockito.mock(ServletConfig.class));

        // サーブレットを呼び出し
        imageServlet.doGet(request, response);

        // 画像データが正しく書き込まれたことを検証
        Mockito.verify(outputStream, Mockito.atLeastOnce()).write(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
    }
}
