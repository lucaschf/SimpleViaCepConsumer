package tsi.ws.viacepconsumer.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import tsi.ws.viacepconsumer.model.ZipCode;

import java.util.Arrays;

public class ViaCepService {

    public ResponseEntity<ZipCode> getZipCodeInfo(String zipCode){
        String BASE_URL = "https://viacep.com.br/ws/";
        var url = String.format("%s%s/json", BASE_URL, zipCode);

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        return restTemplate.getForEntity(url, ZipCode.class);
    }
}
