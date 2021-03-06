/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.convert;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.data.convert.CollectionFactory.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;
import org.springframework.core.CollectionFactory;

/**
 * Unit tests for {@link CollectionFactory}.
 * 
 * @author Oliver Gierke
 */
public class CollectionFactoryUnitTests {

	/**
	 * @see DATACMNS-451
	 */
	@Test
	public void createsSimpleCollectionsCorrectly() {

		assertThat(createCollection(List.class, Object.class, 0), is(instanceOf(ArrayList.class)));
		assertThat(createCollection(Set.class, Object.class, 0), is(instanceOf(HashSet.class)));
		assertThat(createCollection(SortedSet.class, Object.class, 0), is(instanceOf(TreeSet.class)));
	}

	/**
	 * @see DATACMNS-451
	 */
	@Test
	public void createsEnumSet() {
		assertThat(createCollection(EnumSet.class, Color.class, 0), is(instanceOf(EnumSet.class)));
	}

	/**
	 * @see DATACMNS-451
	 */
	@Test(expected = IllegalArgumentException.class)
	public void rejectsInvalidElementTypeForEnumSet() {
		createCollection(EnumSet.class, Object.class, 0);
	}

	/**
	 * @see DATACMNS-451
	 */
	@Test(expected = IllegalArgumentException.class)
	public void rejectsNullElementTypeForEnumSet() {
		createCollection(EnumSet.class, null, 0);
	}

	/**
	 * @see DATACMNS-451
	 */
	@Test(expected = IllegalArgumentException.class)
	public void rejectsNullCollectionType() {
		createCollection(null, Object.class, 0);
	}

	/**
	 * @see DATACMNS-451
	 */
	@Test
	public void createsEnumMap() {
		assertThat(createMap(EnumMap.class, Color.class, 0), is(instanceOf(EnumMap.class)));
	}

	/**
	 * @see DATACMNS-451
	 */
	@Test(expected = IllegalArgumentException.class)
	public void rejectsInvalidKeyTypeForEnumMap() {
		createMap(EnumMap.class, Object.class, 0);
	}

	/**
	 * @see DATACMNS-451
	 */
	@Test(expected = IllegalArgumentException.class)
	public void rejectsNullKeyTypeForEnumMap() {
		createMap(EnumMap.class, null, 0);
	}

	/**
	 * @see DATACMNS-451
	 */
	@Test(expected = IllegalArgumentException.class)
	public void rejectsNullMapType() {
		createMap(null, Object.class, 0);
	}

	enum Color {
		RED, BLUE;
	}
}
