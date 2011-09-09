/*
 * This file is part of the PSL software.
 * Copyright 2011 University of Maryland
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
package edu.umd.cs.psl.optimizer.conic.program;

import java.util.Map;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import edu.umd.cs.psl.optimizer.conic.program.graph.Node;

public class NonNegativeOrthantCone extends Cone {
	NonNegativeOrthantCone(ConicProgram p) {
		super(p);
		Variable var = new Variable(p);
		node.createRelationship(ConicProgram.CONE_REL, var.getNode());
		p.notify(ConicProgramEvent.NNOCCreated, this);
	}
	
	NonNegativeOrthantCone(ConicProgram p, Node n) {
		super(p, n);
	}
	
	@Override
	NodeType getType() {
		return NodeType.nnoc;
	}
	
	public Variable getVariable() {
		return (Variable) Entity.createEntity(program, node.getRelationshipIterator(ConicProgram.CONE_REL).next().getEnd());
	}
	
	@Override
	public final void delete() {
		program.verifyCheckedIn();
		program.notify(ConicProgramEvent.NNOCDeleted, this);
		Variable var = getVariable();
		node.getRelationshipIterator(ConicProgram.CONE_REL).next().delete();
		var.delete();
		super.delete();
	}
	
	public void setBarrierGradient(Map<Variable, Integer> varMap, DoubleMatrix1D x, DoubleMatrix1D g) {
		int i = varMap.get(getVariable());
		g.set(i, -1 / x.get(i));
	}
	
	void setBarrierHessian(Map<Variable, Integer> varMap, DoubleMatrix1D x, DoubleMatrix2D H) {
		int i = varMap.get(getVariable());
		H.set(i, i, Math.pow(x.get(i), -2));
	}
	
	public void setBarrierHessianInv(Map<Variable, Integer> varMap, DoubleMatrix1D x, DoubleMatrix2D Hinv) {
		int i = varMap.get(getVariable());
		Hinv.set(i, i, Math.pow(x.get(i), 2));
	}

	@Override
	boolean isInterior(Map<Variable, Integer> varMap, DoubleMatrix1D x) {
		int i = varMap.get(getVariable());
		return x.get(i) > 0.05;
	}

	@Override
	void setInteriorDirection(Map<Variable, Integer> varMap, DoubleMatrix1D x,
			DoubleMatrix1D d) {
		int i = varMap.get(getVariable());
		if (x.get(i) <= .05)
			d.set(i, 0.25 - x.get(i));
		else
			d.set(i, 0);
	}

	@Override
	public
	double getMaxStep(Map<Variable, Integer> varMap, DoubleMatrix1D x,
			DoubleMatrix1D dx) {
		int i = varMap.get(getVariable());
		if (dx.get(i) >= 0)
			return 1.0;
		else
			return (x.get(i) * .95) / (- dx.get(i));
	}
}
