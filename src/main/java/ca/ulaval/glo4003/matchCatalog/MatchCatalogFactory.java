package ca.ulaval.glo4003.matchCatalog;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.matchCatalog.index.Filter;
import ca.ulaval.glo4003.matchCatalog.index.Index;
import ca.ulaval.glo4003.matchCatalog.index.IndexWithList;

public class MatchCatalogFactory {

    public MatchCatalog createMatchCatalog(String pathToCatalogueFile) {
        return null;
    }

    public MatchCatalog createMatchCatalog() {
        List<Filter<MatchFilterCategories>> filterListByCategories = new ArrayList<Filter<MatchFilterCategories>>();
        for (MatchFilterCategories category : MatchFilterCategories.values()) {
            filterListByCategories.add(new Filter<MatchFilterCategories>(category));
        }
        Index<MatchFilterCategories> index = new IndexWithList<MatchFilterCategories>(filterListByCategories);
        QueryResolver<MatchFilterCategories> queryResolver = new QueryResolver<MatchFilterCategories>(index);

        matchCatalog = new MatchCatalog(queryResolver, index, matchRepository);
    }

}
