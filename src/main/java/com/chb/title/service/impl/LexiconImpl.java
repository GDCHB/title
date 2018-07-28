package com.chb.title.service.impl;

import com.chb.title.mapper.LexiconMapper;
import com.chb.title.model.Lexicon;
import com.chb.title.model.LexiconExample;
import com.chb.title.service.LexiconService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LexiconImpl implements LexiconService {

    @Autowired
    LexiconMapper lexiconMapper ;

    @Override
    public int sava(Lexicon lexicon) {
        return lexiconMapper.insert(lexicon);
    }

    @Override
    public Page<Lexicon> get(Lexicon lexicon , int pageNum, int pageSize) {
        LexiconExample lexiconExample = new LexiconExample();
        LexiconExample.Criteria criteria = lexiconExample.createCriteria();
        if(lexicon.getLexiconNum()!=null)
            criteria.andLexiconNumEqualTo(lexicon.getLexiconNum());
        lexiconExample.setOrderByClause("id desc");
        PageHelper.startPage(pageNum,pageSize);
        return lexiconMapper.selectByExample(lexiconExample);

    }

    @Override
    public int saveList(List<Lexicon> lexiconList) {
        int ret = 0;
        for (Lexicon lexicon : lexiconList) {
            ret += lexiconMapper.insert(lexicon);
        }
        return ret;
    }

    @Override
    public int count(Lexicon lexicon) {
        LexiconExample lexiconExample = new LexiconExample();
        LexiconExample.Criteria criteria = lexiconExample.createCriteria();
        if(lexicon.getLexiconNum()!=null)
            criteria.andLexiconNumEqualTo(lexicon.getLexiconNum());
        lexiconExample.setOrderByClause("id desc");
        return new Long(lexiconMapper.countByExample(lexiconExample)).intValue();
    }

    @Override
    public Lexicon getRandomOne(int lexiconNum) {
        return lexiconMapper.selectRandomOned(lexiconNum);
    }

    @Override
    public int del(int id) {
        return lexiconMapper.deleteByPrimaryKey(id);
    }
}
