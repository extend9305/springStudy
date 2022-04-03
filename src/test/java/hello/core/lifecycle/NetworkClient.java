package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 잘사용 X
public class NetworkClient implements InitializingBean , DisposableBean {

    private String url;

    public NetworkClient() {

        System.out.println("생성자호출 , url = " +url);

    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call (String message){
        System.out.println("call : " + url + ", message = " + message);

    }
    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close : " + url);
    }

    @Override   //생성자생성후
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    @Override //빈 해제 직후
    public void destroy() throws Exception {
        disconnect();
    }
}

