package jp.co.vermore.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jp.co.vermore.common.util.StringUtil;
import jp.co.vermore.entity.Game;
import jp.co.vermore.entity.GameDetail;
import jp.co.vermore.form.admin.GameForm;
import jp.co.vermore.form.admin.GameListForm;
import jp.co.vermore.mapper.GameDetailMapper;
import jp.co.vermore.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jp.co.vermore.common.Constant;
import jp.co.vermore.common.util.DateUtil;


/**
 * GameService
 *
 * <p>
 * DateTime: 2018/03/03 11:13
 * Copyright: sLab, Corp
 */

@Service

public class GameService {

    @Autowired
    private GameMapper gameMapper;

    public Game getGameByUuid(String uuid) {
        Game entity = gameMapper.getGameByUuid(uuid);
        return entity;
    }

    public List<Game> getGameAll() {
        List<Game> gameList = gameMapper.getGameAll();
        return gameList;
    }

    public List<Game> getGameAllForTop() {
        String nowMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
//        String now= DateUtil.dateToStringyyyy_MM_dd_HH_mm(new Date(System.currentTimeMillis()));
        List<Game> gameList = gameMapper.getGameAllForTop(nowMin,nextMin);
        return gameList;
    }

    public List<Game> getGameCategory(int type,int limit,int offset) {
        List<Game> gameList = gameMapper.getGameCategory(type,limit,offset);
        return gameList;
    }

    public List<Game>getGamePre(Date date) {
        String nowMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
        List<Game> game = gameMapper.getGamePre(date,nowMin,nextMin);
        return game;
    }

    public List<Game> getGameNext(Date date) {
        String nowMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
        List<Game> game = gameMapper.getGameNext(date,nowMin,nextMin);
        return game;
    }

    public List<Game> getGameAll(int type,int limit,int offset) {
        String nowMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
        List<Game> gameList = gameMapper.getGameJsonAll(type,nowMin,nextMin,limit, offset);
        return gameList;
    }

    public List<Game> getGameAllByType(int type) {
        String nowMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin= DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
        List<Game> gameList = gameMapper.getGameJsonAllByType(type,nowMin,nextMin);
        return gameList;
    }

    public Game getGameByIdAndType(long id,int type) {
        Game game = gameMapper.getGameByIdAndType(id,type);
        return game;
    }

    public List<Game> getGameEventAll(int type1,int type2,int limit,int offset) {
        String today = DateUtil.getYyyyMMdd();
        String tomorrow = DateUtil.getTomorrow();
        List<Game> gameList = gameMapper.getGameEventAll(type1,type2,tomorrow,today,limit, offset);
        return gameList;
    }

    private List<Game> convertTo(List<Game> demoList) {
        List<Game> resultList = new LinkedList<Game>();
        for (Game entity : demoList) {
            resultList.add(entity);
        }
        return resultList;
    }

    @Autowired
    private GameMapper addGameMapper;

    public long insertGame(GameForm gameForm) {
        Game game = new Game();
        String uuid = "";
        int flagUuid = 0;
        int cntSelect = 0;
        while (flagUuid != 1 && cntSelect < 100){
            uuid = StringUtil.getUuid();
            if (getGameByUuid(uuid) == null){
                flagUuid = 1;
            }
            cntSelect++;
        }

        game.setUuid(uuid);
        String date = gameForm.getDate();
        game.setDate(DateUtil.stringToDateyyyy_MM_dd_HH_mm(date.replace("T"," ")));
        game.setTitle(gameForm.getTitle());
        game.setType(gameForm.getType());
        game.setSortScore(gameForm.getSortScore());
        game.setExcerpt(gameForm.getExcerpt());
        if(gameForm.getPublishStart() == null || "".equals(gameForm.getPublishStart())){
            game.setPublishStart(DateUtil.getDefaultDate());
        }else{
            game.setPublishStart(DateUtil.stringToDateyyyy_MM_dd_HH_mm(gameForm.getPublishStart().replace("T"," ")));
        }
        if(gameForm.getPublishEnd() == null || "".equals(gameForm.getPublishEnd())){
            game.setPublishEnd(DateUtil.getDefaultPublishEnd());
        }else{
            game.setPublishEnd(DateUtil.stringToDateyyyy_MM_dd_HH_mm(gameForm.getPublishEnd().replace("T"," ")));
        }
        game.setCreateDatetime(new Date(System.currentTimeMillis()));
        game.setDelFlg(Boolean.FALSE);
        game.setNote(Constant.EMPTY_STRING);
        addGameMapper.insertGame(game);
        return game.getId();
    }

    public long insertStudioGame(GameForm gameForm) {
       Game game = new Game();
        String uuid = "";
        int flagUuid = 0;
        int cntSelect = 0;
        while (flagUuid != 1 && cntSelect < 100){
            uuid = StringUtil.getUuid();
            if (getGameByUuid(uuid) == null){
                flagUuid = 1;
            }
            cntSelect++;
        }

        game.setUuid(uuid);
        String date = gameForm.getDate();
        game.setDate(DateUtil.stringToDateyyyy_MM_dd(date));
        game.setTitle(gameForm.getTitle());
        game.setType(gameForm.getType());
        game.setSortScore(gameForm.getSortScore());
        game.setExcerpt(gameForm.getExcerpt());
        if(gameForm.getPublishStart() == null || "".equals(gameForm.getPublishStart())){
            game.setPublishStart(DateUtil.getDefaultDate());
        }else{
            game.setPublishStart(DateUtil.stringToDateyyyy_MM_dd(gameForm.getPublishStart()));
        }
        if(gameForm.getPublishEnd() == null || "".equals(gameForm.getPublishEnd())){
            game.setPublishEnd(DateUtil.getDefaultPublishEnd());
        }else{
            game.setPublishEnd(DateUtil.stringToDateyyyy_MM_dd(gameForm.getPublishEnd()));
        }
        game.setCreateDatetime(new Date(System.currentTimeMillis()));
        game.setDelFlg(Boolean.FALSE);
        game.setNote(Constant.EMPTY_STRING);
        addGameMapper.insertGame(game);
        return game.getId();
    }

    @Autowired
    private GameDetailMapper gameDetailMapper;

    public long insertDetailGame(GameForm gameForm,long gameId) {
        GameDetail gameDetail = new GameDetail();
        gameDetail.setGameId(gameId);
        String date = gameForm.getDate();
        gameDetail.setDate(DateUtil.stringToDateyyyy_MM_dd_HH_mm(date.replace("T"," ")));
        gameDetail.setTitle(gameForm.getTitle());
        gameDetail.setType(gameForm.getType());
        gameDetail.setDetail(gameForm.getDetail());
        gameDetail.setCreateDatetime(new Date(System.currentTimeMillis()));
        gameDetail.setDelFlg(Boolean.FALSE);
        gameDetail.setNote(Constant.EMPTY_STRING);
        gameDetailMapper.insertDetailGame(gameDetail);
        return gameDetail.getId();
    }

    public long insertDetailStudioGame(GameForm gameForm,long gameId) {
        GameDetail gameDetail = new GameDetail();
        gameDetail.setGameId(gameId);
        String date = gameForm.getDate();
        gameDetail.setDate(DateUtil.stringToDateyyyy_MM_dd(date));
        gameDetail.setTitle(gameForm.getTitle());
        gameDetail.setType(gameForm.getType());
        gameDetail.setDetail(gameForm.getDetail());
        gameDetail.setCreateDatetime(new Date(System.currentTimeMillis()));
        gameDetail.setDelFlg(Boolean.FALSE);
        gameDetail.setNote(Constant.EMPTY_STRING);
        gameDetailMapper.insertDetailGame(gameDetail);
        return gameDetail.getId();
    }

    public int deleteGame(GameForm gameForm) {
        Game game = new Game();
        game.setId(gameForm.getId());
        game.setDelFlg(Boolean.TRUE);
        int count = gameMapper.deleteGame(game);
        System.out.println(count);
        return count;
    }

    public int deleteDetailGame(GameForm gameForm) {
        GameDetail gameDetail = new GameDetail();
        gameDetail.setGameId(gameForm.getId());
        gameDetail.setDelFlg(Boolean.TRUE);
        int count = gameDetailMapper.deleteDetailGame(gameDetail);
        return count;
    }

    public int updateGame(GameForm gameForm) {
        Game game = new Game();
        game.setId(gameForm.getId());
        String date = gameForm.getDate();
        game.setDate(DateUtil.stringToDateyyyy_MM_dd_HH_mm(date.replace("T"," ")));
        game.setTitle(gameForm.getTitle());
        game.setType(gameForm.getType());
        game.setSortScore(gameForm.getSortScore());
        game.setExcerpt(gameForm.getExcerpt());
        if(gameForm.getPublishStart() == null || "".equals(gameForm.getPublishStart())){
            game.setPublishStart(DateUtil.getDefaultDate());
        }else{
            game.setPublishStart(DateUtil.stringToDateyyyy_MM_dd_HH_mm(gameForm.getPublishStart().replace("T"," ")));
        }
        if(gameForm.getPublishEnd() == null || "".equals(gameForm.getPublishEnd())){
            game.setPublishEnd(DateUtil.getDefaultPublishEnd());
        }else{
            game.setPublishEnd(DateUtil.stringToDateyyyy_MM_dd_HH_mm(gameForm.getPublishEnd().replace("T"," ")));
        }
        game.setUpdateDatetime(new Date(System.currentTimeMillis()));
        game.setDelFlg(Boolean.FALSE);
        game.setNote(Constant.EMPTY_STRING);
        int count = gameMapper.updateGame(game);
        return count;
    }

    public int updateStudioGame(GameForm gameForm) {
        Game game= new Game();
        game.setId(gameForm.getId());
        String date = gameForm.getDate();
        game.setDate(DateUtil.stringToDateyyyy_MM_dd(date));
        game.setTitle(gameForm.getTitle());
        game.setType(gameForm.getType());
        game.setSortScore(gameForm.getSortScore());
        game.setExcerpt(gameForm.getExcerpt());
        if(gameForm.getPublishStart() == null || "".equals(gameForm.getPublishStart())){
            game.setPublishStart(DateUtil.getDefaultDate());
        }else{
            game.setPublishStart(DateUtil.stringToDateyyyy_MM_dd(gameForm.getPublishStart()));
        }
        if(gameForm.getPublishEnd() == null || "".equals(gameForm.getPublishEnd())){
            game.setPublishEnd(DateUtil.getDefaultPublishEnd());
        }else{
            game.setPublishEnd(DateUtil.stringToDateyyyy_MM_dd(gameForm.getPublishEnd()));
        }
        game.setUpdateDatetime(new Date(System.currentTimeMillis()));
        game.setDelFlg(Boolean.FALSE);
        game.setNote(Constant.EMPTY_STRING);
        int count = gameMapper.updateGame(game);
        return count;
    }

    public int updateDetailGame(GameForm gameForm) {
        GameDetail gameDetail = new GameDetail();
        gameDetail.setGameId(gameForm.getId());
        String date = gameForm.getDate();
        gameDetail.setDate(DateUtil.stringToDateyyyy_MM_dd_HH_mm(date.replace("T"," ")));
        gameDetail.setTitle(gameForm.getTitle());
        gameDetail.setType(gameForm.getType());
        gameDetail.setDetail(gameForm.getDetail());
        gameDetail.setUpdateDatetime(new Date(System.currentTimeMillis()));
        gameDetail.setDelFlg(Boolean.FALSE);
        gameDetail.setNote(Constant.EMPTY_STRING);
        int count = gameDetailMapper.updateDetailGame(gameDetail);
        return count;
    }

    public int updateDetailStudioGame(GameForm gameForm) {
        GameDetail gameDetail = new GameDetail();
        gameDetail.setGameId(gameForm.getId());
        String date = gameForm.getDate();
        gameDetail.setDate(DateUtil.stringToDateyyyy_MM_dd(date));
        gameDetail.setTitle(gameForm.getTitle());
        gameDetail.setType(gameForm.getType());
        gameDetail.setDetail(gameForm.getDetail());
        gameDetail.setUpdateDatetime(new Date(System.currentTimeMillis()));
        gameDetail.setDelFlg(Boolean.FALSE);
        gameDetail.setNote(Constant.EMPTY_STRING);
        int count = gameDetailMapper.updateDetailGame(gameDetail);
        return count;
    }

    public List<Game> getGameList(long id) {
        List<Game> gameList = gameMapper.getGameList(id);
        return gameList;
    }

    public String getGameDetail(long id) {
        String detail = gameDetailMapper.getGameDetail(id);
        return detail;
    }

    public List<GameDetail> getGameDetailAll(Long id) {
        List<GameDetail> gameDetail = gameDetailMapper.getGameDetailAll(id);
        List<GameDetail> resultList = convertToDetail(gameDetail);
        return resultList;
    }

    private List<GameDetail> convertToDetail(List<GameDetail> demoList) {
        List<GameDetail> resultList = new LinkedList<GameDetail>();
        for (GameDetail entity : demoList) {
            resultList.add(entity);
        }
        return resultList;
    }

    public List<Game> getGameAllByCondition(GameListForm form) {
        List<Game> game = gameMapper.getGameAllByCondition(form);
        return game;
    }

    public int getGameCountByCondition(GameListForm form) {
        return gameMapper.getGameCountByCondition(form);
    }

    public int getGameCount() {
        return gameMapper.getGameCount();
    }

    public List<Game> getStudioGameALL(int type) {
        List<Game> game = gameMapper.getStudioGameALL(type);
        return game;
    }


    public void updategame(GameForm form) {
    }
}