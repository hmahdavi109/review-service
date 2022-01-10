package mahdavi.example.review.configs;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class SmartLocaleResolver extends AcceptHeaderLocaleResolver {

    @Value("${configuration.defaultLocale.language}")
    private String lang;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale;
        if (StringUtils.isEmpty(request.getHeader("Accept-Language"))) {
            locale = new Locale(lang);
            this.setDefaultLocale(locale);
            return locale;
        }
        locale = new Locale(request.getHeader("Accept-Language"));
        this.setDefaultLocale(locale);
        return locale;
    }


}