package win.doyto.i18n.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import win.doyto.i18n.module.group.ResourceGroup;
import win.doyto.i18n.module.localle.ResourceLocale;
import win.doyto.i18n.module.group.ResourceGroupService;
import win.doyto.i18n.module.localle.ResourceLocaleService;
import win.doyto.web.spring.RestBody;

/**
 * ResourceLocaleController
 *
 * @author f0rb on 2017-04-16.
 */
@Slf4j
@RestBody
@RestController
@RequestMapping("/api/resource/{group}/locale")
@PreAuthorize("hasAnyRole('i18n')")
public class ResourceLocaleController {
    @Resource
    private ResourceLocaleService resourceLocaleService;
    @Resource
    private ResourceGroupService resourceGroupService;

    @RequestMapping(method = RequestMethod.GET)
    public Object query(
            ResourceLocale resourceLocale,
            Authentication oper,
            @PathVariable("group") String group
    ) {
        ResourceGroup resourceGroup = resourceGroupService.getGroup(oper.getName(), group);
        resourceLocale.setGroupId(resourceGroup.getId());
        return resourceLocaleService.page(resourceLocale);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Integer id) {
        return resourceLocaleService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object add(
            @RequestBody @Valid ResourceLocale resourceLocale,
            Authentication oper,
            @PathVariable("group") String group
    ) {
        ResourceGroup resourceGroup = resourceGroupService.getGroup(oper.getName(), group);
        resourceLocale.setGroupId(resourceGroup.getId());
        resourceLocale.setGroup(group);
        return resourceLocaleService.add(resourceLocale);
    }

    @RequestMapping(value = "{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public Object save(
            @RequestBody @Valid ResourceLocale resourceLocale,
            Authentication oper,
            @PathVariable("group") String group,
            @PathVariable(value = "id", required = false) String id
    ) {
        ResourceGroup resourceGroup = resourceGroupService.getGroup(oper.getName(), group);
        resourceLocale.setGroupId(resourceGroup.getId());
        return resourceLocaleService.save(resourceLocale);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Integer id) {
        return resourceLocaleService.delete(id);
    }
}