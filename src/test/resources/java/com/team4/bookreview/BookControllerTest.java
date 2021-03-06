
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import com.team4.bookreview.controller.BookController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BookControllerTest {
    @Autowired
    public BookController bookController;
    private MockMvc mockMvc;

    @Before
    public void createController() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void bookTest() throws Exception {
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/book/get").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
    }
}