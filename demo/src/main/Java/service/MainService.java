package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MainDao;
import pojo.Response;
import pojo.User;

public class MainService {

    private static final MainDao dao = new MainDao();

    public String login(User user) {
        Response response = new Response();
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (user == null) {
                response.setCode(1001);
                response.setMsg("账户或密码为空");
                return mapper.writeValueAsString(response);
            }
            if (user.getAccount() == null || user.getAccount().equals("")) {
                response.setCode(1002);
                response.setMsg("账号为空");
                return mapper.writeValueAsString(response);
            }
            if (user.getPassword() == null || user.getPassword().equals("")) {
                response.setCode(1003);
                response.setMsg("密码为空");
                return mapper.writeValueAsString(response);
            }

            User findUser = dao.findUserByAccount(user.getAccount());
            if (findUser == null || findUser.getAccount() == null || findUser.getAccount().equals("")) {
                response.setCode(1004);
                response.setMsg("该用户未注册");
                return mapper.writeValueAsString(response);
            }
            if (!user.getPassword().equals(findUser.getPassword())) {
                response.setCode(1005);
                response.setMsg("密码错误");
                return mapper.writeValueAsString(response);
            }
            response.setCode(0);
            response.setMsg("登录成功");
            return mapper.writeValueAsString(response);

        } catch (Exception e) {
            response.setCode(-1);
            response.setMsg("数据错误");
            try {
                return mapper.writeValueAsString(response);
            } catch (JsonProcessingException jsonE) {}
        }
        return "";
    }
}
