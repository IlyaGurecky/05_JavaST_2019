package by.guretsky.info_system.runner;

import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.page.PageEnum;
import by.guretsky.info_system.page.PageManager;

import java.sql.SQLException;


public class Runner {
    public static void main(String[] args) throws SQLException {
        JspPage page = PageManager.createPage(PageEnum.HOME);

        page.addParameter("msg", "lol");
        page.addParameter("err", "tr");
        page.addParameter("kek", "ty");

        System.out.println(page.getParameters());
    }

}