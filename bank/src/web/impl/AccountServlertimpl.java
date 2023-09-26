package web.impl;

import service.AccountService;
import service.impl.AccountServiceimpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/transfer")
public class AccountServlertimpl extends HttpServlet {
    AccountService accountService=new AccountServiceimpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取数据
        ServletContext application = request.getServletContext();
        HttpSession session = request.getSession();
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money= Double.parseDouble(request.getParameter("money"));
        //调动业务方法处理业务
        try {
            accountService.transfer(fromActno,toActno,money);
            response.sendRedirect(request.getContextPath()+"/success.jsp");
        }catch (Exception exception){
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }

    }
}
