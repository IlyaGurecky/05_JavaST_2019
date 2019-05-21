package by.guretsky.info_system.command;

import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.page.JspPage;
import by.guretsky.info_system.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

public abstract class Command {
    private static final String SPLIT_DIR_REGEX = "/";
    protected ServiceFactory factory;

    public abstract JspPage execute(final HttpServletRequest request) throws
            CustomException;

    public void setFactory(final ServiceFactory serviceFactory) {
        this.factory = serviceFactory;
    }

    protected String uploadImage(HttpServletRequest request)
            throws IOException, ServletException {
        Part part = request.getPart("filmImage");
        InputStream fileContent = part.getInputStream();
        String imageName =
                Paths.get(part.getSubmittedFileName()).getFileName()
                        .toString();
        if (imageName == null || imageName.isEmpty()) {
            return null;
        }
        String realPath = request.getServletContext()
                .getRealPath("");
        String[] dirs = realPath.split(SPLIT_DIR_REGEX);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < dirs.length - 2; i++) {
            buffer.append(dirs[i]);
            buffer.append("/");
        }
        String pathToSave = buffer + "src/main/webapp/img/films/"
                + imageName;
        OutputStream outputStream = new FileOutputStream(pathToSave);
        fileContent.transferTo(outputStream);
        return imageName;
    }
}
