package win.doyto.i18n.module.locale;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import win.doyto.i18n.common.TestConstant;
import win.doyto.query.service.PageList;
import win.doyto.query.util.BeanUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LocaleEntityControllerTest
 *
 * @author f0rb on 2018-08-27.
 */
public class LocaleControllerTest {
    private final LocaleController localeController = new LocaleController();

    @BeforeEach
    void setUp() throws Exception {
        localeController.add(BeanUtil.loadJsonData("/locale.json", new TypeReference<List<LocaleEntity>>() {}));
    }

    @Test
    public void page() {
        PageList<LocaleEntity> page = localeController.page(LocaleQuery.builder().createUserId(TestConstant.DEFAULT_USER).build());
        assertThat(page.getTotal()).isEqualTo(3);
        assertThat(page.getList()).element(0)
                .hasFieldOrPropertyWithValue("locale", "zh_CN")
                .hasFieldOrPropertyWithValue("baiduLocale", "zh");

        assertThat(page.getList()).extracting(LocaleEntity::getBaiduLocale)
                .containsExactly("zh", "en", "jp");
    }

    @Test
    void update() {
        LocaleEntity localeEntity = new LocaleEntity();
        localeEntity.setLocale("zh");
        localeEntity.setLanguage("Chinese");
        localeEntity.setBaiduLocale("zh2");
        localeEntity.setCreateUserId(TestConstant.DEFAULT_USER);
        localeController.update(1, localeEntity);

        assertThat(localeController.getById(1))
                .hasFieldOrPropertyWithValue("locale", "zh_CN")
                .hasFieldOrPropertyWithValue("language", "Chinese")
                .hasFieldOrPropertyWithValue("baiduLocale", "zh2");

    }
}