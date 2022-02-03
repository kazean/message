package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;
    
    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }
    
    @Test
    public void notFoundMessageCode() throws Exception{
        //given
        //when
        //then
        assertThatThrownBy(()->ms.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    public void notFoundMessageCodeDefaultMessage() throws Exception{
        //given
        //when
        //then
        assertThat(ms.getMessage("code", null, "기본 메세지", null)).isEqualTo("기본 메세지");
    }

    @Test
    public void argumentMessage() throws Exception{
        //given
        //when
        //then
        assertThat(ms.getMessage("hello.name", new Object[]{"Spring"}, null)).isEqualTo("안녕 Spring");
    }

    @Test
    public void defaultLang() throws Exception{
        //given
        //when
        //then
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    public void enLang() throws Exception{
        //given
        //when
        //then
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
        assertThat(ms.getMessage("hello.name", new Object[]{"Spring"}, Locale.ENGLISH)).isEqualTo("hello Spring");
    }
}
