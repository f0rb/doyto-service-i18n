package win.doyto.i18n.module.locale;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;
import win.doyto.i18n.common.CommonUtils;
import win.doyto.query.entity.EntityAspect;

import java.util.HashMap;

import static win.doyto.i18n.module.i18n.I18nView.GROUP_FORMAT;

/**
 * GroupEntityAspect
 *
 * @author f0rb on 2020-02-09
 */
@Slf4j
@AllArgsConstructor
@Component
public class AddLocaleColumnAfterCreateLocale implements EntityAspect<LocaleEntity> {

    private JdbcOperations jdbcOperations;

    @Override
    public void afterCreate(LocaleEntity localeEntity) {
        String sql = "ALTER TABLE " + GROUP_FORMAT + " ADD locale_${locale} VARCHAR(1000) NOT NULL DEFAULT ''";
        log.info("添加语言列: {}", sql);
        HashMap<String, String> params = new HashMap<>();
        params.put("user", localeEntity.getCreateUserId());
        params.put("group", localeEntity.getGroupName());
        params.put("locale", localeEntity.getLocale());
        jdbcOperations.update(CommonUtils.replaceHolderEx(sql, params));
    }

}
