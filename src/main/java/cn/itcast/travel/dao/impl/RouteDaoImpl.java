package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据cid查询总记录数
     *
     * @param cid
     * @return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        if(cid != 0){
            sb.append(" and cid = ?");
            params.add(cid);
        }
        if(rname != null && rname.length() > 0 && !"null".equals(rname)){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");//模糊查询
        }
        sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 根据cid，start，pageSize查询当前页的数据集合
     *
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//        String sql = "select * from tab_route where cid = ? limit ? , ?";
        String sql = "select * from tab_route where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        if(cid != 0){
            sb.append(" and cid = ?");
            params.add(cid);
        }
        if(rname != null && rname.length() > 0 && !"null".equals(rname)){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");//模糊查询
        }
        sb.append(" limit ?, ?");//分页条件
        params.add(start);
        params.add(pageSize);
        sql = sb.toString();
//        System.out.println(sql);
//        System.out.println(params);
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class), params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }
}
