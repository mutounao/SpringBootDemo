package cn.mutounao.demo.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

public class AppErrorPageRegistrar implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/system/400");
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/system/500");

        errorPageRegistry.addErrorPages(page404, page500);
    }
}