package ca.ulaval.glo4003.persistence;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;
import ca.ulaval.glo4003.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.matchCatalog.Query;

public class JSONMatchQueryFactory implements MatchQueryFactory {

    public Query<MatchFilterCategories> create(String serializedQuery) {
        Query<MatchFilterCategories> query = new Query<MatchFilterCategories>();

        try {
            JSONObject unSerializeQuery = new JSONObject(serializedQuery);
            Iterator<?> categories = unSerializeQuery.keys();

            while (categories.hasNext()) {
                String filterCategory = (String) categories.next();
                JSONArray categoryValues = unSerializeQuery.getJSONArray(filterCategory);

                for (int i = 0; i < categoryValues.length(); i++) {
                    JSONObject filter = categoryValues.getJSONObject(i);

                    MatchFilterCategories cat = MatchFilterCategories.valueOf(filterCategory);
                    String filterValue = (String) filter.get("name");
                    query.addFilterValue(cat, filterValue);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return query;
    }
}
