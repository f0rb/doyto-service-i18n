package win.doyto.i18n.service;

import java.util.List;
import java.util.Map;

import win.doyto.i18n.model.I18n;
import win.doyto.i18n.model.Lang;
import win.doyto.web.RestException;

/**
 * I18nService
 *
 * @author f0rb on 2017-03-31.
 */
public interface I18nService {

    List query(String group);

    List query(I18n i18n);

    List<Lang> query(String group, String locale);

    List<Lang> queryWithDefaults(String group, String locale);

    void checkGroup(String group) throws RestException;

    void checkGroupAndLocale(String group, String locale) throws RestException;

    String addLocaleOnGroup(String group, String locale);

    void saveTranslation(String group, String locale, Map<String, String> translationMap);

    void autoTranslate(String group, String locale);
}
