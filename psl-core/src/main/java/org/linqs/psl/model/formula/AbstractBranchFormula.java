/*
 * This file is part of the PSL software.
 * Copyright 2011-2015 University of Maryland
 * Copyright 2013-2017 The Regents of the University of California
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.linqs.psl.model.formula;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.linqs.psl.model.atom.Atom;
import org.linqs.psl.model.term.VariableTypeMap;

/**
 * An abstract branching formula.
 * Note, the order in which formulas appear in an AbstractBranchFormula is important!
 */
abstract class AbstractBranchFormula<T extends AbstractBranchFormula<T>> implements Formula {
	protected final Formula[] formulas;

	public AbstractBranchFormula(Formula... f) {
		if (f.length < 2) {
			throw new IllegalArgumentException("Must provide at least two formulas!");
		}

		//TODO: Should we copy here?
		formulas = f;
		for (int i = 0; i < f.length; i++) {
			if (formulas[i] == null) {
				throw new IllegalArgumentException("Formulas must not be null!");
			}
		}
	}

	public int length() {
		return formulas.length;
	}

	public Formula get(int pos) {
		return formulas[pos];
	}

	@Override
	public VariableTypeMap collectVariables(VariableTypeMap varMap) {
		for (int i = 0; i < formulas.length; i++) {
			formulas[i].collectVariables(varMap);
		}
		return varMap;
	}

	@Override
	public Set<Atom> getAtoms(Set<Atom> atoms) {
		for (int i = 0; i < formulas.length; i++) {
			formulas[i].getAtoms(atoms);
		}
		return atoms;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(formulas);
	}

	@Override
	public boolean equals(Object oth) {
		if (oth == this) {
			return true;
		}

		if (oth == null || !(getClass().isInstance(oth))) {
			return false;
		}

		AbstractBranchFormula of = (AbstractBranchFormula)oth;
		return Arrays.equals(formulas, of.formulas);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("( ");
		for (int i = 0; i < formulas.length; i++) {
			if (i>0) {
				s.append(" ").append(separatorString()).append(" ");
			}
			s.append(formulas[i]);
		}

		s.append(" )");
		return s.toString();
	}

	/**
	 * flatten(), but with the the components belonging to this formula.
	 */
	public T flatten() {
		return flatten(formulas);
	}

	/**
	 * Collapses nested formulas of the same type and remove duplicates at the top level.
	 * Stops descending whereever a Formula other than the same type is found.
	 * Ex: (A ^ B) ^ C ^ (D v E) becomes A ^ B ^ C ^ (D v E).
	 *
	 * @return the flattened Formula.
	 */
	public T flatten(Formula[] components) {
		Set<Formula> flatComponents = new HashSet<Formula>();

		for (Formula component : components) {
			// Check if the component is an instance of the current class.
			// (Cannot use instanceof since we don't have a finite class to check against.)
			if (this.getClass().isAssignableFrom(component.getClass())) {
				// Flatten out the new component.
				Formula[] newComponents = ((AbstractBranchFormula)component).flatten().formulas;
				for (Formula newComponent : newComponents) {
					flatComponents.add(newComponent);
				}
			} else {
				flatComponents.add(component);
			}
		}

		// Ideally, we would do something like: return new T(flatComponents.toArray(new Formula[0]));
		// However, we can't do that with generics.
		// So, we have to reflexivly call the constructor in our top level class (this.getClass()).
		try {
			Formula[] args = flatComponents.toArray(new Formula[0]);

			// Note that we have to cast the arguments to an Object since we are using varargs
			// (variadic arguments) in the constructors.
			return (T)(this.getClass().getConstructor(Formula[].class).newInstance((Object)args));
		} catch (NoSuchMethodException ex) {
			throw new RuntimeException("AbstractBranchFormula does not have a constructor that takes an Array of Formula.", ex);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
			throw new RuntimeException("AbstractBranchFormula could not be constructed.");
		}
	}

	protected abstract String separatorString();
}
