package cn.fwhspace.iceblog.tripartite.mail;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Slf4j(topic = "requestLog")
@Component
public class AliMailUtils {

    /**
     * 初始化信息
     */
    private final static String REGION = "cn-hangzhou";
    private final static String AccessKeyID = "your AccessKeyID";
    private final static String AccessKeySecret = "your AccessKeySecret";
    private final static String SendMailAddress = "Mail Address";
    private final static String Subject = "用户账号激活";
    private final static String Tag_Auth = "Auth";
    private final static String FromAlias = "IceBlog官方";

    //初始化Client
    IClientProfile iClientProfile = DefaultProfile.getProfile(REGION,AccessKeyID,AccessKeySecret);
    IAcsClient client = new DefaultAcsClient(iClientProfile);

    //建立邮件发送详情
    SingleSendMailRequest mailRequest = new SingleSendMailRequest();
    SingleSendMailResponse mailResponse;
    public void simple(String userMailAddress,String userName,String activationAddress){
        //使用https加密链接
        mailRequest.setProtocol(com.aliyuncs.http.ProtocolType.HTTPS);
        //发信地址
        mailRequest.setAccountName(SendMailAddress);
        //1：为发信地址   0：为随机账号
        mailRequest.setAddressType(1);
        //发信人
        mailRequest.setFromAlias(FromAlias);
        //邮件主题
        mailRequest.setSubject(Subject);
        //发送人地址
        mailRequest.setToAddress(userMailAddress);
        //标签
        mailRequest.setTagName(Tag_Auth);
        //是否启用管理控制台中配置好回信地址（状态须验证通过），取值范围是字符串true或者false（不是bool值）。
        mailRequest.setReplyToAddress(false);

        //邮件正文
        String htmlContent =  loadHtml("static/mail.html")
                .replace("[username]",userName)
                .replace("[Subject]",Subject)
                .replace("[userAuthUrl]",activationAddress);
        mailRequest.setHtmlBody(htmlContent);
        log.info("---------------邮件初始化成功---------------");
        try {
            mailResponse = client.getAcsResponse(mailRequest);
            log.info("--------------邮件发送成功---------------");
        } catch (ClientException e) {
            e.printStackTrace();
            log.info("--------------邮件发送异常，请核验---------------");
        }
    }

    /**
     * 读取html内容
     * @param path  静态资源路径
     * @return
     */
    public String loadHtml(String path){
        String html = "";
        ClassPathResource resource = new ClassPathResource(path);
        try {
            Scanner scanner = new Scanner(resource.getInputStream());
            while (scanner.hasNextLine()){
                html += scanner.nextLine() + System.getProperty("line.separator");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;

    }

}
