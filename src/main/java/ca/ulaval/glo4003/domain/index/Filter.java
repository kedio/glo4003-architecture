package ca.ulaval.glo4003.domain.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Filter<E extends Enum<E>, O extends Indexable<E>> {

    private E category;
    private Map<String, List<String>> identifiers = new HashMap<String, List<String>>();

    public Filter(E category) {
        this.category = category;
    }

    protected abstract String getAttributeValue(O aConcreteIndexable);

    public void add(O anIndexable) {
        String filterValue = getAttributeValue(anIndexable);
        String identifier = anIndexable.getIdentifier();
        addInMap(filterValue, identifier);
    }

    private void addInMap(String filterValue, String identifier) {
        if (!identifiers.containsKey(filterValue)) {
            identifiers.put(filterValue, new ArrayList<String>());
        }
        identifiers.get(filterValue).add(identifier);
    }

    public boolean isOfCategory(E category) {
        return this.category == category;
    }

    public List<String> getIdentifiersFor(String filterValue) {
        if (identifiers.containsKey(filterValue)) {
            return identifiers.get(filterValue);
        } else {
            return new ArrayList<String>();
        }

    }

}
