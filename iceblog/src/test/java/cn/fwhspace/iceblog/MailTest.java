package cn.fwhspace.iceblog;

import cn.fwhspace.iceblog.businessmodule.article.dao.ArticleDao;
import cn.fwhspace.iceblog.businessmodule.search.dao.SearchDao;
import cn.fwhspace.iceblog.common.utils.JwtUtils;
import cn.fwhspace.iceblog.common.utils.StringUtils;
import cn.fwhspace.iceblog.pojo.articles.Article;
import cn.fwhspace.iceblog.tripartite.mail.AliMailUtils;
import cn.fwhspace.iceblog.tripartite.qiniu.QiniuUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@Slf4j(topic = "requestLog")
@SpringBootTest
@RunWith(value = SpringRunner.class)
public class MailTest {

    @Autowired
    private AliMailUtils aliMailUtils;
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void sendMail(){
        aliMailUtils.simple("876811855@qq.com","付文豪","测试邮件服务,当前发送时间"+new Date());
    }

    @Test
    public void readHtml(){
        System.out.println(aliMailUtils.loadHtml("static/mail.html"));
    }

    @Test
    public void selectCount(){
        Integer localVisitorCount = 0;

        List<Article> articleIds = articleDao.selectList(new QueryWrapper<Article>().select("v_count").eq("author","zjh"));
        for (int i = 0; i < articleIds.size(); i++) {
            localVisitorCount += articleIds.get(i).getVCount();
        }
        System.out.println(localVisitorCount);
    }

}
