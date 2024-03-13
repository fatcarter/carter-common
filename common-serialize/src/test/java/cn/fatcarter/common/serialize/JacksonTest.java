package cn.fatcarter.common.serialize;

import cn.fatcarter.common.serialize.jackson.JacksonArray;
import cn.fatcarter.common.serialize.jackson.JacksonObject;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JacksonTest {
    String json = "{\n" +
            "        \"__SENDER_MS__\": 1703673653684,\n" +
            "        \"payload\": \"{\\\"msgTask\\\":{\\\"bodyMapping\\\":\\\"\\\",\\\"createAt\\\":\\\"2023-12-27T16:15:47\\\",\\\"isQueryForBody\\\":0,\\\"modifyAt\\\":\\\"2023-12-27T18:37:09\\\",\\\"nextFireTime\\\":\\\"2023-12-27T18:40:53\\\",\\\"query\\\":\\\"{\\\\\\\"end_date\\\\\\\":2334758400000,\\\\\\\"birthday\\\\\\\":-113990400000,\\\\\\\"name_pinyin\\\\\\\":\\\\\\\"zhaozhenbo\\\\\\\",\\\\\\\"account_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"education\\\\\\\":2,\\\\\\\"endDate\\\\\\\":2334758400000,\\\\\\\"nation\\\\\\\":\\\\\\\"汉族\\\\\\\",\\\\\\\"train_count\\\\\\\":0,\\\\\\\"id_card\\\\\\\":\\\\\\\"220602196605232415\\\\\\\",\\\\\\\"worker_photo\\\\\\\":\\\\\\\"b0e5010c-018f-8a74-d63c-1f152319e35e\\\\\\\",\\\\\\\"bank\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"political_status\\\\\\\":1,\\\\\\\"create_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"project_id\\\\\\\":16925,\\\\\\\"role_id\\\\\\\":1,\\\\\\\"profession_id\\\\\\\":39,\\\\\\\"professionName\\\\\\\":\\\\\\\"其它\\\\\\\",\\\\\\\"eventTime\\\\\\\":1703664947386,\\\\\\\"tag\\\\\\\":\\\\\\\"WORKER_MODIFY\\\\\\\",\\\\\\\"sourceApplication\\\\\\\":\\\\\\\"zhuang-application\\\\\\\",\\\\\\\"start_date\\\\\\\":1703664946649,\\\\\\\"is_attendance\\\\\\\":1,\\\\\\\"person_id\\\\\\\":183137,\\\\\\\"workerPhoto\\\\\\\":\\\\\\\"b0e5010c-018f-8a74-d63c-1f152319e35e\\\\\\\",\\\\\\\"data_fingerprint\\\\\\\":\\\\\\\"b0bad2029eafeefdbbb757b1363a227d\\\\\\\",\\\\\\\"census_register\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"company_id\\\\\\\":11864,\\\\\\\"oldWorker\\\\\\\":{\\\\\\\"end_date\\\\\\\":2334758400000,\\\\\\\"gmt_create\\\\\\\":1703061093000,\\\\\\\"birthday\\\\\\\":-113990400000,\\\\\\\"name_pinyin\\\\\\\":\\\\\\\"zhaozhenbo\\\\\\\",\\\\\\\"account_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"education\\\\\\\":2,\\\\\\\"endDate\\\\\\\":2334758400000,\\\\\\\"nation\\\\\\\":\\\\\\\"汉族\\\\\\\",\\\\\\\"train_count\\\\\\\":0,\\\\\\\"id_card\\\\\\\":\\\\\\\"220602196605232415\\\\\\\",\\\\\\\"worker_photo\\\\\\\":\\\\\\\"b0e50046-129f-8a74-d63c-171b663860ac\\\\\\\",\\\\\\\"bank\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"political_status\\\\\\\":1,\\\\\\\"create_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"project_id\\\\\\\":16925,\\\\\\\"role_id\\\\\\\":1,\\\\\\\"profession_id\\\\\\\":39,\\\\\\\"tag\\\\\\\":\\\\\\\"null\\\\\\\",\\\\\\\"start_date\\\\\\\":1703606400000,\\\\\\\"is_attendance\\\\\\\":1,\\\\\\\"person_id\\\\\\\":183137,\\\\\\\"member_id\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"workerPhoto\\\\\\\":\\\\\\\"b0e50046-129f-8a74-d63c-171b663860ac\\\\\\\",\\\\\\\"entry_time\\\\\\\":1703578883000,\\\\\\\"data_fingerprint\\\\\\\":\\\\\\\"8decc3ab4f77b69f9d7a9cb9ffe4c670\\\\\\\",\\\\\\\"census_register\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"company_id\\\\\\\":11864,\\\\\\\"politicalStatus\\\\\\\":1,\\\\\\\"kind\\\\\\\":1,\\\\\\\"sex\\\\\\\":1,\\\\\\\"mobile\\\\\\\":\\\\\\\"18751053988\\\\\\\",\\\\\\\"cooperator_id\\\\\\\":18797,\\\\\\\"timecard\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"worker_id\\\\\\\":369340,\\\\\\\"videoPic\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"native_place\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"group_id\\\\\\\":1100015928,\\\\\\\"modify_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"gmt_modify\\\\\\\":1703621708000,\\\\\\\"name\\\\\\\":\\\\\\\"赵振波\\\\\\\",\\\\\\\"startDate\\\\\\\":1703606400000,\\\\\\\"age\\\\\\\":57,\\\\\\\"status\\\\\\\":1},\\\\\\\"politicalStatus\\\\\\\":1,\\\\\\\"kind\\\\\\\":1,\\\\\\\"sex\\\\\\\":1,\\\\\\\"mobile\\\\\\\":\\\\\\\"18751053988\\\\\\\",\\\\\\\"cooperator_id\\\\\\\":18797,\\\\\\\"timecard\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"worker_id\\\\\\\":369340,\\\\\\\"videoPic\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"qualification_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"group_id\\\\\\\":1100015928,\\\\\\\"employee_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"modify_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"name_acronym\\\\\\\":\\\\\\\"zzb\\\\\\\",\\\\\\\"name\\\\\\\":\\\\\\\"赵振波\\\\\\\",\\\\\\\"nativePlace\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"comment\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"startDate\\\\\\\":1703664946649,\\\\\\\"age\\\\\\\":57,\\\\\\\"status\\\\\\\":1}\\\",\\\"runCount\\\":75,\\\"status\\\":1,\\\"tableName\\\":\\\"s_msg_task_2023_12_27\\\",\\\"taskId\\\":1703664947386769510,\\\"templateId\\\":200,\\\"timeConsuming\\\":0,\\\"userAt\\\":\\\"master\\\"},\\\"msgTaskLog\\\":{\\\"result\\\":\\\"org.apache.dubbo.rpc.RpcException: No provider available from registry 172.16.9.201:2181 for service pms_k8s_test04/cn.pinming.face.recognition.dubbo.dataexchange.api.WorkerChangeListener on consumer 10.244.232.152 use dubbo version 2.7.21, please check status of providers(disabled, not registered or in blacklist).\\\",\\\"resultTime\\\":\\\"2023-12-27T18:40:53.684\\\",\\\"sendTime\\\":\\\"2023-12-27T18:40:53.684\\\",\\\"status\\\":1,\\\"taskId\\\":1703664947386769510,\\\"timeConsuming\\\":0}}\",\n" +
            "        \"receiveTime\": 1703673653684,\n" +
            "        \"tag\": \"zhgd_person_data_exchange_logging\",\n" +
            "        \"tagName\": \"zhgd_person_data_exchange_logging\"\n" +
            "    }";

    String jsonArray = "[{\n" +
            "    \"__SENDER_MS__\": 1703673653684,\n" +
            "    \"payload\": \"{\\\"msgTask\\\":{\\\"bodyMapping\\\":\\\"\\\",\\\"createAt\\\":\\\"2023-12-27T16:15:47\\\",\\\"isQueryForBody\\\":0,\\\"modifyAt\\\":\\\"2023-12-27T18:37:09\\\",\\\"nextFireTime\\\":\\\"2023-12-27T18:40:53\\\",\\\"query\\\":\\\"{\\\\\\\"end_date\\\\\\\":2334758400000,\\\\\\\"birthday\\\\\\\":-113990400000,\\\\\\\"name_pinyin\\\\\\\":\\\\\\\"zhaozhenbo\\\\\\\",\\\\\\\"account_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"education\\\\\\\":2,\\\\\\\"endDate\\\\\\\":2334758400000,\\\\\\\"nation\\\\\\\":\\\\\\\"汉族\\\\\\\",\\\\\\\"train_count\\\\\\\":0,\\\\\\\"id_card\\\\\\\":\\\\\\\"220602196605232415\\\\\\\",\\\\\\\"worker_photo\\\\\\\":\\\\\\\"b0e5010c-018f-8a74-d63c-1f152319e35e\\\\\\\",\\\\\\\"bank\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"political_status\\\\\\\":1,\\\\\\\"create_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"project_id\\\\\\\":16925,\\\\\\\"role_id\\\\\\\":1,\\\\\\\"profession_id\\\\\\\":39,\\\\\\\"professionName\\\\\\\":\\\\\\\"其它\\\\\\\",\\\\\\\"eventTime\\\\\\\":1703664947386,\\\\\\\"tag\\\\\\\":\\\\\\\"WORKER_MODIFY\\\\\\\",\\\\\\\"sourceApplication\\\\\\\":\\\\\\\"zhuang-application\\\\\\\",\\\\\\\"start_date\\\\\\\":1703664946649,\\\\\\\"is_attendance\\\\\\\":1,\\\\\\\"person_id\\\\\\\":183137,\\\\\\\"workerPhoto\\\\\\\":\\\\\\\"b0e5010c-018f-8a74-d63c-1f152319e35e\\\\\\\",\\\\\\\"data_fingerprint\\\\\\\":\\\\\\\"b0bad2029eafeefdbbb757b1363a227d\\\\\\\",\\\\\\\"census_register\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"company_id\\\\\\\":11864,\\\\\\\"oldWorker\\\\\\\":{\\\\\\\"end_date\\\\\\\":2334758400000,\\\\\\\"gmt_create\\\\\\\":1703061093000,\\\\\\\"birthday\\\\\\\":-113990400000,\\\\\\\"name_pinyin\\\\\\\":\\\\\\\"zhaozhenbo\\\\\\\",\\\\\\\"account_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"education\\\\\\\":2,\\\\\\\"endDate\\\\\\\":2334758400000,\\\\\\\"nation\\\\\\\":\\\\\\\"汉族\\\\\\\",\\\\\\\"train_count\\\\\\\":0,\\\\\\\"id_card\\\\\\\":\\\\\\\"220602196605232415\\\\\\\",\\\\\\\"worker_photo\\\\\\\":\\\\\\\"b0e50046-129f-8a74-d63c-171b663860ac\\\\\\\",\\\\\\\"bank\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"political_status\\\\\\\":1,\\\\\\\"create_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"project_id\\\\\\\":16925,\\\\\\\"role_id\\\\\\\":1,\\\\\\\"profession_id\\\\\\\":39,\\\\\\\"tag\\\\\\\":\\\\\\\"null\\\\\\\",\\\\\\\"start_date\\\\\\\":1703606400000,\\\\\\\"is_attendance\\\\\\\":1,\\\\\\\"person_id\\\\\\\":183137,\\\\\\\"member_id\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"workerPhoto\\\\\\\":\\\\\\\"b0e50046-129f-8a74-d63c-171b663860ac\\\\\\\",\\\\\\\"entry_time\\\\\\\":1703578883000,\\\\\\\"data_fingerprint\\\\\\\":\\\\\\\"8decc3ab4f77b69f9d7a9cb9ffe4c670\\\\\\\",\\\\\\\"census_register\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"company_id\\\\\\\":11864,\\\\\\\"politicalStatus\\\\\\\":1,\\\\\\\"kind\\\\\\\":1,\\\\\\\"sex\\\\\\\":1,\\\\\\\"mobile\\\\\\\":\\\\\\\"18751053988\\\\\\\",\\\\\\\"cooperator_id\\\\\\\":18797,\\\\\\\"timecard\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"worker_id\\\\\\\":369340,\\\\\\\"videoPic\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"native_place\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"group_id\\\\\\\":1100015928,\\\\\\\"modify_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"gmt_modify\\\\\\\":1703621708000,\\\\\\\"name\\\\\\\":\\\\\\\"赵振波\\\\\\\",\\\\\\\"startDate\\\\\\\":1703606400000,\\\\\\\"age\\\\\\\":57,\\\\\\\"status\\\\\\\":1},\\\\\\\"politicalStatus\\\\\\\":1,\\\\\\\"kind\\\\\\\":1,\\\\\\\"sex\\\\\\\":1,\\\\\\\"mobile\\\\\\\":\\\\\\\"18751053988\\\\\\\",\\\\\\\"cooperator_id\\\\\\\":18797,\\\\\\\"timecard\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"worker_id\\\\\\\":369340,\\\\\\\"videoPic\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"qualification_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"group_id\\\\\\\":1100015928,\\\\\\\"employee_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"modify_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"name_acronym\\\\\\\":\\\\\\\"zzb\\\\\\\",\\\\\\\"name\\\\\\\":\\\\\\\"赵振波\\\\\\\",\\\\\\\"nativePlace\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"comment\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"startDate\\\\\\\":1703664946649,\\\\\\\"age\\\\\\\":57,\\\\\\\"status\\\\\\\":1}\\\",\\\"runCount\\\":75,\\\"status\\\":1,\\\"tableName\\\":\\\"s_msg_task_2023_12_27\\\",\\\"taskId\\\":1703664947386769510,\\\"templateId\\\":200,\\\"timeConsuming\\\":0,\\\"userAt\\\":\\\"master\\\"},\\\"msgTaskLog\\\":{\\\"result\\\":\\\"org.apache.dubbo.rpc.RpcException: No provider available from registry 172.16.9.201:2181 for service pms_k8s_test04/cn.pinming.face.recognition.dubbo.dataexchange.api.WorkerChangeListener on consumer 10.244.232.152 use dubbo version 2.7.21, please check status of providers(disabled, not registered or in blacklist).\\\",\\\"resultTime\\\":\\\"2023-12-27T18:40:53.684\\\",\\\"sendTime\\\":\\\"2023-12-27T18:40:53.684\\\",\\\"status\\\":1,\\\"taskId\\\":1703664947386769510,\\\"timeConsuming\\\":0}}\",\n" +
            "    \"receiveTime\": 1703673653684,\n" +
            "    \"tag\": \"zhgd_person_data_exchange_logging\",\n" +
            "    \"tagName\": \"zhgd_person_data_exchange_logging\"\n" +
            "},{\n" +
            "    \"__SENDER_MS__\": 1703673653684,\n" +
            "    \"payload\": \"{\\\"msgTask\\\":{\\\"bodyMapping\\\":\\\"\\\",\\\"createAt\\\":\\\"2023-12-27T16:15:47\\\",\\\"isQueryForBody\\\":0,\\\"modifyAt\\\":\\\"2023-12-27T18:37:09\\\",\\\"nextFireTime\\\":\\\"2023-12-27T18:40:53\\\",\\\"query\\\":\\\"{\\\\\\\"end_date\\\\\\\":2334758400000,\\\\\\\"birthday\\\\\\\":-113990400000,\\\\\\\"name_pinyin\\\\\\\":\\\\\\\"zhaozhenbo\\\\\\\",\\\\\\\"account_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"education\\\\\\\":2,\\\\\\\"endDate\\\\\\\":2334758400000,\\\\\\\"nation\\\\\\\":\\\\\\\"汉族\\\\\\\",\\\\\\\"train_count\\\\\\\":0,\\\\\\\"id_card\\\\\\\":\\\\\\\"220602196605232415\\\\\\\",\\\\\\\"worker_photo\\\\\\\":\\\\\\\"b0e5010c-018f-8a74-d63c-1f152319e35e\\\\\\\",\\\\\\\"bank\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"political_status\\\\\\\":1,\\\\\\\"create_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"project_id\\\\\\\":16925,\\\\\\\"role_id\\\\\\\":1,\\\\\\\"profession_id\\\\\\\":39,\\\\\\\"professionName\\\\\\\":\\\\\\\"其它\\\\\\\",\\\\\\\"eventTime\\\\\\\":1703664947386,\\\\\\\"tag\\\\\\\":\\\\\\\"WORKER_MODIFY\\\\\\\",\\\\\\\"sourceApplication\\\\\\\":\\\\\\\"zhuang-application\\\\\\\",\\\\\\\"start_date\\\\\\\":1703664946649,\\\\\\\"is_attendance\\\\\\\":1,\\\\\\\"person_id\\\\\\\":183137,\\\\\\\"workerPhoto\\\\\\\":\\\\\\\"b0e5010c-018f-8a74-d63c-1f152319e35e\\\\\\\",\\\\\\\"data_fingerprint\\\\\\\":\\\\\\\"b0bad2029eafeefdbbb757b1363a227d\\\\\\\",\\\\\\\"census_register\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"company_id\\\\\\\":11864,\\\\\\\"oldWorker\\\\\\\":{\\\\\\\"end_date\\\\\\\":2334758400000,\\\\\\\"gmt_create\\\\\\\":1703061093000,\\\\\\\"birthday\\\\\\\":-113990400000,\\\\\\\"name_pinyin\\\\\\\":\\\\\\\"zhaozhenbo\\\\\\\",\\\\\\\"account_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"education\\\\\\\":2,\\\\\\\"endDate\\\\\\\":2334758400000,\\\\\\\"nation\\\\\\\":\\\\\\\"汉族\\\\\\\",\\\\\\\"train_count\\\\\\\":0,\\\\\\\"id_card\\\\\\\":\\\\\\\"220602196605232415\\\\\\\",\\\\\\\"worker_photo\\\\\\\":\\\\\\\"b0e50046-129f-8a74-d63c-171b663860ac\\\\\\\",\\\\\\\"bank\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"political_status\\\\\\\":1,\\\\\\\"create_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"project_id\\\\\\\":16925,\\\\\\\"role_id\\\\\\\":1,\\\\\\\"profession_id\\\\\\\":39,\\\\\\\"tag\\\\\\\":\\\\\\\"null\\\\\\\",\\\\\\\"start_date\\\\\\\":1703606400000,\\\\\\\"is_attendance\\\\\\\":1,\\\\\\\"person_id\\\\\\\":183137,\\\\\\\"member_id\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"workerPhoto\\\\\\\":\\\\\\\"b0e50046-129f-8a74-d63c-171b663860ac\\\\\\\",\\\\\\\"entry_time\\\\\\\":1703578883000,\\\\\\\"data_fingerprint\\\\\\\":\\\\\\\"8decc3ab4f77b69f9d7a9cb9ffe4c670\\\\\\\",\\\\\\\"census_register\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"company_id\\\\\\\":11864,\\\\\\\"politicalStatus\\\\\\\":1,\\\\\\\"kind\\\\\\\":1,\\\\\\\"sex\\\\\\\":1,\\\\\\\"mobile\\\\\\\":\\\\\\\"18751053988\\\\\\\",\\\\\\\"cooperator_id\\\\\\\":18797,\\\\\\\"timecard\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"worker_id\\\\\\\":369340,\\\\\\\"videoPic\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"native_place\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"group_id\\\\\\\":1100015928,\\\\\\\"modify_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"gmt_modify\\\\\\\":1703621708000,\\\\\\\"name\\\\\\\":\\\\\\\"赵振波\\\\\\\",\\\\\\\"startDate\\\\\\\":1703606400000,\\\\\\\"age\\\\\\\":57,\\\\\\\"status\\\\\\\":1},\\\\\\\"politicalStatus\\\\\\\":1,\\\\\\\"kind\\\\\\\":1,\\\\\\\"sex\\\\\\\":1,\\\\\\\"mobile\\\\\\\":\\\\\\\"18751053988\\\\\\\",\\\\\\\"cooperator_id\\\\\\\":18797,\\\\\\\"timecard\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"worker_id\\\\\\\":369340,\\\\\\\"videoPic\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"qualification_number\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"group_id\\\\\\\":1100015928,\\\\\\\"employee_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"modify_id\\\\\\\":\\\\\\\"2c91808a697fcc5d01697fcc5d250000\\\\\\\",\\\\\\\"name_acronym\\\\\\\":\\\\\\\"zzb\\\\\\\",\\\\\\\"name\\\\\\\":\\\\\\\"赵振波\\\\\\\",\\\\\\\"nativePlace\\\\\\\":\\\\\\\"白山市公安局新建分局\\\\\\\",\\\\\\\"comment\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"startDate\\\\\\\":1703664946649,\\\\\\\"age\\\\\\\":57,\\\\\\\"status\\\\\\\":1}\\\",\\\"runCount\\\":75,\\\"status\\\":1,\\\"tableName\\\":\\\"s_msg_task_2023_12_27\\\",\\\"taskId\\\":1703664947386769510,\\\"templateId\\\":200,\\\"timeConsuming\\\":0,\\\"userAt\\\":\\\"master\\\"},\\\"msgTaskLog\\\":{\\\"result\\\":\\\"org.apache.dubbo.rpc.RpcException: No provider available from registry 172.16.9.201:2181 for service pms_k8s_test04/cn.pinming.face.recognition.dubbo.dataexchange.api.WorkerChangeListener on consumer 10.244.232.152 use dubbo version 2.7.21, please check status of providers(disabled, not registered or in blacklist).\\\",\\\"resultTime\\\":\\\"2023-12-27T18:40:53.684\\\",\\\"sendTime\\\":\\\"2023-12-27T18:40:53.684\\\",\\\"status\\\":1,\\\"taskId\\\":1703664947386769510,\\\"timeConsuming\\\":0}}\",\n" +
            "    \"receiveTime\": 1703673653684,\n" +
            "    \"tag\": \"zhgd_person_data_exchange_logging\",\n" +
            "    \"tagName\": \"zhgd_person_data_exchange_logging\"\n" +
            "}]";

    @Test
    public void parseObjectTest(){
        Map<String, Object> map = Jackson.parseObject(json);
        System.out.println(JSONObject.toJSONString(map, JSONWriter.Feature.PrettyFormat));
    }

    @Test
    public void jacksonObjectTest(){
        JacksonObject json = Jackson.parseObject(this.json);
        Long receiveTime = json.getLong("receiveTime");
        Assert.assertNotNull(receiveTime);
        Assert.assertEquals(Long.parseLong("1703673653684"), (long) receiveTime);
    }

    @Test
    public void parseArrayTest(){
        List<DemoClass> map = Jackson.parseArray(jsonArray, DemoClass.class);
        JacksonArray jacksonObjects = Jackson.parseArray(jsonArray);
        System.out.println(map);
        System.out.println(jacksonObjects);
    }

    @Test
    public void test(){
        Jackson.getObjectMapper().configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

        String text ="{\"taskId\":1704226860547276103,\"templateId\":158,\"status\":2,\"result\":\"{\\\"eventId\\\":571811,\\\"now\\\":\\\"2024-01-03T04:21:01.006368334\\\"}\",\"resultTime\":null,\"sendTime\":null,\"timeConsuming\":6}";
        JacksonObject obj = Jackson.parseObject(text);
        System.out.println(obj.toJSONString());
    }

    @Test
    public void parseArrayTest1() throws JsonProcessingException {
        String jsonArray = "[{\"sn\":\"11767\",\"vPower\":80}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Battery> batteries = objectMapper.readerForListOf(Battery.class).readValue(jsonArray);
        System.out.println(batteries);
    }


    @Data
    public static class DemoClass{
        private String payload;
        private Long receiveTime;
        private String tag;
        private String tagName;
    }


    @Data
    private static class Battery {
        private String sn;
        @JsonProperty("vPower")
        private Integer vPower;
    }
}

