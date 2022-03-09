package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutoWiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        @Autowired(required = false) //->자동 주입할 대상이 없으면 호출 X
        public void seyNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired //->호출은 가능하되 null
        public void seyNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }
        @Autowired //-> 없으면 Optional.empty
        public void seyNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }

    }


}
