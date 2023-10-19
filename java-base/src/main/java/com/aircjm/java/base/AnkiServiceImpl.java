package com.aircjm.java.base;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.java.base.anki.anki.vo.Note;
import com.aircjm.java.base.anki.ankiconnect.AnkiVo;
import com.aircjm.java.base.anki.response.AnkiRespVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author aircjm
 * <a href="https://foosoft.net/projects/anki-connect/">anki-connect</a>
 */
@Service
@Slf4j
public class AnkiServiceImpl {

    public AnkiRespVo postAnki(AnkiVo ankiVo) {
//        HttpHeaders headers = new HttpHeaders();
//        String ankiUrl = "";
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
        // todo
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        log.info("调用anki接口入参为:{}", JSON.toJSONString(ankiVo));
//        JSONObject jsonObj = JSONObject.parseObject(JSONObject.toJSONString(ankiVo));
//        HttpEntity<String> formEntity = new HttpEntity<>(jsonObj.toString(), headers);
//        String result = restTemplate.postForObject(ankiUrl, formEntity, String.class);
//        log.info("调用anki接口回参为:{}", JSON.toJSONString(result));
//        return JSON.parseObject(result, AnkiRespVo.class);
        return null;
    }

    public static void main(String[] args) {

        Note note = Note.convert("hello", "world", Lists.newArrayList());
        AnkiVo ankiVo = new AnkiVo();
        ankiVo.setAction("addNote");
        Map<String, Object> params = Maps.newHashMap();
        params.put("note", note);
        ankiVo.setParams(params);
        String version = HttpUtil.post("http://localhost:8765", "{\"action\": \"deckNames\", \"version\": 5}");
        System.out.println(version);
        System.out.println(JSONUtil.toJsonStr(ankiVo));
        String post = HttpUtil.post("http://localhost:8765",JSONUtil.toJsonStr(ankiVo));
//        String post = HttpUtil.post("http://localhost:8765","{\"action\":\"addNote\",\"version\":5,\"params\":{\"note\":{\"deckName\":\"Default\",\"modelName\":\"Basic\",\"fields\":{\"Front\":\"front content\",\"Back\":\"back content\"},\"tags\":[\"yomichan\"],\"audio\":{\"url\":\"https://assets.languagepod101.com/dictionary/japanese/audiomp3.php?kanji=猫&kana=ねこ\",\"filename\":\"yomichan_ねこ_猫.mp3\",\"skipHash\":\"7e2c2f954ef6051373ba916f000168dc\",\"fields\":\"Front\"}}}}");
        System.out.println(post);
    }


}
