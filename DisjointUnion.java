package week8;

import java.util.*;

public class DisjointUnion<T> 
{
	private final Map<T, Set<T>> disjointUnion = new HashMap();

	public boolean add(T element)
	{
		if (disjointUnion.containsKey(element))
			return false;
		Set<T> singleton = new HashSet();
		singleton.add(element);
		disjointUnion.put(element, singleton);
		return true;
	}
	public boolean union(T element1, T element2)
	{
		if (!disjointUnion.containsKey(element1) || 
			!disjointUnion.containsKey(element2))
			return false;
		Set<T> set1 = disjointUnion.get(element1), set2 = 
			disjointUnion.get(element2);
		set1.addAll(set2);
		for (T element: set1)
			disjointUnion.put(element, set1);
		return true;
	}
	public Set<T> find(T element)
	{
		return disjointUnion.get(element);
	}
	
	public Set<Set<T>> getEquivalenceClasses()
	{
		Set<Set<T>> classes = new HashSet();
		
		Set<T> keys = disjointUnion.keySet();
		for (T key: keys)
			classes.add(disjointUnion.get(key));
		return classes;
	}
}
