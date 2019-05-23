package win.doyto.i18n.module.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.auth.annotation.CurrentUser;
import win.doyto.common.web.JsonBody;
import win.doyto.i18n.module.locale.LocaleApi;
import win.doyto.i18n.module.locale.LocaleQuery;
import win.doyto.i18n.module.locale.LocaleResponse;

import java.util.List;
import javax.annotation.Resource;

/**
 * ResourceController
 *
 * @author f0rb on 2019-05-22
 */
@Slf4j
@JsonBody
@RestController
@RequestMapping("api/resource")
public class ResourceController {

    @Resource
    LocaleApi localeApi;

    @RequestMapping(value = "{group}/locale", method = RequestMethod.GET)
    public List<LocaleResponse> locale(@CurrentUser String username, LocaleQuery localeQuery, @PathVariable String group) {
        localeQuery.setOwner(username);
        localeQuery.setGroup(group);
        return localeApi.query(localeQuery);
    }

}
