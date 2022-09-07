package jp.co.vermore.controller.admin;

import java.util.ArrayList;
import java.util.List;

import jp.co.vermore.common.Constant;
import jp.co.vermore.common.DatatablesJsonObject;
import jp.co.vermore.entity.*;
import jp.co.vermore.form.admin.GameForm;
import jp.co.vermore.form.admin.GameListForm;
import jp.co.vermore.form.admin.NewsListForm;
import jp.co.vermore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jp.co.vermore.common.mvc.BaseController;
import jp.co.vermore.common.util.DateUtil;
import jp.co.vermore.form.admin.NewsForm;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * GameAdminController
 * Created by wubin.
 * <p>
 * DateTime: 2018/03/03 11:13
 * Copyright: sLab, Corp
 */
@Controller
public class AdminGameController extends BaseController {

    @Autowired
    private GameService gameService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private AWSService awsService;

    @Autowired
    private PicService picService;

    @Autowired
    PlatformTransactionManager txManager;

    @RequestMapping(value = "/admin/game/list/", method = RequestMethod.GET)
    public String gameAll(Model model,HttpServletRequest request) {

        int errorCode = 0;
        if(!request.getSession().isNew()){
            if(request.getSession().getAttribute("error") != null && request.getSession().getAttribute("error") != ""){
                errorCode = (int)request.getSession().getAttribute("error");
                request.getSession().setAttribute("error",0);
            }
        }
        model.addAttribute("errorCode", errorCode);

        List<Game> game = gameService.getGameAll();
        GameForm form = new GameForm();
        model.addAttribute("gameDeleteForm", form);
        model.addAttribute("game_all", game);
        return "admin/gameList";
    }

    @RequestMapping(value = "/admin/game/list/", method = RequestMethod.POST)
    @ResponseBody
    public DatatablesJsonObject gameList(@RequestBody GameListForm form){
        logger.debug("----1----");
        // set order statement
        if(form.getOrder().size() > 0
                && form.getColumns().get(form.getOrder().get(0).getColumn()).getName() != null
                && form.getColumns().get(form.getOrder().get(0).getColumn()).getName().length() > 0){
            form.setOrderStatement(form.getColumns().get(form.getOrder().get(0).getColumn()).getName() + " " + form.getOrder().get(0).getDir());
            logger.debug("----2----order statement="+form.getOrderStatement());
        }else{
            form.setOrderStatement("id");
            logger.debug("----2----order statement="+form.getOrderStatement());
        }
        logger.debug("----3----");

        // query data
        List<Game> dataList = gameService.getGameAllByCondition(form);

        for(Game game:dataList){
            int type =0;
            //it's my faults
            if(game.getType() == Constant.GAME_TYPE.EVENT){
                type =  Constant.GAME_TYPE.MOVEUP;
            }else if(game.getType() == Constant.GAME_TYPE.MOVEUP){
                type = Constant.GAME_TYPE.EVENT;
            }
            EntryMail entity = entryService.getEntryMailByEntryIdAndType( game.getId(),type);
            if(entity != null){
                game.setEntryType(1);
            }else{
                game.setEntryType(0);
            }
        }

        int totalCountFiltered = gameService.getGameCountByCondition(form);
        int totalCount = gameService.getGameCount();
        logger.debug("----4----data count="+dataList.size());
        logger.debug("----5----total filtered="+totalCountFiltered);
        logger.debug("----6----total count="+totalCount);
        logger.debug("----7----page="+form.getDraw());

        // return json data
        DatatablesJsonObject jsonparse = new DatatablesJsonObject();
        jsonparse.setDraw(form.getDraw());
        jsonparse.setRecordsFiltered(totalCountFiltered);
        jsonparse.setRecordsTotal(totalCount);
        jsonparse.setData(dataList);
        logger.debug("----8----");
        return jsonparse;
    }

    @RequestMapping(value = "/admin/game/regist/", method = RequestMethod.GET)
    public String gameInsert(Model model) {
        GameForm form = new GameForm();
        model.addAttribute("gameForm", form);
        return "admin/gameRegist";
    }

    @RequestMapping(value = "/admin/game/regist/", method = RequestMethod.POST)
    public String gameInsert(@ModelAttribute GameForm form ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        // トランザクション管理の開始
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);

        try {
            long gameId = gameService.insertGame(form);
            logger.debug("------------------------"+gameId);
            gameService.insertDetailGame(form,gameId);
            MultipartFile[] top = form.getPicFile1();
            MultipartFile[] foot = form.getPicFile2();

            if(!form.getPicFile1()[0].isEmpty()) {
                Pic topPic = new Pic();
                if (top.length>0) {
                    for(int i = 0 ; i < top.length; i++){
                        topPic.setPicUrl(awsService.postFile(top[i]));
                        topPic.setItemId(gameId);
                        topPic.setItemType(Constant.EVENT_PIC_TYPE.GAME_TOP);
                        picService.insertPic(topPic);
                    }
                }
            }

            if(!form.getPicFile2()[0].isEmpty()) {
                Pic footPic = new Pic();
                if (foot.length>0) {
                    for(int i = 0 ; i < foot.length; i++){
                        footPic.setPicUrl(awsService.postFile(foot[i]));
                        footPic.setItemId(gameId);
                        footPic.setItemType(Constant.EVENT_PIC_TYPE.GAME_FOOT);
                        picService.insertPic(footPic);
                    }
                }
            }

            txManager.commit(txStatus);
            session.setAttribute("error",0);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            session.setAttribute("error",1);
            logger.error("insert game failed!, error=" + e.getMessage());
            logger.error("insert game failed!, error=" + e.toString());
            e.printStackTrace();
        }
        return "redirect:/admin/game/list/";
    }

    @RequestMapping(value = "/admin/game/delete/", method = RequestMethod.POST)
    public String gameDetailDelete(@ModelAttribute GameForm form,HttpServletRequest request) {
        HttpSession session = request.getSession();
        // トランザクション管理の開始
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);

        try {
            gameService.deleteGame(form);
            gameService.deleteDetailGame(form);
            picService.deleteGamePicurl(form.getId(),Constant.EVENT_PIC_TYPE.GAME_TOP);
            picService.deleteGamePicurl(form.getId(),Constant.EVENT_PIC_TYPE.GAME_FOOT);
            txManager.commit(txStatus);
            session.setAttribute("error",0);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            session.setAttribute("error",1);
            logger.error("delete game failed!, error=" + e.getMessage());
            logger.error("delete game failed!, error=" + e.toString());
            e.printStackTrace();
        }
        return "redirect:/admin/game/list/";
    }

    @RequestMapping(value = "/admin/game/edit/{id}/", method = RequestMethod.GET)
    public String gameUpdate(Model model , @PathVariable long id) {
        GameForm gameForm = new GameForm();
        List<Game> list = gameService.getGameList(id);
        String detail = gameService.getGameDetail(id);

        List<Pic> topPicList = picService.getPic(id,Constant.EVENT_PIC_TYPE.GAME_TOP);
        List<String> topList = new ArrayList<String>();
        for(Pic pic:topPicList){
            topList.add(pic.getPicUrl());
        }

        List<Pic> footPicList = picService.getPic(id,Constant.EVENT_PIC_TYPE.GAME_FOOT);
        List<String> footList = new ArrayList<String>();
        for(Pic pic:footPicList){
            footList.add(pic.getPicUrl());
        }

        gameForm.setPicUrl1(topList);
        gameForm.setPicUrl2(footList);

        if(list != null && list.size() > 0){
            gameForm.setId(list.get(0).getId());
            gameForm.setDetail(detail);
            gameForm.setTitle(list.get(0).getTitle());
            gameForm.setType(list.get(0).getType());
            gameForm.setExcerpt(list.get(0).getExcerpt());
            gameForm.setPublishStart(DateUtil.dateToStringyyyy_MM_dd_HH_mm(list.get(0).getPublishStart()));
            gameForm.setPublishEnd(DateUtil.dateToStringyyyy_MM_dd_HH_mm(list.get(0).getPublishEnd()));
            String date = DateUtil.dateToStringyyyy_MM_dd_HH_mm(list.get(0).getDate());
//            gameForm.setDate(date.replace(" ", "T"));
            gameForm.setDate(date);
            gameForm.setSortScore(list.get(0).getSortScore());

            model.addAttribute("gameForm", gameForm);
            return "admin/gameEdit";
        }else {
            return "redirect:/admin/game/list/";
        }
    }

    @RequestMapping(value = "/admin/game/update/", method = RequestMethod.POST)
    public String gameUpdate1(@ModelAttribute GameForm form,HttpServletRequest request) {
        HttpSession session = request.getSession();
        // トランザクション管理の開始
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);

        try {
            List<String> picUrl1 = form.getPicUrl1();
            gameService.updategame(form);
            gameService.updateDetailGame(form);

            if(form.getPicUrl1().size()==0 && form.getPicFile1()[0].isEmpty()){
                picService.deleteGamePicurl(form.getId(),Constant.EVENT_PIC_TYPE.GAME_TOP);
            }

            if(form.getPicUrl2().size()==0 && form.getPicFile2()[0].isEmpty()){
                picService.deleteGamePicurl(form.getId(),Constant.EVENT_PIC_TYPE.GAME_FOOT);
            }

            if(!form.getPicFile1()[0].isEmpty()) {
                picService.deleteGamePicurl(form.getId(),Constant.EVENT_PIC_TYPE.GAME_TOP);
                MultipartFile[] top = form.getPicFile1();
                Pic topPic = new Pic();
                if (top.length>0) {
                    for(int i = 0 ; i < top.length; i++){
                        topPic.setPicUrl(awsService.postFile(top[i]));
                        topPic.setItemId(form.getId());
                        topPic.setItemType(Constant.EVENT_PIC_TYPE.GAME_TOP);
                        picService.insertPic(topPic);
                    }
                }
            }

            if(!form.getPicFile2()[0].isEmpty()){
                picService.deleteGamePicurl(form.getId(),Constant.EVENT_PIC_TYPE.GAME_FOOT);
                MultipartFile[] foot = form.getPicFile2();
                Pic footPic = new Pic();
                if (foot.length>0) {
                    for(int i = 0 ; i < foot.length; i++){
                        footPic.setPicUrl(awsService.postFile(foot[i]));
                        footPic.setItemId(form.getId());
                        footPic.setItemType(Constant.EVENT_PIC_TYPE.GAME_FOOT);
                        picService.insertPic(footPic);
                    }
                }
            }

            txManager.commit(txStatus);
            session.setAttribute("error",0);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            session.setAttribute("error",1);
            logger.error("update game failed!, error=" + e.getMessage());
            logger.error("update game failed!, error=" + e.toString());
            e.printStackTrace();
        }
        return "redirect:/admin/game/list/";
    }
}
