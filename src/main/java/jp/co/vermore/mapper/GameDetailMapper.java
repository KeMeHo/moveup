package jp.co.vermore.mapper;

import java.util.List;


import jp.co.vermore.entity.GameDetail;


public interface GameDetailMapper {

    int insertDetailGame(GameDetail gameDetail);

    int deleteDetailGame(GameDetail gameDetail);

    int updateDetailGame(GameDetail gameDetail);

    String getGameDetail(long id);

    List<GameDetail> getGameDetailAll(Long game_id);

    GameDetail getStudioGameDetail(Long id);
}