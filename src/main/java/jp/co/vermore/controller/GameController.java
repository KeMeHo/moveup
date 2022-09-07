package jp.co.vermore.controller;

import jp.co.vermore.common.Constant;
import jp.co.vermore.common.JsonObject;
import jp.co.vermore.common.JsonStatus;
import jp.co.vermore.common.mvc.BaseController;
import jp.co.vermore.common.util.DateUtil;
import jp.co.vermore.entity.*;
import jp.co.vermore.jsonparse.GameDetailJsonParse;
import jp.co.vermore.jsonparse.GameJsonParse;
import jp.co.vermore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GameController
 * Created by wubin.
 * <p>
 * DateTime: 2018/03/03 11:13
 * Copyright: sLab, Corp
 */
@Controller
public class GameController extends BaseController {

    @Autowired
    private GameService gameService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private PicService picService;

    @Autowired
    private WidgetService widgetService;

    @Value(value = "${hosturl}")
    private String hosturl;

    //eg: http://localhost:8081/moveup_war/api/game/list/0/1/0/
    @RequestMapping(value = "/api/game/list/{type}/{limit}/{offset}/", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject getGameList(@PathVariable int type,@PathVariable int limit, @PathVariable int offset) {
        List<Game> list = gameService.getGameAll(type,limit, offset);

        List<Game> countlist = gameService.getGameAllByType(type);
        List<GameJsonParse> ejpList = new ArrayList<>();
        ejpList = list(ejpList, list);
        Map<String,Object> map = new HashMap<>();
        map.put("gameList",ejpList);
        map.put("count",countlist.size());
        jsonObject.setResultList(map);
        return jsonObject;
    }

    //eg:http://localhost:8081/moveup_war/api/game/list/0/1/1/0/
    @RequestMapping(value = "/api/game/list/{type1}/{type2}/{limit}/{offset}/", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject getGameEventList(@PathVariable int type1,@PathVariable int type2,@PathVariable int limit, @PathVariable int offset) {
        List<Game> list = gameService.getGameEventAll(type1,type2,limit, offset);
        List<GameJsonParse> ejpList = new ArrayList<>();
        ejpList = list(ejpList, list);
        Map<String,Object> map = new HashMap<>();
        map.put("gameList",ejpList);
        map.put("count",ejpList.size());
        jsonObject.setResultList(map);
        return jsonObject;
    }

    //eg:http://localhost:8081/moveup_war/api/game/detail/4hIZRgPJFu/
    @RequestMapping(value = "/api/game/detail/{uuid}/", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject getGameDetailList(@PathVariable String uuid) {
        Game game = gameService.getGameByUuid(uuid);
        List<GameDetailJsonParse> ejpList = new ArrayList<>();
        List<GameDetail> list = gameService.getGameDetailAll(game.getId());
        GameDetailJsonParse ejp = new GameDetailJsonParse();
        if(list.size()>0){
            for (GameDetail ed: list) {
                ejp.setGameId(ed.getGameId());
                ejp.setTitle(ed.getTitle());
                ejp.setDate(DateUtil.dateToStringyyyy_MM_dd(ed.getDate()));
                ejp.setTypeStr(widgetService.getGameType(ed.getType()));
                ejp.setType(ed.getType());
                ejp.setColor(widgetService.getGameColor(ed.getType()));
                ejp.setDetail(ed.getDetail());

                Pic topPic = new Pic();
                List<Pic> topPicList = picService.getPic(ed.getGameId(), Constant.EVENT_PIC_TYPE.GAME_TOP);
                List<String> topList = new ArrayList<String>();
                for(Pic pic:topPicList){
                    topList.add(pic.getPicUrl());
                }
                ejp.setTopPic(topList);

                List<Pic> footPicList = picService.getPic(ed.getGameId(),Constant.EVENT_PIC_TYPE.GAME_FOOT);
                List<String> footList = new ArrayList<String>();
                for(Pic pic:footPicList){
                    footList.add(pic.getPicUrl());
                }
                ejp.setFootPic(footList);
                List<Game> listPre = gameService.getGamePre(ed.getDate());
                List<Game> listNext = gameService.getGameNext(ed.getDate());
                List<GameJsonParse> ejpListPre = new ArrayList<>();
                List<GameJsonParse> ejpListNext = new ArrayList<>();
                ejpListPre = list(ejpListPre, listPre);
                ejpListNext = list(ejpListNext, listNext);
                if(listPre.size()>0){
                    ejpListPre.get(0).setColor(widgetService.getGameDetailColor(listPre.get(0).getType()));
                }
                if(listNext.size()>0){
                    ejpListNext.get(0).setColor(widgetService.getGameDetailColor(listNext.get(0).getType()));
                }
                ejp.setGamePre(ejpListPre);
                ejp.setGameNext(ejpListNext);
                ejpList.add(ejp);
            }

            int type =0;
            if(game.getType() == Constant.GAME_TYPE.EVENT){
                type =  Constant.GAME_TYPE.MOVEUP;
            }else if(game.getType() == Constant.GAME_TYPE.MOVEUP){
                type = Constant.GAME_TYPE.EVENT;
            }

            EntryMail entryMail = entryService.getEntryMailByEntryIdAndType(game.getId(),type);
            if(entryMail != null){
                Date startTime = entryMail.getPublishStart();
                Date endTime = entryMail.getPublishEnd();
                Date nowTime = new Date(System.currentTimeMillis());
                if(nowTime.getTime() >= startTime.getTime() && nowTime.getTime() <= endTime.getTime()){
                    ejp.setEntry("1");//応募可能
                }else{
                    ejp.setEntry(null);
                }
            }else {
                ejp.setEntry(null);
            }
            jsonObject.setResultList(ejpList);
        }else{
            jsonObject.setResultList(null);
        }
        return jsonObject;
    }

    private List<GameJsonParse> list(List<GameJsonParse> jpList, List<Game> list) {

        for (Game nd: list) {
            GameJsonParse njp = new GameJsonParse();
            njp.setUuid(nd.getUuid());
            njp.setTitle(nd.getTitle());
            njp.setDate(DateUtil.dateToStringyyyy_MM_dd(nd.getDate()));
            njp.setType(widgetService.getGameType(nd.getType()));
            njp.setColor(widgetService.getGameColor(nd.getType()));
            njp.setExcerpt(nd.getExcerpt());
            jpList.add(njp);
        }
        return jpList;
    }

    // Game detail for sns
    //eg:http://localhost:8081/moveup_war/sns/gameDetail/4hIZRgPJFu/
    @RequestMapping(value = "/sns/gameDetail/{uuid}/", method = RequestMethod.GET)
    public Object getGameSNSDetail(@PathVariable String uuid, Model model, HttpServletRequest hsr) {

        Game game = gameService.getGameByUuid(uuid);
        List<GameDetail> gameDetailList = gameService.getGameDetailAll(game.getId());
        if(gameDetailList.size()>0){
            GameDetail gameDetail = gameDetailList.get(0);

            model.addAttribute("title", gameDetail.getTitle());
            model.addAttribute("url", "https://www.japanmoveupwest.com" + "/gameDetail/" + game.getUuid() + "/");
            model.addAttribute("desc",  game.getExcerpt());
            model.addAttribute("image",  "");
        }
        
        String userAgent = hsr.getHeader("User-Agent");
        logger.debug("-------user-agent=" + userAgent);

        String regex = "facebookexternalhit|Facebot|Twitterbot|Pinterest|Google.*snippet";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(userAgent);
        if (m.find()) {
            logger.debug("-------tosns");
            return "sns";
        } else {
            logger.debug("-------tourl");
            return "redirect:"+ hosturl + "/gameDetail/" + uuid + "/";
        }
    }

    // Game detail for sns
    //eg:http://localhost:8081/moveup_war/api/sns/gameDetail/app/4hIZRgPJFu/
    @RequestMapping(value = "/api/sns/gameDetail/app/{uuid}/", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject getGameDetailSNSForApp(@PathVariable String uuid) {

        Map<String, Object> urlMap = new HashMap<String, Object>();
        urlMap.put("twitter","https://twitter.com/share?url="+hosturl+"/gameDetail/"+uuid+"/");
        urlMap.put("facebook","https://www.facebook.com/sharer/sharer.php?u="+hosturl+"/gameDetail/"+uuid+"/");

        jsonObject.setResultList(urlMap);
        jsonObject.setStatus(JsonStatus.SUCCESS);
        return jsonObject;
    }
}