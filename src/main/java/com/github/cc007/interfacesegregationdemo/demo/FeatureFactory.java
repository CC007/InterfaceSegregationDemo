package com.github.cc007.interfacesegregationdemo.demo;

import com.github.cc007.interfacesegregationdemo.containers.ArrayList;
import com.github.cc007.interfacesegregationdemo.containers.DelegateList;
import com.github.cc007.interfacesegregationdemo.containers.LinkedList;
import com.github.cc007.interfacesegregationdemo.containers.api.Collection;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Addable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Gettable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Modifiable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Ordered;
import com.github.cc007.interfacesegregationdemo.containers.api.features.RandomAccess;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Removable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Subdividable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.bulk.BulkAddable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.bulk.BulkRemovable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.IndexAddable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.IndexGettable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.IndexModifiable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.IndexRemovable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.Indexed;
import com.github.cc007.interfacesegregationdemo.containers.api.features.iteration.Iterable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.iteration.Spliterable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.mapping.ArrayMappable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.sort.SelfSortable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.sort.Sortable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.stream.ParallelStreamable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.stream.Streamable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FeatureFactory {

    private final Map<Class<?>, Supplier<?>> supportedImpl;

    private final Set<Class<?>> supportedFeatures;

    public FeatureFactory() {
        supportedFeatures = new HashSet<>(Set.of(
                BulkAddable.class,
                BulkRemovable.class,

                Indexed.class,
                IndexAddable.class,
                IndexGettable.class,
                IndexModifiable.class,
                IndexRemovable.class,

                Iterable.class,
                Spliterable.class,

                ArrayMappable.class,

                SelfSortable.class,
                Sortable.class,

                ParallelStreamable.class,
                Streamable.class,

                Addable.class,
                Gettable.class,
                Modifiable.class,
                Ordered.class,
                RandomAccess.class,
                Removable.class,
                Subdividable.class
        ));
        supportedImpl = new HashMap<>(Map.ofEntries(
                Map.entry(ArrayList.class, ArrayList::new),
                Map.entry(LinkedList.class, LinkedList::new)
        ));
    }

    public <C> Optional<C> getContainer(Class<?>... features) {
        return getContainer(new DelegateList<>(List.of(features)));
    }

    public <C> Optional<C> getContainer(Collection<Class<?>> features) {
        return getContainer(features, new DelegateList<>(Collections.emptyList()));
    }

    public <C> Optional<C> getContainer(Collection<Class<?>> features, Collection<Class<?>> excludedFeatures) {
        Iterator<Class<?>> featureIterator = features.iterator();
        while (featureIterator.hasNext()) {
            Class<?> feature = featureIterator.next();
            if (!supportedFeatures.contains(feature)) {
                throw new IllegalArgumentException("Unsupported feature given: " + feature.getSimpleName());
            }
        }
        Iterator<Class<?>> excludedFeatureIterator = excludedFeatures.iterator();
        while (excludedFeatureIterator.hasNext()) {
            Class<?> excludedFeature = excludedFeatureIterator.next();
            if (!supportedFeatures.contains(excludedFeature)) {
                throw new IllegalArgumentException("Unsupported excludedFeature given: " + excludedFeature.getSimpleName());
            }
        }
        Stream<Map.Entry<Class<?>, Supplier<?>>> supportedContainerStream = supportedImpl.entrySet().stream();
        for (Class<?> feature : features.toJavaUtil()) {
            supportedContainerStream = supportedContainerStream.filter(container -> feature.isAssignableFrom(container.getKey()));
        }
        for (Class<?> excludedFeature : excludedFeatures.toJavaUtil()) {
            supportedContainerStream = supportedContainerStream.filter(container -> !excludedFeature.isAssignableFrom(container.getKey()));
        }
        @SuppressWarnings("unchecked")
        Optional<C> container = (Optional<C>) supportedContainerStream.findAny()
                .map(Map.Entry::getValue)
                .map(Supplier::get);
        return container;
    }

    public void putSupportedImpl(Class<?> key, Supplier<?> value) {
        this.supportedImpl.put(key, value);
    }

    public void addSupportedFeature(Class<?> e) {
        this.supportedFeatures.add(e);
    }
}
