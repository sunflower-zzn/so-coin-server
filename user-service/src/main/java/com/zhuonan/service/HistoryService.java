package com.zhuonan.service;

import com.zhuonan.mapper.HistoryMapper;
import com.zhuonan.model.HistoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/12/13
 * @Description
 */
@Service
public class HistoryService {
    @Autowired
    HistoryMapper historyMapper;

    @Autowired
    RestTemplate restTemplate;

    public String[] askQuestion(Integer userId, String question) {
        // 构建url请求，请求另一台服务器上的python服务，返回个性问答的推荐结果
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String url = "http://xxx/query";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("query", question);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String[]> response = restTemplate.postForEntity(url, request, String[].class);
        String[] answer = response.getBody();
        HistoryBean historyBean = new HistoryBean(userId, question, Arrays.toString(answer));
        historyMapper.insert(historyBean);
        return answer;
    }

    public List<String> getHistory(Integer userId) {
        return historyMapper.queryByUser(userId);
    }

    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }
}
