package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member", //--> 지정한 패키지 뒤짐
        //--> 아무것도 지정하지 않으면 componentScan 붙은 이파일의 패키지(hello.core)에 있는걸 뒤짐짐        // filter를하는데 AppConfig 뺴려고 적음
        //--> 권장은 config파일을 프로젝트 최상단 패키지경로에 넣고 사용.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)

)
public class AutoAppConfig {


}
