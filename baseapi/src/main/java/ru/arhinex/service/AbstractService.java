package ru.arhinex.service;

import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

abstract public class AbstractService<M> {
    private String connectionUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private M stab;

    public boolean isMock(){
        return StringUtils.isEmpty(connectionUrl);
    }

    protected M getStab(){
        if (stab == null){
            stab = createStab();
        }
        return stab;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected abstract M createStab();

    public static ServiceBuilder builder() {
        return new ServiceBuilder();
    }


    public static class ServiceBuilder {
        private AbstractService service;

        public AbstractService build() {
            return service;
        }

        public ServiceBuilder connectionUrl(String url) {
            service.setConnectionUrl(url);
            return this;
        }
    }

}
