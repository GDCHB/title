package com.chb.title.service;

import com.chb.title.model.Lexicon;
import com.github.pagehelper.Page;

import java.util.List;

public interface LexiconService {
    int sava(Lexicon lexicon);
    Page<Lexicon> get(Lexicon lexicon, int pageNum, int pageSize);
    int saveList(List<Lexicon> lexiconList);
    int count(Lexicon lexicon);
    int del(int id);
    Lexicon getRandomOne(int lexiconNum);
    List<Lexicon> getList(Lexicon lexicon);
}
