package jp.co.vermore.jsonparse;

import jp.co.vermore.common.mvc.BaseJsonParse;
import jp.co.vermore.entity.Game;

import java.util.List;

/**
 * GameDetailJsonParse
 * Created by wubin.
 *
 * DateTime: 2018/03/13 16:52
 * Copyright: sLab, Corp
 */

public class GameDetailJsonParse extends BaseJsonParse {

    private Long gameId;

    private String entry;

    private String date;

    private String typeStr;

    private int type;

    private String color;

    private String title;

    private String detail;

    private List<String> topPic;

    private List<String> footPic;

    private List<GameJsonParse> gamePre;

    private List<GameJsonParse> gameNext;

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<GameJsonParse> getGamePre() {
        return gamePre;
    }

    public void setGamePre(List<GameJsonParse> gamePre) {
        this.gamePre = gamePre;
    }

    public List<GameJsonParse> getGameNext() {
        return gameNext;
    }

    public void setGameNext(List<GameJsonParse> gameNext) {
        this.gameNext = gameNext;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Long getgameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public List<String> getTopPic() {
        return topPic;
    }

    public void setTopPic(List<String> topPic) {
        this.topPic = topPic;
    }

    public List<String> getFootPic() {
        return footPic;
    }

    public void setFootPic(List<String> footPic) {
        this.footPic = footPic;
    }
}
