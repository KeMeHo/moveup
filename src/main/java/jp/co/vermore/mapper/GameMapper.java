package jp.co.vermore.mapper;

import java.util.Date;
import java.util.List;

import jp.co.vermore.entity.Game;

import jp.co.vermore.form.admin.GameListForm;

import org.apache.ibatis.annotations.Param;

public interface GameMapper {

    int insertGame(Game game);

    int deleteGame(Game game);

    int updateGame(Game game);

    Game getGameByUuid(String uuid);

    List<Game> getGameAll();

    List<Game> getGameAllForTop(String nowMin, String nextMin);

    List<Game> getGameJsonAll(int type,String nowMin,String nextMin,int limit, int offset);

    List<Game> getGameJsonAllByType(int type,String nowMin,String nextMin);

    Game getGameByIdAndType(long id,int type);

    List<Game> getGameEventAll(int type1,int type2,String tomorrow,String today,int limit, int offset);

    List<Game> getGameList(long id);

    List<Game> getGamePre(Date date, String nowMin,String nextMin);

    List<Game> getGameNext(Date date,String nowMin,String nextMin);

    List<Game> getGameCategory(int type,int limit,int offset);

    List<Game> getGameAllByCondition(GameListForm form);

    int getGameCountByCondition(GameListForm form);

    int getGameCount();

    List<Game> getStudioGameList(int type, int sortScore, String tomorrow,String today);

    List<Game> getStudioGameListAll(Byte type, int limit, int offset);

    List<Game> getStudioGameALL(int type);

    List<Game> getStudioAllByCondition(GameListForm form);

    int getStudioCountByCondition(GameListForm form);

    int getStudioCount();

    Game getGameById(@Param("id") Long id);

}