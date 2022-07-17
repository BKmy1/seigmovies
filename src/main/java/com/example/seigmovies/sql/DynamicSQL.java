package com.example.seigmovies.sql;

public class DynamicSQL {

    public String selectTypeAndTime(String type, String time) {
        StringBuffer sql = new StringBuffer("select * from tb_video v where 1=1 ");
        if(type != null && !"".equals(type)){
            sql.append("and locate('").append(type).append("', v.tags) ");
        }
        if (time != null && !"".equals(time)){
            if(!"全部".equals(time)){
                sql.append("and locate('").append(time).append("', v.publishTime) ");
            }
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

//    select t.tags from tb_video t inner join tb_video_type v on locate("", v.type) > 0
    // select * from tb_video v where locate(#{title},v.actor) > 0;
    // select * from tb_video v where locate(#{title},v.name) > 0;
//    public String selectVideoNameAndActor(String param) {
//        StringBuffer sql = new StringBuffer("select * from tb_video v where 1=1 ");
//        if (title != null && !"".equals(title)){
//            sql.append("and v.name like concat(#{title},'%','%')")
//                    .append(" or v.name like concat('%',#{title},'%')")
//                    .append(" or v.name like concat('%','%',#{title})");
//        }
//        if (actor != null && !"".equals(actor)){
//            sql.append("and locate(#{actor},v.actors)");
//        }
//        System.out.println(sql.toString());
//        return sql.toString();
//    }
}
