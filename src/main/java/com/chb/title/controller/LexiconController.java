package com.chb.title.controller;

import com.chb.title.base.BaseException;
import com.chb.title.base.JSONResult;
import com.chb.title.model.Lexicon;
import com.chb.title.service.LexiconService;
import com.chb.title.util.Utils;
import com.github.pagehelper.Page;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value="/")
public class LexiconController {

    private final static Logger logger = LoggerFactory.getLogger(LexiconController.class);

    Gson gson = new Gson();
    @Autowired
    LexiconService lexiconService ;

    @RequestMapping(value = "test")
    public String test(){
        logger.debug("******************************");
        return "welcome to lexicon by chb" ;
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "index")
    public ModelAndView index(){
        return new ModelAndView("index.html");
    }


    /**
     * 保存词库内容
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws BaseException
     */
    @RequestMapping(value = "save")
    public ModelAndView save(HttpServletRequest request , HttpServletResponse response) throws IOException, BaseException {
        request.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin","*");
        String contents = request.getParameter("contents");
        String lexiconNum = request.getParameter("lexiconNum");
        if(Utils.isBlank(contents)){
            throw new BaseException("0","[contents]参数为空");
        }
        if(Utils.isBlank(lexiconNum)){
            throw new BaseException("0","[lexiconNum]参数为空");
        }
        JSONResult jsonResult = new JSONResult();
        List<Lexicon> lexiconList = new ArrayList<>();
        String[] contentList;
        if (Utils.indexOf(contents,"，")>-1) {
            contentList = Utils.split(contents,"，");
        }else {
            contentList = Utils.split(contents,",");
        }

        for (String content : contentList) {
            Lexicon lexicon = new Lexicon();
            lexicon.setContent(content);
            lexicon.setLexiconNum(Integer.parseInt(lexiconNum));
            lexiconList.add(lexicon);
        }
        int ret = 0;
        try{
            ret = lexiconService.saveList(lexiconList);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new BaseException("0","sql执行异常"+e.getMessage());
        }

        if(ret==0) {
            jsonResult.setFlag("0");
            jsonResult.setMsg("保存错误");
        }
        response.getWriter().write(gson.toJson(jsonResult));
        return null;
    }

    /**
     * 获取列表
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws BaseException
     */
    @RequestMapping(value = "get")
    public ModelAndView get(HttpServletRequest request , HttpServletResponse response) throws IOException, BaseException {
        request.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin","*");
        String lexiconNum = request.getParameter("lexiconNum");
        Lexicon lexicon = new Lexicon();
        JSONResult jsonResult = new JSONResult();
        int pageNum = 1;
        int pageSize = 15;
        if(Utils.isNotBlank(request.getParameter("pageNum"))){
            pageNum = Utils.toInt(request.getParameter("pageNum"));
        }
        if(Utils.isNotBlank(request.getParameter("pageSize"))){
            pageSize = Utils.toInt(request.getParameter("pageSize"));
        }
        if(Utils.isNotBlank(lexiconNum)){
            if(!"0".equals(lexiconNum))
                lexicon.setLexiconNum(Utils.toInt(lexiconNum));
        }
        Page<Lexicon> lexiconList = new Page<>();
        try {
            lexiconList = lexiconService.get(lexicon,pageNum,pageSize);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new BaseException("0","sql执行异常"+e.getMessage());
        }
        jsonResult.setResult(lexiconList.toPageInfo());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonResult));
        return null;
    }

    /**
     * 删除
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws BaseException
     */
    @RequestMapping(value = "del")
    public ModelAndView del(HttpServletRequest request , HttpServletResponse response) throws IOException, BaseException {
        request.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin","*");
        int id = 0;
        int ret;
        JSONResult jsonResult = new JSONResult();
        if(Utils.isNotBlank(request.getParameter("id"))){
            id = Utils.toInt(request.getParameter("id"));
        }else {
            throw new BaseException("0","参数[id]不能为空");
        }
        try {
            ret = lexiconService.del(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new BaseException("0","sql执行异常"+e.getMessage());
        }
        if(ret==0){
            jsonResult.setFlag("0");
            jsonResult.setMsg("数据没有改记录");
        }else {
            jsonResult.setMsg("删除成功");
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonResult));
        return null;
    }

    /**
     * 生成标题
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws BaseException
     */
    @RequestMapping(value = "buildTitle")
    public ModelAndView buildTitle(HttpServletRequest request , HttpServletResponse response) throws IOException, BaseException {
        request.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.setCharacterEncoding("UTF-8");
        List<String> lexiconList = new ArrayList<>();
        String title = "";
        JSONResult jsonResult = new JSONResult();
        for(int i=1;i<=25;i++){
            Lexicon lexicon = new Lexicon();
            lexicon.setLexiconNum(i);
            int count = lexiconService.count(lexicon);
            if(count>0){
                List<Lexicon> lexiconList1 = lexiconService.getList(lexicon);
                Lexicon lexicon1 = getRandomOne(lexiconList,lexiconList1,10);
                logger.debug(gson.toJson(lexicon1));
                if(lexicon1!=null)
                    lexiconList.add(lexicon1.getContent());
                title = Utils.join(lexiconList,"");
                if(title.length()>50){
                    lexiconList.remove(lexiconList.size()-1);
                    title = Utils.join(lexiconList,"");
                    break;
                }
            }
        }
        jsonResult.setResult(title);
        response.getWriter().write(gson.toJson(jsonResult));
        return null;
    }


    /**
     * 生成标题时，如果以有改内容，递归查找下一个，10次还是找不到就返回
     * @param lexiconList
     * @param resultList
     * @param times
     * @return
     */
    private Lexicon getRandomOne(List<String> lexiconList,List<Lexicon> resultList,int times){
        if(times == 0){
            return null;
        }
        times--;
        Random random = new Random();
        int size = resultList.size();
        int ran = random.nextInt(size);
        Lexicon lexicon =  resultList.get(ran);
        if(lexiconList.contains(lexicon.getContent())){
            return getRandomOne(lexiconList,resultList,times);
        }else {
            return lexicon;
        }
    }



}
