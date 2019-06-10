package win.doyto.i18n.module.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import win.doyto.query.core.PageQuery;

/**
 * GroupQuery
 *
 * @author f0rb on 2018-04-24.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class GroupQuery extends PageQuery {

    public GroupQuery() {//lombok
    }

    @Builder.Default
    private Boolean deleted = false;

    private String owner;

    private String nameLike;

    private Boolean valid;

}