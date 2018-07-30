package com.chb.title.mapper;

import com.chb.title.model.Lexicon;
import com.chb.title.model.LexiconExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface LexiconMapper {
    @SelectProvider(type=LexiconSqlProvider.class, method="countByExample")
    long countByExample(LexiconExample example);

    @DeleteProvider(type=LexiconSqlProvider.class, method="deleteByExample")
    int deleteByExample(LexiconExample example);

    @Delete({
        "delete from lexicon",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into lexicon (id, content, ",
        "lexicon_num)",
        "values (#{id,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, ",
        "#{lexiconNum,jdbcType=INTEGER})"
    })
    int insert(Lexicon record);

    @InsertProvider(type=LexiconSqlProvider.class, method="insertSelective")
    int insertSelective(Lexicon record);

    @SelectProvider(type=LexiconSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="lexicon_num", property="lexiconNum", jdbcType=JdbcType.INTEGER)
    })
    Page<Lexicon> selectByExample(LexiconExample example);

    @SelectProvider(type=LexiconSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="lexicon_num", property="lexiconNum", jdbcType=JdbcType.INTEGER)
    })
    List<Lexicon> selectListByExample(LexiconExample example);

    @Select({
        "select",
        "id, content, lexicon_num",
        "from lexicon",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="lexicon_num", property="lexiconNum", jdbcType=JdbcType.INTEGER)
    })
    Lexicon selectByPrimaryKey(Integer id);

    @UpdateProvider(type=LexiconSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Lexicon record, @Param("example") LexiconExample example);

    @UpdateProvider(type=LexiconSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Lexicon record, @Param("example") LexiconExample example);

    @UpdateProvider(type=LexiconSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Lexicon record);

    @Update({
        "update lexicon",
        "set content = #{content,jdbcType=VARCHAR},",
          "lexicon_num = #{lexiconNum,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Lexicon record);

    @Select({
           " SELECT * FROM ",
            " `lexicon` AS t1",
            " JOIN (",
            " SELECT",
            "ROUND(",
            "RAND() * (",
            " (",
            " SELECT ",
            " MAX(id) ",
            " FROM ",
            "`lexicon` where lexicon_num = #{lexiconNum,jdbcType=INTEGER} ",
            " )-",
            " (",
            " SELECT ",
            " MIN(id) ",
            " FROM ",
            "`lexicon` where lexicon_num = #{lexiconNum,jdbcType=INTEGER} ",
            " )",
            ") + (SELECT MIN(id) FROM `lexicon` where lexicon_num = #{lexiconNum,jdbcType=INTEGER})",
            " ) AS id2 ",
            " ) AS t2 ",
            "WHERE ",
            "t1.id >= t2.id2 ",
            "AND t1.lexicon_num = #{lexiconNum,jdbcType=INTEGER}",
            "ORDER BY",
            "t1.id",
            "LIMIT 1;",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="lexicon_num", property="lexiconNum", jdbcType=JdbcType.INTEGER)
    })
    Lexicon selectRandomOned(Integer lexiconNum);
}